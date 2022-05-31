package com.marion.gof.creteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marion
 * @date 2022/5/31 13:48
 */
public class CriteriaDemo {

    public static void main(String[] args) {
        Person p1 = Person.builder()
                .id(1)
                .age(18)
                .gender("male")
                .build();
        Person p2 = Person.builder()
                .id(2)
                .age(15)
                .gender("female")
                .build();
        Person p3 = Person.builder()
                .id(3)
                .age(13)
                .gender("male")
                .build();
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        MaleCriteria maleCriteria = new MaleCriteria();
        List<Person> males = new MaleCriteria().filter(list);
        printList(males);

        FemaleCriteria femaleCriteria = new FemaleCriteria();
        List<Person> females = new FemaleCriteria().filter(list);
        printList(females);

        CriteriaFactory orCriteria = new OrCriteria(maleCriteria, femaleCriteria);
        List<Person> or = orCriteria.filter(list);
        printList(or);

    }

    public static void printList(List<Person> list) {
        list.forEach(System.out::println);
    }
}
