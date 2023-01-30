package org.course.composability.typescomposition.before;

/**
 * Nowadays, everyone wants to avoid the pitfalls of OOP and write more functional code.
 * The first advice one gets is to avoid using inheritance (which is purely an OOP concept) and use composition instead.
 * How does one do this?
 * <p>
 * Here we have a class Direwolf that inherits from Wolf which in turn implements an interface Animal.
 * How do we get rid of the inheritance and use composition instead?
 */
public class Main {
    public static void main(final String[] args) {
        final Wolf whiteTooth = new Wolf();
        System.out.println(whiteTooth.makeSound());

        System.out.println("-----------------");

        final Direwolf ghost = new Direwolf();
        System.out.println(ghost.makeSound()); // reused functionality from Wolf
        System.out.println(ghost.announceOwner());
    }
}