package org.reusable.lenses.person;

import com.google.common.collect.ImmutableMap;
import org.reusable.either.Either;
import org.reusable.lenses.appointment.Appointment;
import org.reusable.lenses.utils.BuildingError;
import org.reusable.lenses.utils.BuildingObjectFailedException;
import org.reusable.lenses.utils.StringTooLongBuildingError;
import org.reusable.throwing.ThrowingConsumer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Person implements Comparable<Person> {

    private final String name;
    private final String surname;
    private final ImmutableMap<Integer, Appointment> appointments;

    private Person(final String name,
                   final String surname,
                   final ImmutableMap<Integer, Appointment> appointments) {
        this.name = name;
        this.surname = surname;
        this.appointments = appointments;
    }

    private Person(final Person person) {
        this.name = person.name;
        this.surname = person.surname;
        this.appointments = person.appointments;
    }

    public Person copy() {
        return new Person(this);
    }

    public static Person create(final String name,
                                final String surname,
                                final ImmutableMap<Integer, Appointment> appointments) {
        final Either<List<BuildingError>, Person> either = createSafe(name, surname, appointments);
        return fromEither(either);

    }

    public static Either<List<BuildingError>, Person> createSafe(final String name,
                                                                 final String surname,
                                                                 final ImmutableMap<Integer, Appointment> appointments) {
        return new PersonBuilder().setName(name).setSurname(surname).setAppointments(appointments).createPerson();
    }


    private static Person fromEither(final Either<List<BuildingError>, Person> either) {
        either.ifLeft(ThrowingConsumer.uncheck(errors -> {
            throw new BuildingObjectFailedException("One or more building errors occurred during construction of the object", errors);
        }));
        return either.match(l -> null, r -> r);
    }

    public Person withName(final String name) {
        final Either<List<BuildingError>, Person> either = withNameSafe(name);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Person> withNameSafe(final String name) {
        return new PersonBuilder().
                setName(name).
                setSurname(this.surname).
                setAppointments(this.appointments).
                createPerson();
    }

    public Person withSurname(final String surname) {
        final Either<List<BuildingError>, Person> either = withSurnameSafe(surname);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Person> withSurnameSafe(final String surname) {
        return new PersonBuilder().
                setName(this.name).
                setSurname(surname).
                setAppointments(this.appointments).
                createPerson();
    }


    public Person withAppointments(final ImmutableMap<Integer, Appointment> appointments) {
        final Either<List<BuildingError>, Person> listPersonEither = withAppointmentsSafe(appointments);
        return fromEither(listPersonEither);
    }

    public Either<List<BuildingError>, Person> withAppointmentsSafe(final ImmutableMap<Integer, Appointment> appointments) {
        return new PersonBuilder().
                setName(this.name).
                setSurname(this.surname).
                setAppointments(appointments).
                createPerson();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public ImmutableMap<Integer, Appointment> getAppointments() {
        return appointments;
    }

    private static class PersonBuilder {
        private String name;
        private String surname;
        private ImmutableMap<Integer, Appointment> appointments;

        private final List<BuildingError> errors;

        public PersonBuilder() {
            this.errors = new ArrayList<>();
        }

        public PersonBuilder setName(final String name) {
            validateName(name);
            this.name = name;
            return this;
        }

        private void validateName(final String name) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public PersonBuilder setSurname(final String surname) {
            validateSurname(surname);
            this.surname = surname;
            return this;
        }

        private void validateSurname(final String surname) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public PersonBuilder setAppointments(final ImmutableMap<Integer, Appointment> appointments) {
            validateAppointments(appointments);
            this.appointments = appointments;
            return this;
        }

        private void validateAppointments(final Map<Integer, Appointment> appointments) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public Either<List<BuildingError>, Person> createPerson() {
            final boolean canBuild = validateAll(name, surname, appointments);
            if (canBuild)
                return Either.right(new Person(name, surname, appointments));
            return Either.left(errors);
        }

        private boolean validateAll(final String name,
                                    final String surname,
                                    final Map<Integer, Appointment> appointments) {
            return errors.size() == 0;
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(appointments, person.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, appointments);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", appointments=" + appointments +
                '}';
    }

    private static final Comparator<Person> NATURAL_COMPARATOR = Comparator
            .comparing(Person::getName)
            .thenComparing(Person::getSurname)
            //.thenComparing(Person::getAppointments) // not straightforward to compare so we leave it out
            ;

    @Override
    public int compareTo(final Person o) {
        return NATURAL_COMPARATOR.compare(this, o);
    }

}