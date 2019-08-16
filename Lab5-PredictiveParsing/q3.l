%{ 
    #include<stdio.h>
	#include<string.h>

	void check(char *str);
%} 

%% 

"+"|"-"|"*"|"/"|";"					{fprintf(yyout,"%s\n",yytext);}
"<"|">"|"<="|">="|":="|"="			{fprintf(yyout,"%s\n",yytext);}
["\t""\n"" "]						{}
[0-9]*[.][0-9]+ 					{fprintf(yyout,"id\n");}
[0-9]+								{fprintf(yyout,"id\n");}
"if"|"else"|"then"					{fprintf(yyout,"%s\n",yytext);}
"while"|"do"|"end"					{fprintf(yyout,"%s\n",yytext);}
"prog"|"scan"|"print"				{fprintf(yyout,"%s\n",yytext);}
"int"|"float"						{fprintf(yyout,"%s\n",yytext);}
[a-zA-Z][a-zA-Z0-9]* 				{fprintf(yyout,"id\n");}

%% 

int yywrap() {}  

int main(int argc, char*argv[]) 
{ 
    extern FILE *yyin, *yyout;

    yyin = fopen("input.txt", "r");
    yyout = fopen("output.txt", "w");

	yylex(); 
	return 0; 
}