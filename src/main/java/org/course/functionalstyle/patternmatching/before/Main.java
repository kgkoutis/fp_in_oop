package org.course.functionalstyle.patternmatching.before;

/**
 * All FP languages support pattern matching. In Java we often use if statements, switch statements and as of lately switch expressions.
 *
 * If statements and switch statements are not really guaranteed to be exhaustive. It is up to the developer to remember to check for all possible cases.
 * Switch expressions are guaranteed by the compiler to be exhaustive, but they are not supported by old versions of Java.
 *
 * What is the equivalent OOP way of doing pattern matching that always works and guarantees exhaustiveness?
 *
 * SIDE NOTE: There is absolutely nothing wrong with using if statements and switch statements, if you are diligent enough.
 * Probably they make code simpler to read also that what we are about to show but we just want to show another way of doing things.
 */
public class Main {
    public static void main(String[] args) {
        Pet pet = new Cat(); // works with old versions of Java
        String sound = getPetSound(pet);
        System.out.println(sound);

        Pet pet2 = new Dog();
        String sound2 = getPetSound2(pet2); // requires Java 17 or higher
        System.out.println(sound2);
    }

    private static String getPetSound(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("pet cannot be null");
        }

        if (pet instanceof Cat) {
            return "Meow";
        }

        if (pet instanceof Dog) {
            return "Woof";
        }

        if (pet instanceof Parrot) {
            return "Siri, order me 10 boxes of pizza";
        }

        throw new IllegalStateException("Unexpected pet: " + pet);
    }

    /**
     * Obviously this works with the old switch statement..
     */
    private static String getPetSound2(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("pet cannot be null");
        }
        // switch expression for Pet pet
        return switch (pet) {
            case Cat cat -> "Meow";
            case Dog dog -> "Woof";
            case Parrot parrot -> "Siri, order me 10 boxes of pizza";
            default -> throw new IllegalStateException("Unexpected pet: " + pet);
        };
    }
}
