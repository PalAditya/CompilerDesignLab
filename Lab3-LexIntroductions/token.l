%{ 
    #include<stdio.h>
	#include<string.h>
	void check(char *str);
%}   
%% 
[0-9] {printf("Digit: %s\n",yytext);}
[0-9]+ {printf("Digits: %s\n",yytext);}
[0-9]+([.][0-9]+)?([E][+-]?[0-9]+)? {printf("Numbers: %s\n",yytext);}
[a-zA-z] {printf("Letter: %s\n",yytext);}
[a-zA-z]([a-zA-z]|[0-9])* {check(yytext);}
([<]|[>]|[<][=]|[>][=]|[<][>]|[=]) {printf("Relop: %s\n",yytext);}
["\n"" "] {}
%% 
int yywrap(){}  
int main(int argc, char*argv[]) 
{ 
	yylex(); 
	return 0; 
} 
void check(char *str)
{
	if(strcmp(str,"if")==0)
	{
		printf("If\n");
	}
	else if(strcmp(str,"else")==0)
	{
		printf("Else\n");
	}
	else if(strcmp(str,"if")==0)
	{
		printf("Then\n");
	}
	else
	{
		printf("Id: %s\n",yytext);
	}
}