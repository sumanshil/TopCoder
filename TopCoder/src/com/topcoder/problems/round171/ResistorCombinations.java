package com.topcoder.problems.round171;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sshil on 11/2/16.
 */
//
public class ResistorCombinations {
    double[] res;
    double[][] possible = new double[32][];

    public double closestValue(String[] resistances, double target) {
        res = new double[resistances.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = Double.parseDouble(resistances[i]);
        }
        double best = -1;
        for (int bits = 1; bits < (1 << res.length); ++bits) {
            double[] a = f(bits);
            for (int i = 0; i < a.length; ++i) {
                if (best < 0 || Math.abs(a[i] - target) < Math.abs(best - target)) {
                    best = a[i];
                }
            }
        }
        return best;
    }

    double[] f(int bits) {
        if (possible[bits] != null)
            return possible[bits];

        if ((bits & (bits - 1)) == 0) {
            for (int i = 0; i < 5; ++i) {
                if (((bits >> i) & 1) == 1) {
                    return possible[bits] = new double[]{ res[i] };
                }
            }
            return null;
        }
        ArrayList rr = new ArrayList();
        for (int i = 1; i < bits; ++i) {
            if ((bits & i) != i) continue;
            double[] a = f(bits & ~i);
            double[] b = f(bits & i);
            double[] r = new double[2 * a.length * b.length];
            int d = 0;
            for (int ii = 0; ii < a.length; ++ii) {
                for (int jj = 0; jj < b.length; ++jj) {
                    r[d++] = a[ii] + b[jj];
                    r[d++] = 1 / (1 / a[ii] + 1 / b[jj]);
                }
            }
            Arrays.sort(r);
            rr.add(new Double(r[0]));
            for (int s = 1; s < r.length; ++s) {
                if (r[s] - r[s-1] > 1e-10) {
                    rr.add(new Double(r[s]));
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

    public static void main(String[] args) {
        String[] resistances = {"2", "3", "5"};
        double target = 2.5;
        new ResistorCombinations().closestValue(resistances, target);
//        System.out.println(~2);
    }
}
