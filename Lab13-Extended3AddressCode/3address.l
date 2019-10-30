%{
#include <stdio.h>
#include <string.h>

char c;

%}

DIGIT  [0-9]+
ID [a-zA-Z_][0-9a-zA-Z_]*
FLOAT [0-9]+[.][0-9]*

ARITHMETIC_OPERATORS ['+' '\-' '*' '/' '^' '%']
OTHER_CHARACTERS ['=' ';' '(' ')' '{' '}']
TYPES ["int" "float" "long" "char" "double"]
FORMAT ["%d" "%f" "%s"]

%%
[ \t] ;
[\n] ;


"if" 	{ return (IF); }
"else" 	{ return (ELSE); }
"while"	{ return (WHILE); }

"int"	{ return (TYPES); }
"float"	{ return (TYPES); }
"long"	{ return (TYPES); }
"char"	{ return (TYPES); }
"double"	{ return (TYPES); }

"read"	{return (READ);}
"print"	{return (PRINT);}

%[fsd] {yylval.sval = strdup(yytext);
		return (FORMAT);}

">"	{yylval.sval = strdup(yytext);
		return (REL_OPT);}
"<"	{yylval.sval = strdup(yytext);
		return (REL_OPT);}
">="	{yylval.sval = strdup(yytext);
		return (REL_OPT);}
"<="	{yylval.sval = strdup(yytext);
		return (REL_OPT);}
"!="	{yylval.sval = strdup(yytext);
		return (REL_OPT);}
"=="	{yylval.sval = strdup(yytext);
		return (REL_OPT);}

"||"	{yylval.sval = strdup(yytext);
		return (OR);}
"&&"	{yylval.sval = strdup(yytext);
		return (AND);}
"!"	{yylval.sval = strdup(yytext);
		return (NOT);}
"true"	{yylval.sval = strdup(yytext);
		return (TRUE);}
"false"	{yylval.sval = strdup(yytext);
		return (FALSE);}

{DIGIT}  { yylval.ival = atoi(yytext);
	   return DIGIT; }

{FLOAT}  { yylval.fval = atof(yytext);
	   return FLOAT; }

{ID} {
	    yylval.sval = strdup(yytext);
	    return ID; 
}
{ARITHMETIC_OPERATORS}   {
            	c = yytext[0];
                 return(c);
              }

{OTHER_CHARACTERS}   {
                 c = yytext[0];
                 return(c);
              }

%%

int yywrap()
{
	return 1;
}
