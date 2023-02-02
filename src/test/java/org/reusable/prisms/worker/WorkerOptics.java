package org.reusable.prisms.worker;

import org.reusable.lenses.Lens;
import org.reusable.maybe.Maybe;
import org.reusable.prisms.car.Car;

public final class WorkerOptics {
    public static final Lens<Worker, String> leaderNameLens = Lens.of(Worker::getName, newName -> worker -> worker.withName(newName));
    public static final Lens<Worker, Integer> leaderSalaryLens = Lens.of(Worker::getSalary, newSalary -> worker -> worker.withSalary(newSalary));
    public static final Lens<Worker, Maybe<Car>> leaderCarLens = Lens.of(Worker::getCar, newCar -> worker -> worker.withCar(newCar));
}
