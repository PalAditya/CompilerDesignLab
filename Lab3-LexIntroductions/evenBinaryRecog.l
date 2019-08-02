%{ 
    #include<stdio.h>
%}   
%% 
(1|(01*01*))* {printf("Okay");}
.* {printf("Not okay");}
%% 
int yywrap(){}  
int main(int argc, char*argv[]) 
{ 
	yylex(); 
	return 0; 
} 
