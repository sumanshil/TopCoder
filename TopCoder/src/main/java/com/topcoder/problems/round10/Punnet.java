package com.topcoder.problems.round10;

//http://community.topcoder.com/stat?c=problem_statement&pm=95&rd=3010
public class Punnet {

    public double computeProbability(String father, String mother, String child){
        String[] strArray  = new String[father.length()*2];
        int index = 0;
        for(int i = 0 ; i < father.length() ; i++){
            int j;
            if (i%2 ==0){
                j = i;
            } else {
                j = i-1;
            }
            strArray[index++]=father.charAt(i)+""+mother.charAt(j);
            strArray[index++]=father.charAt(i)+""+mother.charAt(j+1);
        }
        for(String ret : strArray){
            System.out.println(ret);
        }
        //return -1;
        double result = 1.0;
        for(int i = 0 ; i < child.length() ; i++){
            char c = child.charAt(i);
            if (c==' ')
                continue;
            else {
                int count = 0;
                for(int j = i*4;j <(i*4)+4 ; j++){
                    if (c >='A' && c <='Z'){
                        if (strArray[j].indexOf(c)>=0){
                            count++;
                        }
                    } else {
                        if (strArray[j].charAt(0)==c && strArray[j].charAt(1)==c){
                            count++;
                        }
                    }
                }
                double d1 = ((double)count/4);
                result= result *d1;
            }
        }
        return result;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        double result = new Punnet().computeProbability("AabBcCdDEe", "Aabbccddee", "ABCDE" );
        System.out.println(result);
//        System.out.println((int)'A');
//        System.out.println((int)'Z');
//        
//        System.out.println((int)'a');
//        System.out.println((int)'z');

    }

}
