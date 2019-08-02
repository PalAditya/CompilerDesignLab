/*%{ 
	#include<string.h>
    #include<stdio.h>
	void onemove();
	void zeromove();
	void check();
	int state=0;
%}   
%% 
[0] {zeromove();}
[1] {onemove();}
["\n"] {check();}
%% 
int yywrap(){}  
void onemove()
{
	if(state==0)
	state=1;
	else if(state==1)
	state=0;
	else if(state==2)
	state=2;
}
void zeromove()
{
	if(state==0)
	state=0;
	else if(state==1)
	state=2;
	else if(state==2)
	state=1;
}
int main(int argc, char*argv[]) 
{ 
	yylex(); 
	return 0; 
} 
void check()
{
	if(state==0)
	{
		printf("Divisible by 3\n");
	}
	else
	{
		printf("Not divisible by 3\n");
	}
	state=0;
	//exit(0);
}*/
%{ 
	#include<stdio.h>
%}   
%% 
(0*|0*110*|0*101*010*)* {printf("Okay");}
.* {printf("Not okay");}
%% 
int yywrap(){}  
int main(int argc, char*argv[]) 
{ 
	yylex(); 
	return 0; 
} 
