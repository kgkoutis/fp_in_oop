package org.course.functionalstyle.patternmatching.after;

/**
 * The Visitor Pattern is a design pattern that separates an algorithm from an object structure on which it operates.
 * A practical result of this separation is the ability to add new operations to existing object structures without modifying the structures.
 * <p>
 * As you can see in the PetVisitor interface, we have a method for each type of Pet. If we define there a visit method for every type of Pet we
 * want always to visit, visitors that implement this interface are guaranteed to visit every type of Pet - it is like we build our own exhaustive
 * pattern matching system.
 * <p>
 * What are the disadvantages of this approach? (think)
 */
public class Main {
    public static void main(final String[] args) {
        final Pet pet = new Cat();
        final String sound = PetExtensions.getSound(pet);
        System.out.println(sound);
    }
}

