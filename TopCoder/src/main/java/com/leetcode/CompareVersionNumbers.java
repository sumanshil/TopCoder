package com.leetcode;

//https://leetcode.com/problems/compare-version-numbers/
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] array1 = version1.split("\\.");
        String[] array2 = version2.split("\\.");

        int index1 = 0;
        int index2 = 0;

        while (index1 < array1.length && index2 < array2.length) {
            int value1 = Integer.parseInt(array1[index1]);
            int value2 = Integer.parseInt(array2[index2]);
            if (value1 < value2) {
                return -1;
            } else if (value2 < value1) {
                return 1;
            }
            index1++;
            index2++;
        }

        while (index1 < array1.length) {
            if (Integer.parseInt(array1[index1]) != 0) {
                return 1;
            }
            index1++;
        }

        while (index2 < array2.length) {
            if (Integer.parseInt(array2[index2]) != 0) {
                return -1;
            }
            index2++;
        }
        return 0;
    }

    public static void main(String[] args) {
        String version1 = "1";
        String version2 = "1.0.1";
        int res = new CompareVersionNumbers().compareVersion(version1, version2);
        System.out.println(res);
    }
}
