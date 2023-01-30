package org.course.composability.sideeffects.after;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("in MainWithThrowingFunction class")
class MainTests {
    @Nested
    @DisplayName("for insertToInputStore method")
    class InsertToInputStoreTests {

        private Method method;

        @BeforeEach
        void setUp() throws NoSuchMethodException {
            Method method = Main.class.getDeclaredMethod("insertPersonsIntoStore", List.class, Consumer.class, Consumer.class);
            method.setAccessible(true);
            this.method = method;
        }

        @Nested
        @DisplayName("given persons list inserted into store with age average above 20")
        class AgeAverageAbove20Tests {
            private Method method() {
                return InsertToInputStoreTests.this.method;
            }

            private List<Person> persons;

            @BeforeEach
            void createNewStructurallyGoodListWithAverageAbove20() {
                // Arrange
                Person p1 = new Person(UUID.fromString("b15ca0a3-7d10-409f-ae6d-42e491a77671"), 40);
                Person p2 = new Person(UUID.fromString("6223a2e6-7d04-46ef-82cb-4b1116c989a8"), 10);
                Person p3 = new Person(UUID.fromString("5376de6c-7ecc-4aa3-a200-984a042e99a9"), 30);
                Person p4 = new Person(UUID.fromString("b96599db-61c7-46f4-a537-b8a22736fd00"), 25);
                this.persons = List.of(p1, p2, p3, p4);
            }

            @Test
            @DisplayName("when retrieved from persons store, should return output list same as input list")
            public void when_retrieved_from_personsStore___should_return_outputList_same_as_inputList() throws InvocationTargetException, IllegalAccessException {
                // Arrange
                final List<List<Person>> output = new ArrayList<>();
                Consumer<List<Person>> callback = output::add;

                AtomicInteger timesIllegalArgumentExceptionWasThrown = new AtomicInteger();
                Consumer<String> throwIllegalArgumentException = (s) -> {
                    timesIllegalArgumentExceptionWasThrown.getAndIncrement();
                };

                // Act
                method().invoke(null, persons, callback, throwIllegalArgumentException);

                // Assert
                assertEquals(1, output.size());
                assertEquals(4, output.get(0).size());
                List<Person> personsSaved = output.get(0);
                assertEquals(4, personsSaved.size());
                for (int i = 0; i < personsSaved.size(); i++) {
                    assertEquals(persons.get(i), personsSaved.get(i)); // by reference
                    assertEquals(persons.get(i).getId(), personsSaved.get(i).getId()); // by value
                    assertEquals(persons.get(i).getAge(), personsSaved.get(i).getAge()); // by value
                }
            }

            @Test
            @DisplayName("when retrieved from persons store, callback should be called exactly once")
            public void when_retrieved_from_personsStore___callback_should_be_called_exactly_once() throws InvocationTargetException, IllegalAccessException {
                // Arrange
                AtomicInteger timesCallbackWasCalled = new AtomicInteger();
                final List<List<Person>> output = new ArrayList<>();
                Consumer<List<Person>> callback = (list) -> {
                    timesCallbackWasCalled.getAndIncrement();
                };

                // Act
                method().invoke(null, persons, callback, null);

                // Assert
                assertEquals(1, timesCallbackWasCalled.get());
            }

            @Test
            @DisplayName("when retrieved from persons store, no exceptions should be thrown by the exception handler")
            public void when_retrieved_from_personsStore___no_exceptions_should_be_thrown_by_the_exception_handler() throws InvocationTargetException, IllegalAccessException {
                // Arrange
                final List<List<Person>> output = new ArrayList<>();
                Consumer<List<Person>> callback = output::add;

                AtomicInteger timesIllegalArgumentExceptionWasThrown = new AtomicInteger();
                Consumer<String> throwIllegalArgumentException = (s) -> {
                    timesIllegalArgumentExceptionWasThrown.getAndIncrement();
                };

                // Act
                method().invoke(null, persons, callback, throwIllegalArgumentException);

                // Assert
                assertEquals(0, timesIllegalArgumentExceptionWasThrown.get());
            }
        }

