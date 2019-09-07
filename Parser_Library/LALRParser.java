import java.util.*;
import java.io.*;
public class LALRParser extends Parser
{
    HashMap<Integer,Integer> new_map;
    LALRParser()
    {
        super();
        new_map=new HashMap<>();
    }
    private String getCorePart(String x)
    {
        int l=x.indexOf(",");
        return x.substring(0,l).trim();
    }
    private ArrayList<ArrayList<Integer>> getMergerList(ArrayList<DFA> states)
    {
        ArrayList<ArrayList<Integer>> al=new ArrayList<>();
        HashSet<Integer> visited=new HashSet<>();
        int i,j;
        for(i=0;i<states.size();i++)
        {
            if(visited.contains(i))
                continue;
            visited.add(i);
            ArrayList<Integer> a=new ArrayList<>();
            a.add(i);            
            for(j=i+1;j<states.size();j++)
            {
                int howMany=0;   
                HashSet<Integer> already_counted=new HashSet<>();
                for(Pair p:states.get(j).rules)
                {
                    int index=0;
                    String r=getCorePart(p.rule);
                    for(Pair p2:states.get(i).rules)
                    {
                        String x=getCorePart(p2.rule);
                        if(x.equals(r)&&p2.dot==p.dot&&!already_counted.contains(index))
                        {                            
                            howMany++;
                            already_counted.add(index);
                            break;
                        }
                        index++;
                    }
                }
                if(howMany==states.get(i).rules.size()&&howMany==states.get(j).rules.size())
                {
                    a.add(j);
                    visited.add(j);
                }
            }
            al.add(a);
        }
        return al;
    }
    private int getRoot(String str)
    {
        return new_map.get(Integer.parseInt(str));
    }
    public void buildDFA()
    {
        LR1Parser obj=new LR1Parser();
        obj.rules.putAll(rules);
        obj.terminals.addAll(terminals);
        obj.nonTerminals.addAll(nonTerminals);
        obj.allSymbols.addAll(allSymbols);
        obj.utils=utils;
        obj.startSymbol=startSymbol;
        obj.buildDFA();
        states.addAll(obj.states);
    }
    public boolean getParsingTable(boolean output)
    {
        LR1Parser obj=new LR1Parser();
        obj.rules.putAll(rules);
        obj.terminals.addAll(terminals);
        obj.nonTerminals.addAll(nonTerminals);
        obj.allSymbols.addAll(allSymbols);
        obj.utils=utils;
        obj.startSymbol=startSymbol;
        obj.buildDFA();
        obj.getParsingTable(false);
        ArrayList<ArrayList<Integer>> al=getMergerList(obj.states);        
        int i,j,k,l;
        for(i=0;i<al.size();i++)
        {
            for(j=0;j<al.get(i).size();j++)
            {
                new_map.put(al.get(i).get(j),i);
            }
        }
        ArrayList<String> colMap=new ArrayList<>();
        terminals.add("$");
        colMap.addAll(terminals);
        colMap.addAll(nonTerminals);
        allSymbols.clear();
        allSymbols.addAll(colMap);
        ArrayList<String> table2[][]=new ArrayList[al.size()][terminals.size()+nonTerminals.size()];
        for(i=0;i<al.size();i++)
            for(j=0;j<terminals.size()+nonTerminals.size();j++)
                table2[i][j]=new ArrayList<>();
        for(i=0;i<al.size();i++)
        {
            /**Copy first one, with proper care to shift*/
            for(j=0;j<terminals.size()+nonTerminals.size();j++)
            {
                for(String str:obj.table[al.get(i).get(0)][j])
                {
                    String s[]=str.split(" ");
                    if(s[0].equals("Shift"))
                    {
                        l=getRoot(s[1]);
                        table2[i][j].add("Shift "+l);
                    }
                    else if(s.length==1)
                    {
                        l=getRoot(s[0]);
                        table2[i][j].add(l+"");
                    }
                    else
                    {
                        table2[i][j].add(str);
                    }
                }
            }
            /**Vertical merge*/
            for(k=1;k<al.get(i).size();k++)
            {
                for(j=0;j<terminals.size()+nonTerminals.size();j++)
                {
                    for(String str:obj.table[al.get(i).get(k)][j])
                    {
                        boolean add=true;
                        for(String str2:table2[i][j])//Iterate over all Strings already in table 2 and see if Unique
                        {
                            String str_s[]=str.split(" ");
                            if(str_s[0].equals("Shift"))
                            {
                                l=getRoot(str_s[1]);
                                str="Shift "+l;
                            }
                            else if(str_s.length==1)
                            {
                                l=getRoot(str_s[0]);
                                str=l+"";
                            }
                            if(str2.equals(str))
                            {
                                add=false;
                                break;
                            }
                        }
                        if(add)
                        {
                            table2[i][j].add(str);
                        }
                    }
                }
            }
        }
        table=table2;
        ArrayList<String> pretty[][]=new ArrayList[table.length+1][table[0].length+1];
        boolean isLALR=true;
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
                    isLALR=false;
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
            if(isLALR)
                System.out.println("Grammar is LALR(1) :)");
            else
                System.out.println("Grammar isn't LALR(1) :(");
        }
        return isLALR;
    }        
}