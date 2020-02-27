package com.leetcode.String;

/*
um1 = "123", num2 = "456"

     123
     456

     738
    615
   492


 */
public class MultiplyString {
    public String multiply(String num1, String num2) {
        if ( "0".equals(num1) && "0".equals(num2)) {
            return "0";
        }
        int[] positions = new int[num1.length() + num2.length()];
        for (int i = num2.length() - 1; i >= 0 ; i--) {
            for (int j = num1.length() - 1 ; j >= 0 ; j-- ) {
                int mul = (num1.charAt(j) - '0') * (num2.charAt(i) - '0');
                int pos1 = i + j;
                int pos2 = i + j + 1;
                int remainder = (mul) % 10;
                int overflow = mul / 10;
                positions[pos1] = overflow + positions[pos1];
                int sum = remainder + positions[pos2];
                positions[pos2] = sum % 10;
                int remainder1 = sum /10;
                positions[pos1] = remainder1 + positions[pos1];
            }
        }

        boolean found = false;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0 ; i < positions.length ; i++) {
            if (!found && positions[i] == 0) {
                continue;
            }
            found = true;
            stringBuffer.append(positions[i]);
        }
        return stringBuffer.length() == 0 ? "0" : stringBuffer.toString();
    }

    public static void main(String[] args) {
        String num1 = "0";
        String num2 = "9";
        String res = new MultiplyString().multiply(num1, num2);
        System.out.println(res);
    }
}

