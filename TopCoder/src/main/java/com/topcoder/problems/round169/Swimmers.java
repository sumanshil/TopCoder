package com.topcoder.problems.round169;

/**
 * Created by sshil on 12/1/2015.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1888&rd=4650
public class Swimmers {
    public int[] getSwimTimes(int[] distances, int[] speeds, int current) {
        int[] result = new int[distances.length];
        for ( int i = 0 ; i < distances.length ; i++) {
            int timeWithCurrent = (int)Math.ceil(distances[i]/
                                                   (speeds[i]+current));
            if (speeds[i] > current) {
                int timeAgainstCurrent = distances[i] / (speeds[i] - current);
                result[i] = timeWithCurrent + timeAgainstCurrent;
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args){
        int[] distances = {500, 500};
        int[] speeds = {4, 5 };
        int current = 2;
        int[] result = new Swimmers().getSwimTimes(distances, speeds, current);
        for ( int i = 0 ; i < result.length ; i++){
            System.out.println(result[i]);
        }
    }

}
