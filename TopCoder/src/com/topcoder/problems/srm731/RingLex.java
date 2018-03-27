package com.topcoder.problems.srm731;


public class RingLex {
    String getmin(String s) {
        Info info = getIndex(s);
        int maxIndex = info.firstIndex + info.secondIndex* (s.length()-1);

        int initialLength = s.length();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s);
        while (initialLength <= maxIndex) {
            stringBuilder.append(s);
            initialLength += initialLength;
        }
        String newString = stringBuilder.toString();
        StringBuilder result = new StringBuilder();
        int x = info.firstIndex;
        int p = info.secondIndex;
        result.append(newString.charAt(x));
        for (int i = 1 ; i < s.length() ; i++) {
            int nextIndex = x + i*p;
            result.append(newString.charAt(nextIndex));
        }
        return result.toString();
    }

    class Info {
        int firstIndex;
        int secondIndex;

        public Info(int firstCharIndex, int secondCharIndex) {
            this.firstIndex = firstCharIndex;
            this.secondIndex = secondCharIndex;
        }
    }


    private Info getIndex (String s) {
        int firstCharValue = Integer.MAX_VALUE;
        int secondCharValue = Integer.MAX_VALUE;
        int firstCharIndex = -1;
        int secondCharIndex = -1;
        String s1 = s + s;
        for (int i = 0 ; i < s1.length() ; i++) {
            int charValue = s1.charAt(i) - 'a';
            if (charValue < firstCharValue) {
                firstCharValue = charValue;
                firstCharIndex = i;
                continue;
            }

            if (charValue >= firstCharValue && charValue < secondCharValue) {
                if ( isPrime(i - firstCharIndex)) {
                    secondCharIndex = i - firstCharIndex;
                    secondCharValue = charValue;
                }
            }
        }
        return new Info(firstCharIndex, secondCharIndex);
    }

    boolean isPrime(int n) {
        for(int i=2;i<n;i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        String s = "abacaba";
        String res = new RingLex().getmin(s);
        System.out.println(res);
    }
}
