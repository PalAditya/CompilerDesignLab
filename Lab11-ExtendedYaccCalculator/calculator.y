%code requires
{
    #define YYSTYPE double
}
%{ 
   /* Definition section */
  #include<stdio.h>
  #include<math.h>
  int flag=0; 
  void yyerror(char *s);
%} 
  
%token NUMBER POWER SINE NUMBER_F COSINE TANGENT LOGARITHM SQ_ROOT COTANGENT SECANT COSECANT

%nonassoc SINE COSINE TANGENT LOGARITHM SQ_ROOT COTANGENT SECANT COSECANT 
  
%left '+' '-'
  
%left '*' '/' '%'

%nonassoc POWER

%left '(' ')' '[' ']'
  
/* Rule Section */
%% 
  
ArithmeticExpression: 
E{ 
    printf("\nResult=%lf\n",$$);
    return 0; 
}; 
 E:E'+'E {$$=$1+$3;} 
  
 |E'-'E {$$=$1-$3;} 
  
 |E'*'E {$$=$1*$3;} 
  
 |E'/'E {$$=$1/$3;} 
 
 |E'%'E {$$=(int)$1%(int)$3;}
 
 | E POWER E {$$=pow($1,$3);}
 
 |'('E')' {$$=$2;}
 
 | SINE'['E']' {$$=sin($3);}
 
 | COSECANT'['E']' {$$=(1.0/sin($3));}
 
 | COSINE'['E']' {$$=cos($3);}
 
 | TANGENT'['E']' {$$=tan($3);}
 
 | COTANGENT'['E']' {$$=1.0/tan($3);}
 
 | SECANT'['E']' {$$=1.0/cos($3);}
 
 | LOGARITHM'['E']' {$$=log10($3);}
 
 | SQ_ROOT'['E']' {$$=sqrt($3);}
  
 | NUMBER {$$=$1;} 
 
 | NUMBER_F {$$=$1;}
 ; 
  
%% 
  
//driver code 
void main() 
{ 
   printf("Enter Any Arithmetic Expression. Supported operations are in README.docx\n"); 
   yyparse(); 
   if(flag==0) 
   printf("Entered arithmetic expression is Valid\n"); 
} 
  
void yyerror(char *s) 
{ 
   printf("Entered arithmetic expression is Invalid\n"); 
   flag=1; 
} 