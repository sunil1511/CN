/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sunildupare
 */
import com.sun.scenario.effect.InvertMask;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class subnetting {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String ip_str;
        
        System.out.println("enter ip address : ");
        ip_str=sc.nextLine();
        String a[]=new String[4];
        a=ip_str.split("\\.");
        System.out.println(Arrays.toString(a));
        int bits=Integer.parseInt(a[3].split("/")[1]);
        
        System.out.println(bits);
        a[3]=a[3].split("/")[0];
        System.out.println(Arrays.toString(a));
        
        int ip_dec[]=new int[4];
        int sub_mask[]={0,0,0,0};
        int mask_dec[]={0,0,0,0};int invrted[]=new int[4];
        int i;
        for(i=0;i<4;i++){
            
            ip_dec[i]=Integer.parseInt(a[i]);
        }
        System.out.println("decimal ip "+Arrays.toString(ip_dec));
        int j=0;
        while(j<4){
            
            for(i=0;i<bits&&i<8;i++){
                sub_mask[j]=sub_mask[j]*10+1;
                 invrted[j]=invrted[j]*10;
            }
            while(sub_mask[j]<11111111)
            {
                sub_mask[j]=sub_mask[j]*10;
                invrted[j]=invrted[j]*10+1;
            }
            if(sub_mask[j]>11111111)
            {
                sub_mask[j]=sub_mask[j]/10;
                 invrted[j]=invrted[j]/10;
            }
            
            bits-=8;
            j++;
            
        } 
       
            
         System.out.println("inv addrs "+Arrays.toString(invrted));
        for(i=0;i<4;i++){
            mask_dec[i]=Integer.parseInt(String.valueOf(sub_mask[i]),2);
            invrted[i]=Integer.parseInt(String.valueOf(invrted[i]),2);
            
        }
        System.out.println("decimal mask "+Arrays.toString(mask_dec));
        int sub_addr[]=new int[4];
        for(i=0;i<4;i++){
            sub_addr[i]=mask_dec[i]&ip_dec[i];
            
        }
        System.out.println("subnet addrs "+Arrays.toString(sub_addr));
        int broad_addr[]=new int[4];
       
        
       
        for(i=0;i<4;i++){
            broad_addr[i]=invrted[i]^sub_addr[i];
            
        }
         System.out.println("broad addrs "+Arrays.toString(broad_addr));
         int frsthost[]=new int[4];
        for(i=0;i<4;i++){
            frsthost[i]=sub_addr[i];
            
        }
        
        
        frsthost[3]+=1;
        
         System.out.println("host addrs "+Arrays.toString(frsthost));
        
    }
    
}