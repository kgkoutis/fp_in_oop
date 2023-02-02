package org.reusable.prisms.car;

import org.reusable.lenses.Lens;

public final class CarOptics {
    public static final Lens<Car, String> carMakeLens = Lens.of(Car::getMake, newMake -> car -> car.withMake(newMake));
    public static final Lens<Car, String> carModelLens = Lens.of(Car::getModel, newModel -> car -> car.withModel(newModel));

    public static final Lens<Car, Integer> carMileageLens = Lens.of(Car::getMileage, newMileage -> car -> car.withMileage(newMileage));
}
