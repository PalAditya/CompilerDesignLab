import java.util.*;
import java.io.*;
public class LR1Parser extends Parser
{
    LR1Parser()
    {
        super();
    }
    public void buildDFA()
    {
        augment();
        dfa.id=getId();
        dfa.rules.add(new Pair(startSymbol+"' -> "+startSymbol+" , $",0));
        getLR1Closure(dfa.rules);
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
                    HashSet<Pair> Goto=getLR1Goto(current.rules,str);
                    int index=getIndex(Goto);
                    if(index>=0)
                    {
                        current.transitions.put(str,states.get(index));
                    }
                    else
                    {
                        DFA new_DFA=new DFA();
                        new_DFA.id=getId();
                        new_DFA.rules=Goto;
                        states.add(new_DFA);
                        current.transitions.put(str,new_DFA);
                        done=false;
                    }
                }
            }
        }
        unAugment();
        minId=-1;
    }
    public boolean getParsingTable(boolean output)
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
        int row=0;
        boolean isLR1=true;
        for(DFA dfa: states)
        {
            for(Pair p:dfa.rules)
            {
                int l=p.rule.indexOf("->");
                int l2=p.rule.indexOf(",");
                String left=p.rule.substring(0,l).trim();
                String mid=p.rule.substring(l+2,l2).trim();
                String right=p.rule.substring(l2+2).trim();
                String str[]=mid.split(" ");
                if(p.dot<0||p.dot>str.length)
                    continue;
                if(left.equals(startSymbol+"'")&&p.dot==str.length)
                {
                    table[row][colMap.indexOf("$")].add("Accept :)");
                }
                else if((p.dot==str.length)||(p.dot==0&&str[0].equals("@")))//Condition 2 for epsilon only
                {
                    String G[]=right.split("[|]");
                    for(String g:G)
                        table[row][colMap.indexOf(g.trim())].add("Reduce "+left+" -> "+mid);
                }
                else if(terminals.contains(str[p.dot]))
                {
                    HashSet<Pair> hs=getLR1Goto(dfa.rules,str[p.dot]);
                    int index=getIndex(hs);
                    if(index>=0)
                    {
                        boolean add=true;
                        for(String s: table[row][colMap.indexOf(str[p.dot].trim())])
                        {
                            if(s.equals("Shift "+index))
                               add=false;
                        }
                        if(add)
                            table[row][colMap.indexOf(str[p.dot].trim())].add("Shift "+index);
                    }
                }
            }
            for(String ss:nonTerminals)
            {
                HashSet<Pair> hs=getLR1Goto(dfa.rules,ss);
                int index=getIndex(hs);
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
                    isLR1=false;
                pretty[i][j].addAll(table[i-1][j-1]);
            }
        }
        if(output)
        {
            PrettyPrinter printer = new PrettyPrinter(System.out);
            printer.convert(pretty);
        }
        terminals.remove("$");
        if(output)
        {
            if(isLR1)
                System.out.println("Grammar is LR(1) :)");
            else
                System.out.println("Grammar isn't LR(1) :(");
        }
        return isLR1;
    }
    private void getLR1Closure(HashSet<Pair> closure)
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
                int l2=pair.rule.indexOf(",");
                String left=pair.rule.substring(0,l).trim();
                String mid=pair.rule.substring(l+2,l2).trim();
                String right=pair.rule.substring(l2+2).trim();
                String tokens[]=mid.split(" ");
                String newRight="";
                if(pair.dot>=tokens.length-1)
                {
                    newRight=right;
                }
                else
                {
                    if(terminals.contains(tokens[pair.dot+1]))
                        newRight=tokens[pair.dot+1];
                    else
                        newRight=join(utils._first.get(tokens[pair.dot+1])," | ");
                }
                if(pair.dot>=tokens.length||pair.dot<0)
                    continue;
                else if(nonTerminals.contains(tokens[pair.dot]))
                {
                    ArrayList<ArrayList<String>> al=rules.get(tokens[pair.dot]);
                    for(i=0;i<al.size();i++)
                    {
                        String str=join(al.get(i)," ");
                        Pair p=new Pair(tokens[pair.dot]+" -> "+str.trim()+" , "+newRight,0);
                        if(!closure.contains(p))
                        {
                            done=false;
                            addAble.add(p);
                        }
                    }
                }
            }
            closure.addAll(addAble);
        }
    }    
    private HashSet<Pair> getLR1Goto(HashSet<Pair> X, String I)
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
            getLR1Closure(temp);
            add.addAll(temp);
        }
        goTo.addAll(add);
        return goTo;
    }
}