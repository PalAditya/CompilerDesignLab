
# Solution

First of all, I defined a node type to hold the result as follows:

```C
%union {
int ival; //Useful to hold integer numbers
float fval;  //Useful for floating point numbers
char *sval;  //Strings, id, label, etc.
exprType *EXPRTYPE;  //The whole expression which is propagated upwards
}
```

Where exprtype itself is a struct with two fields, **address** and **code**.

The *modified* grammar: (Contains a subset of grammar in assignment 7) alongside SDD is given below. **Note**: This grammar requires semicolons to work, although that could have been omitted. Also, I used C style operators (&&, ||, etc.) although that can be easily changed in the lex file.

```bash
S:  prog {Final output; adds the final label at end for any exits like exit(0) from within code}
prog : prog construct
| construct
|list

construct : block
| WHILE ‘(‘ bool ‘)’ block { Backpatching happens here. We first search for the label TRUE and then replace it with a newlabel, and do the same thing to labels NEXT and FAIL. We keep doing this recursively}
| IF ‘( bool ’)’ block {Same backpatching as while}
| IF ‘(‘ bool ‘)’ block else block {Same backpatching as while}

block:  '{' list '}'
| ‘{‘ construct ‘}’

list:  stat
| list stat

stat:  ';'
| READ FORMAT stat {They simplay assign the variables to $$ and propagate values upwards}
| PRINT FORMAT stat
| PRINT text ‘;’
| expr ';'
| dec ‘;’
| dec ‘=’ expr ‘;’

dec: TYPES text

bool:  expr RELOP expr { We know this happens only for if/else statements or while statements, but as per our grammar, this is only for if/else. So first we add the code of both expression to a new node, then we generate the line “if {addr1} goto TRUE”, add a newline and then write “goto FAIL”  }
| bool OR bool {AND, OR, NOT is similar -> They first generate a newlabel, then they use the _strstr_ function inside C to find the label true (inserted when the expressions evaluate to true/false either directly or due to evaluation of itself) and replaces with the newlabel. This is like backpatching.)}
| bool AND bool
| NOT bool
| ‘(‘ bool ‘)
| TRUE {adds code “goto TRUE”}
| FALSE {adds code “goto FAIL”}

expr: expr ‘+’ expr // And so on, like, ‘-‘, ‘/’, etc. { gen(node); Add expr1’s address, arithmetic symbol and expr2’s address to node; Add their code, if any and return node. }

text: ID {return the string to propagate upwards}

number: FLOAT {convert floating value to string and return;} | DIGIT {convert integer value to string and return;}
```
**Example**: Let fragment be

```C
if (x<100)
{
print x;
}
```
Parser expands it as: *S -> prog -> construct -> block -> if ‘(‘ bool ‘)’ block -> if ‘(‘ expr RELOPT expr ‘)’ block -> if ‘(‘ expr RELOPT expr ‘)’ ‘{ list ‘}’ -> if ‘(‘ expr RELOPT expr ‘)’ ‘{ stat ‘}’ -> if ‘(‘ expr RELOPT expr ‘)’ ‘{ PRINT text ‘;’ ‘}’*

Now, the innermost _PRINT text;_ evaluates to _PRINT x_ (as text->id gives id=x)_._ This code is passed untouched to _stat, list_ and finally ‘block’ of _if ‘( bool ’)’ block_ as we can see by walking backwards in the tree

Then, _expr RELOP expr_ gives us, as per SDD: _if x < 100 goto TRUE \n goto FAIL_. There will be no code associated with x or 100, they will simply be propagated as x and 100 and their addresses will be added to the text.

Now, if ‘( bool ‘)’ block handles it as follows: It first searches for substring true and replaces it with new label, so we now have : *if x<100 goto L1 \n goto FAIL*

Next, it searches for FAIL and replaces it, so we get if x<100 goto L1 \n goto L2.

Now the code of _block_ is concatenated with it, so we get: if x<100 goto L1\n goto L2  T1=print x;

So, finally, output is:

_if x<100 goto L1  
goto L2  
L1: T1 = print x 
L2_

The code expects to read from the file input.txt in the same directory. The sample code is already written there (changed a little, like ‘:=’ to ‘=’) to suit this C-style grammar. Compile with **lex 3address.l & yacc -d 3address.y & gcc 3address.tab.c**. 

**Note**: The *basic* idea is very simple. We force all expressions to be evaluated first and add TRUE/FAIL/NEXT statements to it as we don’t know where it will go yet, and when everything is evaluated inside the if/else or while block, we use substrings to change those true/false to new labels.

**Output for input.txt:**

```
T1=y+5
x=T1
T3=x*2
z=T3
T5=x+z
a=T5
T7=PRINT abc
T8=READ %f zxc
L3 : L1 : if(awe>qwe) goto L2
goto L8
L2 : T9=PRINT qwe123
T10=qwe+1
jkl=T10
T12=qwe+2
qwe=T12
goto L1
L8 : if(awe!=qwe) goto L5
goto L4
L4 : T14=qwe+abc
if(T14>50) goto L5
goto L7
L5 : if(abc==10.234000) goto L6
goto L7
L6 : T15=PRINT POI123QWE
goto L9
L7 : T16=PRINT abc
T17=PRINT lkj
L9
```