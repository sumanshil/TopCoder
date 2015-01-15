package com.geeksforgeeks.strings;
//http://www.geeksforgeeks.org/print-possible-strings-can-made-placing-spaces/
public class PrintAllPossibleStrings
{
//    public void print(String str)
//    {
//        int totalLength = str.length();
//        int totalSpaces = totalLength-1;
//        
//        for(int i = 1 ; i <= totalSpaces; i++)
//        {
//            printUtil(str.substring(0, 1),
//                      str.substring(0, 1),
//                      str.substring(1),
//                      0,
//                      i,
//                      str.length());
//        }
//        
//    }
//    
//    private void printUtil(String prefix,
//                           String prefixModified,
//                           String suffix,
//                           int suffixIndex,
//                           int totalSpace,
//                           int totalStringLength)
//    {
//        if (totalSpace > suffix.length())
//        {
//            return;
//        }
//        else if (totalSpace==0)
//        {
//            System.out.println(prefixModified+suffix.substring(suffixIndex));
//            return;
//        }
//        else if (suffix.length()==0)
//        {
//            return;
//        }
//        for(int i = 1 ; i <= suffix.length() ; i++)
//        {
//            prefixModified = prefixModified +"_"+ suffix.charAt(i-1);
//            prefix = prefix + suffix.charAt(i-1);
//           // suffix = suffix.substring(1);
//            printUtil(prefix,
//                      prefixModified,
//                      suffix,
//                      i,
//                      totalSpace-1,
//                      totalStringLength);
//        }
//    }

    public void print(String str)
    {
        int totalLength = str.length()+ str.length()-1;
        char[] arr = new char[totalLength];
        int strIndex = 0;
        for(int i = 0 ; i < totalLength ; i++)
        {
            if (i % 2 == 0)
            {
                arr[i] = str.charAt(strIndex++);
            }
            else
            {
                arr[i] = 'N';
            }
        }
        printRecursive(arr,
                       0);                
    }
    
    
    
    
    private void printRecursive(char[] arr, int index)
    {
        if (index >= arr.length)
        {
            for(int i = 0 ; i < arr.length ; i++)
            {
//                if (arr[i] == 'Y')
//                    System.out.print(' ');
//                else if (arr[i] != 'N')
//                    System.out.print(arr[i]);
                if (i % 2 != 0)
                {
                    if (arr[i] == ' ')
                        System.out.print(' ');
                }
                else
                {
                    System.out.print(arr[i]);
                }
            }
            System.out.println();
            return;
        }
        
        if (index % 2 != 0)
        {
            //put a space
            arr[index] = ' ';
            printRecursive(arr, index+1);
            //don't put a space
            arr[index] = 'N';
            printRecursive(arr, index+1);
        }
        else
        {
            printRecursive(arr, index+1);
        }
        
    }




    public static void main(String[] args)
    {
        String str = "ABCD";
        new PrintAllPossibleStrings().print(str);

    }

}
