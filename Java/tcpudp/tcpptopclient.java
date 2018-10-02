/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpudp;

import java.io.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tcpudp.tcpptopser.in;

/**
 *
 * @author sunildupare
 */
public class tcpptopclient {
 public static void client()
 {
     try {
         Socket sock=new Socket(InetAddress.getLocalHost(), 5000);
         DataInputStream in=new DataInputStream(sock.getInputStream());
         DataOutputStream out=new DataOutputStream(sock.getOutputStream());
         String res;
          Scanner sc=new Scanner(System.in);
            while(true)
            {
                System.out.println("enter your message:,enter bye:$ to terminate:");
                res=sc.nextLine();
                if(res.equals("bye:$"))
                {
                    System.out.println("chat terminated by client:");
                    in.close();
                    out.close();
                    sock.close();
                    break;
                }
                out.writeUTF(res);
                res=in.readUTF();
                System.out.println("server says:"+res);
            }
         
     } catch (IOException ex) {
         Logger.getLogger(tcpptopclient.class.getName()).log(Level.SEVERE, null, ex);
     }
 }
    public static void main(String[] args) {
        tcpptopclient.client();
    }
}