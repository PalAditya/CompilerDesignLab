import java.util.*;
import java.io.*;
class Q1
{
    enum Strategy
    {
        KEYS_ASCENDING,
        KEYS_DESCENDING,
        KEYS_NATURAL;
    }
    public static void main(String args[])throws IOException
    {
        Q1 obj=new Q1();
        obj.go();
    }
    int a=-1;
    String getChar()
    {
        a++;
        return ((char)('M'+a))+"";
    }
    ArrayList<String> keyGen(ArrayList<String> keys, Strategy strategy)
    {
        if(strategy==Strategy.KEYS_ASCENDING)
        {
            Collections.sort(keys);
        }
        else if(strategy==Strategy.KEYS_DESCENDING)
        {
            Collections.sort(keys, Collections.reverseOrder());
        }
        return keys;
    }
    public void go()throws IOException
    {
        BufferedReader br=new BufferedReader(new FileReader("a.txt"));
        String str="";
        int i;
        HashMap<String,ArrayList<String>> hm=new HashMap<>();
        HashMap<String,ArrayList<String>> processed=new HashMap<>();
        while((str=br.readLine())!=null)
        {
            if(str.length()>15)
            break;
            int l=str.indexOf("->");
            String left=str.substring(0,l).trim();
            String right=str.substring(l+2).trim();
            String tokens[]=right.split("[|]");
            hm.put(left,new ArrayList<String>(Arrays.asList(tokens)));
        }
        ArrayList<String> keys = new ArrayList<>(hm.keySet());
        /**
         * Set Strategy here for iterating the map; KEYS_ASCENDING for ascending iterator, KEYS_DESCENDING for descending itertor,
         * KEYS_NATURAL for natural order of map;
           */
        keyGen(keys,Strategy.KEYS_ASCENDING);
        System.out.println("The order of processing will be: "+keys);
        for(int k=0;k<keys.size();k++)
        {
            String left=keys.get(k);
            ArrayList<String> al=hm.get(left);
            int l;
            /**
             * Debug
               */
              /*System.out.println("Map: "+left+"->"+al);
              for(Map.Entry<String, ArrayList<String>> mpp:processed.entrySet())
              {
                  System.out.println("Processed: "+mpp.getKey()+"->"+mpp.getValue().toString());
              }*/
            /**
             * End Debug
               */
            for(i=0;i<al.size();i++)
            {
                if(processed.get(al.get(i).charAt(0)+"")!=null)
                {
                    String p=al.get(i);
                    al.remove(p);
                    for(String s:processed.get(p.charAt(0)+""))
                    al.add(s+p.substring(1));
                }
            }
            ArrayList<String> noNeed=new ArrayList<>();
            ArrayList<String> need=new ArrayList<>();
            for(i=0;i<al.size();i++)
            {
                if(al.get(i).charAt(0)==left.charAt(0))
                need.add(al.get(i).substring(1));
                else
                {
                    if(al.get(i).charAt(0)=='@')
                        noNeed.add("");
                    else
                        noNeed.add(al.get(i));
                }
            }
            l=need.size();
            String newChar=getChar();
            if(l!=0)
            {
                String toUse=newChar;
                String puttable="";
                l=noNeed.size();
                for(i=0;i<l;i++)
                {
                    String inside=noNeed.get(i)+toUse;
                    if(inside.charAt(0)=='@')
                        inside=inside.substring(1);
                    puttable+=noNeed.get(i)+toUse;
                    if(i!=l-1)
                    {
                        puttable+="|";
                    }
                }
                String breakAgain[]=puttable.split("[|]");
                ArrayList<String> forHashSake=new ArrayList<>();
                for(i=0;i<breakAgain.length;i++)
                forHashSake.add(breakAgain[i]);
                processed.put(left,forHashSake);
                puttable="";
                for(String ii:need)
                {
                    String inside=ii+toUse+"|";
                    if(inside.charAt(0)=='@')
                        inside=inside.substring(1);
                    puttable+=ii+toUse+"|";
                }
                puttable+="@";
                String breakAgain2[]=puttable.split("[|]");
                ArrayList<String> forHashSake2=new ArrayList<>();
                for(i=0;i<breakAgain2.length;i++)
                forHashSake2.add(breakAgain2[i]);
                processed.put(toUse,forHashSake2);
            }
            else
            {
                String puttable="";
                l=noNeed.size();
                for(i=0;i<l;i++)
                {
                    puttable+=noNeed.get(i);
                    if(i!=l-1)
                    {
                        puttable+="|";
                    }
                }
                String breakAgain[]=puttable.split("[|]");
                ArrayList<String> forHashSake=new ArrayList<>();
                for(i=0;i<breakAgain.length;i++)
                forHashSake.add(breakAgain[i]);
                processed.put(left,forHashSake);
            }
        }
        System.out.println("The paranthesized values indicate the production list");
        for(Map.Entry<String, ArrayList<String>> mp:processed.entrySet())
        {
            System.out.println(mp.getKey()+"->"+mp.getValue().toString());
        }
    }
}