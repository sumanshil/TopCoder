package com.topcoder.problems;
//http://community.topcoder.com/stat?c=problem_statement&pm=67&rd=2009
public class Vigenere {

    public String coder(String input, String code, int action){
        if (action == 1){
            String s = encodeStep1(input, code);
            System.out.println("String after step 1"+s);
            s = encodeStep2(input, s);
            return s;
        } else if (action == 2){
            String s= decode(input, code);
            return s;
        }
        return null;
    }
    
    private String decode(String encodedString, String code){
        StringBuffer retVal = new StringBuffer();
        StringBuffer codedString = new StringBuffer();
        for(int i = 0 ; i < encodedString.length(); ){
            int j = 0 ; 
            for(; j < code.length() && (i+j)< encodedString.length() ; j++){
                codedString.append(code.charAt(j));
            }
            i+=j;
        }
        System.out.println("Encoded String length "+ encodedString.length());
        System.out.println("Coded String length "+ codedString.length());
        for(int i = 0 ; i < codedString.length() ; i++){
            int startIndex  = codedString.charAt(i);
            int targetIndex = encodedString.charAt(i);
            
            int count = 0;
            while(true){                
                if (startIndex == targetIndex){
                    break;
                } else{
                    startIndex++;
                    if (startIndex > 90){
                        startIndex = 65;
                    }
                }
                count++;
            }
            
            retVal.append((char)(65+count));
            System.out.println(retVal);
        }
        return retVal.toString();
    }
    private String encodeStep2(String input, String encoded) {
        StringBuffer retVal = new StringBuffer();
        for(int i = 0 ; i < encoded.length();i++){
            char c= encoded.charAt(i);
            int rotationIndex =  c-65;
            int startIndex = 65 + rotationIndex;
            int originalIndex= input.charAt(i);
            int needToMove = originalIndex - 65;
            int targetIndex = startIndex;
            for(int j = 0 ; j <= needToMove ; j++){
                if (startIndex + j > 90){
                    targetIndex = ((startIndex + j)-91)+65;
                } else {
                    targetIndex = startIndex + j;
                }
            }
            retVal.append((char)targetIndex);
            System.out.println(retVal);
        }        
        return retVal.toString();
    }
    private String encodeStep1(String input, String code) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < input.length(); ){
            int j = 0;
            for( ; j < code.length()&& (i+j)< input.length() ; j++){
                sb.append(code.charAt(j));
            }
            i += j;
        }
        return sb.toString();
        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int c ='A';
        int c1 = 'Z';
        System.out.println(c +" "+c1);

//        String s1 = "TOPCODERISGREAT";
//        String s2 = "CODE";
//         int action = 1;
//        String s1 = "DOESTHISMESSAGEWORK";
//        String s2 = "TESTING";
//         int action = 1;
//        String s1 = "WSWLBUOLQWLANMXAGKS";
//        String s2 = "TESTING";
//        int action = 2;
        String s1 =  "NODECODE";
        String s2 =  "A";
        int action = 2;
        String result = new Vigenere().coder(s1, s2, action);
        System.out.println(result);        
    }

}
