package com.topcoder.problems.round171;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SumanChandra on 11/6/2016.
 */
//https://community.topcoder.com/stat?c=problem_solution&cr=152073&rd=4660&pm=1941
public class ResistorComination1 {
    double[] res;
    double[][] possible = new double[32][];

    public double closestValue(String[] resistances, double target){
        res = new double[resistances.length];
        double best = -1;
        for (int i = 1 ; i < (1 << resistances.length) ; i++) {
            double[] arr = f(i);
            for ( int j = 0 ; j < arr.length; j++){
                if ( best == 1 || (target-arr[j] < target-best)){
                    best = arr[j];
                }
            }

        }
        return best;
    }

    private double[] f(int bits) {
        if (possible[bits] != null)
            return possible[bits];

        if ((bits & (bits-1) )== 0){
            for ( int i =1 ; i<= 5 ; i++){
                if ((bits &( 1 >> i)) == 1){
                    return possible[bits] = new double[]{res[i]};
                }
            }
            return null;
        }

        List<Double> rr = new ArrayList<>();
        for ( int i = 1 ; i < bits ; i++){
            if ((bits & i) != i)
                continue;
            double[] a = f(bits & ~i);
            double[] b = f(bits & i);
            double[] r = new double[2 * a.length * b.length];
            int d = 0;
            for (int ii = 0 ; ii < a.length ; ii++){
                for (int jj = 0 ; jj < b.length ; jj++){
                    r[d++] = a[ii]+b[ii];
                    r[d++] = 1/(1/a[ii]+1/b[jj]);
                }
            }
            Arrays.sort(r);
            rr.add(r[0]);
            for ( int s = 1 ; s < r.length ; ++s){
                if (r[s]-r[s-1] > 1e-10){
                    rr.add(r[s]);
                }
            }
        }

        double[] r = new double[rr.size()];
        for (int i = 0; i < r.length; ++i) {
            r[i] = ((Double) rr.get(i)).doubleValue();
        }
        rr.clear();
        Arrays.sort(r);
        rr.add(new Double(r[0]));
        for (int s = 1; s < r.length; ++s) {
            if (r[s] - r[s-1] > 1e-10) {
                rr.add(new Double(r[s]));
            }
        }
        r = new double[rr.size()];
        for (int i = 0; i < r.length; ++i) {
            r[i] = ((Double) rr.get(i)).doubleValue();
        }
        return possible[bits] = r;
    }
}
