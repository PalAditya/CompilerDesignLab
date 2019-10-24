%{
#include<stdio.h>
void yyerror(char *s);
int index1=0;
typedef struct node{
	char addr[10];
	char code[100];
  }node;
#define YYSTYPE node*
#include "3address.tab.h"
#include "lex.yy.c"
char label[5];
void makeLabel();
%}


%left '+' '-'
%left '/' '*'

%token ID NUMBER
%start prog
%%

prog: S {
			printf("Output is:\n%s\n",$1->code);
			/*int i;
			for(i=0;i<100;i++)
				if($1->code[i]=='\0')
					break;
				else
					printf("%c",$1->code[i]);
			printf("\n");*/
		}
	/*|BE {
			printf("Output is:\n%s\n",$1->code);
		}
		*/

S: ID '=' E {
				node *newnode=(node *)malloc(sizeof(node));
				//printf("Values are: %s,%s\n",$3->code,$3->addr);
				strcpy(newnode->code,$3->code);
				strcat(newnode->code,$1->addr);
				strcat(newnode->code,"=");
				strcat(newnode->code,$3->addr);
				$$=newnode;
			};

E: E '+' E  {
				node *newnode=(node *)malloc(sizeof(node));
				makeLabel();
				//printf("%s\n",label);
				strcpy(newnode->addr,label);
				strcpy(newnode->code,$1->code);
				strcat(newnode->code,$3->code);
				//printf("Code block in 1(in): %s\n",newnode->code);
				char newcode[10];
				strcpy(newcode,newnode->addr);
				strcat(newcode,"=");
				strcat(newcode,$1->addr);
				strcat(newcode,"+");
				strcat(newcode,$3->addr);
				//printf("newcode block in 1: %s\n",newcode);
				strcat(newnode->code,newcode);
				strcat(newnode->code,"\n");
				//printf("Code block in 1: %s\n",newnode->code);
				$$=newnode;
			}
	|E '*' E {
				node *newnode=(node *)malloc(sizeof(node));
				makeLabel();
				//printf("%s\n",label);
				strcpy(newnode->addr,label);
				strcpy(newnode->code,$1->code);
				strcat(newnode->code,$3->code);
				//printf("Code block in 1(in): %s\n",newnode->code);
				char newcode[10];
				strcpy(newcode,newnode->addr);
				strcat(newcode,"=");
				strcat(newcode,$1->addr);
				strcat(newcode,"*");
				strcat(newcode,$3->addr);
				//printf("newcode block in 1: %s\n",newcode);
				strcat(newnode->code,newcode);
				strcat(newnode->code,"\n");
				//printf("Code block in 1: %s\n",newnode->code);
				$$=newnode;
			}
	|E '-' E {
				node *newnode=(node *)malloc(sizeof(node));
				makeLabel();
				//printf("%s\n",label);
				strcpy(newnode->addr,label);
				strcpy(newnode->code,$1->code);
				strcat(newnode->code,$3->code);
				//printf("Code block in 1(in): %s\n",newnode->code);
				char newcode[10];
				strcpy(newcode,newnode->addr);
				strcat(newcode,"=");
				strcat(newcode,$1->addr);
				strcat(newcode,"-");
				strcat(newcode,$3->addr);
				//printf("newcode block in 1: %s\n",newcode);
				strcat(newnode->code,newcode);
				strcat(newnode->code,"\n");
				//printf("Code block in 1: %s\n",newnode->code);
				$$=newnode;
			}
	|E '/' E {
				node *newnode=(node *)malloc(sizeof(node));
				makeLabel();
				//printf("%s\n",label);
				strcpy(newnode->addr,label);
				strcpy(newnode->code,$1->code);
				strcat(newnode->code,$3->code);
				//printf("Code block in 1(in): %s\n",newnode->code);
				char newcode[10];
				strcpy(newcode,newnode->addr);
				strcat(newcode,"=");
				strcat(newcode,$1->addr);
				strcat(newcode,"/");
				strcat(newcode,$3->addr);
				//printf("newcode block in 1: %s\n",newcode);
				strcat(newnode->code,newcode);
				strcat(newnode->code,"\n");
				//printf("Code block in 1: %s\n",newnode->code);
				$$=newnode;
			}
	| '-' E {
				node *newnode=(node *)malloc(sizeof(node));
				makeLabel();
				strcpy(newnode->addr,label);
				strcpy(newnode->code,$2->code);
				strcat(newnode->code,newnode->addr);
				strcat(newnode->code,"=-");
				strcat(newnode->code,$2->addr);
				strcat(newnode->code,"\n");
				$$=newnode;
			}
	| '(' E ')' {
				node *newnode=(node *)malloc(sizeof(node));
				strcpy(newnode->addr,$2->addr);
				strcpy(newnode->code,$2->code);
				$$=newnode;
			}
	|ID {
		$$=$1;
		//printf("ID Values are: %s,%s\n",$1->code,$1->addr);
	  }
	|NUMBER {
				$$=$1;
				//printf("NUMBER Values are: %s,%s\n",$1->code,$1->addr);
			};

//BE: E RELOP E
%%

void yyerror(char *s){
    printf("Error: %s",s);
}

void makeLabel()
{
	int i=0;
	for(i=0;i<5;i++)
		label[i]='\0';
	label[0]='L';
	char str[4];
	sprintf(str, "%d", index1++);
	strcat(label,str);
}

int main(){
    printf("Enter the expression: ");
    yyparse();
    return 0;
}