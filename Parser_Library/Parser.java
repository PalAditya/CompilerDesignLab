import java.util.*;
import java.io.*;
public class Parser
{
    HashMap<String,ArrayList<ArrayList<String>>> rules;
    HashSet<String> terminals;
    HashSet<String> nonTerminals;
    ArrayList<String> allSymbols;
    ArrayList<String> table[][];
    String startSymbol;
    AugmentedFirstAndFollow utils;
    ArrayList<DFA> states;
    DFA dfa;
    int minId;
    static class Pair
    {
        String rule;
        int dot;
        Pair(String c,int d)
        {
            rule=c;
            dot=d;
        }
        @Override
        public String toString()
        {
            int l=rule.indexOf("->"),i;
            String left=rule.substring(0,l).trim();
            String right=rule.substring(l+2).trim();
            StringBuilder sb=new StringBuilder();
            sb.append(left+" -> ");
            String tokens[]=right.split(" ");
            for(i=0;i<tokens.length;i++)
            {               
                if(tokens[i].equals(","))
                {
                    sb.append(": ");
                    continue;
                }
                if(i==dot)
                    sb.append(". ");
                sb.append(tokens[i]+" ");
            }
            if(i==dot)
                sb.append(". ");
            return sb.toString();
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + rule.hashCode();
            result = prime * result + dot;
            return result;
        }       
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pair other = (Pair) obj;
            if (dot!= other.dot)
                return false;
            if (!rule.equals(other.rule))
                return false;
            return true;
        }
    }
    static class DFA
    {
        HashSet<Pair> rules;
        int id;
        HashMap<String, DFA> transitions;
        DFA()
        {
            rules=new HashSet<>();
            id=-1;
            transitions=new HashMap<>();
        }
        @Override
        public String toString()
        {
            return "Id: "+id+" , Rules: "+rules+"\n";//Mapping: "+transitions+"\n\n";
        }
    }
    Parser()
    {
        rules=new HashMap<>();
        startSymbol="";
        terminals=new HashSet<>();
        nonTerminals=new HashSet<>();
        allSymbols=new ArrayList<>();
        minId=-1;
        dfa=new DFA();
        utils=new AugmentedFirstAndFollow();
        states=new ArrayList<>();
    }
    protected int getId()
    {
        return ++minId;
    }
    void modify(AugmentedFirstAndFollow utils)
    {
        utils.ntCount.clear();
        utils.ntCount.addAll(nonTerminals);
        utils.tCount.clear();
        utils.tCount.addAll(terminals);
        utils.tCount.add("$");
        utils.ntCount.add(startSymbol+"'");
    }
    public void read_grammar(String filePath)
    {
        String str="";
        int line=0;
        try
        {
            BufferedReader br=new BufferedReader(new FileReader(filePath));
            str=br.readLine();
            String _terminals[]=str.split(" ");
            for(int i=0;i<_terminals.length;i++)
                terminals.add(_terminals[i]);
            str=br.readLine();
            String _nonTerminals[]=str.split(" ");
            for(int i=0;i<_nonTerminals.length;i++)
                nonTerminals.add(_nonTerminals[i]);
            allSymbols.addAll(terminals);
            allSymbols.addAll(nonTerminals);
            while((str=br.readLine())!=null)//Read the string, put in map
            {
                int l=str.indexOf("->"),i,j;
                String left=str.substring(0,l).trim();
                String right=str.substring(l+2).trim();
                l=right.length();
                String tokens[]=right.split("[|]");
                if(line==0)
                {
                    startSymbol=left;
                }
                line++;
                ArrayList<ArrayList<String>> al=new ArrayList<>();
                for(i=0;i<tokens.length;i++)
                {
                    String s[]=tokens[i].trim().split(" ");
                    ArrayList<String> temp=new ArrayList<>();
                    for(j=0;j<s.length;j++)
                        temp.add(s[j]);
                    al.add(temp);
                }
                rules.put(left,al);//Put all the rules in the map
                line++;
            }
            br.close();
            augment();
            modify(utils);
            unAugment();
            utils.module1(filePath,true,false);
        }catch(Exception e)
        {
            System.out.println("Couldn't read grammar file: "+e.getMessage());
            System.exit(0);
        }
    }
    public String join(ArrayList<String> v, String delim) 
    {
        if(v==null||v.size()==0)
            return "";
        StringBuilder ss=new StringBuilder();
        for(int i = 0; i < v.size(); ++i)
        {
            if(i != 0)
                ss.append(delim);
            ss.append(v.get(i));
        }
        return ss.toString();
    }
    public void getClosure(HashSet<Pair> closure)
    {
        boolean done=false;
        while(!done)
        {
            done=true;
            Iterator iterator=closure.iterator();
            HashSet<Pair> addAble=new HashSet<>();
            while(iterator.hasNext())
            {
                Pair pair=(Pair)iterator.next();
                int l=pair.rule.indexOf("->"),i;
                String left=pair.rule.substring(0,l).trim();
                String right=pair.rule.substring(l+2).trim();
                String tokens[]=right.split(" ");
                if(pair.dot>=tokens.length||pair.dot<0)
                    continue;
                else if(nonTerminals.contains(tokens[pair.dot]))
                {
                    ArrayList<ArrayList<String>> al=rules.get(tokens[pair.dot]);
                    for(i=0;i<al.size();i++)
                    {
                        String str=join(al.get(i)," ");
                        Pair p=new Pair(tokens[pair.dot]+" -> "+str,0);
                        if(!closure.contains(p))
                        {
                            done=false;
                            addAble.add(new Pair(tokens[pair.dot]+" -> "+str.trim(),0));
                        }
                    }
                }
            }
            closure.addAll(addAble);
        }
    }
    public HashSet<Pair> getGoto(HashSet<Pair> X, String I)
    {
        HashSet<Pair> goTo=new HashSet<>();
        HashSet<Pair> add=new HashSet<>();
        for(Pair p: X)
        {
            String str[]=p.rule.substring(p.rule.indexOf("->")+2).trim().split(" ");
            if(p.dot>=str.length||p.dot<0)
                continue;
            if(str[p.dot].equals(I))
                goTo.add(new Pair(p.rule,p.dot+1));
        }
        for(Pair p:goTo)
        {
            HashSet<Pair> temp=new HashSet<>();
            temp.add(p);
            getClosure(temp);
            add.addAll(temp);
        }
        goTo.addAll(add);
        return goTo;
    }
    public void augment()
    {
        ArrayList<ArrayList<String>> al=new ArrayList<>();
        ArrayList<String> al2=new ArrayList<>();
        al2.add(startSymbol);
        al.add(al2);
        rules.put(startSymbol+"'",al);
    }
    public void unAugment()
    {
        rules.remove(startSymbol+"'");
    }
    public boolean parse(String _toParse, boolean output)
    {
        _toParse=_toParse+" $";
        Stack<String> stack=new Stack<>();
        ArrayList<ArrayList<String>> al=new ArrayList<>();
        ArrayList<String> a=new ArrayList<>();
        a.add("Step");
        a.add("Stack");
        a.add("Action");
        a.add("Input");
        al.add(a);
        stack.push("$");
        stack.push("0");
        int pointer=0,i,step=0;
        String toParse[]=_toParse.split(" ");
        while(!stack.empty()&&pointer<toParse.length)
        {
            a=new ArrayList<>();
            int row=Integer.parseInt(stack.peek());
            int col=allSymbols.indexOf(toParse[pointer]);
            a.add(step+"");
            a.add(stack+"");
            if(col<0)
            {
                a.add("Parse error");
                al.add(a);
                if(output)
                    pretty_it(al);
                break;
            }
            if(table[row][col].size()==0)
            {
                a.add("Parse error");
                al.add(a);
                if(output)
                    pretty_it(al);
                break;
            }
            String action=table[row][col].get(0);
            a.add(action);
            String str[]=action.split(" ");
            String left=str[0].trim();
            String right="";
            for(i=1;i<str.length;i++)
                right+=str[i]+" ";
            right=right.trim();
            if(left.equals("Shift"))
            {
                stack.push(toParse[pointer]);
                stack.push(right);
                pointer++;
            }
            else if(left.equals("Reduce"))
            {
                left=right.substring(0,right.indexOf("->")).trim();
                right=right.substring(right.indexOf("->")+2).trim();
                if(right.charAt(0)!='@')
                {
                    for(i=0;i<2*(str.length-3);i++)
                    {
                        if(stack.size()!=0)
                            stack.pop();
                        else
                        {
                            a.add("Parse error");
                            al.add(a);
                            if(output)
                                pretty_it(al);
                            return false;
                        }
                    }
                }
                int top=Integer.parseInt(stack.peek());
                stack.push(left);                
                stack.push(table[top][allSymbols.indexOf(left)].get(0));
            }
            else if(left.equals("Accept")&&pointer==toParse.length-1)
            {
                String ppp="";
                for(i=pointer;i<toParse.length;i++)
                    ppp+=toParse[i]+" ";
                a.add(ppp);
                al.add(a);
                if(output)
                {
                    pretty_it(al);
                    System.out.println("Woohoo, accepted :) ");
                }
                return true;
            }
            String pp="";
            for(i=pointer;i<toParse.length;i++)
                pp+=toParse[i]+" ";
            a.add(pp);
            al.add(a);
            step++;
        }
        return false;
    }
    protected void pretty_it(ArrayList<ArrayList<String>> al)
    {
        int n=al.size(),i,j;
        String t[][]=new String[n][4];
        for(i=0;i<n;i++)
        {
            for(j=0;j<4;j++)
            {
                if(al.get(i).size()==3&&j==3)
                    t[i][j]="Parse Error";
                else
                    t[i][j]=al.get(i).get(j);
            }
        }
        PrettyPrinter printer = new PrettyPrinter(System.out);
        printer.print(t);
    }
    protected int getIndex(HashSet<Pair> Goto)
    {
        int i=0;
        for(DFA dfa: states)
        {
            if((dfa.rules.containsAll(Goto)&&Goto.containsAll(dfa.rules)))
                return i;
            i++;
        }
        return -1;
    }
    protected void print_transitions()
    {
        for(DFA dfa: states)
        {
            System.out.println("Map for state: "+dfa);
            System.out.println("Transitions: ");
            for(Map.Entry<String, DFA> mp:dfa.transitions.entrySet())
            {
                System.out.println(mp.getKey()+"->"+mp.getValue().rules+" ( S"+getIndex(mp.getValue().rules)+" )");
            }
            System.out.println();
        }
    }
}