        @Nested
        @DisplayName("given persons list inserted into store with age below above 20")
        class AgeAverageBelow20Tests {
            private Method method() {
                return InsertToInputStoreTests.this.method;
            }

            private List<Person> persons;

            @BeforeEach
            void createNewStructurallyGoodListWithAverageBelow20() {
                Person p1 = new Person(UUID.fromString("b15ca0a3-7d10-409f-ae6d-42e491a77671"), 10);
                Person p2 = new Person(UUID.fromString("6223a2e6-7d04-46ef-82cb-4b1116c989a8"), 10);
                Person p3 = new Person(UUID.fromString("5376de6c-7ecc-4aa3-a200-984a042e99a9"), 20);
                Person p4 = new Person(UUID.fromString("b96599db-61c7-46f4-a537-b8a22736fd00"), 20);
                this.persons = List.of(p1, p2, p3, p4);
            }

            @Test
            @DisplayName("when retrieved from persons store, should return output list same as input list but with ages of persons incremented by 9000")
            public void when_retrieved_from_personsStore___should_return_outputList_same_as_inputList() throws InvocationTargetException, IllegalAccessException {
                // Arrange
                final List<List<Person>> output = new ArrayList<>();
                Consumer<List<Person>> callback = output::add;

                AtomicInteger timesIllegalArgumentExceptionWasThrown = new AtomicInteger();
                Consumer<String> throwIllegalArgumentException = (s) -> {
                    timesIllegalArgumentExceptionWasThrown.getAndIncrement();
                };

                // Act
                method().invoke(null, persons, callback, throwIllegalArgumentException);

                // Assert
                assertEquals(1, output.size());
                assertEquals(4, output.get(0).size());
                List<Person> personsSaved = output.get(0);
                assertEquals(4, personsSaved.size());
                for (int i = 0; i < personsSaved.size(); i++) {
                    Person expectedPerson = new Person(persons.get(i).getId(), persons.get(i).getAge() + 9000);
                    assertEquals(expectedPerson, personsSaved.get(i)); // by reference
                    assertEquals(expectedPerson.getId(), personsSaved.get(i).getId()); // by value
                    assertEquals(expectedPerson.getAge(), personsSaved.get(i).getAge()); // by value
                }
            }

            @Test
            @DisplayName("when retrieved from persons store, callback should be called exactly once")
            public void when_retrieved_from_personsStore___callback_should_be_called_exactly_once() throws InvocationTargetException, IllegalAccessException {
                // Arrange
                AtomicInteger timesCallbackWasCalled = new AtomicInteger();
                final List<List<Person>> output = new ArrayList<>();
                Consumer<List<Person>> callback = (list) -> {
                    timesCallbackWasCalled.getAndIncrement();
                };

                // Act
                method().invoke(null, persons, callback, null);

                // Assert
                assertEquals(1, timesCallbackWasCalled.get());
            }

            @Test
            @DisplayName("when retrieved from persons store, no exceptions should be thrown by the exception handler")
            public void when_retrieved_from_personsStore___no_exceptions_should_be_thrown_by_the_exception_handler() throws InvocationTargetException, IllegalAccessException {
                // Arrange
                final List<List<Person>> output = new ArrayList<>();
                Consumer<List<Person>> callback = output::add;

                AtomicInteger timesIllegalArgumentExceptionWasThrown = new AtomicInteger();
                Consumer<String> throwIllegalArgumentException = (s) -> {
                    timesIllegalArgumentExceptionWasThrown.getAndIncrement();
                };

                // Act
                method().invoke(null, persons, callback, throwIllegalArgumentException);

                // Assert
                assertEquals(0, timesIllegalArgumentExceptionWasThrown.get());
            }
        }

        @Nested
        @DisplayName("given persons list inserted into store with age equal to 20")
        class AgeAverageEqualTo20Tests {
            private Method method() {
                return InsertToInputStoreTests.this.method;
            }

            private List<Person> persons;

