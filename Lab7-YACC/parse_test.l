%{ 
  /* Definition section */
  #include<stdio.h> 
  #include "parse.tab.h" 
  extern int yylval; 
%} 
  
/* Rule Section */
%% 
[/][/].*["\n"] {}
and { printf("AND\n"); }
:= { printf(" ASSIGN\n");}
: {printf(" COLON\n");}
, {printf(" COMMA\n");}
def {printf(" DEF\n");}
\/ {printf(" DIV\n");}
[.] {printf(" DOT\n");}
[e][l][s][e] {printf(" ELSE\n");} 
end {printf(" END\n");}
= {printf(" EQ\n");}
exit {printf(" EXITLOOP\n");}
float {printf(" FLOAT\n");}
[0-9]+[.][0-9]+ {printf(" FLOAT_CONST\n");}
%[fsd] {printf(" FORMAT\n");}
from {printf(" FROM\n");}
fun {printf(" FUN\n");}
>= {printf(" GE\n");}
global {printf(" GLOBAL\n");}
> {printf(" GT\n");}
if {printf(" IF\n");}
int {printf(" INT\n");}
[0-9]+ {printf(" INT_CONST\n");}
[(] {printf(" LEFT_PAREN\n");}
\[ {printf(" LEFT_SQ_BKT\n");}
[<][=] {printf(" LE\n");}
[<] {printf(" LT\n");}
\- {printf(" MINUS\n");}
mod {printf(" MOD\n"); }
\* {printf(" MULT\n"); } 
[<][>] {printf(" NE\n"); }
not {printf(" NOT\n");}
null {printf(" NUL\n");}
or {printf(" OR\n");}
\+ {printf(" PLUS\n");}
print {printf(" PRINT\n");}
product {printf(" PRODUCT\n");}
read {printf(" READ\n");}
return {printf(" RETURN\n");}
\-> {printf(" RETURNS\n");}
[)] {printf(" RIGHT_PAREN\n");}
\] {printf(" RIGHT_SQ_BKT\n");}
; {printf(" SEMICOLON\n");}
skip {printf(" SKIP\n");}
step {printf(" STEP\n");}
to {printf(" TO\n");}
while {printf(" WHILE\n");}
[a-zA-Z][a-zA-Z0-9]+ {printf(" ID\n");}
\".*\" {printf(" STRING\n");}
[\t\n" "] {}; 
  
%% 
  
int yywrap() 
{ 
	return 1; 
} 

int main()
{
	extern FILE *yyin;  
	yyin = fopen("a.txt","r");
	yylex();
	return 1;
}