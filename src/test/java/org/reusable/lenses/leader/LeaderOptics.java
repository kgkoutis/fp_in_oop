package org.reusable.lenses.leader;

import org.reusable.lenses.Lens;
import org.reusable.lenses.car.Car;

public final class LeaderOptics {
    public static final Lens<Leader, String> leaderFullNameLens = Lens.of(Leader::getFullName, newFullName -> leader -> leader.withFullName(newFullName));
    public static final Lens<Leader, Integer> leaderSalaryLens = Lens.of(Leader::getSalary, newSalary -> leader -> leader.withSalary(newSalary));
    public static final Lens<Leader, Car> leaderCarLens = Lens.of(Leader::getCar, newCar -> leader -> leader.withCar(newCar));
}
