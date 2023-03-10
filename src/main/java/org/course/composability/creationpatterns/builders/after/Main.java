package org.course.composability.creationpatterns.builders.after;

/**
 * The builder pattern is a standard pattern for creating objects with optional parameters in Java.
 * Other alternatives include telescoping constructors (as seen below), factories, parameter objects, and so on.
 * <p>
 * Note: Builders, factories, iterators, etc. are often used as static nested classes, possibly private.
 * Nested (in general) classes are not the best idea (why?) but in a couple of cases they can be ok.
 * <p>
 * Note: NutritionFacts continues to stay immutable which is something we want. Builder though is NOT immutable.
 * Is this **likely** a problem? (think - with the rest of the class)
 * <p>
 * Do you know / can you think of any other functional way to achieve the same result (think)?
 */
public class Main {
    public static void main(final String[] args) {
        //---building for optional parameters

        // 1. Telescoping constructor: it works, but tedious. It is functional though (why?)
        final NutritionFactsTelescopingConstructor cocaColaSimple = new NutritionFactsTelescopingConstructor(240, 8, 100, 0, 35, 27);
        System.out.println(cocaColaSimple);

        // 2. building with builder: best solution for a language that doesn't have optional parameters
        final NutritionFactsWithBuilder cocaCola = new NutritionFactsWithBuilder
                .Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27)
                .build();
        System.out.println(cocaCola);

        System.out.println("EXTRA");
        System.out.println();
    }
}
