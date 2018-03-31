package com.java8.groupby;

/**
 * Created by sshil on 7/2/2016.
 */
public class Person {
    private String name;
    private int age;

    Person(String name, int age) {

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Person{name='%s', age=%d}", name, age);
    }
}
