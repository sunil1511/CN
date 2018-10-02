#include<iostream>//1<<r
#include<math.h>
using namespace std;
class ham
{
 int data[20],rec[20],ds,r;
 public:
 void send()
 {
  cout<<"\nenter data size:";
  cin>>ds;
  cout<<"\nParity bits req for given data size are:";
  for(r=0;r<10;r++)
  {
   if(pow(2,r)>=ds+r+1)
   {
    break;
    }
    }
    cout<<r;
    cout<<"\nenter data:";
    int flag=0;
     for(int i=0;i<ds;i++)
    {
     for(int j=0;j<r;j++)
     { flag=0;
     if(i=pow(2,r))
       {
         flag=1;
         }
        }
        if(flag==0)
        { 
         cin>>data[i];}
     }
     int count=0;
     int i=0,k;
     while(count<r)
     {
     count++;
     k=pow(2,i)
     data[k]=  
   
   
   
   
   
   
   
   
   
   }
   
 };
 
 
 
 int main()
 {
  ham h;
  h.send();
  return 0;
  }   
