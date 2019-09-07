import java.util.*;
import java.io.*;
public class LR0Parser extends Parser
{
    LR0Parser()
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
        boolean isLR0=true;
        for(DFA dfa:states)
        {
            for(Map.Entry<String,DFA> mp:dfa.transitions.entrySet())
            {
                int index=getIndex(mp.getValue().rules);
                if(terminals.contains(mp.getKey()))
                {                    
                    if (states.get(index).rules.size()!=0)
                        table[row][colMap.indexOf(mp.getKey())].add("Shift "+getIndex(mp.getValue().rules));
                }
                else
                {
                    if (states.get(index).rules.size()!=0)
                        table[row][colMap.indexOf(mp.getKey())].add(getIndex(mp.getValue().rules)+"");
                }
            }
            for(Pair p:dfa.rules)
            {
                String str[]=p.rule.substring(p.rule.indexOf("->")+2).trim().split(" ");
                String left=p.rule.substring(0,p.rule.indexOf("->")).trim();
                if(left.equals(startSymbol+"'")&&p.dot==str.length)
                {
                    table[row][colMap.indexOf("$")].add("Accept :)");
                }
                if((p.dot==str.length)||(p.dot==0&&str[0].equals("@")))
                {
                    for(i=0;i<terminals.size();i++)
                    {
                        table[row][i].add("Reduce "+p.rule);
                    }
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
                {
                    int shift=0,reduce=0;
                    for(String string:table[i-1][j-1])
                    {
                        if(string.indexOf("Shift")>=0)
                            shift++;
                        else if(string.indexOf("Reduce")>=0)
                            reduce++;
                    }
                    if ((shift>=1&&reduce>=1)||reduce>=2)
                        isLR0=false;
                }
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
            if(isLR0)
                System.out.println("Grammar is LR0 :)");
            else
                System.out.println("Grammar isn't LR0 :(");
        }
        return isLR0;
    }
}