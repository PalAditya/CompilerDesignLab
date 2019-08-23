import java.util.*;
import java.io.*;
class LR_parser
{
    HashMap<String,ArrayList<ArrayList<String>>> rules;
    HashSet<String> terminals;
    HashSet<String> nonTerminals;
    ArrayList<String> allSymbols;
    ArrayList<String> table[][];
    String startSymbol;
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
    LR_parser()
    {
        rules=new HashMap<>();
        startSymbol="";
        terminals=new HashSet<>();
        terminals.add("(");
        terminals.add("+");
        terminals.add("*");
        terminals.add(")");
        terminals.add("id");
        //terminals.add("=");
        terminals.add("/");
        terminals.add("-");
        nonTerminals=new HashSet<>();
        nonTerminals.add("E");
        nonTerminals.add("T");
        nonTerminals.add("F");
        allSymbols=new ArrayList<>();
        allSymbols.addAll(nonTerminals);
        allSymbols.addAll(terminals);
        minId=-1;
        dfa=new DFA();
    }
    private int getId()
    {
        return ++minId;
    }
    static void modify(AugmentedFirstAndFollow utils,LR_parser obj)
    {
        utils.ntCount.clear();
        utils.ntCount.addAll(obj.nonTerminals);
        utils.tCount.clear();
        utils.tCount.addAll(obj.terminals);
        utils.tCount.add("$");
        utils.ntCount.add(obj.startSymbol+"'");
    }
    public static void main(String args[])throws IOException
    {
        LR_parser obj=new LR_parser();
        obj.read_grammar("Grammar.txt");
        AugmentedFirstAndFollow utils=new AugmentedFirstAndFollow();
        obj.augment();
        modify(utils,obj);
        utils.module1("Grammar.txt");
        obj.unAugment();
        HashSet<Pair> hs=new HashSet<>();
        HashSet<Pair> closure=new HashSet<>();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        HashSet<Pair> goTo=new HashSet<>();
        /*goTo.add(new Pair("E -> T",0));
        goTo.add(new Pair("E -> T * F",0));
        System.out.println(obj.getGoto(goTo,"T"));
        goTo.clear();
        goTo.add(new Pair("F -> ( E )",0));
        System.out.println(obj.getGoto(goTo,"("));
        goTo.clear();
        goTo.add(new Pair("F -> ( E )",0));
        System.out.println(obj.getGoto(goTo,"id"));*/
        ArrayList<DFA> states=obj.buildDFA();
        obj.getParsingTable(states,utils);
        obj.parse("id $");
        /*for(;;)
        {
            closure.clear();
            String str=br.readLine();
            int k=Integer.parseInt(br.readLine());
            if(str.equals("1"))
                break;
            closure.add(new Pair(str,k));
            obj.getClosure(closure);
            System.out.println(closure);
        }*/
    }
    public void read_grammar(String filePath)
    {
        String str="";
        int line=0;
        try
        {
            BufferedReader br=new BufferedReader(new FileReader(filePath));
            while((str=br.readLine())!=null)//Read the string, put in map
            {
                int l=str.indexOf("->"),i,j;
                String left=str.substring(0,l).trim();
                String right=str.substring(l+2).trim();
                l=right.length();
                String tokens[]=right.split("[|]");
                //System.out.println("Tokens: "+Arrays.toString(tokens));
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
        }catch(Exception e)
        {
            System.out.println("Parse failure: "+e.getMessage());
            System.exit(0);
        }
    }
    String join(ArrayList<String> v, String delim) 
    {
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
                //System.out.println(pair.rule);
                int l=pair.rule.indexOf("->"),i;
                //System.out.println(l);
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
                        //System.out.println(p);
                        if(!closure.contains(p))
                        {
                            //System.out.println("Inside: "+p);
                            done=false;
                            addAble.add(new Pair(tokens[pair.dot]+" -> "+str.trim(),0));
                        }
                    }
                }
            }
            /*System.out.println(closure+"\n"+addAble);
            try
            {
                Thread.sleep(5000);
            }catch(Exception e)
            {
            }*/
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
            //System.out.println(Arrays.toString(str));
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
    public ArrayList<DFA> buildDFA()
    {
        augment();
        dfa.id=getId();
        for(Map.Entry<String, ArrayList<ArrayList<String>>> mp: rules.entrySet())
        {
            for(ArrayList<String> al: mp.getValue())
            {
                dfa.rules.add(new Pair(mp.getKey()+" -> "+join(al," "),0));
            }
        }
        //System.out.println(dfa);
        ArrayList<DFA> states=new ArrayList<>();
        states.add(dfa);
        int i=0;
        boolean done=false;
        while(!done)
        {
            done=true;
            for(i=0;i<states.size();i++)
            {
                DFA current=states.get(i);
                for(String str: allSymbols)//All symbol iteration
                {
                    HashSet<Pair> Goto=getGoto(current.rules,str);
                    int index=getIndex(Goto,states);
                    if(index>=0)
                    {
                        //System.out.println("Old");
                        current.transitions.put(str,states.get(index));
                    }
                    else
                    {
                        //System.out.println("New");
                        DFA new_DFA=new DFA();
                        new_DFA.id=getId();
                        new_DFA.rules=Goto;
                        states.add(new_DFA);
                        current.transitions.put(str,new_DFA);
                        done=false;
                    }
                }
            }
            //break;
        }
        System.out.println(states);
        print_transitions(states);
        unAugment();
        return states;
    }
    public void getParsingTable(ArrayList<DFA> states,AugmentedFirstAndFollow utils)
    {
        terminals.add("$");
        table=new ArrayList[states.size()][terminals.size()+nonTerminals.size()];
        int i,j;
        for(i=0;i<states.size();i++)
            for(j=0;j<terminals.size()+nonTerminals.size();j++)
                table[i][j]=new ArrayList<>();
        ArrayList<String> colMap=new ArrayList<>();
        colMap.addAll(terminals);
        colMap.addAll(nonTerminals);
        allSymbols.clear();
        allSymbols.addAll(colMap);
        System.out.println(colMap);
        System.out.println(allSymbols);
        int row=0;
        boolean isLR=true;
        for(DFA dfa: states)
        {
            for(Pair p:dfa.rules)
            {
                String str[]=p.rule.substring(p.rule.indexOf("->")+2).trim().split(" ");
                String left=p.rule.substring(0,p.rule.indexOf("->")).trim();
                if(p.dot<0||p.dot>str.length)
                    continue;
                if(left.equals(startSymbol+"'")&&p.dot==str.length)
                {
                    table[row][colMap.indexOf("$")].add("Accept :)");
                }
                else if(p.dot==str.length)
                {
                    ArrayList<String> al=utils._follow.get(left);
                    for(String s:al)
                    {
                        table[row][colMap.indexOf(s)].add("Reduce "+p.rule);
                    }
                }
                else if(terminals.contains(str[p.dot]))
                {
                    HashSet<Pair> hs=getGoto(dfa.rules,str[p.dot]);
                    int index=getIndex(hs,states);
                    if(index>=0)
                    {
                        table[row][colMap.indexOf(str[p.dot].trim())].add("Shift "+index);
                    }
                }
            }
            for(String ss:nonTerminals)
            {
                HashSet<Pair> hs=getGoto(dfa.rules,ss);
                int index=getIndex(hs,states);
                if(index>=0&&states.get(index).rules.size()!=0)
                {
                    table[row][colMap.indexOf(ss)].add(index+"");
                }
            }
            row++;
        }
        ArrayList<String> pretty[][]=new ArrayList[table.length+1][table[0].length+1];
        for(i=0;i<pretty.length;i++)
        {
            for(j=0;j<pretty[0].length;j++)
            {
                pretty[i][j]=new ArrayList<>();
                /*if(i==0)
                System.out.print(tMap.get(j)+"\t\t");*/
            }
        }
        pretty[0][0].add("State");
        for(i=1;i<pretty[0].length;i++)
        {
            pretty[0][i].add(colMap.get(i-1)+"");
        }
        for(i=1;i<pretty.length;i++)
        {
            pretty[i][0].add((i-1)+"");
        }
        for(i=1;i<pretty.length;i++)
        {
            for(j=1;j<pretty[0].length;j++)
            {
                if(table[i-1][j-1].size()>1)
                    isLR=false;
                pretty[i][j].addAll(table[i-1][j-1]);
            }
        }
        PrettyPrinter printer = new PrettyPrinter(System.out);
        printer.convert(pretty);
        terminals.remove("$");
        if(isLR)
            System.out.println("Grammar is LR :)");
        else
            System.out.println("Grammar isn't LR :(");
    }
    public void parse(String _toParse)
    {
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
        //System.out.println("Step:\t\tStack\t\t\t\t\tAction\t\tInput");
        while(!stack.empty()&&pointer<toParse.length)
        {
            a=new ArrayList<>();
            int row=Integer.parseInt(stack.peek());
            int col=allSymbols.indexOf(toParse[pointer]);
            a.add(step+"");
            a.add(stack+"");
            //System.out.print(step+"\t\t"+stack+"\t\t\t\t\t");
            if(table[row][col].size()==0)
            {
                a.add("Parse error");
                al.add(a);
                pretty_it(al);
                System.exit(0);
                break;
            }
            String action=table[row][col].get(0);
            a.add(action);
            //System.out.print(action+"\t\t");
            String str[]=action.split(" ");
            String left=str[0].trim();
            String right="";
            for(i=1;i<str.length;i++)
                right+=str[i]+" ";
            right=right.trim();
            if(left.equals("Shift"))
            {
                //stack.pop();
                stack.push(toParse[pointer]);
                stack.push(right);
                pointer++;
            }
            else if(left.equals("Reduce"))
            {
                left=right.substring(0,right.indexOf("->")).trim();
                right=right.substring(right.indexOf("->")+2).trim();
                for(i=0;i<2*(str.length-3);i++)
                {
                    if(stack.size()!=0)
                        stack.pop();
                    else
                    {
                        a.add("Parse error");
                        al.add(a);
                        pretty_it(al);
                        //System.out.println("Parse error");
                        System.exit(0);
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
                //System.out.println(ppp+"\t\t");
                a.add(ppp);
                al.add(a);
                pretty_it(al);
                System.out.println("Woohoo, accepted :) ");
                System.exit(0);
            }
            String pp="";
            for(i=pointer;i<toParse.length;i++)
                pp+=toParse[i]+" ";
            //System.out.println(pp+"\t\t");
            a.add(pp);
            al.add(a);
            step++;
        }
    }
    private void pretty_it(ArrayList<ArrayList<String>> al)
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
    private int getIndex(HashSet<Pair> Goto,ArrayList<DFA> states)
    {
        int i=0;
        for(DFA dfa: states)
        {
            if(dfa.rules.containsAll(Goto)&&Goto.containsAll(dfa.rules))
                return i;
            i++;
        }
        return -1;
    }
    private void print_transitions(ArrayList<DFA> states)
    {
        for(DFA dfa: states)
        {
            System.out.println("Map for state: "+dfa);
            System.out.println("Transitions: ");
            for(Map.Entry<String, DFA> mp:dfa.transitions.entrySet())
            {
                System.out.println(mp.getKey()+"->"+mp.getValue().rules+" ( S"+getIndex(mp.getValue().rules,states)+" )");
            }
            System.out.println();
        }
    }
}