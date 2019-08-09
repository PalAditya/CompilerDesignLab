import java.util.*;
import java.io.*;
class Q3
{
    public static void main(String args[])throws IOException
    {
        Q3 obj=new Q3();
        obj.go();
    }
    long t1,t2;
    public void go()throws IOException
    {
        BufferedReader br1=new BufferedReader(new FileReader("a.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        /**
         * Map: E ->0
         * T ->1
         * + ->2
         * E'->A->3
         * other->D->4
         * @->5
         * F->6
         * star->7
         * (->8
         * )->9
         * id->B->10
         * T'->C->11
           */ 
        /**
         * E->TA->FC->i

        or FC->(E)->(TA)->(i)
        
        (i)*i
           */  
        String str="";
        int i=0,l=str.length();
        HashMap<String,ArrayList<String>> rules=new HashMap<>();
        int line=0;
        Queue<String> q=new LinkedList<>();
        ArrayList<String> dfsSources=new ArrayList<>();
        while((str=br1.readLine())!=null)
        {
            l=str.indexOf("->");
            String left=str.substring(0,l).trim();
            String right=str.substring(l+2).trim();
            String token[]=right.split("[|]");
            ArrayList<String> al=new ArrayList<>();
            for(i=0;i<token.length;i++)
            {
                if(line==0)
                {
                    q.add(token[i]);
                    dfsSources.add(token[i]);
                }
                line++;
                al.add(token[i]);
            }
            rules.put(left,al);
            System.out.print(left+","+al.toString());
            System.out.println();
        }
        str=br.readLine();
        t1=System.nanoTime();
        bfs(q,str,rules);
        //Call dfs from all start productions. If it succeeds, System.exit() prevents other calls.
        for(String s:dfsSources)
        {
            t1=System.nanoTime();
            dfs(str,s,rules,new HashSet<String>());
        }
        t2=System.nanoTime();
        System.out.println("Failed DFS took: "+(((t2-t1)*(1.0))/1000)+" microsecends");
    }
    boolean nonTerminal(int ch)
    {
        return ch>=65&&ch<=90;
    }
    boolean valid(String str, String target)
    {
        int count=0,i,l=str.length(),l1=target.length(),j;
        for(i=0;i<l;i++)
        if(nonTerminal(str.charAt(i)))
        count++;
        //System.out.println("Validator: "+str+" , Count: "+count);
        if(target.length()<str.length()-count)
        return false;
        /*for(i=0,j=0;i<l&&j<l1;i++)
        {
            if(nonTerminal(str.charAt(i)))
            continue;
            if(str.charAt(i)!=target.charAt(j))
            return false;
            j++;
        }*/
        return true;
    }
    void dfs(String target, String formed, HashMap<String,ArrayList<String>> rules, HashSet<String> visited)
    {
        int i,l;
        //System.out.println("Formed is: "+formed);
        if(formed.equals(target))
        {
            t2=System.nanoTime();
            System.out.println("Succesful DFS took: "+(((t2-t1)*(1.0))/1000)+" microsecends");
            System.out.println("Accepted");
            System.exit(0);
        }
        visited.add(formed);
        l=formed.length();
        for(i=0;i<l;i++)
        {
            char ch=formed.charAt(i);
            if(nonTerminal(ch))
            {
                ArrayList<String> al=rules.get(ch+"");
                for(String s:al)
                {
                    if(s.equals("@"))
                    s="";
                    String possible=formed.substring(0,i)+s+formed.substring(i+1);                    
                    //Add conditions
                    if(valid(possible,target)&&!visited.contains(possible))
                    {
                        //System.out.println(formed+"--->"+possible);
                        visited.add(possible);
                        dfs(target,possible,rules,visited);
                    }
                }
            }
        }
    }
    void bfs(Queue<String> q,String target, HashMap<String,ArrayList<String>> rules)
    {
        int i,l;
        HashSet<String> visited=new HashSet<>();
        while(!q.isEmpty())
        {
            String str=q.poll();
            //System.out.println(str);
            visited.add(str);
            if(str.equals(target))
            {
                t2=System.nanoTime();
                System.out.println("BFS took: "+(((t2-t1)*(1.0))/1000)+" microsecends");
                System.out.println("Accepted");
                return;
            }
            l=str.length();
            for(i=0;i<l;i++)
            {
                char ch=str.charAt(i);
                if(nonTerminal(ch))
                {
                     ArrayList<String> al=rules.get(ch+"");
                     for(String s:al)
                     {
                         if(s.equals("@"))
                         s="";
                         String possible=str.substring(0,i)+s+str.substring(i+1);
                         //Add conditions
                         if(valid(possible,target)&&!visited.contains(possible))
                         {
                             //System.out.println("Will explore: "+str+"--->"+target+", on applying rule: "+ch+"->"+((s.length()==0)?"@":s));
                             q.add(possible);
                             visited.add(possible);
                         }
                     }
                }
            }
            //System.out.println(q.toString());
        }
        t2=System.nanoTime();
        System.out.println("BFS took: "+(((t2-t1)*(1.0))/1000)+" microsecends");
        System.out.println("Not accepted :(");
    }   
}