            @BeforeEach
            void createNewStructurallyGoodListWithAverageEqualTo20() {
                Person p1 = new Person(UUID.fromString("b15ca0a3-7d10-409f-ae6d-42e491a77671"), 20);
                Person p2 = new Person(UUID.fromString("6223a2e6-7d04-46ef-82cb-4b1116c989a8"), 30);
                Person p3 = new Person(UUID.fromString("5376de6c-7ecc-4aa3-a200-984a042e99a9"), 10);
                Person p4 = new Person(UUID.fromString("b96599db-61c7-46f4-a537-b8a22736fd00"), 20);
                this.persons = List.of(p1, p2, p3, p4);
            }

            @Test
            @DisplayName("when retrieved from persons store, should return output list same as input list but with ages of persons incremented by 9000")
            public void when_retrieved_from_personsStore___should_return_outputList_same_as_inputList() throws InvocationTargetException, IllegalAccessException {
                // Arrange
                final List<List<Person>> output = new ArrayList<>();
                Consumer<List<Person>> callback = output::add;

                AtomicInteger timesIllegalArgumentExceptionWasThrown = new AtomicInteger();
                Consumer<String> throwIllegalArgumentException = (s) -> {
                    timesIllegalArgumentExceptionWasThrown.getAndIncrement();
                };

                // Act
                method().invoke(null, persons, callback, throwIllegalArgumentException);

                // Assert
                assertEquals(1, output.size());
                assertEquals(4, output.get(0).size());
                List<Person> personsSaved = output.get(0);
                assertEquals(4, personsSaved.size());
                for (int i = 0; i < personsSaved.size(); i++) {
                    Person expectedPerson = new Person(persons.get(i).getId(), persons.get(i).getAge() + 9000);
                    assertEquals(expectedPerson, personsSaved.get(i)); // by reference
                    assertEquals(expectedPerson.getId(), personsSaved.get(i).getId()); // by value
                    assertEquals(expectedPerson.getAge(), personsSaved.get(i).getAge()); // by value
                }
            }

            @Test
            @DisplayName("when retrieved from persons store, callback should be called exactly once")
            public void when_retrieved_from_personsStore___callback_should_be_called_exactly_once() throws InvocationTargetException, IllegalAccessException {
                // Arrange
                AtomicInteger timesCallbackWasCalled = new AtomicInteger();
                final List<List<Person>> output = new ArrayList<>();
                Consumer<List<Person>> callback = (list) -> {
                    timesCallbackWasCalled.getAndIncrement();
                };

                // Act
                method().invoke(null, persons, callback, null);

                // Assert
                assertEquals(1, timesCallbackWasCalled.get());
            }

            @Test
            @DisplayName("when retrieved from persons store, no exceptions should be thrown by the exception handler")
            public void when_retrieved_from_personsStore___no_exceptions_should_be_thrown_by_the_exception_handler() throws InvocationTargetException, IllegalAccessException {
                // Arrange
                final List<List<Person>> output = new ArrayList<>();
                Consumer<List<Person>> callback = output::add;

                AtomicInteger timesIllegalArgumentExceptionWasThrown = new AtomicInteger();
                Consumer<String> throwIllegalArgumentException = (s) -> {
                    timesIllegalArgumentExceptionWasThrown.getAndIncrement();
                };

                // Act
                method().invoke(null, persons, callback, throwIllegalArgumentException);

                // Assert
                assertEquals(0, timesIllegalArgumentExceptionWasThrown.get());
            }
        }

        @Nested
        @DisplayName("given persons list not structurally good")
        class ListIsNotStructurallyGood {

            @Nested
            @DisplayName("when list of persons is null")
            class OneListOfPersonsIsNull {

                private Method method() {
                    return InsertToInputStoreTests.this.method;
                }

                private List<Person> persons;

                @BeforeEach
                void createNewListWithOneOfPersonsEqualToNull() {
                    this.persons = null;
                }

