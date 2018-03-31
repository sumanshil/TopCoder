package com.geeksforgeeks.recursion;
//http://www.geeksforgeeks.org/recursively-remove-adjacent-duplicates-given-string/
public class RecursivelyRemoveAllAdjacentDuplicates {

    public String removeDuplicates(String str){
        String result= removeDuplicateUtil(str,0);
        return result;
    }
    public char prevRemovedChar;
    private String removeDuplicateUtil(String str, int index) {
        if (index == str.length()){
            return null;
        }
        
        char c = str.charAt(index);
        
        String str1 = removeDuplicateUtil(str, index+1);
        
        if (str1 != null && str1.length()>=1 && c == str1.charAt(0) && prevRemovedChar==' '){
            prevRemovedChar = c;
            if (str1.length() > 1){                
                return str1.substring(1);
            } else {                
                return new String();
            }
        } else if (str1 == null){
            return ""+c;
        } else if (c == prevRemovedChar) {
            return str1;
        } else {
            prevRemovedChar =' ';
            return c+str1;
        }        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = "gghhg";
        String result = new RecursivelyRemoveAllAdjacentDuplicates().removeDuplicates(str);
        System.out.println(result);
    }

}
