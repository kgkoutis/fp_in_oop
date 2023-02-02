package org.reusable.lenses.leader;

import org.reusable.either.Either;
import org.reusable.lenses.car.Car;
import org.reusable.lenses.utils.BuildingError;
import org.reusable.lenses.utils.BuildingObjectFailedException;
import org.reusable.lenses.utils.StringTooLongBuildingError;
import org.reusable.throwing.ThrowingConsumer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public final class Leader implements Comparable<Leader> {
    private final String fullName;
    private final int salary;
    private final Car car;

    private Leader(final String fullName,
                   final int salary,
                   final Car car) {
        this.fullName = fullName;
        this.salary = salary;
        this.car = car;
    }

    private Leader(final Leader leader) {
        this.fullName = leader.fullName;
        this.salary = leader.salary;
        this.car = leader.car;
    }

    public Leader copy() {
        return new Leader(this);
    }

    public static Leader create(final String fullName,
                                final int salary,
                                final Car car) {
        final Either<List<BuildingError>, Leader> either = createSafe(fullName, salary, car);
        return fromEither(either);
    }

    private static Leader fromEither(final Either<List<BuildingError>, Leader> either) {
        either.ifLeft(ThrowingConsumer.uncheck(errors -> {
            throw new BuildingObjectFailedException("One or more building errors occurred during construction of the object", errors);
        }));
        return either.match(l -> null, r -> r);
    }

    public static Either<List<BuildingError>, Leader> createSafe(final String fullName,
                                                                 final int salary,
                                                                 final Car car) {
        final LeaderBuilder tb = new LeaderBuilder();
        return tb.setFullName(fullName)
                .setSalary(salary)
                .setCar(car)
                .createTeam();
    }

    private static class LeaderBuilder {
        private String fullName;
        private int salary;
        private Car car;

        private final List<BuildingError> errors;

        public LeaderBuilder() {
            this.errors = new ArrayList<>();
        }

        public LeaderBuilder setFullName(final String fullName) {
            validateName(fullName);
            this.fullName = fullName;
            return this;
        }

        private void validateName(final String fullName) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        private LeaderBuilder setSalary(final int salary) {
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

        public LeaderBuilder setCar(final Car car) {
            validateCar(car);
            this.car = car;
            return this;
        }

        private void validateCar(final Car car) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public Either<List<BuildingError>, Leader> createTeam() {
            final boolean canBuild = validateAll(fullName, salary, car);
            if (canBuild)
                return Either.right(new Leader(fullName, salary, car));
            return Either.left(errors);
        }

        private boolean validateAll(final String fullName,
                                    final int salary,
                                    final Car car) {
            return errors.size() == 0;
        }
    }

    public Either<List<BuildingError>, Leader> withFullNameSafe(final String fullName) {
        return new LeaderBuilder().setFullName(fullName).setSalary(this.salary).setCar(this.car).createTeam();
    }

    public Leader withFullName(final String fullName) {
        final Either<List<BuildingError>, Leader> either = withFullNameSafe(fullName);
        return fromEither(either);
    }

    public Leader withSalary(final int salary) {
        final Either<List<BuildingError>, Leader> either = withSalarySafe(Leader.this.salary);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Leader> withSalarySafe(final int salary) {
        return new LeaderBuilder().setFullName(this.fullName)
                .setSalary(Leader.this.salary)
                .setCar(this.car)
                .createTeam();
    }

    public Leader withCar(final Car car) {
        final Either<List<BuildingError>, Leader> either = withCarSafe(car);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Leader> withCarSafe(final Car car) {
        return new LeaderBuilder().setFullName(this.fullName).setSalary(this.salary).setCar(car).createTeam();
    }

    public String getFullName() {
        return fullName;
    }

    public int getSalary() {
        return salary;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Leader leader = (Leader) o;
        return Objects.equals(fullName, leader.fullName) &&
                Objects.equals(salary, leader.salary) &&
                Objects.equals(car, leader.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, salary, car);
    }

    @Override
    public String toString() {
        return "Leader {" +
                "fullName='" + fullName + '\'' +
                ", salary='" + salary + '\'' +
                ", car=" + car +
                '}';
    }

    private static final Comparator<Leader> NATURAL_COMPARATOR = Comparator
            .comparing(Leader::getFullName)
            .thenComparing(Leader::getSalary)
            .thenComparing(Leader::getCar);

    @Override
    public int compareTo(final Leader o) {
        return NATURAL_COMPARATOR.compare(this, o);
    }

}

