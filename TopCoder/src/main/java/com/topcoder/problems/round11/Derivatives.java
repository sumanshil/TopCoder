package com.topcoder.problems.round11;

import java.util.ArrayList;
import java.util.List;

public class Derivatives {
    static class Poly{
        public int n;
        public int power;
        public Poly(int n, int power){
            this.n = n;
            this.power = power;
        }
        
        public String toString(){
            return n+","+power;
        }
    }

    public long calcDerivative(String param0, int k, int x){
         String[] strArray = param0.split("\\+");
         List<Poly> list = new ArrayList<Poly>();
         for(String str : strArray){
             String[] str1 = str.split("\\*");
             int n = Integer.parseInt(str1[0]);
             String[] str2 = str1[1].split("\\^");
             int power = Integer.parseInt(str2[1]);
             list.add(new Poly(n, power));
         }
         int size = list.size();
         while(k>0){
             for(int i = 0 ; i < size; i++){
                 Poly poly = list.remove(0);
                 int n = poly.n;
                 int power = poly.power;
                 int result = n*power;
                 list.add(new Poly(result, power-1));
             }
             k--;
         }
         long result = 0;
         for(Poly poly : list){
             result += poly.n * Math.pow(x, poly.power);
         }
         return result;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = "2*x^5+3*x^2";
        int k = 2;
        int x = -2;
        long result = new Derivatives().calcDerivative("19*x^9+18*x^8+17*x^7", 5, 9);
        System.out.println(result);
        

    }

}
