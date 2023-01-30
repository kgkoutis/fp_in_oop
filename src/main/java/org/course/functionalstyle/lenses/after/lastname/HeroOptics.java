package org.course.functionalstyle.lenses.after.lastname;

import org.course.functionalstyle.lenses.after.Firstname;
import org.course.functionalstyle.lenses.after.Lastname;
import org.reusable.lenses.Lens;
import org.reusable.maybe.Maybe;
import org.reusable.prisms.Prism;

import static org.reusable.lenses.LensCompositions.lens;
import static org.reusable.prisms.PrismCompositions.prism;

final public class HeroOptics {
    // Firstname
    private static final Lens<Hero, Firstname> heroFirstnameLens = Lens.of(Hero::firstname, newFirstname -> hero -> new Hero(newFirstname, hero.lastname(), hero.friend()));
    private static final Lens<Firstname, String> firstnameLens = Lens.of(Firstname::getValue, newName -> firstname -> new Firstname(newName));

    public static final Lens<Hero, String> firstname = lens(heroFirstnameLens, firstnameLens);

    // Lastname
    private static final Lens<Hero, Maybe<Lastname>> heroLastnameLens = Lens.of(Hero::lastname, newLastname -> hero -> new Hero(hero.firstname(), newLastname, hero.friend()));
    private static final Lens<Lastname, String> lastnameLens = Lens.of(Lastname::getValue, newName -> lastname -> new Lastname(newName));
    public static final Prism<Hero, String> lastname = prism(Prism.mfromLens(heroLastnameLens), Prism.fromLens(lastnameLens));

    // Friends lastname
    private static final Prism<Hero, Hero> heroFriendPrism = Prism.of(Hero::friend, newFriend -> hero -> new Hero(hero.firstname(), hero.lastname(), Maybe.just(newFriend)));
    public static final Prism<Hero, String> friendsLastname = prism(heroFriendPrism, lastname);

}
