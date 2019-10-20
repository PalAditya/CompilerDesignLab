%{ 
/* Definition section */
#include<stdio.h> 
%} 

/* Rule Section */
%% 
[0-9]+ { 
		node *newnode=(node *)malloc(sizeof(node));
		newnode->isOp=0;
		newnode->left=NULL;
		newnode->right=NULL;
		newnode->type=atoi(yytext);
		yylval=newnode;
		return NUMBER;		
	} 
[\t] ; 
[\n] return 0; 
. return yytext[0]; 
%% 
int yywrap() 
{ 
	return 1; 
}
