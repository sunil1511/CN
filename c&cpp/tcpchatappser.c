#include<stdio.h>
#include<stdlib.h>
#include<arpa/inet.h>
#include<unistd.h>
#include<string.h>
#include<sys/socket.h>
int main()
{
 
 int opt=1;
 struct sockaddr_in sock;
 sock.sin_family=AF_INET;
 sock.sin_port=htons(8000);
 sock.sin_addr.s_addr=inet_addr("127.0.0.1");
 int l=sizeof(sock);
 printf("socket error");
 int sfd=socket(AF_INET,SOCK_STREAM,0);
 
 if(sfd<0)
 {
  printf("socket error");
  return 0;
  }
  printf("socket error");
  if(setsockopt(sfd,SOL_SOCKET,SO_REUSEPORT|SO_REUSEADDR,&opt,sizeof(int))<0)
  {
  printf("error");
  return 0;
  }
  if(bind(sfd,(struct sockaddr *)&sock,l)<0)
  {
   printf("bind failed");
   return 0;
   }
   printf("bind success");
   if(listen(sfd,3))
   {
    printf("listen");
    }
    printf("listen success");
    int newsfd=accept(sfd,(struct sockaddr *)&sock,&l);
    if(newsfd<0)
    {
    printf("accept failed");
    return 0;
    }
    char buffer[1024];
   while(1)
   {
   read(newsfd,buffer,1024);
   printf("\nclient:%s",buffer);
   if(strcmp(buffer,"bye:$")==0)
   {
   printf("\nchat ended by client");
   close(newsfd);
   break;
   }
   printf("\nenter message for client,enter bye:$ to end chat:");
   scanf("%s",&buffer);
   write(newsfd,buffer,1024);
   }  
   return 0;
  } 
