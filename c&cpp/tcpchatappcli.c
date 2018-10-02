#include<stdio.h>
#include<arpa/inet.h>
#include<unistd.h>
#include<sys/socket.h>
#include<string.h>
int main()
{
char data[1024]= {0};
 struct sockaddr_in sock;
 sock.sin_family=AF_INET;
 sock.sin_port=htons(8000);
 sock.sin_addr.s_addr=inet_addr("127.0.0.1");
 int l=sizeof(sock);
 int sfd=socket(AF_INET,SOCK_STREAM,0);
 if(sfd<0)
 {
  printf("failed");
  }
  if((connect(sfd, (struct sockaddr* )&sock,l))<0)
        {
                printf("\nConnection failed");
                return -1;
                
        }
        while(1)
        {
        printf("\nenter message for server,enter bye:$ to exit chat:");
        scanf("%s",&data);
        write(sfd,data,1024);
        printf("\nserver:");
        read(sfd,data,1024);
        printf("%s",data);
        if(strcmp(data,"bye:$")==0)
        {
         printf("chat ended by server:");
         close(sfd);
         break;
         }
         }
        return 0;
}     
        
