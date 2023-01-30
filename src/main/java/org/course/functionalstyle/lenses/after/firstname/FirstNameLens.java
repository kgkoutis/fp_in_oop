package org.course.functionalstyle.lenses.after.firstname;

import org.course.functionalstyle.lenses.after.Firstname;

import java.util.function.Function;

public class FirstNameLens {
    public final Function<Firstname, String> getF;
    public final Function<String, Function<Firstname, Firstname>> setF;

    private FirstNameLens(Function<Firstname, String> get, Function<String, Function<Firstname, Firstname>> set) {
        this.getF = get;
        this.setF = set;
    }

    public String get(Firstname value) {
        return getF.apply(value);
    }

    public Firstname set(String value, Firstname cont) {
        return setF.apply(value).apply(cont);
    }

    public static FirstNameLens of(Function<Firstname, String> Get, Function<String, Function<Firstname, Firstname>> Set) {
        return new FirstNameLens(Get, Set);
    }

    public Function<Firstname, Firstname> update(Function<String, String> f) {
        var self = this;
        return a -> self.set(f.apply(self.getF.apply(a)), a);
    }

    public Firstname update(Function<String, String> f, Firstname value) {
        return set(f.apply(getF.apply(value)), value);
    }
}