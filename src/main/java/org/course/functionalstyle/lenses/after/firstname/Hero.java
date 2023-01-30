package org.course.functionalstyle.lenses.after.firstname;

import org.course.functionalstyle.lenses.after.Firstname;
import org.course.functionalstyle.lenses.after.Lastname;

public final class Hero {
    private final Firstname firstname;
    private final Lastname lastname;

    public Hero(final Firstname firstname,
                final Lastname lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Firstname firstname() {
        return firstname;
    }

    public Lastname lastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return "Hero {" +
                "firstname = " + firstname +
                ", lastname = " + lastname +
                " }";
    }
}