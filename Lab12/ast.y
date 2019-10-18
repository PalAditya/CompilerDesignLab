%{ 
   /* Definition section */
  #include<stdio.h>
  #include<stdlib.h>
  typedef struct node{
	  int isOp;
	  int type;
	  struct node *left;
	  struct node *right;
  }node;
  #define YYSTYPE node*
  void yyerror(char *s);
  void preorder(node *x);
  #include "ast.tab.h"
  #include "lex.yy.c"
  int flag=0; 
%} 
  
%token NUMBER 
  
%left '+' '-'
  
%left '*' '/' '%'
  
/* Rule Section */
%% 
  
ArithmeticExpression: 
E{ 
printf("\nResult\n");
preorder($1); 
return 0; 
}; 
 E:E'+'T 
 {
	node *newnode=(node *)malloc(sizeof(node));
	newnode->isOp=1;
	newnode->type=0;
	newnode->left=$1;
	newnode->right=$3;
	$$=newnode;
 } 
 |E'-'T 
{
	node *newnode=(node *)malloc(sizeof(node));
	newnode->isOp=1;
	newnode->type=1;
	newnode->left=$1;
	newnode->right=$3;
	$$=newnode;
} 
|T
{
	$$=$1;
};
 T: T'*'F 
 {
	node *newnode=(node *)malloc(sizeof(node));
	newnode->isOp=1;
	newnode->type=2;
	newnode->left=$1;
	newnode->right=$3;
	$$=newnode;
 } 
  
 |T'/'F 
 {
	node *newnode=(node *)malloc(sizeof(node));
	newnode->isOp=1;
	newnode->type=3;
	newnode->left=$1;
	newnode->right=$3;
	$$=newnode;
 }
  
 | F {$$=$1;} 
  
 ;

 F: NUMBER 
 {
	
	$$=$1;
 }
 | '('NUMBER')'
 {
	$$=$2;
 };
  
%% 
void main() 
{ 
   printf("Enter Any Arithmetic Expression with +,-,* and / only\n"); 
   yyparse(); 
} 
void preorder(node *x)
{
	if (x==NULL)
		return;
	if(x->isOp)
	{
		switch(x->type)
		{
			case 0:
				printf("+ ");
			break;
			case 1:
				printf("- ");
			break;
			case 2:
				printf("* ");
			break;
			case 3:
				printf("/ ");
			break;
			default:
				yyerror("Unknown symbol");
			break;
		}
	}
	else
	{
		printf("%d ",x->type);
	}
	preorder(x->left);
	preorder(x->right);
}
void yyerror(char *s) 
{ 
   printf("Entered arithmetic expression is Invalid\n"); 
   flag=1; 
} 