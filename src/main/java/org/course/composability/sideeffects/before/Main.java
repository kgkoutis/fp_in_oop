package org.course.composability.sideeffects.before;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This is a mini application that uses a database to store and retrieve data.
 * Person is a database model class.
 * PersonsRepository is a class that abstracts the database interaction for the Person class. It follows the repository pattern.
 * SqliteConnector is a class that is responsible for the actual database interaction.
 *
 * The application mixes the database interaction with the business logic in the insertPersonsIntoStore method.
 * That makes the business logic hard to unit test because it is mixed with side effects. Unit tests need to basically
 * run on memory, they cannot call external dependencies.
 *
 * Functional programming is "allergic" to side effects. Everything that talks to the outside world is a side effect.
 * How do we separate the parts of the code that are "side effect free" from the parts that are not?
 */
public class Main {
    public static void main(String[] args) {
        PersonsRepository personsRepository = new PersonsRepository();
        Person p1 = new Person(UUID.randomUUID(), 40);
        Person p2 = new Person(UUID.randomUUID(), 10);
        Person p3 = new Person(UUID.randomUUID(), 30);
        Person p4 = new Person(UUID.randomUUID(), 25);

        List<Person> persons = List.of(p1, p2, p3, p4);
        insertPersonsIntoStore(persons, personsRepository);
        UUID id = persons.get(0).getId();
        Optional<Person> personFromRepository = personsRepository.getPerson(id);

        if (personFromRepository.isPresent()) {
            System.out.println("Person with id: " + id + " was found in the database");
        } else {
            System.out.println("Person with id: " + id + " was not found in the database");
        }
    }

    private static void insertPersonsIntoStore(List<Person> persons, PersonsRepository personsRepository) {
        boolean structurallyGood = persons != null &&
                !persons.isEmpty() &&
                persons.stream().allMatch(Objects::nonNull);
        boolean ageAverageIsAbove20 = structurallyGood && persons.stream()
                .map(Person::getAge)
                .collect(Collectors.averagingDouble(i -> i)) > 20.0;

        if (ageAverageIsAbove20) {
            personsRepository.insertPersons(persons); // This is the side effect
        } else if (structurallyGood) {
            persons = persons.stream().map(p -> new Person(p.getId(), p.getAge() + 9000)).collect(Collectors.toList());
            personsRepository.insertPersons(persons); // This is the side effect
        } else {
            throw new IllegalArgumentException("Persons list is not structurally good");
        }
    }
}

