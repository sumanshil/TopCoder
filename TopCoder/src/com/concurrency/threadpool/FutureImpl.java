package com.concurrency.threadpool;

public class  FutureImpl<T> implements Future {

    private boolean isResultAvailable = false;

    private T result;

    @Override
    public T get() {
        synchronized (this) {
            while(!isResultAvailable) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
            return result;
        }
    }

    public synchronized void set(T result) {
        this.result = result;
        this.isResultAvailable = true;
        notify();
    }

}
