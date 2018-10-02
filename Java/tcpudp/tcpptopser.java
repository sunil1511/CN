/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sunildupare
 */
package tcpudp;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class tcpptopser extends Thread{
   static DataInputStream in;
    static DataOutputStream out;
    public static void server()
    {
        try {
            ServerSocket ser=new ServerSocket(5000);
            Socket sock=ser.accept();
             in=new DataInputStream(sock.getInputStream());
             out=new DataOutputStream(sock.getOutputStream());
            //tcpptopser t=new tcpptopser();
            //t.run();
            String res;
            Scanner sc=new Scanner(System.in);
            while(true)
            {
                res=in.readUTF();
                System.out.println("Client:"+res);
                System.out.println("enter your reply:,enter bye:$ to terminate:");
                res=sc.nextLine();
                if(res.equals("bye:$"))
                {
                    System.out.println("chat terminated by server:");
                    in.close();
                    out.close();
                    sock.close();
                    break;
                }
                out.writeUTF(res);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(tcpptopser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     /*public void run()
        {
            while(response)
            {
                
            }
        }*/
    public static void main(String[] args) {
        tcpptopser.server();
        
    }
}
