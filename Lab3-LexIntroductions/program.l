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
[=] {printf("Assignment\n");}
[++|+=|\-=|\-\-] {printf("Shorthands\n");}
[<|>|<=|>=|<>|==] {printf("Relop: %s\n",yytext);}
([+][-][*][/]) {printf("Arithmetic operator: %s\n",yytext);}
["\n"" "\(\)\{\};] {}
%% 
int yywrap(){}  
int main(int argc, char*argv[]) 
{ 
	extern FILE *yyin;  
	yyin = fopen("a.txt","r");
	yylex(); 
	fclose(yyin);
	return 0; 
} 
void check(char *str)
{
	if(strcmp(str,"main")==0)
	{
		printf("Main function\n");
	}
	else if(strcmp(str,"if")==0)
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
	else if(strcmp(str,"for")==0)
	{
		printf("For loop\n");
	}
	else if(strcmp(str,"while")==0)
	{
		printf("While loop\n");
	}
	else if(strcmp(str,"read")==0)
	{
		printf("Input: Read\n");
	}
	else if(strcmp(str,"print")==0)
	{
		printf("Output: print\n");
	}
	else if(strcmp(str,"INT")==0||strcmp(str,"int")==0)
	{
		printf("Data type: Integer\n");
	}
	else if(strcmp(str,"FLOAT")==0||strcmp(str,"float")==0)
	{
		printf("Data type: Real\n");
	}
	else
	{
		printf("Id: %s\n",yytext);
	}
}