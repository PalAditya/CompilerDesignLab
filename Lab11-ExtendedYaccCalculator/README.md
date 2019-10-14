# Assignment 11

The operations that are now supported are all the trigonometric functions, logarithm (to base 10) and square root. 

All extended operations should be wrapped in square brackets and for normal precedence, use standard round brackets.

Compile with ```lex calculator.l & yacc -d calculator.y & gcc lex.yy.c calculator.tab.c``` (Use y.tab.c for *Linux/Mac*

Example I/O:
1)	2 + 3 = 5.000000
2)	9 * sin [2] = 8.183677
3)	1 + 2 + 3 * tan [20] - (8 * 7) = -46.288517

![image](https://user-images.githubusercontent.com/25523604/66759874-a91da500-eebe-11e9-8307-cd0a22bd6c70.png)
