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
        Random r = new Random();
        for(int i = 0; i < hw2.getTotal(); i++){
            if(i != clicked){
                hw2.getButtons()[i].setBackground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

            }
        }
    }
}

public class hw2 {  
    private static int total;
    private static JButton[] buttons;
    
    static JButton[] getButtons(){
        return buttons;
    }
    
   static int getTotal(){
       return total;
   }

    public static void main(String[] args){
        int col = 2;
        total = 8;
        buttons = new JButton[total];
        
        JFrame jf = new JFrame("hw2");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1000,1000);
        jf.setVisible(true);
        
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(total/col, col, 10, 10));
        
        Random r = new Random();
        for(int i = 0; i < total; i ++){
            buttons[i] = new JButton();
            //used RGB to set colors, 256 is the 
            buttons[i].setBackground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            buttons[i].addActionListener(new Listener(i));
            
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            
            jp.add(buttons[i]);
                    
        }
        jf.add(jp);
        
                
    }
}
    

    
    
   

