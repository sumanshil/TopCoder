package com.topcoder.problems.round17;
//http://community.topcoder.com/stat?c=problem_statement&pm=122&rd=3017
public class Rot13 {

    public String rot13(String s){
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if ((c>='a' && c <='z')){
                int j = (int)c;
                if ((j+13) > 122){
                   int k =  ((j+13)-122)+96;
                   sb.append((char)k);
                } else {
                    sb.append((char)(j+13));
                }
            } else if ((c>='A'&&c<='Z')){
                int j = (int)c;
                if ((j+13) > 90){
                    int k =  ((j+13)-90)+64;
                    sb.append((char)k);
                 } else {
                     sb.append((char)(j+13));
                 }
                
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println((int)'a');
//        System.out.println((int)'z');
//        System.out.println((int)'A');
//        System.out.println((int)'Z');
        String str = "This is a test message.";
        String result = new Rot13().rot13(str);
        System.out.println(result);
        //System.out.println((char)98);
    }

}
