#include<iostream>
#include<sys/socket.h>
#include<unistd.h>
#include<arpa/inet.h>
#include<fstream>
#define SIZE 512
using namespace std;
class udp
{public:
 int server()
 {
 char fileBuf[SIZE];
 int filesize;
 char buffer[5];
  	int flag = 1;
	
	//Structure to store server ip address,port,protocol
	sockaddr_in serverAdd,clientAdd;
	serverAdd.sin_family = AF_INET;
	serverAdd.sin_port = htons(8000);
	serverAdd.sin_addr.s_addr = inet_addr("127.0.0.1");
	
	unsigned int len = sizeof(serverAdd);
	
	
	//Create socket
	//AF_INET = IPV4
	//SOCK_DGRAM = UDP Protocol
	int sockfd = socket(AF_INET,SOCK_DGRAM,0);
	
	//So that we can reuse same address(Optional but important)
	if (setsockopt(sockfd, SOL_SOCKET, SO_REUSEADDR|SO_REUSEPORT, &flag, sizeof(int)) < 0)
	{
		cout<<"Failure";
		return 0;
	}
	
	if(bind(sockfd,(sockaddr *)&serverAdd,len)<0)
	{
		cout<<"Error on binding\n";
		return 0;
	}
  cout<<"listening...\n";
  recvfrom(sockfd,buffer,5,0,(sockaddr *)&clientAdd,&len);
  cout<<"\nreceived.."<<buffer;
  ifstream in;
  string filename;
  cout<<"enter file name:";
  cin>>filename;
  int begin,end;
  in.open(filename,ios::binary);
  
  if(in.is_open())
  {
   begin=in.tellg();
   in.seekg(0,ios::end);
   end=in.tellg();
   filesize=end-begin;
   cout<<"size"<<filesize;
   //sendto(sockfd,filesize,sizeof(filesize),0,(sockaddr *)clientAdd,len);
   in.seekg(0,ios::beg);
   int reclen=SIZE;
   int count=0;
   while(filesize>0)
   {
    if(filesize<reclen)
    {
    reclen=filesize;
    }
                      in.read(fileBuf,reclen);
			//cout<<fileBuf<<endl;
			//temp.write(fileBuf,readSize);
			sendto(sockfd,fileBuf,reclen,0,(sockaddr *)&clientAdd,len);
			int recv_len = recvfrom(sockfd,buffer,SIZE,0,(sockaddr *)&clientAdd,&len);
			if(!(recv_len==1 && buffer[0]=='a')){
				cout<<"Error Sending\n";
				return 0;
			}
			cout<<reclen<<" bytes send";	
			filesize-=reclen;
			count++;
  }
		cout<<count;
		in.close();
		//temp.close();
	}
	else
	{
		cout<<"Could not open file\nMost probably not found\n";
		return 0;
	}
	char finish = 'f';
	sendto(sockfd,&finish,1,0,(sockaddr *)&clientAdd,len);
	close(sockfd);
	return 0;
  
  }
 };
 
 int main()
 {
 udp u;
 u.server();
 return 0;
 } 
 
 
