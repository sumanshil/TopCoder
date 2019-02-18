package com.apas;

public class Test {

    public static void main(String[] args) {
        String DELETE_FORWARD_ZONE = "'/name: \"%s\"/{N;s/.*//;x;d;};x;p;${x;p;}'";
        System.out.println(String.format(DELETE_FORWARD_ZONE,"."));

    }
}
