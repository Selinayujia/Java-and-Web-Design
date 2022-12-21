/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author selina
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Midterm {

    static JPanel jp;
    static JButton left;
    static JButton right;
    static JLabel question;
    static String[] answers;
    static int qnum;
    static Question[] questions;
    static Timer timer;

    public Midterm() {} //since I am not creating midterm obj so I don't need constructor

    public static void main(String[] args) {
        
        questions = new Question[6];
        questions[0]= new Question("Favorite Ice Cream", "Vanilla", "Chocolate");
        questions[1]= new Question("Which season is better", "Winter", "Summer");
        questions[2]= new Question("Which pet is better", "Cat", "Dog");
        questions[3]= new Question("Unicorns are real");
        questions[4]= new Question("Text or call", "Text", "Call");
        questions[5]= new Question("");
        
        answers = new String[5];
        
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500,200);
        
        jp = new JPanel();
        jp.setLayout(new BorderLayout());
        qnum = 0;
        question = new JLabel(questions[qnum].q);
        
        
        JPanel jp_b = new JPanel();
        jp_b.setLayout(new GridLayout(1,2,10,10));
        left = new JButton(questions[qnum].left);
        left.addActionListener(new ButtonListener(0));
        right = new JButton(questions[qnum].right);
        right.addActionListener(new ButtonListener(1));
        jp_b.add(left);
        jp_b.add(right);
        
        jp.add(question, BorderLayout.NORTH);
        jp.add(jp_b,BorderLayout.SOUTH);
        jf.add(jp);
        jf.setVisible(true);
        timer = new Timer(5000);
        timer.start();
        
        
    }
}

class Timer extends Thread {
    boolean awake;
    int secs;

    Timer(int newsecs) {
        secs = newsecs;
        awake = false;
    }

    @Override
    public void run() {
       try{
           sleep(secs);
       }
       catch(InterruptedException ex){
           return;
       }
       if(!awake){
           Midterm.qnum = 5; //jump to the result one
           Midterm.questions[Midterm.qnum].q = "";
            int i = 0;
            while(i != Midterm.qnum){
                if(Midterm.answers[i] == null){
                    Midterm.questions[Midterm.qnum].q += "NO RESPONSE";
                }
                else{
                    Midterm.questions[Midterm.qnum].q += Midterm.answers[i];
                }
                if(i != Midterm.qnum - 1){
                    Midterm.questions[Midterm.qnum].q += ",";
                }
                i++;
            }
        Midterm.question.setText(Midterm.questions[Midterm.qnum].q);
        Midterm.left.setText(Midterm.questions[Midterm.qnum].left);
        Midterm.right.setText(Midterm.questions[Midterm.qnum].right);
       }
    }
}
class Question {

    String q;
    String left;
    String right;

    Question(String newq, String newleft, String newright) {
        q = newq;
        left = newleft;
        right = newright;
    }

    Question(String newq) {
        q = newq;
        left = "True";
        right = "False";
    }
}
class ButtonListener implements ActionListener {
    int b;
    ButtonListener(int button_id) {
       b = button_id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean need_timer = true;
        if(Midterm.qnum < 5){
            Midterm.timer.awake = true;
            if( b == 0){
                Midterm.answers[Midterm.qnum] = Midterm.questions[Midterm.qnum].left;
            }
            else{
                Midterm.answers[Midterm.qnum] = Midterm.questions[Midterm.qnum].right;
            }
            Midterm.qnum += 1;
        }
        
        if(Midterm.qnum == 5){
            if (Midterm.questions[Midterm.qnum].q == ""){ // didn't get interrupted
                need_timer = false;
                int i = 0;
                while(i != Midterm.qnum){

                    Midterm.questions[Midterm.qnum].q += Midterm.answers[i];

                    if(i != Midterm.qnum - 1){
                        Midterm.questions[Midterm.qnum].q += ",";
                    }
                    i++;
                }
            }
        }
        Midterm.question.setText(Midterm.questions[Midterm.qnum].q);
        Midterm.left.setText(Midterm.questions[Midterm.qnum].left);
        Midterm.right.setText(Midterm.questions[Midterm.qnum].right);
        if(need_timer){
            Midterm.timer = new Timer(5000);
            Midterm.timer.start();
        }
        
        
    }
}
