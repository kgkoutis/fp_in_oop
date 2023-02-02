package org.reusable.prisms.job;

import org.reusable.either.Either;
import org.reusable.lenses.utils.BuildingError;
import org.reusable.lenses.utils.BuildingObjectFailedException;
import org.reusable.lenses.utils.StringTooLongBuildingError;
import org.reusable.prisms.worker.Worker;
import org.reusable.throwing.ThrowingConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Job {
    private final String name;
    private final String description;
    private final Worker worker;

    private Job(final String name,
                final String description,
                final Worker worker) {
        this.name = name;
        this.description = description;
        this.worker = worker;
    }

    private Job(final Job job) {
        this.name = job.name;
        this.description = job.description;
        this.worker = job.worker;
    }

    public Job copy() {
        return new Job(this);
    }


    public static Job create(final String name,
                             final String description,
                             final Worker worker) {
        final Either<List<BuildingError>, Job> either = createSafe(name, description, worker);
        return fromEither(either);
    }

    public static Either<List<BuildingError>, Job> createSafe(final String name,
                                                              final String description,
                                                              final Worker worker) {
        return new JobBuilder().setName(name).setDescription(description).setWorker(worker).createJob();
    }

    private static Job fromEither(final Either<List<BuildingError>, Job> either) {
        either.ifLeft(ThrowingConsumer.uncheck(errors -> {
            throw new BuildingObjectFailedException("One or more building errors occurred during construction of the object", errors);
        }));
        return either.match(l -> null, r -> r);
    }

    public Job withName(final String name) {
        final Either<List<BuildingError>, Job> either = withNameSafe(name);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Job> withNameSafe(final String name) {
        return new Job.JobBuilder()
                .setName(name)
                .setDescription(this.description)
                .setWorker(this.worker)
                .createJob();
    }

    public Job withDescription(final String description) {
        final Either<List<BuildingError>, Job> either = withDescriptionSafe(description);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Job> withDescriptionSafe(final String description) {
        return new Job.JobBuilder()
                .setName(this.name)
                .setDescription(description)
                .setWorker(this.worker)
                .createJob();
    }

    public Job withWorker(final Worker worker) {
        final Either<List<BuildingError>, Job> either = withWorkerSafe(worker);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Job> withWorkerSafe(final Worker worker) {
        return new Job.JobBuilder()
                .setName(this.name)
                .setDescription(this.description)
                .setWorker(worker)
                .createJob();
    }

    private static class JobBuilder {

        private String name;
        private String description;
        private Worker worker;

        private List<BuildingError> errors;

        public JobBuilder() {
            this.errors = new ArrayList<>();
        }

        public JobBuilder setName(final String name) {
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

        public JobBuilder setDescription(final String description) {
            validateDescription(description);
            this.description = description;
            return this;
        }

        private void validateDescription(final String description) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public JobBuilder setWorker(final Worker worker) {
            validateWorker(worker);
            this.worker = worker;
            return this;
        }

        private void validateWorker(final Worker worker) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public Either<List<BuildingError>, Job> createJob() {
            final boolean canBuild = validateAll(name, description, worker);
            if (canBuild)
                return Either.right(new Job(name, description, worker));
            return Either.left(errors);
        }

        private boolean validateAll(final String name,
                                    final String description,
                                    final Worker worker) {
            return errors.size() == 0;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Worker getWorker() {
        return worker;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Job job = (Job) o;
        return Objects.equals(name, job.name) &&
                Objects.equals(description, job.description) &&
                Objects.equals(worker, job.worker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, worker);
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", worker=" + worker +
                '}';
    }
}
