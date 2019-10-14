%{ 
   /* Definition section */
  #include<stdio.h>
  int flag=0; 
%} 
  
%token NUMBER 
  
%left '+' '-'
  
%left '*' '/' '%'
  
/* Rule Section */
%% 
  
ArithmeticExpression: 
E{ 
printf("\nResult=%d\n", $$); 
return 0; 
}; 
 E:E'+'E {$$=$1+$3;} 
  
 |E'-'E {$$=$1-$3;} 
  
 |E'*'E {$$=$1*$3;} 
  
 |E'/'E {$$=$1/$3;} 
  
 | NUMBER {$$=$1;} 
  
 ; 
  
%% 
  
//driver code 
void main() 
{ 
   printf("Enter Any Arithmetic Expression with +,-,* and / only\n"); 
   yyparse(); 
   if(flag==0) 
   printf("Entered arithmetic expression is Valid\n"); 
} 
  
void yyerror() 
{ 
   printf("Entered arithmetic expression is Invalid\n"); 
   flag=1; 
} 