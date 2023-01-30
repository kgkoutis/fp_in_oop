package org.course.functionalstyle.lenses.before;

import org.reusable.maybe.Maybe;

/**
 * Working with Maybe values can be tricky because you have to always pattern match on the value.
 *
 * Here we have a Hero class with a firstname, a lastname and a friend. The friend is also a Hero.
 *
 * The lastname is a Maybe value, because it is designed to be optional.
 * The friend value is also a Maybe value, because it is designed to be optional.
 *
 * In our example we have 3 heros, 2 of them are missing values. Look how complicated the code is at step 4 to update the lastname of the friend.
 * It has several nested pattern matches and it is not easy to understand.
 *
 * What can we do about it?
 * */
public class Main {
    public static void main(String[] args) {
        final Hero superman = new Hero(Firstname.of("Clark"), Maybe.just(Lastname.of("Kent")), Maybe.nothing());
        final Hero batman = new Hero(Firstname.of("Bruce"), Maybe.just(Lastname.of("Wayne")), Maybe.just(superman));

        mainLogic(batman);

        System.out.println("---------------------");

        final Hero guyNamedBruce = new Hero(Firstname.of("Bruce"), Maybe.nothing(), Maybe.nothing());
        mainLogic(guyNamedBruce);
    }

    private static void mainLogic(Hero hero) {

        // 1. set new lastname, if not nothing
        final Hero springsteen = new Hero(hero.firstname(), hero.lastname().bind(
                lastname -> Maybe.just(Lastname.of("Springsteen"))), hero.friend()
        );

        // 2. get current lastname, if not nothing
        final Maybe<String> maybeSpringSteen = springsteen.lastname().bind(lastname -> Maybe.just(lastname.getValue()));

        // 3. print current lastname by handling both cases (present and not present)...
        maybeSpringSteen.handle(
                x -> System.out.println("My lastname now is: " + x),
                () -> System.out.println("He has no lastname")
        );

        // 4. update current lastname, if not nothing, with a new value based on the old one
        final Hero heroWithUpdatedFriend = new Hero(springsteen.firstname(), springsteen.lastname(), springsteen.friend().bind(
                friend -> Maybe.just(new Hero(friend.firstname(), friend.lastname().bind(
                        lastname -> Maybe.just(Lastname.of(lastname.getValue() + " sucks"))
                ), friend.friend()))
        ));

        // 5. print hero
        System.out.println(heroWithUpdatedFriend);
    }
}
