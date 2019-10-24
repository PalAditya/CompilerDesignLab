%{ 

  #include<stdio.h>
  #include<stdlib.h>
  #include<string.h>

  typedef struct node{
    int isOp;
    char type;
    int id;
    struct node *left;
    struct node *right;
  }node;

  #define YYSTYPE node*

  void yyerror(char *s);
  void postorder(node *x, int visited[]);
  int check(char temp[100]);

  int flag=0;
  int index1=0;
  char expressions[100][100];
  node* list[100];

  #include "dag.tab.h"
  #include "lex.yy.c"

%} 
  
%token ID 
  
%left '+' '-'
%left '*' '/'
  
%% 
  
ArithmeticExpression: 
E
  { 
    printf("\nResult\n");
    int visited[index1];
    int i;
    for(i=0;i<index1;i++) visited[i]=0;
    postorder($1,visited);
    printf("\n");
  }; 

E:E'+'E 
  {
    char temp[100];
    sprintf(temp,"%s + %s",expressions[$1->id],expressions[$3->id]);

    int ret = check(temp);
    if(ret >= 0)
      $$ = list[ret];
    else
    {
      node *newnode=(node *)malloc(sizeof(node));
      newnode->isOp=1;
      newnode->type='+';
      newnode->left=$1;
      newnode->right=$3;
      newnode->id = index1;

      $$=newnode;

      list[index1] = $$;
      strcpy(expressions[index1++],temp);
    }  
  } 

|E'-'E
  {
    char temp[100];
    sprintf(temp,"%s - %s",expressions[$1->id],expressions[$3->id]);

    int ret = check(temp);
    if(ret >= 0)
      $$ = list[ret];
    else
    {
      node *newnode=(node *)malloc(sizeof(node));
      newnode->isOp=1;
      newnode->type='-';
      newnode->left=$1;
      newnode->right=$3;
      newnode->id = index1;

      $$=newnode;

      list[index1] = $$;
      strcpy(expressions[index1++],temp);
    }  
  } 

|E'*'E 
  {
	//printf("In multiply");
    char temp[100];
	//strcpy(temp,$1->
    sprintf(temp,"%s * %s",expressions[$1->id],expressions[$3->id]);
	//printf("temp is: %s\n",temp);
	//printf("Didn't crash");
    int ret = check(temp);
    if(ret >= 0)
      $$ = list[ret];
    else
    {
      node *newnode=(node *)malloc(sizeof(node));
      newnode->isOp=1;
      newnode->type='*';
      newnode->left=$1;
      newnode->right=$3;
      newnode->id = index1;

      $$=newnode;

      list[index1] = $$;
      strcpy(expressions[index1++],temp);
    }   
  }

|E'/'E
  {
    char temp[100];
    sprintf(temp,"%s / %s",expressions[$1->id], expressions[$3->id]);

    int ret = check(temp);
    if(ret >= 0)
      $$ = list[ret];
    else
    {
      node *newnode=(node *)malloc(sizeof(node));
      newnode->isOp=1;
      newnode->type='/';
      newnode->left=$1;
      newnode->right=$3;
      newnode->id = index1;

      $$=newnode;

      list[index1] = $$;
      strcpy(expressions[index1++],temp);
    }
  } 

|ID
  {
	//printf("ID %c :)\n",$1->type);
    $$=$1;
  }
|'(' E ')' 
{
	$$=$2;
}
;
  
%% 

int main() 
{ 
   printf("Enter Any Arithmetic Expression with +,-,* and / only: "); 
   yyparse(); 
   return 1;
} 

int check(char temp[100]){

  int i;
  for(i=0;i<index1;i++){
    if(strcmp(temp,expressions[i]) == 0){
      return i;
    }
  }
  return -1;
}

void postorder(node *x, int visited[])
{
  if(x==NULL)
    return;

  if(visited[x->id]){
    printf("node: %s (done)\n",expressions[x->id]);
    return;
  }

  visited[x->id] = 1;

  postorder(x->left,visited);
  postorder(x->right,visited);

  printf("node: %c\n",x->type);

}

void yyerror(char *s) 
{ 
   printf("Entered arithmetic expression is Invalid\n"); 
   flag=1; 
}