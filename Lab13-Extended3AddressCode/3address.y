%{
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

typedef struct exprType{

	char *addr;
	char *code;
	
}exprType;

int n=1;
int nl = 1;
char *var;
char num_to_concatanate[10];
char num_to_concatanate_l[10];
char *ret;
char *temp;

char *label;
char *label2;
char *check;

char *begin;

char *b1;
char *b2;

char *s1;
char *s2;

exprType *to_return_expr;

//Creates a new temporary variable like T1, T2, T3...
char* newTemp(){
	
	char *newTemp = (char *)malloc(20);
	strcpy(newTemp,"T");
	snprintf(num_to_concatanate, 10,"%d",n);
	strcat(newTemp,num_to_concatanate);
	n++;
	return newTemp;
}

//Creates a new label like L1, L2, L3...
char* newLabel(){
	
	char *newLabel = (char *)malloc(20);
	strcpy(newLabel,"L");
	snprintf(num_to_concatanate_l, 10,"%d",nl);
	strcat(newLabel,num_to_concatanate_l);
	nl++;
	return newLabel;
}

void yyerror(char *s);

#include "3address.tab.h"
#include "lex.yy.c"

%}

%start S

//A union to hold possible data types
//I can have integer or float (for numbers), an identifier (sval) and an expression
%union {
	int ival;
	float fval;
	char *sval;
	exprType *EXPRTYPE;
}
%token <ival> DIGIT
%token <fval> FLOAT
%token <sval> ID IF ELSE WHILE TYPES REL_OPT OR AND NOT TRUE FALSE READ PRINT FORMAT
%token <sval> '+' '-' '*' '/' '^' '%' '\n' '=' ';'
%type <sval> list text number construct block dec bool prog S
%type <EXPRTYPE> expr stat

%left READ PRINT FORMAT
%left REL_OPT
%right '='
%left '+' '-'
%left '*' '/' '%'
%right '^'
%left AND OR
%nonassoc NOT
%nonassoc '(' ')'

%%

S:	prog
		{
			s1 = $1;
			strcat(s1,"\0");
			label = newLabel();
			check = strstr (s1,"NEXT");
			while(check!=NULL){
				char *restofString=(char *)calloc(100*sizeof(char),'\0');
				strcpy(restofString, check+4);
				strcpy (check,label);
				strcat (check,restofString);
				check = strstr (s1,"NEXT");
				break;
			}
			printf("%s\n",s1);
			printf("%s\n",label);
			$$ = NULL;
		}
		;

prog : 	prog construct
		{

			s1 = $1;
			s2 = $2;

			label = newLabel();

			check = strstr (s1,"NEXT");
			
			while(check!=NULL){
				strncpy (check,label,strlen(label));
				strncpy (check+strlen(label),"    ",(4-strlen(label)));
				check = strstr (s1,"NEXT");
				}

			ret = (char *)malloc(strlen($1)+strlen($2)+4);
			ret[0] = 0;
			strcat(ret,$1);
			strcat(ret,"\n");
			strcat(ret,label);
			strcat(ret," : ");
			strcat(ret,$2);
			strcat(ret, "\0");
			$$ = ret;
		}
		|
		construct
		{
			$$ = $1;
		}
		|
		list
		{
			$$ = $1;
		};

