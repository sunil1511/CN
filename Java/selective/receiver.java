/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selective;

/**
 *
 * @author sunildupare
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sunildupare
 */
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
public class receiver {
    void receive()
    {
        try {
            int msg[]=new int[8];
            ServerSocket ser=new ServerSocket(5000);
            Socket sock=ser.accept();
            DataInputStream in=new DataInputStream(sock.getInputStream());
            DataOutputStream out=new DataOutputStream(sock.getOutputStream());
            System.out.println("Sender Sending packets:"+"packets are stored in buffer and processed one by one");
            int i=0;
            
            while(i<msg.length)
            {
                msg[i]=in.read();
                i++;
            }
            System.out.println("Received packets are:"+Arrays.toString(msg));
            System.out.println("checking packets for error one by one:");
            int preseq=-1;
            int seq=-1;
           i=0;
           int flag=0;
            while(seq!=7)
            {
                seq=msg[i];
                if(seq==preseq+1)
                {
                    System.out.println("No error in packet "+i+" acknowledgement sent to sender checking next packet");
                }
                else
                {
                    System.out.println("error in packet "+i+" NAK send to sender and checking next packet");
                    System.out.println("Receiving packet from sender after packet "+(i-1));
                    out.write(preseq);
                    msg[i]=in.read();
                    seq=msg[--i];
                    System.out.println("corrected packet:"+i+" received from sender "+"content of packets:"+Arrays.toString(msg));
                   
                }
                i++;
                preseq=seq;
            }
            
            System.out.println("Packets received successfully");
        } catch (IOException ex) {
            Logger.getLogger(receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void main(String[] args) {
        receiver r=new receiver();
        r.receive();
    }
}

