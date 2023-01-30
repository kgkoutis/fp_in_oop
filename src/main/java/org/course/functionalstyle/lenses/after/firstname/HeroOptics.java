package org.course.functionalstyle.lenses.after.firstname;

import org.course.functionalstyle.lenses.after.Firstname;
import org.course.functionalstyle.lenses.after.Lastname;
import org.reusable.lenses.Lens;

import static org.reusable.lenses.LensCompositions.lens;

final public class HeroOptics {
    // Firstname
    private static final Lens<Hero, Firstname> heroFirstnameLens = Lens.of(Hero::firstname, newFirstname -> hero -> new Hero(newFirstname, hero.lastname()));
    private static final Lens<Firstname, String> firstnameLens = Lens.of(Firstname::getValue, newName -> firstname -> new Firstname(newName));

    public static final Lens<Hero, String> firstname = lens(heroFirstnameLens, firstnameLens);

    // Lastname
    private static final Lens<Hero, Lastname> heroLastnameLens = Lens.of(Hero::lastname, newLastname -> hero -> new Hero(hero.firstname(), newLastname));
    private static final Lens<Lastname, String> lastnameLens = Lens.of(Lastname::getValue, newName -> lastname -> new Lastname(newName));

    public static final Lens<Hero, String> lastname = lens(heroLastnameLens, lastnameLens);

}