                @Test
                @DisplayName("should throw an IllegalArgumentException and not call the callback handler")
                public void should_throw_an_IllegalArgumentException_and_not_call_the_callback_handler() throws InvocationTargetException, IllegalAccessException {
                    // Arrange
                    final List<List<Person>> output = new ArrayList<>();
                    AtomicInteger timesCallbackWasCalled = new AtomicInteger();
                    Consumer<List<Person>> callback = e -> {
                        output.add(e);
                        timesCallbackWasCalled.getAndIncrement();
                    };

                    AtomicInteger timesIllegalArgumentExceptionWasThrown = new AtomicInteger();
                    Consumer<String> throwIllegalArgumentException = (s) -> {
                        timesIllegalArgumentExceptionWasThrown.getAndIncrement();
                    };

                    // Act
                    method().invoke(null, persons, callback, throwIllegalArgumentException);

                    // Assert
                    assertEquals(1, timesIllegalArgumentExceptionWasThrown.get());
                    assertEquals(0, timesCallbackWasCalled.get());
                }
            }

            @Nested
            @DisplayName("when list of persons is empty")
            class OneListOfPersonsIsEmpty {

                private Method method() {
                    return InsertToInputStoreTests.this.method;
                }

                private List<Person> persons;

                @BeforeEach
                void createNewListWithOneOfPersonsEqualToNull() {
                    this.persons = List.of();
                }

                @Test
                @DisplayName("should throw an IllegalArgumentException and not call the callback handler")
                public void should_throw_an_IllegalArgumentException_and_not_call_the_callback_handler() throws InvocationTargetException, IllegalAccessException {
                    // Arrange
                    final List<List<Person>> output = new ArrayList<>();
                    AtomicInteger timesCallbackWasCalled = new AtomicInteger();
                    Consumer<List<Person>> callback = e -> {
                        output.add(e);
                        timesCallbackWasCalled.getAndIncrement();
                    };

                    AtomicInteger timesIllegalArgumentExceptionWasThrown = new AtomicInteger();
                    Consumer<String> throwIllegalArgumentException = (s) -> {
                        timesIllegalArgumentExceptionWasThrown.getAndIncrement();
                    };

                    // Act
                    method().invoke(null, persons, callback, throwIllegalArgumentException);

                    // Assert
                    assertEquals(1, timesIllegalArgumentExceptionWasThrown.get());
                    assertEquals(0, timesCallbackWasCalled.get());
                }
            }

            @Nested
            @DisplayName("when one of the persons in the list is equal to null")
            class OneOfPersonsIsNull {

                private Method method() {
                    return InsertToInputStoreTests.this.method;
                }

                private List<Person> persons;

                @BeforeEach
                void createNewListWithOneOfPersonsEqualToNull() {
                    this.persons = new ArrayList<>();
                    Person p1 = new Person(UUID.fromString("b15ca0a3-7d10-409f-ae6d-42e491a77671"), 20);
                    Person p2 = null;
                    Person p3 = new Person(UUID.fromString("5376de6c-7ecc-4aa3-a200-984a042e99a9"), 10);
                    Person p4 = new Person(UUID.fromString("b96599db-61c7-46f4-a537-b8a22736fd00"), 20);
                    this.persons.add(p1);
                    this.persons.add(p2);
                    this.persons.add(p3);
                    this.persons.add(p4);
                }

                @Test
                @DisplayName("should throw an IllegalArgumentException and not call the callback handler")
                public void should_throw_an_IllegalArgumentException_and_not_call_the_callback_handler() throws InvocationTargetException, IllegalAccessException {
                    // Arrange
                    final List<List<Person>> output = new ArrayList<>();
                    AtomicInteger timesCallbackWasCalled = new AtomicInteger();
                    Consumer<List<Person>> callback = e -> {
                        output.add(e);
                        timesCallbackWasCalled.getAndIncrement();
                    };

                    AtomicInteger timesIllegalArgumentExceptionWasThrown = new AtomicInteger();
                    Consumer<String> throwIllegalArgumentException = (s) -> {
                        timesIllegalArgumentExceptionWasThrown.getAndIncrement();
                    };

                    // Act
                    method().invoke(null, persons, callback, throwIllegalArgumentException);

                    // Assert
                    assertEquals(1, timesIllegalArgumentExceptionWasThrown.get());
                    assertEquals(0, timesCallbackWasCalled.get());
                }
            }
        }
    }
}
