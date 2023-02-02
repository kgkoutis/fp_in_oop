package org.reusable.lenses;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.reusable.lenses.appointment.Appointment;
import org.reusable.lenses.appointment.AppointmentOptics;
import org.reusable.lenses.appointment.AppointmentState;
import org.reusable.lenses.car.Car;
import org.reusable.lenses.car.CarOptics;
import org.reusable.lenses.leader.Leader;
import org.reusable.lenses.leader.LeaderOptics;
import org.reusable.lenses.person.Person;
import org.reusable.lenses.person.PersonOptics;
import org.reusable.lenses.team.Team;
import org.reusable.lenses.team.TeamOptics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.reusable.lenses.LensCompositions.lens;

public class LensesTests {

    @Test
    public void lensMutate() {
        // Arrange
        final Car car = Car.create("Ferrari", "335 S Spider Scaglietti", 10000);
        final Leader leader = Leader.create("Lionel Messi", 41000000, car);
        final Team team = Team.create("Barcelona", "Spain", leader);
        final Lens<Team, Integer> bookLeaderCarMileage = lens(TeamOptics.teamLeaderLens, LeaderOptics.leaderCarLens, CarOptics.carMileageLens);
        // Act
        final Integer mileage = bookLeaderCarMileage.get(team);
        final Team team2 = bookLeaderCarMileage.set(25000, team);
        // Assert
        assertEquals(20000, mileage);
        assertEquals(25000, team2.getLeader().getCar().getMileage());
    }

    @Test
    public void collectionsMutate() {
        // Arrange
        final DateTimeFormatter df = DateTimeFormatter.ofPattern("d/M/yyyy");

        final ImmutableMap.Builder<Integer, Appointment> builder = ImmutableMap.builder();
        builder.put(1, Appointment.create(1, LocalDate.parse("1/1/2010", df), AppointmentState.NotArrived));
        builder.put(2, Appointment.create(2, LocalDate.parse("2/1/2010", df), AppointmentState.NotArrived));
        builder.put(3, Appointment.create(3, LocalDate.parse("3/1/2010", df), AppointmentState.NotArrived));
        final ImmutableMap<Integer, Appointment> map = builder.build();

        final Person person = Person.create("Kostas", "Gkoutis", map);
        // Act
        final Person person2 = arrive(2).set(AppointmentState.Arrived, person);
        // Assert
        assertEquals(AppointmentState.Arrived, person2.getAppointments().get(2).getState());
    }

    private Lens<Person, AppointmentState> arrive(final int id) {
        return lens(PersonOptics.personAppointmentsLens, LensesTests.item(id), AppointmentOptics.appointmentStateLens);
    }

    /**
     * Lens for an item in a ImmutableMap.
     * */
    public static <K, V> Lens<ImmutableMap<K, V>, V> item(final K key) {
        return Lens.of(
                map -> map.get(key),
                value -> map -> addOrUpdate(key, value, map));
    }

    /**
     * Create a new ImmutableMap with the given key and value added or updated.
     * */
    private static <K, V> ImmutableMap<K, V> addOrUpdate(final K key,
                                                         final V value,
                                                         final ImmutableMap<K, V> map) {
        final ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        map.forEach((k, v) -> {
            if (k != key) {
                builder.put(k, v);
            }
        });
        builder.put(key, value);
        return builder.build();
    }
}

