#include <stdio.h>
#include <stdlib.h>
#include <netdb.h>
#include <netinet/in.h>
#include <string.h>
#include<sys/socket.h>
#define PORT 8080

int main( int argc, char const *argv[] ) 
{
   
   int socket_fd, new_socket, valueread;
   struct sockaddr_in s_addr;
   int opt = 1;
   int addrlen = sizeof(s_addr);
   char buffer[1024] = {0};
   char *hello = "Hello from server";
   
   if((socket_fd=socket(AF_INET, SOCK_STREAM, 0))==0)
   {
        perror("Socket not created");
   
   }
   
   if(setsockopt(socket_fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt)))
   {
        perror("Set Socketopt");
        
   
   }
   
   s_addr.sin_family= AF_INET;
   s_addr.sin_addr.s_addr = htonl(INADDR_ANY);
   s_addr.sin_port= htons(PORT);
   
   if(bind(socket_fd,(struct sockaddr *) &s_addr, sizeof(s_addr))<0)
   {
        perror("Bind Failed");
   
   }
   
   if(listen(socket_fd, 3))
   {
        perror("listen");
   }
   
   if((new_socket= accept(socket_fd, (struct sockaddr * ) &s_addr, (socklen_t*)&addrlen))<0)
   {
        perror("Accept");
   }
   
  FILE *fp;
  
  read(new_socket,buffer,1024);
  fp=fopen("ad.txt","w");
  fprintf(fp,buffer);
  printf(buffer);
   return 0;
  } 
