package org.course.functionalstyle.lenses.after.lastname;

import org.course.functionalstyle.lenses.after.Firstname;
import org.course.functionalstyle.lenses.after.Lastname;
import org.reusable.maybe.Maybe;

final public class Hero {
    private final Firstname firstname;
    private final Maybe<Lastname> lastname;
    private final Maybe<Hero> friend;

    public Hero(Firstname firstname, Maybe<Lastname> lastname, Maybe<Hero> friend) {
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