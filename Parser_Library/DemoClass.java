import java.util.*;
import java.io.*;
public class DemoClass
{
    public static void main(String args[])
    {
        /**First demonstarate LR0*/
        /**Standard mode, to be used for quick checking*/
        LR0Parser obj=new LR0Parser();
        obj.read_grammar("LR0.txt");
        obj.buildDFA();
        System.out.println(obj.getParsingTable(false)?"Grammar is LR0 :)":"Grammar isn't LR0  :(");
        System.out.println(obj.parse("a c d",false)?"Successfully parsed":"Parse Failure");
        System.out.println(obj.parse("a c d k",false)?"Successfully parsed":"Parse Failure") ;
        /**Debug mode*/
        System.out.println(obj.states);
        obj.print_transitions();
        obj.getParsingTable(true);
        obj.parse("a c d",true);
        /**Now SLR*/
        SLRParser obj2=new SLRParser();
        obj2.read_grammar("LR0.txt");
        obj2.buildDFA();
        System.out.println(obj2.getParsingTable(false)?"Grammar is SLR :)":"Grammar isn't SLR  :(");
        System.out.println(obj2.parse("a c d",false)?"Successfully parsed":"Parse Failure");
        System.out.println(obj2.parse("a c d k",false)?"Successfully parsed":"Parse Failure");
        /**Debug mode*/
        System.out.println(obj2.states);
        obj2.print_transitions();
        obj2.getParsingTable(true);
        obj2.parse("a c d",true);
        /**Now LR1*/
        LR1Parser obj3=new LR1Parser();
        obj3.read_grammar("LR0.txt");
        obj3.buildDFA();        
        System.out.println(obj3.getParsingTable(false)?"Grammar is LR1 :)":"Grammar isn't LR1  :(");
        System.out.println(obj3.parse("a c d",false)?"Successfully parsed":"Parse Failure");
        System.out.println(obj3.parse("a c d k",false)?"Successfully parsed":"Parse Failure");
        /**Debug mode*/
        System.out.println(obj3.states);
        obj3.print_transitions();
        obj3.getParsingTable(true);
        obj3.parse("a c d",true);
        LALRParser obj4=new LALRParser();
        obj4.read_grammar("LR0.txt");
        obj4.buildDFA();        
        System.out.println(obj4.getParsingTable(false)?"Grammar is LALR :)":"Grammar isn't LALR :(");
        System.out.println(obj4.parse("a c d",false)?"Successfully parsed":"Parse Failure");
        System.out.println(obj4.parse("a c d k",false)?"Successfully parsed":"Parse Failure");
        /**Debug mode*/
        System.out.println(obj4.states);
        obj4.print_transitions();
        obj4.getParsingTable(true);
        obj4.parse("a c d",true);
    }
}