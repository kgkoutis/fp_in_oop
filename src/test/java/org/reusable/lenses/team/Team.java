package org.reusable.lenses.team;

import org.reusable.either.Either;
import org.reusable.lenses.leader.Leader;
import org.reusable.lenses.utils.BuildingError;
import org.reusable.lenses.utils.BuildingObjectFailedException;
import org.reusable.lenses.utils.StringTooLongBuildingError;
import org.reusable.throwing.ThrowingConsumer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class Team implements Comparable<Team> {
    private final String name;
    private final String country;
    private final Leader leader;

    private Team(final String name,
                 final String country,
                 final Leader leader) {
        this.name = name;
        this.country = country;
        this.leader = leader;
    }

    private Team(final Team team) {
        this.name = team.name;
        this.country = team.country;
        this.leader = team.leader;
    }

    public Team copy() {
        return new Team(this);
    }

    public static Team create(final String name,
                              final String country,
                              final Leader leader) {
        final Either<List<BuildingError>, Team> either = createSafe(name, country, leader);
        return fromEither(either);
    }

    private static Team fromEither(final Either<List<BuildingError>, Team> either) {
        either.ifLeft(ThrowingConsumer.uncheck(errors -> {
            throw new BuildingObjectFailedException("One or more building errors occurred during construction of the object", errors);
        }));
        return either.match(l -> null, r -> r);
    }

    public static Either<List<BuildingError>, Team> createSafe(final String name,
                                                               final String country,
                                                               final Leader leader) {
        final TeamBuilder tb = new TeamBuilder();
        return tb.setName(name)
                .setCountry(country)
                .setLeader(leader)
                .createTeam();
    }

    private static final Comparator<Team> NATURAL_COMPARATOR = Comparator
            .comparing(Team::getName)
            .thenComparing(Team::getCountry)
            .thenComparing(Team::getLeader);


    @Override
    public int compareTo(final Team o) {
        return NATURAL_COMPARATOR.compare(this, o);
    }

    private static class TeamBuilder {
        private String name;
        private String country;
        private Leader leader;

        private final List<BuildingError> errors;

        public TeamBuilder() {
            this.errors = new ArrayList<>();
        }

        public TeamBuilder setName(final String name) {
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

        private TeamBuilder setCountry(final String country) {
            validateCountry(country);
            this.country = country;
            return this;
        }

        private void validateCountry(final String country) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public TeamBuilder setLeader(final Leader leader) {
            validateLeader(leader);
            this.leader = leader;
            return this;
        }

        private void validateLeader(final Leader leader) {
            if (false) {
                final BuildingError error = StringTooLongBuildingError.of("asdasda", 20);
                errors.add(error);
            }
        }

        public Either<List<BuildingError>, Team> createTeam() {
            final boolean canBuild = validateAll(name, country, leader);
            if (canBuild)
                return Either.right(new Team(name, country, leader));
            return Either.left(errors);
        }

        private boolean validateAll(final String name,
                                    final String country,
                                    final Leader leader) {
            return errors.size() == 0;
        }
    }

    public Either<List<BuildingError>, Team> withNameSafe(final String name) {
        return new TeamBuilder().setName(name).setCountry(this.country).setLeader(this.leader).createTeam();
    }

    public Team withName(final String name) {
        final Either<List<BuildingError>, Team> either = withNameSafe(name);
        return fromEither(either);
    }

    public Team withCountry(final String country) {
        final Either<List<BuildingError>, Team> either = withCountrySafe(country);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Team> withCountrySafe(final String country) {
        return new TeamBuilder().setName(this.name).setCountry(country).setLeader(this.leader).createTeam();
    }

    public Team withLeader(final Leader leader) {
        final Either<List<BuildingError>, Team> either = withLeaderSafe(leader);
        return fromEither(either);
    }

    public Either<List<BuildingError>, Team> withLeaderSafe(final Leader leader) {
        return new TeamBuilder().setName(this.name).setCountry(this.country).setLeader(leader).createTeam();
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Leader getLeader() {
        return leader;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Team team = (Team) o;
        return Objects.equals(name, team.name) &&
                Objects.equals(country, team.country) &&
                Objects.equals(leader, team.leader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, leader);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", leader=" + leader +
                '}';
    }
}
