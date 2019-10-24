%{ 
/* Definition section */
#include<stdio.h> 
%} 

/* Rule Section */
%% 
[a-z] { 
		node *newnode=(node *)malloc(sizeof(node));
		newnode->isOp=0;
		newnode->left=NULL;
		newnode->right=NULL;
		newnode->type=yytext[0];
		strcpy(expressions[index1],yytext);
		newnode->id=index1++;
		yylval=newnode;
		return ID;		
	} 
[\t" "] ; 
[\n] return 0; 
. return yytext[0]; 
%% 
int yywrap() 
{ 
	return 1; 
}
 