#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define i 1000000
int b;  
struct word
{
  char w[20]; 
  int num; 

}s[i];
void file1()
{
  FILE *p;
  int j,k;
  char temp[20];
  if((p=fopen("essay.txt","r"))==NULL) 
  {
    printf("�޷��򿪴��ļ���\n");
    exit(0);

  }
  while(!feof(p))
  {

   for(j=0;j<20;j++)
   {
     temp[j]=fgetc(p);
     if(temp[j]==' '||temp[j]==','||temp[j]=='.'||temp[j]=='!'||temp[j]=='?')
     {
       temp[j]='\0';
           strcpy(s[b].w,temp); 
       b++;
       break; 
     }
 
   }
      }
  fclose(p); 

}

void pv()
{
   int j,k,flag=1;
      for(j=0;j<b;j++)
      {
        for(k=0;k<j;k++)
        {
          if(strcmp(s[j].w,s[k].w)==0)
          {
              flag=0;  
              break; 
          }
          else 
		    flag=1;
        }
        while(flag) 
        {
          for(k=j;k<b;k++)
          {
            if(strcmp(s[k].w,s[j].w)==0)
                s[j].num++;
          }
         flag=0;  
        }
      }
}

void px()
{
  int j,k,c;
  char temp[20]; 
  for(j=0;j<b-1;j++)
  {
      c=j; 
      for(k=j+1;k<b;k++)
      { 
         if(strcmp(s[c].w,s[k].w)>0)
             c=k;
         if(c!=j)
         {
           strcpy(temp,s[c].w);
           strcpy(s[c].w,s[j].w);
           strcpy(s[j].w,temp);

         }
      } 

  }

}

void printer()
{
    int j;
    printf("������ʼ����Ƶ�ʣ�\n");
    for(j=0;j<b;j++)
    {
        if(s[j].num>0)
        printf("%s   ����%d��\n ",s[j].w,s[j].num);
    }
       return menu();
}
int menu()
{
	int item;
	printf("\n");
	printf("              *********************************************************\n");
	printf("              *                                                       *\n");
	printf("              *                    ��Ƶͳ�ƹ��ܲ˵�                   *\n");
	printf("              *                                                       *\n");
	printf("              *                      1.����Ƶ��                       *\n");
	printf("              *                                                       *\n");
	printf("              *                      2.�˳�                           *\n");
	printf("              *                                                       *\n");
	printf("              *********************************************************\n"); 
    printf("                     ��ѡ������Ҫ�Ĳ������(1-2)���س�ȷ��:");
    scanf("%d",&item);
    printf("\n");
    switch(item)
  {
   	  case 1:printer();break;
      case 2: break;
      default:printf("����1-2֮��ѡ��\n");break;
   }
}
int main()
{
  file1();
  px();
  pv();
  menu(); 
}

