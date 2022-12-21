/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author selina
 */


public class CS3913SPring2020FinalQ2 {
    public static void main(String[] args) {
        Connection conn = null; 
        TreeMap<Integer, String> products = new TreeMap<>();
        try{
            String url = "jdbc:mariadb://127.0.0.1:3306/cs3913"; // using port 3306, since that is in class
            String username = "CS3913";
            String pwd = " GettingAnA+";
            conn = DriverManager.getConnection(url, username, pwd);
            Statement s = conn.createStatement();
            ResultSet rs1 = s.executeQuery("Select * from Products");
            int count, sum;
            int avg;
            while(rs1.next()){
                String pid = rs1.getString("PID"); 
                String name = rs1.getString("ProductName"); 
                ResultSet rs2 = s.executeQuery("Select Rating from Reviews where PID="+pid);
                count = 0;
                sum = 0;
                while(rs2.next()){
                    int rate = rs1.getInt(1);  // selected only rating, so first column
                    sum += rate;
                    count += 1;
                }
                rs2.close();
                avg = sum / count;
                products.put(avg,name);
            }
            rs1.close(); 
            s.close(); 
            conn.close(); 
        }
        catch(SQLException e){
            System.out.println("Error: " + e.toString());
        }
        
        // since treemap is ascending, sort it to descending 
        ArrayList arr = new ArrayList<String>();
        String s;
        for(Integer key : products.keySet()) {
            s = products.get(key);
            arr.add(s);
        }
        for (int i = arr.size() - 1; i >= 0; i--) {
            System.out.println(arr.get(i));
        }
        
}
        
        
        
}
    

