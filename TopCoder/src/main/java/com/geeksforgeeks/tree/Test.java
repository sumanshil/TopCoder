package com.geeksforgeeks.tree;

/**
 * Created by sshil on 3/31/2016.
 */
public class Test {

    public static void main(String[] args) {
    //    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int lowerNumber = 1  ;// Integer.parseInt(bufferedReader.readLine());
        int higherNumber = 99999;//Integer.parseInt(bufferedReader.readLine());
        for ( int i = lowerNumber ; i <= higherNumber ; i++) {
            long sqrtNumber = (long)Math.pow(i, 2);
            String str = Long.toString(sqrtNumber);
            if (sqrtNumber == i){
                System.out.print(" "+i);
            } else {
                for (int j = 1; j <= str.length() - 1; j++) {
                    String part1 = str.substring(0, j);
                    String part2 = str.substring(j, str.length());
                    if (part2.length() == Integer.toString(i).length()){
                        int total = Integer.parseInt(part1) + Integer.parseInt(part2);
                        //   System.out.println(total);
                        if (total == i) {
                            System.out.print(" "+i);
                            break;
                        }
                    }
                }
            }
        }

    }
}
