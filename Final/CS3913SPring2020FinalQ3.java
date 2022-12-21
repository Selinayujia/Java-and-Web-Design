import java.util.LinkedList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author selina
 */
public class CS3913SPring2020FinalQ3 {
    class CafeQueue{
        LinkedList<String> cq;
        CafeQueue(){
            cq = new LinkedList<String>();
        }
        synchronized  void enterQueue(String customer){
            cq.add(customer);
        }
        synchronized String serveCustomer(){
            if(cq.isEmpty()){ 
                return "No customer in queue now.";
            }
            return cq.remove();
        }
     
    }
    
    
}
