package org.juandavyc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Person {

    private final String name = "People";
    private final Integer age;

    public Person(Integer age) {
        this.age = age;
    }


}

