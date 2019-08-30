# YACC: Yet Another Compiler-Compiler
The relevant files are  *parse.l* and *parse.y* . *parse_test.l* is there for helping debug the tokenizing process of the input grammar.  
Both *parse_test.l* and *parse.y* expect to read input from a file called **a.txt**, placed in the same directory. So, either write your own programs there, or copy/paste the sample programs given in the *sample_inputs* directory to test (only input1.txt is valid)  
Compile in the following order:  
1. yacc parse.y -d  
2. lex parse.l  
3. cc (or gcc) parse.tab.c  
4. a.exe  

(Note: In Linux, the file would most likely be called y.tab.c, and in tha case, change the #include in parse.l to #include "y.tab.h")
The screenshot for a run using the three sample input files, in-order, is attached.  