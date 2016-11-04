package com.dzone;

/**
 * Created by sshil on 10/4/16.
 */
public abstract class AbstractVending implements Ivending {
    public void start()
    {
        System.out.println("Start Vending machine");
    }
    public void stop()
    {
        System.out.println("Stop Vending machine");
    }

    public void process()
    {
        start();
        chooseProduct();
        stop();
    }
}
