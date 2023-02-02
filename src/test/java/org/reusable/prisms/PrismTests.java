package org.reusable.prisms;

import org.junit.jupiter.api.Test;
import org.reusable.lenses.LensExtensions;
import org.reusable.maybe.Just;
import org.reusable.maybe.Maybe;
import org.reusable.maybe.Nothing;
import org.reusable.prisms.car.Car;
import org.reusable.prisms.car.CarOptics;
import org.reusable.prisms.job.Job;
import org.reusable.prisms.job.JobOptics;
import org.reusable.prisms.worker.Worker;
import org.reusable.prisms.worker.WorkerOptics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.reusable.prisms.PrismCompositions.prism;

public class PrismTests {
    private static final Prism<Job, Integer> jobWorkerCarMileage =
            prism(LensExtensions.toPrism(JobOptics.leaderWorkerLens), LensExtensions.mToPrism(WorkerOptics.leaderCarLens), LensExtensions.toPrism(CarOptics.carMileageLens));

    @Test
    public void prismShouldGetSome() {
        // Arrange
        final Maybe<Integer> expected = Just.of(20000);
        final Car car = Car.create("Maserati", "Ghibli", 20000);
        final Worker worker = Worker.create("Joe Bloggs", 50000, Just.of(car));
        final Job job = Job.create("Software Engineer", "Write code", worker);
        // Act
        final Maybe<Integer> actual = jobWorkerCarMileage.get(job);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void prismShouldGetNone() {
        // Arrange
        final Maybe<Integer> expected = Maybe.<Integer>nothing();

        final Worker worker = Worker.create("Joe Bloggs", 50000, Nothing.get());
        final Job job = Job.create("Software Engineer", "Write code", worker);
        // Act
        final Maybe<Integer> actual = jobWorkerCarMileage.get(job);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void prismShouldSetWhenOptionalPartOfChainIsSome() {
        // Arrange
        final Car expectedCar = Car.create("Maserati", "Ghibli", 30000);
        final Worker expectedWorker = Worker.create("Joe Bloggs", 50000, Just.of(expectedCar));
        final Job expected = Job.create("Software Engineer", "Write code", expectedWorker);

        final Car car = Car.create("Maserati", "Ghibli", 20000);
        final Worker worker = Worker.create("Joe Bloggs", 50000, Just.of(car));
        final Job job = Job.create("Software Engineer", "Write code", worker);
        // Act
        final Job actual = jobWorkerCarMileage.set(30000, job);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void prismShouldNotSetWhenOptionalPartOfChainIsNone() {
        // Arrange
        final Worker expectedWorker = Worker.create("Joe Bloggs", 50000, Maybe.nothing());
        final Job expected = Job.create("Programmer", "Write code and tests.", expectedWorker);

        final Worker worker = Worker.create("Joe Bloggs", 50000, Nothing.get());
        final Job job = Job.create("Programmer", "Write code and tests.", worker);

        final Job actual = jobWorkerCarMileage.set(25000, job);

        // Assert
        assertEquals(expected, actual);
    }
}
