package com.geeksforgeeks.bitmagic;

public class AddTwoBitStrings {

    public void add(String a, String b){
        int[] arr1 = new int[a.length()];
        int[] arr2 = new int[b.length()];
        
        for(int i = 0 ; i < a.length() ; i++){
            arr1[i] = a.charAt((a.length()-1)-i)=='0'?  0 : 1;
        }
        
        for(int i = 0 ; i < b.length() ; i++){
            arr2[i] = b.charAt((b.length()-1)-i)=='0'?  0 : 1;
        }
        int[] smaller = (arr1.length> arr2.length) ? arr2 : arr1;
        int[] bigger =  (arr1.length> arr2.length) ? arr1 : arr2;
        int[] result = new int[32];
        int index = 1;
        int carry = 0;
        result[0] = smaller[0] ^ bigger[0];
        carry = smaller[0] & bigger[0];
        while(index < smaller.length){
            int x = smaller[index];
            int y = bigger[index];            
            int z = x ^ y ^ carry;
            result[index] = z;
            carry = (x&y)|(y &carry)|(x&carry) ;
            index++;
        }
        
        while(index < bigger.length){
            int x = carry ;
            int y = bigger[index];
            int z = x ^ y ;
            result[index] = z;
            carry = (y &carry) ;
            index++;            
        }
        
        System.out.println("Printing result array");
        for(int j = 0 ; j < result.length ; j++){
            System.out.println(result[j]);
        }
        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String a = "1100011";
        String b = "10";
        new AddTwoBitStrings().add(a, b);
        

    }

}
