package com.java8.lambda;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTryOut {
    private final int i = 10;
    private static void staticM1(String msg) {
        System.out.println("Static method called: " + msg);
    }
    private void instanceM1(String msg) {
        System.out
                .println("Instance method called: " + i + ", " + msg);
    }
    public static void main(String[] args) {
        try {
            MethodType desc = MethodType.methodType(void.class, String.class);
            MethodHandle mh = MethodHandles.lookup()
                    .findStatic(MethodHandleTryOut.class, "staticM1", desc);
            MethodHandle mh2 = MethodHandles.lookup()
                    .findVirtual(MethodHandleTryOut.class, "instanceM1", desc);
            mh.invoke("Hey");
            mh2.invoke(new MethodHandleTryOut(), "Hey");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
