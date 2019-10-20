%{
#include<stdio.h>
char addtotable(char,char,char);
void yyerror(char *s);
int index1=0;
char temp = 'A'-1;
struct expr{
char operand1;
char operand2;
char operator;
char result;
};
%}

%union{
char symbol;
}

%left '+' '-'
%left '/' '*'

%token <symbol> LETTER NUMBER
%type <symbol> exp
%%

statement: LETTER '=' exp ';' {addtotable((char)$1,(char)$3,'=');};
exp: exp '+' exp {$$ = addtotable((char)$1,(char)$3,'+');}
    |exp '-' exp {$$ = addtotable((char)$1,(char)$3,'-');}
    |exp '/' exp {$$ = addtotable((char)$1,(char)$3,'/');}
    |exp '*' exp {$$ = addtotable((char)$1,(char)$3,'*');}
    |'(' exp ')' {$$= (char)$2;}
    |NUMBER {$$ = (char)$1;}
    |LETTER {(char)$1;};

%%

struct expr arr[20]; 

void yyerror(char *s){
    //printf("Error %s",s);
}

char addtotable(char a, char b, char o){
    temp++;
    arr[index1].operand1 =a;
    arr[index1].operand2 = b;
    arr[index1].operator = o;
    arr[index1].result=temp;
    index1++;
    return temp;
}

void threeAddressCode(){
    int i=0;
    char temp='A';
    while(i<index1){
        printf("%c := ",arr[i].result);
        printf("%c ",arr[i].operand1);
        printf("%c ",arr[i].operator);
        printf("%c ",arr[i].operand2);
        i++;
        temp++;
        printf("\n");
    }
}

int find(char l){
    int i;
    for(i=0;i<index1;i++)
        if(arr[i].result==l) break;
    return i;
}


int main(){
    printf("Enter the expression: ");
    yyparse();
    threeAddressCode();
    return 0;
}