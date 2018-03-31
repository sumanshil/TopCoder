package com.topcoder.problems.round14;
//http://community.topcoder.com/stat?c=problem_statement&pm=103&rd=3013
public class Hands {
    //http://community.topcoder.com/stat?c=problem_statement&pm=103&rd=3013
    public String overlap (int a) {
        if (a == 11 || a == 12) return "12:00:00";
        int h = a;
        int m = a * 60 / 11;
        int s = (int)(((float)a * 60f / 11f - (float)m) * 60);
        String hh = "" + a;
        String mm = "" + m;
        String ss = "" + s;
        String t = "";
        if (h < 10) t+= "0"; t +=hh + ":";
        if (m < 10) t+= "0"; t +=mm + ":";
        if (s < 10) t+= "0"; t +=ss;
        return t;
      }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String result = new Hands().overlap(4);
        System.out.println(result);
    }

}
