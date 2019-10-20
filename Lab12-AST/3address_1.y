%{
#include<stdio.h>
void yyerror(char *s);
int index1=0;
char temp = 'A'-1;
#define YYSTYPE char
%}


%left '+' '-'
%left '/' '*'

%token LETTER NUMBER
%%

statement: LETTER '=' exp {printf("%c = %c\n", (char)$1,(char)$3);};
exp: exp '+' exp {printf("%c = %c + %c\n", ++temp,(char)$1,(char)$3);$$ = temp;}
    |exp '-' exp {printf("%c = %c - %c\n", ++temp,(char)$1,(char)$3);$$ = temp;}
    |exp '/' exp {printf("%c = %c / %c\n", ++temp,(char)$1,(char)$3);$$ = temp;}
    |exp '*' exp {printf("%c = %c * %c\n", ++temp,(char)$1,(char)$3);$$ = temp;}
    |'(' exp ')' {$$= (char)$2;}
    |NUMBER {$$ = (char)$1;}
    |LETTER {(char)$1;};

%%

void yyerror(char *s){
    printf("Error %s",s);
}


int main(){
    printf("Enter the expression: ");
    yyparse();
    return 0;
}