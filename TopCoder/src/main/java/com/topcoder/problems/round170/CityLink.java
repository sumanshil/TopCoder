package com.topcoder.problems.round170;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sshil on 5/7/2016.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1864&rd=4655
public class CityLink {
    class con implements Comparable {
        public int tm;
        int c1, c2;
        public int compareTo( Object b) {
            int t = tm - ((con)b).tm;
            if (t != 0) return t;
            t = c1 - ((con)b).c1;
            if (t != 0) return t;
            return c2 - ((con)b).c2;
        }
        public boolean equals(Object b) { return compareTo(b) == 0; }
    }

    public int timeTaken(int[] x, int[] y) {
        int n = x.length;
        TreeSet cons = new TreeSet();
        for (int i=0;i<n;i++)
            for (int j=i+1;j<n;j++) {
                con c = new con();
                c.c1 = i;
                c.c2 = j;
                int d;
                if (x[i] == x[j])
                    d = (Math.abs(y[i] - y[j]) + 1)/2;
                else if (y[i] == y[j])
                    d = (Math.abs(x[i] - x[j]) + 1)/2;
                else
                    d = Math.max(Math.abs(x[i] - x[j]), Math.abs(y[i] - y[j]));
                c.tm = d;
                cons.add(c);
            }
        Set[] nodes = new Set[n];
        for (int i=0;i<n;i++) {
            nodes[i] = new HashSet();
            nodes[i].add(new Integer(i));
        }
        int tm=0;
        while (true) {
            if (nodes[0].size() == n) return tm;
            con c = (con)cons.first();
            cons.remove(c);
            tm = c.tm;
            Integer b = new Integer(c.c2);
            Set s = nodes[c.c1];
            if (s.contains(b)) continue;
            nodes[c.c2].addAll(s);
            for (Iterator iter = s.iterator(); iter.hasNext(); ) {
                Integer item = (Integer)iter.next();
                nodes[item.intValue()] = nodes[c.c2];
            }
        }
    }

    public static void main(String[] args) {
        int[] x = {0, -1038, 591, 9813, 10000, 9015, 656631, -195};
        int[] y = {4, 1000, 0, -10000, 129, 9573, 15320, 15320};
        int result = new CityLink().timeTaken(x, y);
        System.out.println(result);

    }
}
