package org.course.functionalstyle.lenses.after.firstname;

import org.course.functionalstyle.lenses.after.Firstname;
import org.course.functionalstyle.lenses.after.Lastname;

/**
 * Here we show how to use the lenses to update the Hero object. Lenses are a way to access and update immutable objects.
 * How they work you can see it in FirstNameLens class where we basically have a specific Lens for Firstname and String (String representing its inner value).
 * <p>
 * Lenses have an important property: they are composable. This means that we can compose them to access and update deeply nested objects.
 */
public class Main {
    public static void main(final String[] args) {
        final Firstname firstname = Firstname.of("Peter");
        final Lastname lastname = Lastname.of("Parker");

        final Hero spiderman = new Hero(firstname, lastname);

        // set with lens
        final Hero auntMary = HeroOptics.firstname.set("Mary", spiderman);
        // get with lens
        final String mary = HeroOptics.firstname.get(auntMary);
        System.out.println("Hero firstname: " + mary);

        // update with lens
        final Hero updatedHeroLastname = HeroOptics.lastname.update(oldLastname -> oldLastname + "-Jameson", auntMary);
        System.out.println("Hero updated lastname: " + updatedHeroLastname.lastname().getValue());
    }
}

