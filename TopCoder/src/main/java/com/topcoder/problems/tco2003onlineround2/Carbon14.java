package com.topcoder.problems.tco2003onlineround2;

/**
 * Created by sshil on 10/24/2015.
 */

/**
 * Created by sshil on 10/24/2015.
 */
//http://community.topcoder.com/stat?c=round_overview&er=5&rd=4703
//http://community.topcoder.com/stat?c=problem_statement&pm=1916&rd=4703
public class Carbon14 {

    public int[] dateRange(int concentration, int err){
        int[] result = new int[2];
        double trueRatioOfConcentration = concentration + err;
        double t = getAge(trueRatioOfConcentration);
        System.out.println(t);
        result[0] = (int)Math.floor(t);
        trueRatioOfConcentration = concentration - err;
        t = getAge(trueRatioOfConcentration);
        result[1] = (int)Math.ceil(t);
        return result;
    }

    private double getAge(double trueRatioOfConcentration) {
        double ratioOfConcentration = trueRatioOfConcentration/10000;
        return -1*(Math.log(ratioOfConcentration))*8267;
    }

    public static void main(String[] args){
        int[] result = new Carbon14().dateRange(3456, 18);
        for ( int i = 0 ; i < result.length ; i++) {
            System.out.println(result[i]);
        }
    }
}
