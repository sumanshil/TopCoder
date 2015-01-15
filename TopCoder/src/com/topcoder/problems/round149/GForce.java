package com.topcoder.problems.round149;

public class GForce {
	  double area(int[] y, int[] x, double a, double b) {
	    double s = 0;
	    for (int i = 0; i < x.length - 1; i++) {
	      if (a <= x[i + 1] && b >= x[i]) {
	        double u = Math.max(a, x[i]) - x[i];
	        double v = Math.min(b, x[i + 1]) - x[i];
	        double m = ((double) (y[i + 1] - y[i])) / (x[i + 1] - x[i]);
	        s += (u * m + v * m + 2 * y[i]) / 2 * (v - u);
	      }
	    }
	    return s;
	  }
	  public double avgAccel(int p, int[] a, int[] t) {
	    int N = a.length;
	    double max = 0;
	    for (int i = 0; i < N; i++) {
	      max = Math.max(max, area(a, t, t[i], t[i] + p));
	      max = Math.max(max, area(a, t, t[i] - p, t[i]));
	    }
	    for (int i = 0; i < N - 1; i++) {
	      double mi = ((double) a[i + 1] - a[i]) / (t[i + 1] - t[i]);
	      double bi = a[i] - t[i] * mi;
	      for (int j = i + 1; j < N - 1; j++) {
	        double mj = ((double) a[j + 1] - a[j]) / (t[j + 1] - t[j]);
	        if (Math.abs(mj - mi) < 1e-9) continue;
	        double bj = a[j] - t[j] * mj;
	        double x = (bi - bj - mj * p) / (mj - mi);
	        max = Math.max(max, area(a, t, x, x + p));
	      }
	    }
	    return max / p;
	  }
	  
	  public static void main(String[] args)
	  {
		  double result = new GForce().avgAccel(100, new int[]{1500, 1500, 500, 2000}, new int[]{0, 100, 150, 225});
		  System.out.println(result);
	  }
	}