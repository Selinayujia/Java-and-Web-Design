/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author selina
 */

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import javax.swing.*;
class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point() {
        this(0,0);
    }
}

public class hw5{
    static int sec, min, hr;
    static int DFL_PORT = 13;
    static ClockPanel panel;

    public static void main(String[] args) {
        JFrame jf = new JFrame("My Window");
        jf.setSize(400,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new ClockPanel();
        panel.setLayout(new BorderLayout());
        jf.add(panel, BorderLayout.CENTER);
        getTime();
        Time t = new Time();
        t.start();
        JPanel text = new JPanel();
        text.setLayout(new BorderLayout());
        String s_hr, s_min, s_sec;
        if(hr < 10){ s_hr = "0" + Integer.toString(hr);} else{ s_hr = Integer.toString(hr);}
        if(min < 10){ s_min = "0" + Integer.toString(min);} else{ s_min = Integer.toString(min);}
        if(sec < 10){ s_sec = "0" + Integer.toString(sec);} else{ s_sec = Integer.toString(sec);}
        JLabel digital = new JLabel("UTC Time when clicked: " + s_hr + ":" + s_min + ":" + s_sec);
        text.add(digital);
        jf.add(text, BorderLayout.NORTH);
        jf.setVisible(true);
    }

    static void getTime() {
        try {
            Socket socket = new Socket("time-a-g.nist.gov", DFL_PORT);
            if (socket.isConnected()) {
                Scanner input = new Scanner(socket.getInputStream());
                input.nextLine(); 
                String[] data = input.nextLine().substring(15, 23).split(":");  // just get the time part
                hw5.hr = Integer.parseInt(data[0]);
                hw5.min = Integer.parseInt(data[1]);
                hw5.sec = Integer.parseInt(data[2]);
            }
        }
        catch (IOException e) { }
    }

}
class Time extends Thread{
        @Override
        public void run() {
            while (true){
                if(hw5.sec == 60) { //update once every 60 mins
                       hw5.getTime();
                       hw5.sec = 0;
                }
                hw5.sec += 1;
                hw5.panel.repaint();
                try {
                    sleep(1000);
                }
                catch(InterruptedException e) {}
            }
        }
   }

class ClockPanel extends JPanel{
    int r;

    ClockPanel() {
        super();
        hw5.sec = 0;
    }

    Point getPoint(String type) {
        Point p = new Point();
        double xAngle = 0;
        double yAngle = 0;

        switch (type) {
            case "s":
                xAngle = Math.sin(Math.toRadians(hw5.sec * 6)); //actually /60*360
                yAngle = Math.cos(Math.toRadians(hw5.sec * 6));
                break;
            case "m":
                xAngle = Math.sin(Math.toRadians(hw5.min * 6)); // /60*360
                yAngle = Math.cos(Math.toRadians(hw5.min * 6));
                break;
            case "h":
                xAngle = Math.sin(Math.toRadians((hw5.hr % 12) * 30));  // /12*260
                yAngle = Math.cos(Math.toRadians((hw5.hr % 12) * 30));
                break;
        }
        p.x = (int)(xAngle * r);
        p.y = (int)(yAngle * r);
        return p;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int height = this.getSize().height;
        int width = this.getSize().width;
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, width, height);

        
        r = height / 4;
        int x = width / 2;
        int y = height / 2;
        int x_center = x - r;
        int y_center = y - r;
        int d = r * 2; 
        g.setColor(Color.WHITE);
        g.fillOval(x_center, y_center, d, d);

     
        Point secP = getPoint("s");
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x + secP.x, y - secP.y);
        Point minP = getPoint("m");
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x + minP.x, y - minP.y);
        Point hrP = getPoint("h");
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x + hrP.x, y - hrP.y);
    }
}

