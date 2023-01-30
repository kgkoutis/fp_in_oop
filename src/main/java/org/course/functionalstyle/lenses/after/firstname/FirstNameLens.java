package org.course.functionalstyle.lenses.after.firstname;

import org.course.functionalstyle.lenses.after.Firstname;

import java.util.function.Function;

public final class FirstNameLens {
    public final Function<Firstname, String> getF;
    public final Function<String, Function<Firstname, Firstname>> setF;

    private FirstNameLens(final Function<Firstname, String> get,
                          final Function<String, Function<Firstname, Firstname>> set) {
        this.getF = get;
        this.setF = set;
    }

    public String get(final Firstname value) {
        return getF.apply(value);
    }

    public Firstname set(final String value,
                         final Firstname cont) {
        return setF.apply(value).apply(cont);
    }

    public static FirstNameLens of(final Function<Firstname, String> Get,
                                   final Function<String, Function<Firstname, Firstname>> Set) {
        return new FirstNameLens(Get, Set);
    }

    public Function<Firstname, Firstname> update(final Function<String, String> f) {
        final var self = this;
        return a -> self.set(f.apply(self.getF.apply(a)), a);
    }

    public Firstname update(final Function<String, String> f,
                            final Firstname value) {
        return set(f.apply(getF.apply(value)), value);
    }
}