construct : block
		{
			$$ = $1;
		}
		|
		WHILE '(' bool ')' block
		{
			b1 = $3;
			s1 = $5;
			begin = newLabel();
			label = newLabel();
			check = strstr (b1,"TRUE");
			while(check!=NULL){
				strncpy (check,label,strlen(label));
				strncpy (check+strlen(label),"    ",(4-strlen(label)));
				check = strstr (b1,"TRUE");
			}
			check = strstr (b1,"FAIL");
			while(check!=NULL){
				strncpy (check,"NEXT",4);
				check = strstr (b1,"FAIL");
				}
			check = strstr (s1,"NEXT");
			while(check!=NULL){
				strncpy (check,begin,strlen(begin));
				strncpy (check+strlen(begin),"    ",(4-strlen(begin)));
				check = strstr (s1,"NEXT");
				}
			ret = (char *)malloc(strlen(b1)+strlen(s1)+20);
			ret[0] = 0;
			strcat(ret,begin);
			strcat(ret," : ");
			strcat(ret,b1);
			strcat(ret,"\n");
			strcat(ret,label);
			strcat(ret," : ");
			strcat(ret,s1);
			strcat(ret,"\n");
			strcat(ret,"goto ");
			strcat(ret,begin);
			strcat(ret,"\0");
			printf("\n\n");
			$$ = ret;
		}
		|
		IF '(' bool ')' block
		{
			label = newLabel();
			b1 = $3;

			check = strstr (b1,"TRUE");
			
			while(check!=NULL){
				strncpy (check,label,strlen(label));
				strncpy (check+strlen(label),"    ",(4-strlen(label)));
				check = strstr (b1,"TRUE");
				}
			
			check = strstr (b1,"FAIL");
			
			while(check!=NULL){
				strncpy (check,"NEXT",4);
				check = strstr (b1,"FAIL");
				}

			ret = (char *)malloc(strlen(b1)+strlen($5)+4);
			ret[0] = 0;
			strcat(ret,b1);
			strcat(ret,"\n");
			strcat(ret,label);
			strcat(ret," : ");
			strcat(ret,$5);
			strcat(ret, "\0");
			$$ = ret;
		}
		|
		IF '(' bool ')' block ELSE block
		{
			b1 = $3;
			label = newLabel();

			check = strstr (b1,"TRUE");
			
			while(check!=NULL){
				strncpy (check,label,strlen(label));
				strncpy (check+strlen(label),"    ",(4-strlen(label)));
				check = strstr (b1,"TRUE");
				}
			label2 = newLabel();
			check = strstr (b1,"FAIL");

			while(check!=NULL){
				strncpy (check,label2,strlen(label2));
				strncpy (check+strlen(label2),"    ",(4-strlen(label2)));
				check = strstr (b1,"FAIL");
				}

			ret = (char *)malloc(strlen(b1)+strlen($5)+strlen($7)+20);
			ret[0] = 0;
			strcat(ret,b1);
			strcat(ret,"\n");
			strcat(ret,label);
			strcat(ret," : ");
			strcat(ret,$5);
			strcat(ret,"\n");
			strcat(ret,"goto NEXT");
			strcat(ret,"\n");
			strcat(ret,label2);
			strcat(ret," : ");
			strcat(ret,$7);
			strcat(ret, "\0");
			$$ = ret;
		}
		;

block:	'{' list '}'
		{
			$$ = $2;
		}
		|
		'{' construct '}'
		{
			$$ = $2;
		};

list:  stat 
		{
			$$ = $1->code;
		}
        |
        list stat
		{
			ret = (char *)malloc(strlen($1)+strlen($2->code)+4);
			ret[0] = 0;
			strcat(ret,$1);
			strcat(ret,"\n");
			strcat(ret,$2->code);
			strcat(ret,"\0");
			$$ = ret;
		}
	 |
        list error '\n'
         {
           yyerrok;
         }
         ;


