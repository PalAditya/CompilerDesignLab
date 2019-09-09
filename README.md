# CompilerDesignLab
Code for Compiler Design Lab, Semester 7  
Will contain **images** and **README** files for each assignment if possible.

The work is now available in the form of a library :smile:

A basic usage of the library is as easy as follows:

```java
LR1Parser obj=new LR1Parser();//Create an instance of the desired parser-In this case LR1 type parser
obj.read_grammar("D://Documents/Lab4/LR0.txt");//Read the grammar file
obj.buildDFA();//Build a dfa from the file
System.out.println(obj.states);//Prints the transitions in all states
obj.print_transitions();//Print all the transitions
obj.getParsingTable(true);//Use false to avoid printing the table after creation
obj.parse("a c e",true);//Parse the string; use false to prevent the stack actions from displaying on screen
```
Please move to [docs](Parser_Library/README.md) for a full understanding of the library.  

For **release** build:

```java
<dependency>
  <groupId>io.github.PalAditya</groupId>
  <artifactId>parser-library</artifactId>
  <version>0.1.1</version>
</dependency>
```

For **snapshot** build:

```java
<dependency>
  <groupId>io.github.PalAditya</groupId>
  <artifactId>parser-library</artifactId>
  <version>0.1-SNAPSHOT</version>
</dependency>
```

In case you want the pre-release (latest) versions, you can download it manually from [here](https://oss.sonatype.org/content/repositories/central_bundles-18962/io/github/PalAditya/parser-library/0.1.1/)  

**Note**: This is not being maintained anymore. Please see [here](https://github.com/PalAditya/JParser) to get the latest version of the code.
