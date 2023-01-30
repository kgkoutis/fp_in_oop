package org.course.typestypestypes.buildingpatterns.after.recursivegenerics;

import static org.course.typestypestypes.buildingpatterns.after.recursivegenerics.NyPizza.Size.SMALL;
import static org.course.typestypestypes.buildingpatterns.after.recursivegenerics.Pizza.Topping.*;

/**
* Here we see the builder pattern, retrofitted for class hierarchies. The builder of the abstract class is itself abstract
* so that the child classes can specialize the behavior on their own builder using recursive generics.
*
* Is this a functional pattern? (think hard)
*/
public class Main {
    public static void main(String[] args) {
        //--- builder with class hierarchies

        NyPizza nyPizza = new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE).addTopping(ONION).build();

        System.out.println(nyPizza);


        Calzone calzone = new Calzone.Builder().addTopping(HAM)
                .sauceInside().build();

        System.out.println(calzone);

    }
}
