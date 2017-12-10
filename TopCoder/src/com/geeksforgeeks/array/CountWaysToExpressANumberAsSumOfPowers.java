package com.geeksforgeeks.array;

//http://www.geeksforgeeks.org/count-ways-express-number-sum-powers/
public class CountWaysToExpressANumberAsSumOfPowers {

    public void find (int n, int x) {
        recursive(n, x, 1);
        System.out.println(result);
    }

    private int result = 0;
    private void recursive(int n, int x, int number) {
        if (x == 0){
            result++;
        }

        if (x < 0) {
            return;
        }
        for ( int i = number ; i <= 10 ; i++){
            int res = (int)Math.pow(i, n);
            recursive(n, x-res, i+1);
        }

    }


    public static void main(String[] args) {
        new CountWaysToExpressANumberAsSumOfPowers().find(2, 100);
    }

}