stat:    ';'
	 {
		to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = $1;
		
		to_return_expr->code = (char *)malloc(2);
		to_return_expr->code[0] = 0;
		$$ = to_return_expr;
	 }
	 |
	 READ FORMAT stat
	{
		to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = newTemp();
		ret = (char *)malloc(20);
		ret[0] = 0;
		strcat(ret,to_return_expr->addr);
		strcat(ret,"=");
		strcat(ret,"READ ");
		strcat(ret,$2);
		strcat(ret," ");
		strcat(ret,$3->addr);
		strcat(ret,"\0");
		to_return_expr->code = ret;
		$$ = to_return_expr;
	}
	 |
	PRINT FORMAT expr ';'
	{
		to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = newTemp();
		ret = (char *)malloc(20);
		ret[0] = 0;
		strcat(ret,to_return_expr->addr);
		strcat(ret,"=");
		strcat(ret,"PRINT ");
		strcat(ret,$2);
		strcat(ret," ");
		strcat(ret,$3->addr);
		strcat(ret,"\0");
		to_return_expr->code = ret;
		$$ = to_return_expr;
	}
	|
	PRINT text ';'
	{
		to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = newTemp();
		ret = (char *)malloc(20);
		ret[0] = 0;
		strcat(ret,to_return_expr->addr);
		strcat(ret,"=");
		strcat(ret,"PRINT ");
		strcat(ret,$2);
		strcat(ret,"\0");
		to_return_expr->code = ret;
		$$ = to_return_expr;
	}
	|
	 expr ';'
         {
		$$ = $1;
         }
	 |
	 dec ';'
         {
		to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = $1;
		
		to_return_expr->code = (char *)malloc(2);
		to_return_expr->code[0] = 0;
		
		$$ = to_return_expr;
         }
         |
		text '=' expr ';'
		{
		to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = newTemp();
		ret = (char *)malloc(20);
		ret[0] = 0;
		strcat(ret,$1);
		strcat(ret,"=");
		strcat(ret,$3->addr);
		temp = (char *)malloc(strlen($3->code)+strlen(ret)+6);
		temp[0] = 0;
		if ($3->code[0]!=0){
			strcat(temp,$3->code);
			strcat(temp,"\n");
			}
		strcat(temp,ret);
		strcat(temp,"\0");
		to_return_expr->code = temp;
		$$ = to_return_expr;
		}
	 |
	 dec '=' expr ';'
         {
	    //printf("Dec and Assignment statement \n");
	    
		to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = newTemp();
		
		ret = (char *)malloc(20);
		ret[0] = 0;

		//strcat(ret,to_return_expr->addr);
		
		strcat(ret,$1);
		strcat(ret,"=");
		strcat(ret,$3->addr);
		printf("RET  = \n");
		//puts(ret);

		temp = (char *)malloc(strlen($1)+strlen($3->code)+strlen(ret)+6);

		temp[0] = 0;
		
		if ($3->code[0]!=0){
			strcat(temp,$3->code);
			strcat(temp,"\n");
			}
		strcat(temp,ret);
		to_return_expr->code = temp;
		$$ = to_return_expr;
         }
         ;

dec : 	TYPES text 
		{	
			$$ = $2;
		}
		;

bool : 	 	expr REL_OPT expr
		{
			temp = (char *)malloc(strlen($1->code)+strlen($3->code)+50);
			temp[0] = 0;
	
			if($1->code[0]!=0){
				strcat(temp,$1->code);
				strcat(temp,"\n");
				}
			if($3->code[0]!=0){
				strcat(temp,$3->code);
				strcat(temp,"\n");
				}

			ret = (char *)malloc(50);
			ret[0] = 0;
			strcat(ret,"if(");
			strcat(ret,$1->addr);
			strcat(ret,$2);
			strcat(ret,$3->addr);
			strcat(ret,") goto TRUE \n goto FAIL");

			strcat(temp,ret);
			strcat(temp,"\0");

			$$ = temp;
		}
		|
		bool OR bool
		{
			//printf("Inside OR\n");
			
			b1 = $1;
			b2 = $3;

			label = newLabel();

			check = strstr (b1,"FAIL");
			
			while(check!=NULL){
				strncpy (check,label,strlen(label));
				strncpy (check+strlen(label),"    ",(4-strlen(label)));
				check = strstr (b1,"FAIL");
				}
			
			temp = (char *)malloc(strlen(b1)+strlen(b2)+10);
			temp[0] = 0;

			strcat(temp,b1);
			strcat(temp,"\n");
			strcat(temp,label);
			strcat(temp," : ");
			strcat(temp,b2);
			strcat(temp,"\0");
			$$ = temp;
		}
		|
		bool AND bool
		{
			//printf("Inside AND\n");

			b1 = $1;
			b2 = $3;

			label = newLabel();

			check = strstr (b1,"TRUE");
			
			while(check!=NULL){
				strncpy (check,label,strlen(label));
				strncpy (check+strlen(label),"    ",(4-strlen(label)));
				check = strstr (b1,"TRUE");
				}
			
			temp = (char *)malloc(strlen(b1)+strlen(b2)+10);
			temp[0] = 0;

			strcat(temp,b1);
			strcat(temp,"\n");
			strcat(temp,label);
			strcat(temp," : ");
			strcat(temp,b2);
			strcat(temp,"\0");
			$$ = temp;
		}
		|
		NOT '(' bool ')'
		{
			b1 = $3;
			label = "TEFS";
			check = strstr (b1,"TRUE");
			
			while(check!=NULL){
				strncpy (check,label,strlen(label));
				//strncpy (check+strlen(label),"    ",(4-strlen(label)));
				check = strstr (b1,"TRUE");
				}
			
			label = "TRUE";
			check = strstr (b1,"FAIL");
			while(check!=NULL){
				strncpy (check,label,strlen(label));
				//strncpy (check+strlen(label),"    ",(4-strlen(label)));
				check = strstr (b1,"FAIL");
				}

			label = "FAIL";
			check = strstr (b1,"TEFS");
			
			while(check!=NULL){
				strncpy (check,label,strlen(label));
				//strncpy (check+strlen(label),"    ",(4-strlen(label)));
				check = strstr (b1,"TEFS");
				}
			
			$$ = b1;
		}
		|
		'(' bool ')'
		{
			$$ = $2;
		}
		|
		TRUE
		{
			ret = (char *)malloc(20);
			ret[0] = 0;
			strcat(ret,"\ngoto TRUE");
			strcat(ret,"\0");
			$$ = ret;
		}
		|
		FALSE
		{
			ret = (char *)malloc(20);
			ret[0] = 0;
			strcat(ret,"\ngoto FAIL");
			strcat(ret,"\0");
			$$ = ret;
		}
		;

