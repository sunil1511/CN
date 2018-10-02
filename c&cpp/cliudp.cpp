#include<iostream>
#include<sys/socket.h>
#include<arpa/inet.h>
#include<fstream>
#include<unistd.h>
#define SIZE 512
using namespace std;
class udp
{public:
 int client()
 {sockaddr_in serverAdd;
	serverAdd.sin_family = AF_INET;
	serverAdd.sin_port = htons(8000);
	serverAdd.sin_addr.s_addr = inet_addr("127.0.0.1");
	int sockfd=socket(AF_INET,SOCK_DGRAM,0);
	char buffer[SIZE]="hello";
	unsigned int len=sizeof(serverAdd);
	sendto(sockfd,buffer,5,0,(sockaddr *)&serverAdd,len);
	cout<<"send";
	ofstream out;
	out.open("out",ios::binary);
	if(out.is_open()){
	unsigned int recv_len = recvfrom(sockfd,buffer,SIZE,0,(sockaddr *)&serverAdd,&len);
	while(recv_len)
	{
	if(recv_len==1 && buffer[0]=='f')
			{
				//cout<<"Got: "<<buffer<<endl;
				cout<<"Finishing\n";
				out.close();
				break;
			}
			out.write(buffer,recv_len);
			char ack = 'a';
			sendto(sockfd,&ack,1,0,(sockaddr *)&serverAdd,len);
			recv_len = recvfrom(sockfd,buffer,SIZE,0,(sockaddr *)&serverAdd,&len);
			cout<<"\ndata received :"<<recv_len;
		}
	}
	else{
		cout<<"Error opening file\n";
		return 0;
	}	
	close(sockfd);
	return 0;
  }
  
 };
 int main()
 {
 udp u;
 u.client();
 return 0;
 }
 
 
 
