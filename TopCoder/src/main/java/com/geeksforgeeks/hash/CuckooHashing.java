package com.geeksforgeeks.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshil on 5/26/2016.
 */
//http://www.geeksforgeeks.org/category/hash/
//http://www.geeksforgeeks.org/cuckoo-hashing/
public class CuckooHashing {
    private static final Integer MAX = 11;
    interface HashFunction{
        int hash(int key);
    }

    static class Hash1 implements HashFunction {
        @Override
        public int hash(int key) {
            return key % MAX;
        }
    }

    static class Hash2 implements HashFunction{

        @Override
        public int hash(int key) {
            return (key/MAX)%MAX;
        }
    }

    class Bucket {
        int key;
        int value;
        Bucket(int key, int value){
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
    static List<HashFunction> hashFunctionList = new ArrayList<>();
    static List<Bucket[]> hash = new ArrayList<>();
    static {
        hashFunctionList.add(new Hash1());
        hashFunctionList.add(new Hash2());

        hash.add(new Bucket[MAX]);
        hash.add(new Bucket[MAX]);

    }

    public void put(int key, int value){
        putCuckoo(key, value, 0);
    }

    private void putCuckoo(int key, int value, int hashIndex) {
        HashFunction hashFunction = hashFunctionList.get(hashIndex);
        int hashValue = hashFunction.hash(key);
        Bucket[] buckets = hash.get(hashIndex);
        Bucket bucket = buckets[hashValue];
        if (bucket == null ) {
            buckets[hashValue] = (new Bucket(key, value));
            //hash.get(hashIndex) = buckets;
        } else {
            putCuckoo(bucket.getKey(), bucket.getValue(), (hashIndex+1)%hash.size());
        }
    }

    public Integer get(int key) {
        int index = 0;
        for (HashFunction hashFunction : hashFunctionList){
            int hashIndex = hashFunction.hash(key);
            if (hash.get(index)[hashIndex] != null && hash.get(index)[hashIndex].key == key){
                return hash.get(index)[hashIndex].value;
            }
            index++;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] keys = {20, 50, 53, 75, 100, 67, 105,
            3, 36, 39};
        for ( int i : keys){
            new CuckooHashing().put(i, 0);
        }
    }
}
