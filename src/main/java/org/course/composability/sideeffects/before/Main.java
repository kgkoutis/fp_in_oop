package org.course.composability.sideeffects.before;

import java.util.*;

import java.util.stream.Collectors;

import static org.reusable.Lists.*;

/**
 * This is a mini application that uses a database to store and retrieve data.
 * Person is a database model class.
 * PersonsRepository is a class that abstracts the database interaction for the Person class. It follows the repository pattern.
 * SqliteConnector is a class that is responsible for the actual database interaction.
 * <p>
 * The application mixes the database interaction with the business logic in the insertPersonsIntoStore method.
 * That makes the business logic hard to unit test because it is mixed with side effects. Unit tests need to basically
 * run on memory, they cannot call external dependencies.
 * <p>
 * Functional programming is "allergic" to side effects. Everything that talks to the outside world is a side effect.
 * How do we separate the parts of the code that are "side effect free" from the parts that are not?
 */
public class Main {
    public static void main(final String[] args) {
        final PersonsRepository personsRepository = new PersonsRepository();
        final Person p1 = new Person(UUID.randomUUID(), 40);
        final Person p2 = new Person(UUID.randomUUID(), 10);
        final Person p3 = new Person(UUID.randomUUID(), 30);
        final Person p4 = new Person(UUID.randomUUID(), 25);

        final List<Person> persons = listOf(p1, p2, p3, p4);
        insertPersonsIntoStore(persons, personsRepository);
        final UUID id = persons.get(0).getId();
        final Optional<Person> personFromRepository = personsRepository.getPerson(id);

        if (personFromRepository.isPresent()) {
            System.out.println("Person with id: " + id + " was found in the database");
        } else {
            System.out.println("Person with id: " + id + " was not found in the database");
        }
    }

    private static void insertPersonsIntoStore(List<Person> persons,
                                               final PersonsRepository personsRepository) {
        // validate the input
        final boolean structurallyGood = persons != null &&
                !persons.isEmpty() &&
                persons.stream().allMatch(Objects::nonNull);
        final boolean ageAverageIsAbove20 = structurallyGood && persons.stream()
                .map(Person::getAge)
                .collect(Collectors.averagingDouble(i -> i)) > 20.0;

        // do the database work
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