expr:    '(' expr ')'
         {
           $$ = $2;
         }
		 |
	 expr '^' expr
         {
		to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = newTemp();
		ret = (char *)malloc(20);
		ret[0] = 0;
		strcat(ret,to_return_expr->addr);
		strcat(ret,"=");
		strcat(ret,$1->addr);
		strcat(ret,"^");
		strcat(ret,$3->addr);
		temp = (char *)malloc(strlen($1->code)+strlen($3->code)+strlen(ret)+6);
		temp[0] = 0;
		if ($1->code[0]!=0){
			strcat(temp,$1->code);
			strcat(temp,"\n");
			}
		if ($3->code[0]!=0){
			strcat(temp,$3->code);
			strcat(temp,"\n");
			}
		strcat(temp,ret);
		strcat(temp,"\0");
		to_return_expr->code = temp;
		$$ = to_return_expr;
         }
	 |
         expr '*' expr
         {

           //printf("Multiplication : ");
	   	to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = newTemp();
		
		ret = (char *)malloc(20);
		ret[0] = 0;

		strcat(ret,to_return_expr->addr);

		strcat(ret,"=");
		strcat(ret,$1->addr);
		strcat(ret,"*");
		strcat(ret,$3->addr);
		temp = (char *)malloc(strlen($1->code)+strlen($3->code)+strlen(ret)+6);
		temp[0] = 0;
		
		if ($1->code[0]!=0){
			strcat(temp,$1->code);
			strcat(temp,"\n");
			}
		if ($3->code[0]!=0){
			strcat(temp,$3->code);
			strcat(temp,"\n");
			}
		strcat(temp,ret);
		strcat(temp,"\0");
		to_return_expr->code = temp;
		$$ = to_return_expr;
         }
         |
         expr '/' expr
         {
	  	to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = newTemp();
		
		ret = (char *)malloc(20);
		ret[0] = 0;

		strcat(ret,to_return_expr->addr);

		strcat(ret,"=");
		strcat(ret,$1->addr);
		strcat(ret,"/");
		strcat(ret,$3->addr);
		temp = (char *)malloc(strlen($1->code)+strlen($3->code)+strlen(ret)+6);
		temp[0] = 0;
		if ($1->code[0]!=0){
			strcat(temp,$1->code);
			strcat(temp,"\n");
			}
		if ($3->code[0]!=0){
			strcat(temp,$3->code);
			strcat(temp,"\n");
			}
		strcat(temp,ret);

		//puts(temp);

		to_return_expr->code = temp;

           	$$ = to_return_expr;
	   
         }
         |
         expr '%' expr
         {
	   	to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = newTemp();
		
		ret = (char *)malloc(20);
		ret[0] = 0;

		strcat(ret,to_return_expr->addr);

		strcat(ret,"=");
		strcat(ret,$1->addr);
		strcat(ret,"%");
		strcat(ret,$3->addr);
		//puts(ret);

		temp = (char *)malloc(strlen($1->code)+strlen($3->code)+strlen(ret)+6);

		temp[0] = 0;
		
		if ($1->code[0]!=0){
			strcat(temp,$1->code);
			strcat(temp,"\n");
			}
		if ($3->code[0]!=0){
			strcat(temp,$3->code);
			strcat(temp,"\n");
			}
		strcat(temp,ret);

		//puts(temp);

		to_return_expr->code = temp;

           	$$ = to_return_expr;
         }
         |
         expr '+' expr
         {
	   	to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = newTemp();

		ret = (char *)malloc(20);
		ret[0] = 0;

		strcat(ret,to_return_expr->addr);

		strcat(ret,"=");
		strcat(ret,$1->addr);
		strcat(ret,"+");
		strcat(ret,$3->addr);
		//puts(ret);

		temp = (char *)malloc(strlen($1->code)+strlen($3->code)+strlen(ret)+6);

		temp[0] = 0;
		
		if ($1->code[0]!=0){
			strcat(temp,$1->code);
			strcat(temp,"\n");
			}
		if ($3->code[0]!=0){
			strcat(temp,$3->code);
			strcat(temp,"\n");
			}
		strcat(temp,ret);

		to_return_expr->code = temp;

           	$$ = to_return_expr;
         }
         |
         expr '-' expr
         {
           	to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = newTemp();

		ret = (char *)malloc(20);
		ret[0] = 0;

		strcat(ret,to_return_expr->addr);

		strcat(ret,"=");
		strcat(ret,$1->addr);
		strcat(ret,"-");
		strcat(ret,$3->addr);
		//puts(ret);

		temp = (char *)malloc(strlen($1->code)+strlen($3->code)+strlen(ret)+6);

		temp[0] = 0;
		
		if ($1->code[0]!=0){
			strcat(temp,$1->code);
			strcat(temp,"\n");
			}
		if ($3->code[0]!=0){
			strcat(temp,$3->code);
			strcat(temp,"\n");
			}
		strcat(temp,ret);
		to_return_expr->code = temp;

           	$$ = to_return_expr;
		
         }
         |
	 text {
		to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = $1;

		to_return_expr->code = (char *)malloc(2);
		to_return_expr->code[0] = 0;

		$$ = to_return_expr;}
         |
         number {
		to_return_expr = (struct exprType *)malloc(sizeof(struct exprType));
		to_return_expr->addr = (char *)malloc(20);
		to_return_expr->addr = $1;
		
		to_return_expr->code = (char *)malloc(2);
		to_return_expr->code[0] = 0;
		
		$$ = to_return_expr;}
         ;

text: 	ID
         {
           	$$ = $1;
         }
	  ;

number:  DIGIT
         {
		var = (char *)malloc(20);
           	snprintf(var, 10,"%d",$1);
		$$ = var;
           
         } 
	 |
         FLOAT
         {
		var = (char *)malloc(20);
        snprintf(var, 10,"%f",$1);
		$$ = var;
         } 
	;
	
%%

extern int yylex();
extern int yyparse();
extern FILE *yyin;

int main() {
	FILE *myfile = fopen("input.txt", "r");
	if (!myfile) {
		printf("File open error :(");
		return -1;
	}
	yyin = myfile;
	do {
		yyparse();
	} while (!feof(yyin));
	
}

void yyerror(char *s) {
	printf("Parse error: ");
	puts(s);
	exit(-1);
}
