package org.course.composability.sideeffects.before;

import java.util.Objects;
import java.util.UUID;

public class Person {
    private final UUID id;
    private final int age;
    public Person(UUID uuid, int age) {
        this.id = uuid;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age);
    }
}
