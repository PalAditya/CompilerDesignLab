# Parsing  
  
**Q1:** It involves the following algorithm:  
1. Arrange the non-terminal symbols in order: A<sub>1</sub>, A<sub>2</sub>, A<sub>3</sub>, ..., A<sub>n</sub>  
2. For i=1 to n do:  
&emspFor j=1 to i-1 do:  
&emsp&emsp*I)* replace each production of the form A<sub>i</sub>->A<sub>j</sub>Y with the productions:  
&emsp&emspA<sub>i</sub>->$<sub>1</sub>Y|$<sub>2</sub>Y|...|$<sub>k</sub>Y  
&emsp&emspwhere A<sub>j</sub>->$<sub>1</sub>|$<sub>2</sub>|...|$<sub>k</sub>  
&emsp&emspare all the current A<sub>j</sub> productions. 
&emsp&emsp*II)* Eliminate the immediate left recursion among the A<sub>i</sub>  
