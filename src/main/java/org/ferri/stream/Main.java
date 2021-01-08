package org.ferri.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println("hello");
        List<Person> people = getPeople();


        //Filter
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
        //females.forEach(System.out::println);

        //Sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getName))
                .collect(Collectors.toList());
        //sorted.forEach(System.out::println);

        //all match
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 8);
        //System.out.println(allMatch);

        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 8);
        //System.out.println(anyMatch);

        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Antonio"));
        //System.out.println(noneMatch);

        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people1) -> {
            //System.out.println(gender);
            people1.forEach(System.out::println);
            //System.out.println();
        });

        Optional<String> oldestFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestFemaleAge.ifPresent(System.out::println);


    }


    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 20, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }

}
