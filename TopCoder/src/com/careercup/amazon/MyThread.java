package com.careercup.amazon;

public class MyThread extends Thread {
    public void run(){
    	if (true)
    		throw new RuntimeException();
    }
}
