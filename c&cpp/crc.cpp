#include<iostream>
using namespace std;
class crcx
{
 int i,j,k,frame[20],fs,gen[10],gs,rs,crc[15],rem[15],temp[20],tf[20];
 public:
 crcx()
 {
  i=0;j=0;k=0;fs=0;gs=0;rs=0;
  }
  
  void sender()
  {
   cout<<"\nEnter Frame size:";
   cin>>fs;
   cout<<"\nEnter Frame:";
   for(i=0;i<fs;i++)
   { 
    cin>>frame[i];
    }
   cout<<"\nEnter Generator size:";
   cin>>gs;
   cout<<"\nEnter Generator:";
   for(i=0;i<gs;i++)
   { 
    cin>>gen[i];
    }
   cout<<"\n<Sender Side>"<<"\n Frame:";
   for(i=0;i<fs;i++)
   {
    cout<<frame[i];
    }
   cout<<"\nGenerator:";
   for(i=0;i<gs;i++)
   {
    cout<<gen[i];
    }  
    rs=gs-1; 
   cout<<"\nNo. of Zeroes to be appended to the Frame:"<<rs;
   cout<<"\nFrame After Appending Zeroes:";
    for(i=0;i<fs+rs;i++)
   {
    temp[i]=frame[i];
    }
   for(i=fs;i<fs+rs;i++)
   {
    temp[i]=0;
    }
     for(i=0;i<fs+rs;i++)
   {
    cout<<temp[i];
    }
    
    for(i=0;i<fs+rs;i++)
    {
     k=i;
     j=0;
      if(temp[k]>=gen[j])
     {
       for(j=0,k=i;j<gs;j++,k++)
       {
         if((temp[k]==1 && gen[j]==1) || (temp[k]==0 && gen[j]==0))
         {
           temp[k]=0;
           }
         else
         {
          temp[k]=1;
          }
        }
      }
     }
     cout<<"\nCRC Bits:";
     for(i=fs,k=0;i<fs+rs;i++,k++)
     {
      crc[k]=temp[i];
      cout<<crc[k];
      }
     cout<<"\nTransmitted Frame:";
     for(i=0;i<fs;i++)
     {
       tf[i]=frame[i];
      }
      for(i=fs;i<fs+rs;i++)
      {
       tf[i]=temp[i];
       }
       
       for(i=0;i<fs+rs;i++)
     {
       cout<<tf[i];
      }
  }    
      
      
      
 void receiver()
 {     
      
     cout<<"\n\n<Received Side>"<<"\nReceived Frame:";
     for(i=0;i<fs+rs;i++)
     {
      temp[i]=tf[i];
      cout<<temp[i];
      }
      cout<<"\nGenerator:";
      for(int i=0;i<gs;i++)
      {
       cout<<gen[i];
       }
       for(i=0;i<fs+rs;i++)
    {
     k=i;
     j=0;
      if(temp[k]>=gen[j])
     {
       for(j=0,k=i;j<gs;j++,k++)
       {
         if((temp[k]==1 && gen[j]==1) || (temp[k]==0 && gen[j]==0))
         {
           temp[k]=0;
           }
         else
         {
          temp[k]=1;
          }
        }
      }
     }
     
     cout<<"\nRemainder after Division in receiver side:";
     for(i=fs,k=0;i<fs+rs;i++,k++)
     {
      rem[k]=temp[i];
      cout<<rem[k];
      }
      int flag=0;
       for(i=0;i<rs;i++)
       {
        if(rem[i]!=0)
        {
         flag=1;
         }
         }
        if(flag==1)
        {
         cout<<"\n<ERROR>";
         }
        else
        {
         cout<<"\n no error detected as the remainder is 0";
         }
  }      
          
};

int main()
{
 crcx c;
 c.sender();
 c.receiver();
 return 0;
 }         
            
