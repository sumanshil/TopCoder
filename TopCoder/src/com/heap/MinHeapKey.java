package com.heap;

/**
 * Created by sshil on 4/18/2016.
 */
public class MinHeapKey<T extends Object> {
    private T key;

    public MinHeapKey(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public int hashCode(){
        return this.key.hashCode();
    }

    @Override
    public boolean equals(Object other){
        return ((MinHeapKey) other).getKey().equals(this.getKey());
    }
}
