package org.course.functionalstyle.lenses.before;

import org.reusable.maybe.Maybe;

public final class Hero {
    private final Firstname firstname;
    private final Maybe<Lastname> lastname;
    private final Maybe<Hero> friend;

    public Hero(final Firstname firstname,
                final Maybe<Lastname> lastname,
                final Maybe<Hero> friend) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.friend = friend;
    }

    public Firstname firstname() {
        return firstname;
    }

    public Maybe<Lastname> lastname() {
        return lastname;
    }

    public Maybe<Hero> friend() {
        return friend;
    }

    @Override
    public String toString() {
        return "Hero {" +
                "firstname = " + firstname +
                ", lastname = " + lastname.match(x -> x, "no lastname") +
                ", friend = " + friend.match(Hero::toString, "I have no friends") +
                " }";
    }
}
