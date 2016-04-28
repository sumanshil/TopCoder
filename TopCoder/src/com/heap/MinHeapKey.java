package com.heap;

/**
 * Created by sshil on 4/18/2016.
 */
public class MinHeapKey {
    private Object key;

    public MinHeapKey(Object key) {
        this.key = key;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public int hashCode(){
        return this.key.hashCode();
    }

    public boolean equals(Object other){
        return ((MinHeapKey) other).getKey().equals(this.getKey());
    }
}
