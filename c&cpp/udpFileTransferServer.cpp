#include<sys/socket.h>
#include<arpa/inet.h>
#include<unistd.h>
#include<iostream>
#include<fstream>
using namespace std;
#define SIZE 20480
int main()
{
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
	
	cout<<"Listening...\n";
	char buffer[1000];
	recvfrom(sockfd,buffer,1000,0,(sockaddr *)&clientAdd,&len);
	cout<<"recieved: \n"<<buffer;
	
	ifstream image;
	image.open("index.jpeg",ios::binary);
	
	
	if(image.is_open())
	{
		int count = 0;
		unsigned long begin,end;
		
		begin = image.tellg();
		image.seekg(0,ios::end);
		end = image.tellg();
		
		
		int size = end-begin;
		
		cout<<"File size = "<<size;
		
		image.seekg(0,ios::beg);
		char fileBuf[SIZE];
		int readSize = SIZE;
		
		//ofstream temp;
		//temp.open("temp.mp3",ios::binary);
		
		
		while(size>0)
		{
			if(size<readSize)
				readSize = size;
			image.read(fileBuf,readSize);
			//cout<<fileBuf<<endl;
			//temp.write(fileBuf,readSize);
			sendto(sockfd,fileBuf,readSize,0,(sockaddr *)&clientAdd,len);
			int recv_len = recvfrom(sockfd,buffer,SIZE,0,(sockaddr *)&clientAdd,&len);
			if(!(recv_len==1 && buffer[0]=='a')){
				cout<<"Error Sending\n";
				return 0;
			}	
			size-=readSize;
			count++;
		}
		cout<<count;
		image.close();
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
