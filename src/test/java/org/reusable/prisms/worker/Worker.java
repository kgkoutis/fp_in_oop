package org.reusable.prisms.worker;

import org.reusable.either.Either;
import org.reusable.lenses.utils.BuildingError;
import org.reusable.lenses.utils.BuildingObjectFailedException;
import org.reusable.lenses.utils.StringTooLongBuildingError;
import org.reusable.maybe.Maybe;
import org.reusable.prisms.car.Car;
import org.reusable.throwing.ThrowingConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Worker {
    private final String name;
    private final int salary;
    private final Maybe<Car> car;

    private Worker(final String fullName,
                   final int salary,
                   final Maybe<Car> car) {
        this.name = fullName;
        this.salary = salary;
        this.car = car;
    }

    private Worker(final Worker worker) {
        this.name = worker.name;
        this.salary = worker.salary;
        this.car = worker.car;
    }

    public Worker copy() {
        return new Worker(this);
    }

    public static Worker create(final String fullName,
                                final int salary,
                                final Maybe<Car> car) {
        final Either<List<BuildingError>, Worker> either = createSafe(fullName, salary, car);
        return fromEither(either);
    }

    private static Worker fromEither(final Either<List<BuildingError>, Worker> either) {
        either.ifLeft(ThrowingConsumer.uncheck(errors -> {
            throw new BuildingObjectFailedException("One or more building errors occurred during construction of the object", errors);
        }));
        return either.match(l -> null, r -> r);
    }

    public static Either<List<BuildingError>, Worker> createSafe(final String name,
                                                                 final int salary,
                                                                 final Maybe<Car> car) {
        final WorkerBuilder workerBuilder = new WorkerBuilder();
        return workerBuilder.
                setName(name).
                setSalary(salary).
                setCar(car).
                createWorker();
    }

    public Worker withName(final String name) {
        final Either<List<BuildingError>, Worker> either = withNameSafe(name);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Worker> withNameSafe(final String name) {
        return new WorkerBuilder()
                .setName(name)
                .setSalary(this.salary)
                .setCar(this.car)
                .createWorker();
    }

    public Worker withSalary(final int salary) {
        final Either<List<BuildingError>, Worker> either = withSalarySafe(salary);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Worker> withSalarySafe(final int salary) {
        return new WorkerBuilder()
                .setName(this.name)
                .setSalary(salary)
                .setCar(this.car)
                .createWorker();
    }

    public Worker withCar(final Maybe<Car> car) {
        final Either<List<BuildingError>, Worker> either = withCarSafe(car);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Worker> withCarSafe(final Maybe<Car> car) {
        return new WorkerBuilder()
                .setName(this.name)
                .setSalary(this.salary)
                .setCar(car)
                .createWorker();
    }

    private static class WorkerBuilder {
        private String name;
        private int salary;
        private Maybe<Car> car;
        private List<BuildingError> errors;

        private WorkerBuilder() {
            this.errors = new ArrayList<>();
        }

        public WorkerBuilder setName(final String name) {
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

        public WorkerBuilder setSalary(final int salary) {
            validateSalary(salary);
            this.salary = salary;
            return this;
        }

        private void validateSalary(final int salary) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public WorkerBuilder setCar(final Maybe<Car> car) {
            validateCar(car);
            this.car = car;
            return this;
        }

        private void validateCar(final Maybe<Car> car) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public Either<List<BuildingError>, Worker> createWorker() {
            final boolean canBuild = validateAll(name, salary, car);
            if (canBuild)
                return Either.right(new Worker(name, salary, car));
            return Either.left(errors);
        }

        private boolean validateAll(final String name,
                                    final int salary,
                                    final Maybe<Car> car) {
            return errors.size() == 0;
        }
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public Maybe<Car> getCar() {
        return car;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Worker worker = (Worker) o;
        return salary == worker.salary &&
                Objects.equals(name, worker.name) &&
                Objects.equals(car, worker.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, car);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", car=" + car +
                '}';
    }
}
