package com.topcoder.problems.round167;

import java.util.ArrayList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=927&rd=4700
public class Inserter
{
    class Command
    {
        String insertThis;
        String before;
        int index;
        public Command(String insertThis,
                       String before,
                       int index)
        {
            this.insertThis = insertThis;
            this.before = before;
            this.index = index;
        }
    }
    
    private String applyCommand(Command command, String str)
    {
        int index = str.indexOf(command.before);
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while ( index != -1 )
        {
            count++;
            if ( count == command.index )
            {
                sb.append(str.substring(0, index));
                sb.append(command.insertThis);
                sb.append(str.substring(index));      
                break;
            }
            else
            {
                index = str.indexOf(command.before,
                        index + 1);
            }
        }
        return sb.toString().length() > 0 ? sb.toString() : str;
    }
    
   // {"#cfe dc# #e# 1", "# hdd# # # 2", "#bbhi# # d# 1"}, "fcec"     "fccfe hddbbhi dcec"
    public String insert(String[] commands, String original)
    {
        List<Command> list = createCommandsList(commands);
        String str = original;
        for ( Command command : list)
        {
           str = applyCommand(command, str);
        }
        return str;
    }
    
    
    private List<Command> createCommandsList(String[] commands)
    {
        List<Command> list = new ArrayList<Command>();
        for (String command : commands)
        {
            int lastSpaceIndex = command.lastIndexOf(' ');
            int occurence = Integer.parseInt(command.substring(lastSpaceIndex+1));
            String str1 = command.substring(0, lastSpaceIndex);
            
            int hashIndex2 = str1.indexOf('#', 1);
            String insert = str1.substring(1, hashIndex2);
            
            int hashIndex3 = str1.indexOf('#', hashIndex2+1);
            int hashIndex4 = str1.indexOf('#', hashIndex3+1);
            String before = str1.substring(hashIndex3+1, hashIndex4);
//            String[] arr = str1.split("# #");
//            String insert = arr[0].substring(1, arr[0].length());
//            String before = arr[1].substring(0, arr[1].length()-1);
            list.add( new Command(insert, before, occurence));            
        }
        return list;
    }
    
 // {"#cfe dc# #e# 1", "# hdd# # # 2", "#bbhi# # d# 1"}, "fcec"     "fccfe hddbbhi dcec"
    //fccfe dcec
    //fccfe hdd dcec
    
    public static void main(String[] args)
    {
        String[] commands = {"#cfe dc# #e# 1", "# hdd# # # 1", "#bbhi# # d# 1"};
        String original = "fcec";
        String result = new Inserter().insert(commands, original) ;
        System.out.println(result);
    }

}
