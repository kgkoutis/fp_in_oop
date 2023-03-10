package org.course.composability.creationpatterns.builders.after.recursivegenerics;

import static org.course.composability.creationpatterns.builders.after.recursivegenerics.Pizza.Topping.*;

/**
 * Here we see the builder pattern, retrofitted for class hierarchies. The builder of the abstract class is itself abstract
 * so that the child classes can specialize the behavior on their own builder using recursive generics.
 * <p>
 * Is this a functional pattern? (think hard)
 */
public class Main {
    public static void main(final String[] args) {
        //--- builder with class hierarchies

        final NyPizza nyPizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(SAUSAGE).addTopping(ONION).build();

        System.out.println(nyPizza);


        final Calzone calzone = new Calzone.Builder().addTopping(HAM)
                .sauceInside().build();

        System.out.println(calzone);

    }
}
