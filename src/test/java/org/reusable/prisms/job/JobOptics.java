package org.reusable.prisms.job;

import org.reusable.lenses.Lens;
import org.reusable.prisms.worker.Worker;

public final class JobOptics {
    public static final Lens<Job, String> leaderNameLens = Lens.of(Job::getName, newName -> job -> job.withName(newName));
    public static final Lens<Job, String> leaderDescriptionLens = Lens.of(Job::getDescription, newDescription -> job -> job.withDescription(newDescription));
    public static final Lens<Job, Worker> leaderWorkerLens = Lens.of(Job::getWorker, newCar -> job -> job.withWorker(newCar));
}
