package org.course.composability.sideeffects.after;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.reusable.Lists.listOf;

/**
 * The code below behaves the same as the code in the before package. We have also written unit tests for the insertPersonsIntoStore method.
 * How does all this work? Well in the pureAndTestablePart method we have passed callbacks that are doing the same thing as the code in the before package.
 * You can already see that we can either a) pass callbacks that are doing the same thing as the code in the before package
 * or b) pass callbacks that are pure functions doing something else.
 *
 * And that is how we mock the side effects in our tests. Notice that our tests do not import any mocking library/framework,
 * they only use an assertion library (JUnit 5), for convenience. In FP languages there is no concept of mocks, spies, test doubles etc.
 * It's all about functions and callbacks!
 *
 * Challenge question: why do we need to mock with a callback the createPersons method?
 * Challenge question2: what are the cons of this approach?
 * Challenge question2: Looking back at the before package, there was a missed opportunity to test the code without mocking it. Can you find it?
 * (hint: it has to do something with OOP)
 * */
public class Main {
    public static void main(final String[] args) {
        // dirty part with side effects
        final PersonsRepository personsRepository = new PersonsRepository();

        final Supplier<List<Person>> createPersons = () -> {
            final Person p1 = new Person(UUID.randomUUID(), 40);
            final Person p2 = new Person(UUID.randomUUID(), 10);
            final Person p3 = new Person(UUID.randomUUID(), 30);
            final Person p4 = new Person(UUID.randomUUID(), 25);

            return listOf(p1, p2, p3, p4);
        };

        final Consumer<List<Person>> saveInDB = personsRepository::insertPersons;

        final Function<UUID, Optional<Person>> getPersonFromRepository = personsRepository::getPerson;

        final Consumer<String> print = System.out::println;

        final Consumer<String> throwIllegalArgumentException = (s) -> { throw new IllegalArgumentException(s); };

        // pure and testable part
        pureAndTestablePart(createPersons, saveInDB, getPersonFromRepository, print, throwIllegalArgumentException);
    }

    private static void pureAndTestablePart(final Supplier<List<Person>> createPersons,// this can be a pure function!
                                            final Consumer<List<Person>> saveInStore,// this can be a pure function!
                                            final Function<UUID, Optional<Person>> getPersonFromRepository,// this can be a pure function!
                                            final Consumer<String> print, final Consumer<String> throwIllegalArgumentException// this can be a pure function!
    ) {
        final List<Person> persons = createPersons.get();
        insertPersonsIntoStore(persons, saveInStore, throwIllegalArgumentException);
        final UUID id = persons.get(0).getId();
        final Optional<Person> personFromDatabase = getPersonFromRepository.apply(id);

        if (personFromDatabase.isPresent()) {
            print.accept("Person with id: " + id + " was found in the database");
        } else {
            print.accept("Person with id: " + id + " was not found in the database");
        }
    }

    private static void insertPersonsIntoStore(List<Person> persons,
                                               final Consumer<List<Person>> saveInStore, // this can be a pure function!
                                               final Consumer<String> throwIllegalArgumentException // this can be a pure function!
    ) {
        final boolean structurallyGood = persons != null &&
                !persons.isEmpty() &&
                persons.stream().allMatch(Objects::nonNull);
        final boolean ageAverageIsAbove20 = structurallyGood && persons.stream()
                .map(Person::getAge)
                .collect(Collectors.averagingDouble(i -> i)) > 20.0;

        if (ageAverageIsAbove20) {
            saveInStore.accept(persons);
        } else if (structurallyGood) {
            persons = persons.stream().map(p -> new Person(p.getId(), p.getAge() + 9000)).collect(Collectors.toList());
            saveInStore.accept(persons);
        } else {
            throwIllegalArgumentException.accept("Persons list is not structurally good");
        }
    }
}