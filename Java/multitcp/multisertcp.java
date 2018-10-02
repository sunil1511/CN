/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multitcp;

import java.util.*;
import java.net.*;
import java.io.*;
/**
 *
 * @author sunildupare
 */
import java.util.logging.Level;
import java.util.logging.Logger;
public class multisertcp extends Thread {
    Socket s1;
    static ArrayList<Socket> list;

    public multisertcp(Socket s1) {
    this.s1=s1;
    }
    public void run()
    {
        try {
            DataInputStream in=new DataInputStream(s1.getInputStream());
            String name=in.readUTF();
            this.setName(name);
            while(true)
            {
                String msg=in.readUTF();
                System.out.println(this.getName()+":"+msg);
                if(msg.equals("bye:$"))
                        {
                            System.out.println("terminated by:"+this.getName());
                            this.s1.close();
                            break;
                        }
            }
        } catch (IOException ex) {
            Logger.getLogger(multisertcp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String[] args) {
        try {
            list=new ArrayList<Socket>();
            ServerSocket ser=new ServerSocket(5000);
            while(true)
            {
            Socket sock=ser.accept();
            multisertcp m=new multisertcp(sock);
            list.add(sock);
            m.start();
            
            }
        } catch (IOException ex) {
            Logger.getLogger(multisertcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
