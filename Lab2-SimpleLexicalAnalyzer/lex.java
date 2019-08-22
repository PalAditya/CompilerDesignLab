import java.util.*;
import java.util.regex.*;
import java.io.*;
class LexicalAnalyzer
{
public static void main(String args[])throws IOException
{
LexicalAnalyzer obj=new LexicalAnalyzer();
obj.go();
}
void analyze(String str[])
{
int i,l=str.length;
for(i=0;i<l;i++)
{
if(str[i].equals("if"))
System.out.println("If: if");
else if(str[i].equals("then"))
System.out.println("Then: then");
else if(str[i].equals("else"))
System.out.println("Else: else");
else if(Pattern.matches("[0-9]",str[i]))
System.out.println("Digit: "+str[i]);
//else if(Pattern.matches("[0-9]+",str[i]))
//System.out.println("Digits: "+str[i]);
else if (Pattern.matches("([0-9]+[.][0-9]+)|[0-9]+",str[i]))
System.out.println("Number: "+str[i]);
else if (Pattern.matches("[a-zA-Z]",str[i]))
System.out.println("Letter: "+str[i]);
else if (Pattern.matches("[a-zA-z]([a-zA-Z]|[0-9])+",str[i]))
System.out.println("id: "+str[i]);
else if(Pattern.matches("<|>|<=|>=|=|<>",str[i]))
System.out.println("Relop: "+str[i]);
else
System.out.println("Unknown token "+str[i]+" :( ");
}
}
public void go()throws IOException
{
String str="";
BufferedReader br=new BufferedReader(new FileReader("a.txt"));
while((str=br.readLine())!=null)
{
analyze(str.split(" "));
}
}
}
