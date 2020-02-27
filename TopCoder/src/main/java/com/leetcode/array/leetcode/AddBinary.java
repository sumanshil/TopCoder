package com.leetcode.array.leetcode;

//https://leetcode.com/problems/add-binary/
public class AddBinary {
    public String addBinary(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        int diff = Math.abs(aLength - bLength);
        String smallerStr = a;
        if (aLength > bLength) {
            while (diff > 0) {
                b = "0" + b;
                diff--;
            }
        } else {
            while (diff > 0) {
                a = "0" + a;
                diff--;
            }
        }


        char[] charArray = a.toCharArray();
        boolean shouldPlus = false;
        for (int i = charArray.length - 1 ; i >= 0 ; i--) {
            if (charArray[i] == '1' && b.charAt(i) == '1') {
                charArray[i] = shouldPlus ? '1' : '0';
                shouldPlus = true;
            } else if (charArray[i] == '0' && b.charAt(i) == '1') {
                charArray[i] = shouldPlus ? '0' : '1';
            } else if (charArray[i] == '1' && b.charAt(i) == '0') {
                charArray[i] = shouldPlus ? '0' : '1';
            } else if (charArray[i] == '0' && b.charAt(i) == '0') {
                charArray[i] = shouldPlus ? '1' : '0';
                shouldPlus = false;
            }
        }
        return (shouldPlus ? "1" : "0") + new String(charArray);
    }

    /*
    public String addBinary(String a, String b) {
        int i = a.length()-1;
        int j = b.length()-1;
        String carryober = "0";
        StringBuffer stringBuffer = new StringBuffer();
        while ( i >= 0 && j >= 0) {
            String aVal = a.charAt(i) +"";
            String bVal = b.charAt(j) + "";
            String res = binarySum(aVal, bVal);
            res = binarySum(res, carryober);
            if (res.length() > 1) {
                String lastVal = res.charAt(1)+"";
                stringBuffer.insert(0, lastVal);
                carryober = res.charAt(0) +"";
            } else {
                String lastVal = res.charAt(0)+"";
                stringBuffer.insert(0, lastVal);
                carryober = "0";
            }
            i--;
            j--;
        }

        while ( i >= 0) {
            String aVal = a.charAt(i) +"";
            String res = binarySum(aVal, carryober);

            if (res.length() > 1) {
                String lastVal = res.charAt(1)+"";
                stringBuffer.insert(0, lastVal);
                carryober = res.charAt(0) +"";
            } else {
                String lastVal = res.charAt(1)+"";
                stringBuffer.insert(0, lastVal);
            }
            i--;
        }

        while ( j >= 0) {
            String aVal = b.charAt(j) +"";
            String res = binarySum(aVal, carryober);

            if (res.length() > 1) {
                String lastVal = res.charAt(1)+"";
                stringBuffer.insert(0, lastVal);
                carryober = res.charAt(0) +"";
            } else {
                String lastVal = res.charAt(0)+"";
                stringBuffer.insert(0, lastVal);
            }
            j--;
        }

        if (carryober.equals("1")) {
            stringBuffer.insert(0, carryober);
        }
        return stringBuffer.toString();
    }

    public String binarySum(String a, String b) {
        if (a.equals("0") && b.equals("0")) {
            return "0";
        } else if ((a.equals("0") && b.equals("1")) || (a.equals("1") && b.equals("0"))) {
            return "1";
        } else if (a.equals("1") && b.equals("1")) {
            return "10";
        } else if ((a.equals("10") && b.equals("0")) || (a.equals("0") && b.equals("10"))) {
            return "10";
        }else if ((a.equals("10") && b.equals("1")) || (a.equals("1") && b.equals("10"))) {
            return "11";
        }
        return null;
    }
    */

    public static void main(String[] args) {
        String a = "1";
        String b = "111";
        String res = new AddBinary().addBinary(a, b);
        System.out.println(res);
    }
}
