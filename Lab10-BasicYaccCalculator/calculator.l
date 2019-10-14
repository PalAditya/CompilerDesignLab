%{ 
/* Definition section */
#include<stdio.h> 
#include "calculator.tab.h" 
extern int yylval; 
%} 

/* Rule Section */
%% 
[0-9]+ { 
		yylval=atoi(yytext); 
		//printf("%d\n",yylval);
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
