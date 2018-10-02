/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpudp;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
/**
 *
 * @author sunildupare
 */
public class udpptopser {
    public static void main(String[] args) {
        try {
            DatagramSocket sock=new DatagramSocket(5001);
            byte send[]=new byte[512];
             byte rec[] = new byte[512];
             Scanner sc=new Scanner(System.in);
            while(true)
            {
                DatagramPacket recp;
                recp = new DatagramPacket(rec, rec.length);
                sock.receive(recp);
                String msg=new String(recp.getData(), 0, recp.getLength());
                System.out.println("Client says:"+msg);
                if(msg.equals("bye:$"))
                {
                    System.out.println("terminated by client:");
                    sock.close();
                    break;
                }
                System.out.println("enter ur reply,enter bye:$ to terminate");
                String reply=sc.nextLine();
                send=reply.getBytes();
                DatagramPacket sen=new DatagramPacket(send, 0,send.length,recp.getAddress(),recp.getPort());
                sock.send(sen);
            }
            sock.close();
        } catch (SocketException ex) {
            Logger.getLogger(udpptopser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(udpptopser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
