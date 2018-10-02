/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sunildupare
 */
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
public class sender {
   void sendPackets()
   {
       try {
           Socket sock=new Socket(InetAddress.getLocalHost(),5000);
           DataInputStream in=new DataInputStream(sock.getInputStream());
           DataOutputStream out=new DataOutputStream(sock.getOutputStream());
           int msg[]={0,1,2,3,4,5,6,7};
           System.out.println("Window size of sender is 8;\n packets are:"+Arrays.toString(msg));
           msg[3]=33;
           
           int i=0;
           while(i<msg.length)
           {   System.out.println("sending packet:"+(i+1));
               out.write(msg[i]);
               i++;
           }
           System.out.println("Packets send waiting for acks");
           i=in.read();
           System.out.println("Nak Received for packet aftr "+i);
           System.out.println("Retransmitting packets aftr:"+i);
           i++;
           msg[i]=3;
           while(i<8)
           {
               out.write(msg[i]);
               i++;
           }
           System.out.println("Packets send successfully");
       } catch (UnknownHostException ex) {
           Logger.getLogger(sender.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(sender.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }
    public static void main(String[] args) {
        sender s=new sender();
        s.sendPackets();
    }
}

