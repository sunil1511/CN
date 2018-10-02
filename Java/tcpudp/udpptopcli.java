/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sunildupare
 */
public class udpptopcli {
    public static void main(String[] args) {
        try {
            DatagramSocket sock=new DatagramSocket();
            byte send[]=new byte[512];
             byte rec[] = new byte[512];
             Scanner sc=new Scanner(System.in);
            while(true)
            {
                System.out.println("enter ur msg,enter bye:$ to terminate");
                String msg=sc.nextLine();
                send=msg.getBytes();
                DatagramPacket sen=new DatagramPacket(send, 0,send.length,InetAddress.getLocalHost(),5001);
                sock.send(sen);
                DatagramPacket recp;
                recp = new DatagramPacket(rec, rec.length);
                sock.receive(recp);
                String reply=new String(recp.getData(), 0, recp.getLength());
                System.out.println("Client says:"+reply);
                if(reply.equals("bye:$"))
                {
                    System.out.println("terminated by server:");
                    sock.close();
                    break;
                }
                
            }
            sock.close();
        } catch (SocketException ex) {
            Logger.getLogger(udpptopser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(udpptopser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
