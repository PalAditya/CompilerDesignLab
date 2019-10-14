%{ 
/* Definition section */
#include<stdio.h> 
#include "calculator.tab.h" 
extern double yylval; 
%} 

/* Rule Section */
%% 
[0-9]+ { 
		yylval=atof(yytext);
		return NUMBER; 
	} ;
[0-9]+[.][0-9]+ {
        yylval=atof(yytext); 
		return NUMBER_F; 
};
"**" return POWER;
"sin"e? {
return SINE;
}
"cos" return COSINE;
"cosec" return COSECANT;
"tan" return TANGENT;
"sec" return SECANT;
"cot" return COTANGENT;
"log" return LOGARITHM;
"root" return SQ_ROOT;

[\t] {}; 
[\n] {return 0;}; 
. return yytext[0]; 
%% 
int yywrap() 
{ 
	return 1; 
}
