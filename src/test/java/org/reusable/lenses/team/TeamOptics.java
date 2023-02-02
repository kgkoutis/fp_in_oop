package org.reusable.lenses.team;

import org.reusable.lenses.Lens;
import org.reusable.lenses.leader.Leader;

public final class TeamOptics {
    public static final Lens<Team, String> teamNameLens = Lens.of(Team::getName, newName -> team -> team.withName(newName));
    public static final Lens<Team, String> teamCountryLens = Lens.of(Team::getCountry, newCountry -> team -> team.withCountry(newCountry));
    public static final Lens<Team, Leader> teamLeaderLens = Lens.of(Team::getLeader, newLeader -> team -> team.withLeader(newLeader));
}
