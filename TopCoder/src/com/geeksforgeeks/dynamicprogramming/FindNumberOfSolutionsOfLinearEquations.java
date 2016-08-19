package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 8/15/2016.
 */
//http://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
public class FindNumberOfSolutionsOfLinearEquations {

    public int find(int[] coeff, int index, int rhs){
        if (index == coeff.length && rhs == 0){
            return 1;
        }
        if (index >= coeff.length){
            return 0;
        }
        int result = 0;
        for ( int i = 0 ; coeff[index]*i <= rhs ; i++){
            result += find(coeff, index+1, rhs-(coeff[index]*i));
        }
        return result;
    }

    public int find(int[] coeffs, int start, int end, int rhs) {
        if (rhs == 0){
            return 1;
        }
        int result = 0;
        for (int i = start; i <= end; i++){
            if (coeffs[i] <=  rhs){
                result += find(coeffs, i, end, rhs-coeffs[i]);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] coeffs = {2, 2, 4};
        int rhs = 4;
        //int result = new FindNumberOfSolutionsOfLinearEquations().find(coeffs,0,coeffs.length-1, rhs);
        int result = new FindNumberOfSolutionsOfLinearEquations().find(coeffs,0, rhs);
        System.out.println(result);
    }
}
