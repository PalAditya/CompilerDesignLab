import java.util.*;
import java.io.*;
/**
 * Solution for Question 1
 * Please use <13 non-Terminals and name them from A to L
 * An example input is provided in the file in1.txt
 * Please note that epsilon should be denoted by the "@" symbol
 * For the output, if it shows A->[B,Cd], that shows the production A->B|Cd
 * Please don't use non terminals or terminals with >1 character
   */
class Q1
{
    /*Describes three possible straegies to order the keys*/
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
    //Get the new symbol
    String getChar()
    {
        a++;
        return ((char)('M'+a))+"";
    }
    //Strategy to reorder the map keys
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
        BufferedReader br=new BufferedReader(new FileReader("in1.txt"));
        String str="";
        int i;
        HashMap<String,ArrayList<String>> hm=new HashMap<>();
        HashMap<String,ArrayList<String>> processed=new HashMap<>();
        while((str=br.readLine())!=null)//Read the string, put in map
        {
            if(str.length()>15)
            break;
            int l=str.indexOf("->");
            String left=str.substring(0,l).trim();
            String right=str.substring(l+2).trim();
            String tokens[]=right.split("[|]");
            hm.put(left,new ArrayList<String>(Arrays.asList(tokens)));//Put all the rules in the map
        }
        ArrayList<String> keys = new ArrayList<>(hm.keySet());
        /**
         * Set Strategy here for iterating the map; KEYS_ASCENDING for ascending iterator, KEYS_DESCENDING for descending itertor,
         * KEYS_NATURAL for natural order of map;
           */
        keyGen(keys,Strategy.KEYS_NATURAL);
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
            //Critical step: We see productions of form Ai->AjY, and replace them as Ai->ExY|EyY|EzY, where Aj has already been processed
            //and gives production Aj->Ex|Ey|Ez
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
            ArrayList<String> noNeed=new ArrayList<>();//Will store rules with no immediate left recursion
            ArrayList<String> need=new ArrayList<>();//Will have rules with immediate left recursion
            for(i=0;i<al.size();i++)
            {
                if(al.get(i).charAt(0)==left.charAt(0))
                need.add(al.get(i).substring(1));
                else
                {
                    if(al.get(i).charAt(0)=='@')
                        noNeed.add("");//Epsilon, so add empty string
                    else
                        noNeed.add(al.get(i));
                }
            }
            l=need.size();
            String newChar=getChar();//Get the new symbol
            if(l!=0)
            {
                String toUse=newChar;
                String puttable="";
                l=noNeed.size();
                for(i=0;i<l;i++)
                {
                    String inside=noNeed.get(i)+toUse;
                    if(inside.charAt(0)=='@')
                        inside=inside.substring(1);//Remove epsilon
                    puttable+=noNeed.get(i)+toUse;
                    if(i!=l-1)
                    {
                        puttable+="|";
                    }
                }
                String breakAgain[]=puttable.split("[|]");//Tokenize it again to convert it in form <String, ArrayList<String>> so that we can 
                //store it in the 'processed' HashMap
                ArrayList<String> forHashSake=new ArrayList<>();
                for(i=0;i<breakAgain.length;i++)
                forHashSake.add(breakAgain[i]);
                processed.put(left,forHashSake);//Store the processed production
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
                processed.put(toUse,forHashSake2);//Store the processed production
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
                processed.put(left,forHashSake);//Store the processed production
            }
        }
        System.out.println("The paranthesized values indicate the production list :) ");
        for(Map.Entry<String, ArrayList<String>> mp:processed.entrySet())
        {
            System.out.println(mp.getKey()+"->"+mp.getValue().toString());
        }
    }
}