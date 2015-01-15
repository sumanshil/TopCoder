package com.topcoder.problems;
//http://community.topcoder.com/stat?c=problem_statement&pm=86&rd=3007
public class WellOrdered {
    //topcoder version
//    public int numberOf(int _digits, int _base) {  
//        digits = _digits;
//        base = _base;
//        count = 0;
//        int i;
//        for (i=1;i<base;i++)
//          search(1, i);
//        return count;
//      }
//      private void search(int pos, int limit) {   
//        if (pos >= digits) {
//          count++;
//          return;
//        }
//        int i;
//        for (i=limit+1; i<base;i++) search(pos+1, i);
//      }
    
    public int numberOf(int digits, int base){
        int diff = base - digits;
        int pos = 0;
        numbers = new int[digits];
        for(int i = 1; i  <= diff ; i++){            
            numberOfUtil(pos, i,base, digits);
        }
        return count;
    }
    private int count = 0;
    private int[] numbers = null;
    private void numberOfUtil(int pos, int i, int base, int digits) {
        if ((pos == digits-1) &&(i < base)){
            count++;
            numbers[pos] = i;
          //  System.out.println("Considering digit "+i+" at position "+pos);
            printArray();
            System.out.println("Returning");
            return;
        }        
        //System.out.println("Considering digit "+i+" at position "+pos); 
        numbers[pos] = i;
        for(int j = i+1 ; j < base ; j++){            
            numberOfUtil(pos+1, j, base,digits);
        }
    }
    
    
    
    private void printArray() {
        for(int i = 0 ; i < numbers.length ; i++){
            System.out.print(numbers[i]);
        }
        System.out.println();
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int digits = 3;
        int base = 13;
        int result = new WellOrdered().numberOf(digits, base);
        System.out.println(result);

    }

}
