package org.reusable.lenses.appointment;

import org.reusable.either.Either;
import org.reusable.lenses.utils.BuildingError;
import org.reusable.lenses.utils.BuildingObjectFailedException;
import org.reusable.lenses.utils.StringTooLongBuildingError;
import org.reusable.throwing.ThrowingConsumer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class Appointment implements Comparable<Appointment> {
    private final int id;
    private final LocalDate startDate;
    private final AppointmentState state;

    private Appointment(final int id,
                        final LocalDate startDate,
                        final AppointmentState state) {
        this.id = id;
        this.startDate = startDate;
        this.state = state;
    }

    private Appointment(final Appointment appointment) {
        this.id = appointment.id;
        this.startDate = appointment.startDate;
        this.state = appointment.state;
    }

    public Appointment copy() {
        return new Appointment(this);
    }

    public static Appointment create(final int id,
                                     final LocalDate startDate,
                                     final AppointmentState state) {
        final Either<List<BuildingError>, Appointment> either = createSafe(id, startDate, state);
        return fromEither(either);
    }

    public static Either<List<BuildingError>, Appointment> createSafe(final int id,
                                                                      final LocalDate startDate,
                                                                      final AppointmentState state) {
        final AppointmentBuilder tb = new AppointmentBuilder();
        return tb.setId(id)
                .setStartDate(startDate)
                .setState(state)
                .createAppointment();
    }

    private static Appointment fromEither(final Either<List<BuildingError>, Appointment> either) {
        either.ifLeft(ThrowingConsumer.uncheck(errors -> {
            throw new BuildingObjectFailedException("One or more building errors occurred during construction of the object", errors);
        }));
        return either.match(l -> null, r -> r);
    }

    public Appointment withId(final int id) {
        final Either<List<BuildingError>, Appointment> either = withIdSafe(id);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Appointment> withIdSafe(final int id) {
        return new AppointmentBuilder().
                setId(id).
                setStartDate(this.startDate).
                setState(this.state).
                createAppointment();
    }

    public Appointment withStartDate(final LocalDate startDate) {
        final Either<List<BuildingError>, Appointment> either = withStartDateSafe(startDate);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Appointment> withStartDateSafe(final LocalDate startDate) {
        return new AppointmentBuilder().
                setId(this.id).
                setStartDate(startDate).
                setState(this.state).
                createAppointment();
    }

    public Appointment withState(final AppointmentState state) {
        final Either<List<BuildingError>, Appointment> either = withStateSafe(state);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Appointment> withStateSafe(final AppointmentState state) {
        return new AppointmentBuilder().
                setId(this.id).
                setStartDate(this.startDate).
                setState(state).
                createAppointment();
    }

    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public AppointmentState getState() {
        return state;
    }


    private static class AppointmentBuilder {
        private int id;
        private LocalDate startDate;
        private AppointmentState state;

        private final List<BuildingError> errors;

        private AppointmentBuilder() {
            this.errors = new ArrayList<>();
        }

        public AppointmentBuilder setId(final int id) {
            validateId(id);
            this.id = id;
            return this;
        }

        private void validateId(final int id) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public AppointmentBuilder setStartDate(final LocalDate startDate) {
            validateStartDate(startDate);
            this.startDate = startDate;
            return this;
        }

        private void validateStartDate(final LocalDate startDate) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public AppointmentBuilder setState(final AppointmentState state) {
            validateState(state);
            this.state = state;
            return this;
        }

        private void validateState(final AppointmentState state) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public Either<List<BuildingError>, Appointment> createAppointment() {
            final boolean canBuild = validateAll(id, startDate, state);
            if (canBuild) {
                return Either.right(new Appointment(id, startDate, state));
            }

            return Either.left(errors);
        }

        private boolean validateAll(final int id,
                                    final LocalDate startDate,
                                    final AppointmentState state) {
            return errors.size() == 0;
        }

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Appointment that = (Appointment) o;
        return id == that.id && Objects.equals(startDate, that.startDate) && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, state);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", state=" + state +
                '}';
    }

    private static final Comparator<Appointment> NATURAL_COMPARATOR = Comparator
            .comparing(Appointment::getId)
            .thenComparing(Appointment::getStartDate)
            .thenComparing(Appointment::getState);

    @Override
    public int compareTo(final Appointment o) {
        return NATURAL_COMPARATOR.compare(this, o);
    }
}
