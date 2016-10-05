package com.dzone;

/**
 * Created by sshil on 10/4/16.
 */
public class CandyVending implements Ivending {

    AbstractVendingDelegator delegator = new AbstractVendingDelegator();
    private class AbstractVendingDelegator extends AbstractVending {
        @Override
        public void chooseProduct() {
            System.out.println("Produce diiferent candies");
            System.out.println("Choose a type of candy");
            System.out.println("pay for candy");
            System.out.println("collect candy");
        }
    }

    @Override
    public void start() {
        delegator.start();
    }

    @Override
    public void chooseProduct() {
        delegator.chooseProduct();
    }

    @Override
    public void stop() {
        delegator.stop();
    }

    @Override
    public void process() {
        delegator.process();
    }
}
