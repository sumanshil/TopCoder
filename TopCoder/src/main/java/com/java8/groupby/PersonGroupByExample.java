package com.java8.groupby;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sshil on 7/2/2016.
 */
public class PersonGroupByExample {

    public static void main(String[] args) {
        Stream<Person> people = Stream.of(new Person("Paul", 24), new Person("Mark", 30), new Person("Will", 28));
        Map<Integer, List<String>> peopleByAge
            = people.collect(Collectors.groupingBy(Person::getAge,
                                                   Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println(peopleByAge);
    }
}
