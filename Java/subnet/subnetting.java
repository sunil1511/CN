/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subnet;

/**
 *
 * @author sunildupare
 */
import java.util.*;
public class subnetting {
    static void subnet()
    {   String []in=new String[4];
        int []ip_decimal=new int[4];
        int []mask_binary={0,0,0,0};
        int []mask_decimal=new int[4];
        int []subnet_addr=new int[4];
        int []first_addr=new int[4];
        int []temp=new int[4];
        int []last_addr=new int[4];
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the ip address in following format:eg.192.168.1.12/28:");
        String ip=sc.next();
        in=ip.split("\\.");
        System.out.println("inputted ip"+Arrays.toString(in));
        int ntwbts=Integer.parseInt(in[3].split("/")[1]);
        int t=ntwbts;
        in[3]=in[3].split("/")[0];
        int i=0;
        while(i<4)
        {
            ip_decimal[i]=Integer.parseInt(in[i]);
            i++;
        }
        System.out.println("Given ip address:"+Arrays.toString(ip_decimal));
        i=0;
        while(i<4)
        {
            for(int j=0;j<ntwbts&&j<8;j++)
            {
                mask_binary[i]=mask_binary[i]*10+1;
            }
            while(mask_binary[i]<11111111)
            {
                mask_binary[i]=mask_binary[i]*10;
            }
            if(mask_binary[i]>11111111)
            {
                mask_binary[i]=mask_binary[i]/10;
            }
            ntwbts-=8;
            i++;
        }
        System.out.println("Subnet mask in binary:"+Arrays.toString(mask_binary));
        i=0;
        while(i<4)
        {
            mask_decimal[i]=Integer.parseInt(String.valueOf(mask_binary[i]),2);
            i++;
        }
        System.out.println("subnet mask in decimal:"+Arrays.toString(mask_decimal));
        i=0;
        while(i<4)
        {
            subnet_addr[i]=ip_decimal[i] & mask_decimal[i];
            i++;
        }
        System.out.println("subnet addr of given ip:"+Arrays.toString(subnet_addr));
        i=0;
        while(i<4)
        {
            first_addr[i]=subnet_addr[i];
            i++;
        }
        first_addr[3]+=1;
        System.out.println("first host address in that subnet:"+Arrays.toString(first_addr));
        i=0;
        while(i<4)
        {
            last_addr[i]=mask_binary[i];
            i++;
        }
        int hbits=32-t;
        System.out.println("hbits"+hbits);
        System.out.println("no of host per subnet:"+Math.pow(2, hbits));
        //for(int j=8;j<)
        i=0;
        
        while(i<4)
        {
        temp[i]=~mask_decimal[i];
            System.out.println(temp[i]);
        i++;
        }
        i=0;
        while(i<4)
        {
        last_addr[i]=subnet_addr[i] ^ temp[i];
        
        i++;
        }
        System.out.println("broadcast address:"+Arrays.toString(last_addr));
        
        
    }
    public static void main(String[] args) {
        subnetting.subnet();
    }
}
