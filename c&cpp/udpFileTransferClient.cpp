#include<sys/socket.h>
#include<arpa/inet.h>
#include<unistd.h>
#include<iostream>
#include<fstream>
using namespace std;
#define SIZE 20480

int main()
{
	//Structure to store server ip address,port,protocol
	sockaddr_in serverAdd;
	serverAdd.sin_family = AF_INET;
	serverAdd.sin_port = htons(8000);
	serverAdd.sin_addr.s_addr = inet_addr("127.0.0.1");
	
	
	unsigned int len = sizeof(serverAdd);
	
	int sockfd = socket(AF_INET,SOCK_DGRAM,0);
	char buffer[SIZE]="hello";
	sendto(sockfd,buffer,5,0,(sockaddr *)&serverAdd,len);
	
	unsigned int recv_len = recvfrom(sockfd,buffer,SIZE,0,(sockaddr *)&serverAdd,&len);
	ofstream temp;
	temp.open("temp.jpeg",ios::binary);
	if(temp.is_open())
	{
		while(recv_len){
	
			//cout<<buffer<<endl;	
			if(recv_len==1 && buffer[0]=='f')
			{
				//cout<<"Got: "<<buffer<<endl;
				cout<<"Finishing\n";
				temp.close();
				break;
			}
			temp.write(buffer,recv_len);
			char ack = 'a';
			sendto(sockfd,&ack,1,0,(sockaddr *)&serverAdd,len);
			recv_len = recvfrom(sockfd,buffer,SIZE,0,(sockaddr *)&serverAdd,&len);
		}
	}
	else{
		cout<<"Error opening file\n";
		return 0;
	}	
	close(sockfd);
	return 0;
}

