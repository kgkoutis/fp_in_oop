package org.reusable.prisms.car;

import org.reusable.either.Either;
import org.reusable.lenses.utils.BuildingError;
import org.reusable.lenses.utils.BuildingObjectFailedException;
import org.reusable.lenses.utils.StringTooLongBuildingError;
import org.reusable.throwing.ThrowingConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Car {
    private final String make;
    private final String model;
    private final int mileage;

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    private Car(final String make,
                final String model,
                final int mileage) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
    }

    private Car(final Car car) {
        this.make = car.make;
        this.model = car.model;
        this.mileage = car.mileage;
    }

    public Car copy() {
        return new Car(this);
    }

    public static Car create(final String make,
                             final String model,
                             final int mileage) {

        final Either<List<BuildingError>, Car> either = createSafe(make, model, mileage);
        return fromEither(either);
    }
    private static Car fromEither(final Either<List<BuildingError>, Car> either) {
        either.ifLeft(ThrowingConsumer.uncheck(errors -> {
            throw new BuildingObjectFailedException("One or more building errors occurred during construction of the object", errors);
        }));
        return either.match(l -> null, r -> r);
    }

    public static Either<List<BuildingError>, Car> createSafe(final String make,
                                                              final String model,
                                                              final int mileage) {
        return new CarBuilder().setMake(make).
                setModel(model).
                setMileage(mileage).
                createCar();
    }

    private static class CarBuilder {
        private String make;
        private String model;
        private int mileage;

        private final List<BuildingError> errors;

        public CarBuilder() {
            this.errors = new ArrayList<>();
        }
        public CarBuilder setMake(final String make) {
            validateMake(make);
            this.make = make;
            return this;
        }

        public CarBuilder setModel(final String model) {
            validateModel(model);
            this.model = model;
            return this;
        }

        public CarBuilder setMileage(final int mileage) {
            validateMileage(mileage);
            this.mileage = mileage;
            return this;
        }

        private void validateMake(final String mileage) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        private void validateModel(final String model) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        private void validateMileage(final int mileage) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public Either<List<BuildingError>, Car> createCar() {
            final boolean canBuild = validateAll(make, model, mileage);
            if (canBuild)
                return Either.right(new Car(make, model, mileage));
            return Either.left(errors);
        }

        private boolean validateAll(final String make,
                                    final String model,
                                    final int mileage) {
            return errors.size() == 0;
        }
    }

    public Car withMake(final String make) {
        final Either<List<BuildingError>, Car> either = withMakeSafe(make);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Car> withMakeSafe(final String make) {
        return new CarBuilder().setMake(make).setModel(this.model).setMileage(this.mileage).createCar();
    }

    public Car withModel(final String model) {
       final Either<List<BuildingError>, Car> either = withModelSafe(model);
         return fromEither(either);
    }

    public Either<List<BuildingError>, Car> withModelSafe(final String model) {
        return new CarBuilder().setMake(this.make).setModel(model).setMileage(this.mileage).createCar();
    }

    public Car withMileage(final int mileage) {
        final Either<List<BuildingError>, Car> either = withMileageSafe(mileage);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Car> withMileageSafe(final int mileage) {
        return new CarBuilder().setMake(this.make).setModel(this.model).setMileage(mileage).createCar();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Car car = (Car) o;
        return mileage == car.mileage &&
                Objects.equals(make, car.make) &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, mileage);
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
