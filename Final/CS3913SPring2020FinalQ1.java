import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author selina
 */
public class CS3913SPring2020FinalQ1 {
    
    public static void main(String[] args) {
        JFrame jf = new JFrame("My Window");
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawingPanel dp = new DrawingPanel();
        dp.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent m){
                if (m.getButton() == MouseEvent.BUTTON1){
                    MyPoint p = new MyPoint(m.getX(), m.getY());
                    dp.addPoint(p);
                    dp.repaint();
                }
            }
        });
        jf.add(dp);
        jf.setVisible(true);
        
    }
}
    
class MyPoint {
        int x;
        int y;
        MyPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        MyPoint() {
            this(0,0);
        }
    }

class DrawingPanel extends JPanel {

    ArrayList<MyPoint> al;
    final int size = 5;

    DrawingPanel() {
        super();
        al = new ArrayList<MyPoint>();
    }

    void drawScale(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        int scale = 30;
        int height = this.getSize().height;
        int width = this.getSize().height;
        int x = 0;
        int y = 0;
        while(x <= width){
            g.drawLine(x, y, x, height);
            x += scale;
        }
        x = 0;
        while(y <= height){
            g.drawLine(x, y, width, y);
            y += scale;
        }
    }
    
    void drawPointsAndDist(Graphics g){
        g.setColor(Color.RED);
        NumberFormat formatter = new DecimalFormat("#0.00");
        for (MyPoint p: al){
            g.fillOval(p.x-(size/2), p.y-(size/2), size, size);
            String s = "(" + p.x+","+p.y +")";
            g.drawString(s, p.x+5, p.y+5);
        }
        for(int i = 0; i < al.size()-1; i++){
            g.drawLine(al.get(i).x, al.get(i).y, al.get(i+1).x, al.get(i+1).y);
            double dist = calcDistance(al.get(i), al.get(i+1));
            String s = formatter.format(dist);
            int mid_x = (al.get(i).x+al.get(i+1).x)/2;
            int mid_y = (al.get(i).y+al.get(i+1).y)/2;
            g.drawString(s, mid_x, mid_y);
        }
        
        
        
    }

    void addPoint(MyPoint p) {
       al.add(p);
    }

    void clearList(){
        al.clear();
        this.repaint();
    }

    double calcDistance(MyPoint p1, MyPoint p2) {
       int delta_x = p2.x - p1.x;
       int delta_y = p2.y - p1.y;
       double dist = Math.sqrt(Math.pow(delta_x, 2)+ Math.pow(delta_y, 2));
       return dist;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int height = this.getSize().height;
        int width = this.getSize().width;
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, width, height);
        drawScale(g);
        drawPointsAndDist(g);
        
    }
}