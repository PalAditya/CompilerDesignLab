%{
#include <stdio.h>
#include <string.h>
char symtab[100][100];
char* pop();
void push(char *s);
int top=-1;
%}
%%

[0-9]+ {
		node *newnode=(node *)malloc(sizeof(node));
		strcpy(newnode->addr,yytext);
		int i;
		for(i=0;i<100;i++)
			newnode->code[i]='\0';
		//printf("Address and code are: %s,%s\n",newnode->addr,newnode->code);
		yylval=newnode;
		push(yytext);
		return NUMBER;
		}
[a-z]+ {
		node *newnode=(node *)malloc(sizeof(node));
		strcpy(newnode->addr,yytext);
		int i;
		for(i=0;i<100;i++)
			newnode->code[i]='\0';
		//printf("Address and code are: %s,%s\n",newnode->addr,newnode->code);
		yylval=newnode;
		push(yytext);
		return ID;
		}
[" "\t] {}
. {return yytext[0];}
\n {return 0;}

%%

int yywrap()
{
 return 1;
}
/*
//---RELOP---
[<>]|<=|>= {
		node *newnode=(node *)malloc(sizeof(node));
		strcpy(newnode->addr,yytext);
		int i;
		for(i=0;i<100;i++)
			newnode->code[i]='\0';
		//printf("Address and code are: %s,%s\n",newnode->addr,newnode->code);
		yylval=newnode;
		push(yytext);
		return RELOP;
		}
*/

void push(char *s)
{
	//printf("Pushed %s\n",s);
	strcpy(symtab[++top],s);
}
char *pop()
{
	//printf("Popped %s\n",symtab[top]);
	return symtab[top--];
}
