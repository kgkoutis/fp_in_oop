package org.reusable.lenses.person;

import com.google.common.collect.ImmutableMap;
import org.reusable.lenses.Lens;
import org.reusable.lenses.appointment.Appointment;

public final class PersonOptics {
    public static final Lens<Person, String> personNameLens = Lens.of(Person::getName, newName -> person -> person.withName(newName));
    public static final Lens<Person, String> personSurnameLens = Lens.of(Person::getSurname, newSurname -> person -> person.withSurname(newSurname));
    public static final Lens<Person, ImmutableMap<Integer, Appointment>> personAppointmentsLens = Lens.of(Person::getAppointments, newMap -> person -> person.withAppointments(newMap));
}
