/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multitcp;

/**
 *
 * @author sunildupare
 */
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
public class multiclitcp {
    public static void main(String[] args) {
        try {
            Scanner sc=new Scanner(System.in);
            Socket s=new Socket(InetAddress.getLocalHost(), 5000);
            DataOutputStream out=new DataOutputStream(s.getOutputStream());
            System.out.println("enter ur name:");
            String name=sc.next();
            out.writeUTF(name);
            while(true)
            {
                System.out.println("enter ur msgg:");
            String msg=sc.next();
            if(msg.equals("bye:$"))
            {
                System.out.println("terminated");
                out.close();
                s.close();
                break;
            }
            out.writeUTF(msg);  
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(multiclitcp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(multiclitcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
