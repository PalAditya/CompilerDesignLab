# Solution
We start by defining an AST node. In C style, it will be:
```c
typedef struct node{
	  int isOp; //Decides if it is an operator or not
	  int val; //0 for addition, 1 for subtraction, 2 for multiplication and 3 for division, //however we consider it only if isOp = 1, else it holds the number
	  struct node *left; //The left child node
	  struct node *right; //The right child node
  }node;
```
The grammar is then simply:
```
Start:  E -> E + T {E.val = new PlusNode(E.val, T.val) }
Here, by PlusNode we refer to creating a node with isOp set to 1 (true) and type set to 0. Similarly, MinusNode, MultNode and DivNode will have isOp = 1 and type = 1, 2 and 3 respectively
| E -> E – T { E.val = new MinusNode(E.val, T.val) }
| T { E.val = T.val}
 T -> T * F {T.val = new MultNode(T.val, F.val)}
    | T / F {T.val = new DivNode(T.val, F.val)}  
    | F  {T.val=F.val} 
 F -> NUMBER {F.val = new ValNode}
For valnode, left and right are NULL as AST stops at numbers. Also, isOp = 0 (false) so type holds the actual value of the number 
 | (NUMBER) {F.val = number} //That is, the actual numerical value
  Using this, we write a simple preorder traversal of tree as follows:
```
## How does this work?
Note, for each semantic action, we are creating an AST node with symbol as root and expressions as subtrees. With that in mind, we get:
Let expression to parse = ```5 + 6 * 7``` 
**Parser actions**: ```Start -> E -> E + T -> E + T * F -> T + T * T -> F + F * F```
## AST construction:
We walk backwards. All the literals ‘F’ create the **ASTNode** *ValNode*, and then ```T + T * T``` gets *PlusNode* and *MultNode* respectively by their semantic actions. Also, *PlusNode* is processed earlier, followed by *MultNode* as is the order in the syntax tree.
**Output**: `1+ 5 * 6 7`

The 3 address code construction is much simpler. The grammar here is:
```
Start -> Letter ‘=’ Expression, i.e of the form a = b op c op … op z. The associated semantic action is {$$=addToTable($1, $3, ‘=’)} 
Now, Expression (E) is defined as: 
E -> E + E {$$=addToTable($1, $3, ‘+’)}
| E – E {$$=addToTable($1, $3, ‘-’)}
| E * E {$$=addToTable($1, $3, ‘*’)}
| E / E {$$=addToTable($1, $3, ‘/’)}
| ( E ) {$$=$2}
|NUMBER {$$ = (char)$1;} 
|LETTER {(char)$1;}; *//That is, numbers and operators are returned unchanged*
```
Now, this is written in pure YACC style, but here all $$ is referring to is the result (LHS), while $1, $2…$n refers to the RHS tokens, in order. So, what is happening is the following: Since in 3-address-code we can only have 3 operators in RHS at maximum, everytime we find that, we add that subcomponent to a table (done by function addToTable()) and return their result ($$) as a new token to be processed further and to be consumed by other nodes.

Let expression to parse: `a = 5+6*7` 
*Parser actions*: `Start -> Letter = E -> a = E -> a = E + E -> a = E + E * E -> a = NUMBER + NUMBER * NUMBER`

- In step 1, we insert (‘Letter’, ‘Expression’, ‘=’) in table.
- In step 2, we do E -> E + E, so table gets entry (‘E’, ’E’, ‘+’) and returned result is ‘B’
- So in step 3, we have E -> A * E, which causes entry (‘A’, ‘E’, ‘*’) to be inserted in table and returned result is ‘C’. 
- However, we must remember that these semantic actions can only be performed after the parsing is complete (for LR grammars), o the ‘E’ and ‘A’ are now filled with the return values after their expansion into terminal token NUMBER. So, the table actually has:
```
A = 6 * 7
B = 5 + A // Since the * nodes are expanded at a later stage, they come earlier when recursion backtracking starts
```
**Note**: Compilation
 - lex ast.l & yacc -d ast.y & gcc ast.tab.c  
-  lex 3address.l & yacc -d 3address.y & gcc lex.yy.c 3address.tab.c 

Replace ast.tab.c and 3address.tab.c with y.tab.c on linux 😁 

**Note 2**:

A brilliant astParser implementation in Python and graphviz can be found [here](https://github.com/rspivak/lsbasi/tree/master/part7/python)
