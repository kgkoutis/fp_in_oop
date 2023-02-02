package org.reusable.lenses.appointment;

import org.reusable.lenses.Lens;

import java.time.LocalDate;

public final class AppointmentOptics {
    public static final Lens<Appointment, Integer> appointmentIdLens = Lens.of(Appointment::getId, newId -> appointment -> appointment.withId(newId));
    public static final Lens<Appointment, LocalDate> appointmentLocalDateLens = Lens.of(Appointment::getStartDate, newStartDate -> appointment -> appointment.withStartDate(newStartDate));
    public static final Lens<Appointment, AppointmentState> appointmentStateLens = Lens.of(Appointment::getState, newState -> appointment -> appointment.withState(newState));
}
