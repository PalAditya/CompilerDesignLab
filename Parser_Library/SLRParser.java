import java.util.*;
import java.io.*;
public class SLRParser extends Parser
{
    SLRParser()
    {
        super();
    }
    public void buildDFA()
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
        states.add(dfa);
        int i=0;
        boolean done=false;
        while(!done)
        {
            done=true;
            for(i=0;i<states.size();i++)
            {
                DFA current=states.get(i);
                for(String str: allSymbols)
                {
                    HashSet<Pair> Goto=getGoto(current.rules,str);
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
        boolean isSLR=true;
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
                else if((p.dot==str.length)||(p.dot==0&&str[0].equals("@")))//Condition 2 for epsilon only
                {
                    ArrayList<String> al=utils._follow.get(left);
                    for(String s:al)
                    {
                        boolean add=true;
                        for(String sss:table[row][colMap.indexOf(s)])
                        {
                            if(sss.equals("Reduce "+p.rule))
                            {
                                add=false;
                                break;
                            }
                        }
                        if(add)
                            table[row][colMap.indexOf(s)].add("Reduce "+p.rule);
                    }
                }
                else if(terminals.contains(str[p.dot]))
                {
                    HashSet<Pair> hs=getGoto(dfa.rules,str[p.dot]);
                    int index=getIndex(hs);
                    if(index>=0)
                    {
                        boolean add=true;
                        for(String sss:table[row][colMap.indexOf(str[p.dot].trim())])
                        {
                            if(sss.equals("Shift "+index))
                            {
                                add=false;
                                break;
                            }
                        }
                        if(add)
                            table[row][colMap.indexOf(str[p.dot].trim())].add("Shift "+index);
                    }
                }
            }
            for(String ss:nonTerminals)
            {
                HashSet<Pair> hs=getGoto(dfa.rules,ss);
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
                    isSLR=false;
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
            if(isSLR)
                System.out.println("Grammar is SLR :)");
            else
                System.out.println("Grammar isn't SLR :(");
        }
        return isSLR;
    }
}