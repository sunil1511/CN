import java.io.*;
import java.util.Scanner;

class subnetclass
{
	Scanner sc=new Scanner(System.in);
	String ip;
	String ips[]=new String[4];
	String bips[]=new String[4];
	String subnet;
	String subs[]=new String[4];
	String bsubs[]=new String[4];
	String netadd;
	String netadds[]=new String[4];
	String isub;
	String broadadd;
	String broadadds[]=new String[4];
	char classsub;
	int n,bits;
	public void input()
	{
		System.out.println("Enter the IP Address ");
		ip=sc.next();
		System.out.println("\nEnter the no. of subnets ");
		n=sc.nextInt();
		ips=ip.split("\\.");
		for(int i=0;i<4;i++)
		{
			bips[i]=appendZeros(Integer.toBinaryString(Integer.parseInt(ips[i])));
		}
		bits=getbits();
		if(classsub=='a')
		{
		      subnet="255.";
			if(bits>16)
			{
				subnet+="255.255.";
				subnet+=Integer.toString(getsubnet(bits-16));
				
			}
			else if(bits>8)
			{
			subnet+="255."+Integer.toString(getsubnet(bits-8))+".0";
			}
			else
			{
			subnet+=Integer.toString(getsubnet(bits))+".0.0";
			}
		}
		else if(classsub=='b')
		{
			subnet="255.255.";
			if(bits>8)
			{
				subnet+="255."+Integer.toString(getsubnet(bits-16));
			}
			else
			{
				subnet+=Integer.toString(getsubnet(bits))+".0";
			}
			
			
		}
		
		else if(classsub=='c')
		{
			subnet="255.255.255."+Integer.toString(getsubnet(bits));

		}
		
		subs=subnet.split("\\.");
		for(int i=0;i<4;i++)
		{
			bsubs[i]=appendZeros(Integer.toBinaryString(Integer.parseInt(subs[i])));
		}
		System.out.print("Subnet mask is:-");
		System.out.println(subnet);
		getnetadd();
		invertsubnet();
		genbroadadd();
		//System.out.println(bits);

		
	}
	
	void invertsubnet()
	{
		for(int i=0;i<4;i++)
		{
			String e="";
			for(int j=0;j<bsubs[i].length();j++)
			{
			if(bsubs[i].charAt(j)=='1')
			{
				e+='0';
			}

			if(bsubs[i].charAt(j)=='0')
			{
				e+='1';
			}
			}
		bsubs[i]=e;
		}
	}

		void genbroadadd()
		{
			broadadd="";
			for(int i=0;i<4;i++)
			{
				broadadd+=Integer.toString(Integer.parseInt(bsubs[i],2));
				if(i!=3)
				broadadd+=".";
				
			}

			broadadds=broadadd.split("\\.");
			broadadd="";
			for(int i=0;i<4;i++)
		        {
			//int h=0;
				broadadd+=Integer.toString(Integer.parseInt(netadds[i])|Integer.parseInt(broadadds[i]));
				if(i!=3)
				broadadd+=".";
				
			
		        }
		System.out.print("Broadcast Adress is:-");
		System.out.println(broadadd);
			
		}

	void getnetadd()
	{
		netadd="";
		for(int i=0;i<4;i++)
		{
			//int h=0;
				netadd+=Integer.toString(Integer.parseInt(ips[i])&Integer.parseInt(subs[i]));
				if(i!=3)
				netadd+=".";
				
			
		}
		System.out.print("Subnetwork Adress is:-");
		System.out.println(netadd);
		netadds=netadd.split("\\.");
	}

	int getsubnet(int d)
	{
		int res=0;
		int t=7;
		for(int i=d;i>0;i--)
		{
			res=res+(int)Math.pow(2,t);
			t--;
			
		}
		return res;
	}
	
	public int getbits()
	{
		int k=(int)Math.ceil(Math.log(n)/Math.log(2));
		if(bips[0].charAt(0)=='0')
		classsub='a';
		if(bips[0].charAt(1)=='1'&&bips[0].charAt(0)=='0')
		classsub='b';
		if(bips[0].charAt(1)=='1'&&bips[0].charAt(1)=='1'&&bips[0].charAt(2)=='0')
		classsub='c';

		return k;
		
	}



	public String appendZeros(String s)
	{
		String temp=new String("00000000");
		if(s.length()>=8)
		return s;
		else
		{
		return temp.substring(s.length())+s;
		}
	}

public static void main(String[]args)
{
	subnetclass obj=new subnetclass();
	obj.input();
}


	
	
}
