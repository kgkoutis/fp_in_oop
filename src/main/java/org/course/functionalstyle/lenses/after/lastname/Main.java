package org.course.functionalstyle.lenses.after.lastname;

import org.course.functionalstyle.lenses.after.Firstname;
import org.course.functionalstyle.lenses.after.Lastname;
import org.reusable.maybe.Maybe;

/**
 * Here we show how to use the prisms to update the Hero object. Prisms are the next step after lenses.
 * <p>
 * They offer everything lenses do, plus the ability to handle nested Maybe cases effortlessly.
 * <p>
 * Prisms have been dubbed as "the jQuery of data types" because the same way in jQuery you can write this:
 * <p>
 * $("#myDiv")
 * .children().first()                            // go to first child, if present
 * .children().first().css("color", "red");   // go to first grandchild, if present
 * <p>
 * Where you are altering the CSS two levels deep in the DOM, and jQuery guarantees that you will not crash if any of the
 * intermediate elements are not present, prisms do the same for nested data types that may or may not be present.
 * <p>
 * (side note: better written like $("#myDiv:first-child:first-child").css("color", "red"); but that's not the point)
 * <p>
 * Look how nice the code got simplified.
 */

public class Main {
    public static void main(final String[] args) {
        final Hero superman = new Hero(Firstname.of("Clark"), Maybe.just(Lastname.of("Kent")), Maybe.nothing());
        final Hero batman = new Hero(Firstname.of("Bruce"), Maybe.just(Lastname.of("Wayne")), Maybe.just(superman));

        mainLogic(batman);

        System.out.println("---------------------");

        final Hero guyNamedBruce = new Hero(Firstname.of("Bruce"), Maybe.nothing(), Maybe.nothing());
        mainLogic(guyNamedBruce);
    }

    private static void mainLogic(final Hero hero) {

        // 1. set new lastname, if not nothing
        final Hero springsteen = HeroOptics.lastname.set("Springsteen", hero);

        // 2. get current lastname, if not nothing
        final Maybe<String> maybeSpringSteen = HeroOptics.lastname.get(springsteen);

        // 3. print current lastname by handling both cases (present and not present)...
        maybeSpringSteen.handle(
                x -> System.out.println("Hero lastname: " + x),
                () -> System.out.println("Lastname is nothing")
        );

        // 4. update current lastname, if not nothing, with a new value based on the old one
        final Hero heroWithUpdatedFriend = HeroOptics.friendsLastname.update(oldLastname -> oldLastname +
                " sucks", springsteen);

        // 5. print hero
        System.out.println(heroWithUpdatedFriend);
    }
}