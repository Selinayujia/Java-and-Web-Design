/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author selina
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
        
class Listener implements ActionListener{
    int clicked;
    Listener(int index){
        clicked = index;
    }
    @Override
    public void actionPerformed(ActionEvent event){
        hw3.active[clicked] = !hw3.active[clicked];
        if(hw3.active[clicked] == true){
            hw3.bthreads[clicked] = new ButtonThread(clicked);
        }
        else{
            hw3.bthreads[clicked].freeze = !hw3.bthreads[clicked].freeze;
        }
        
        hw3.bthreads[clicked].start();
        
    }
      
}


public class hw3 {  
    static int total;
    static JButton[] buttons;
    static boolean[] active;
    static ButtonThread[] bthreads;
  
    public static void main(String[] args){
        int col = 2;
        total = 8;
        buttons = new JButton[total];
        bthreads = new ButtonThread[total];
        active = new boolean[total];
        
        JFrame jf = new JFrame("hw3");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1000,1000);
       
        
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(total/col, col, 10, 10));
        
        Random r = new Random();
        for(int i = 0; i < total; i ++){
            buttons[i] = new JButton();
            buttons[i].setBackground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            buttons[i].addActionListener(new Listener(i));
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            active[i] = true;
            bthreads[i] = new ButtonThread(i);
            bthreads[i].start();
            jp.add(buttons[i]);
            
                    
        }
        jf.add(jp);
        jf.setVisible(true);
                
    }
}
    

class ButtonThread extends Thread {
    
    boolean freeze = false;
    int ind;
    
    ButtonThread(int newind){
        ind = newind;
        
    }
    @Override
    public void run() {
        while(!freeze){
        try{
            sleep(1000);
        }
        catch(InterruptedException ex){
            return;
        }
 
        Random r = new Random();
        hw3.buttons[ind].setBackground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
      
               
       
    }
    }
      
}
   
