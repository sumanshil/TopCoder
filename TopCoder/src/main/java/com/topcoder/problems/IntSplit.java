package com.topcoder.problems;


    public class IntSplit {
        public int maxSplit(int a, int b) {
          int w[] = new int[9];                           // where to split (no/yes)
          int best = 0;
          for(int i=0;i<9;i++) w[i] = 0;
          while(w[8] == 0) {
            String s = new String();
            int sum = 0;
            int mult = 1;
            int cur = 0;
            s += a;
            for(int i=0;i<s.length();i++) {
              cur *= 10;
              cur += s.charAt(i) - '0';
              if(w[i] == 1){
                  sum += cur;
                  cur = 0;
              }
            }
            sum += cur;
            if(sum <= b)
              if(sum > best)
                best = sum;
            for(int i=0;i<9;i++) {
              w[i]++;
              if(w[i] == 1) break;
              else w[i] = 0;
            }
          }
          return best;
        }
        public static void main(String[] args) {
            int a = 323;
            int b = 31;
            int result = new IntSplit().maxSplit(a, b);
            System.out.println(result);
        }
        
      }
        
