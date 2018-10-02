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
   
 char buffer[1024]={0};
  char op[1024]={0};
  read(new_socket,buffer,1024);
   int a=atoi(buffer);
  read(new_socket,op,1024);
   read(new_socket,buffer,1024); 
  
   int b=atoi(buffer);
   int c;
   printf("%d",a);
   printf("%c",op[0]);
   printf("%d",b);
   switch(op[0])
   {
    case '+':
     c=a+b;
    printf("=%d",c);
    break;
    case '-':
    c=a-b;
    printf("=%d",c);
    break;
    case '*':
     c=a*b;
    printf("=%d",c);
    break;
    case '/':
     c=a/b;
    printf("=%d",c);
    break;
    
    default:
    printf("invalid operator");
    break;
    }
   return 0;
  } 
