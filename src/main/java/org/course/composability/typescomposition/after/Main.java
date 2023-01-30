package org.course.composability.typescomposition.after;

/**
 * This is basically the class composition pattern. Is this functional?
 *
 * Well, FP speaks about function composition not about class composition. However, in FP the concept of inheritance cannot really exist.
 *
 * Inheritance is a mechanism where a new class can inherit properties and methods from an existing class.
 * This can be useful for creating a hierarchy of classes where subclasses inherit the behavior of their parent classes.
 * However, it can also lead to tight coupling and make the code more rigid and harder to change.
 *
 * Class composition is a mechanism where an object can contain one or more other objects, and use their behavior.
 * This allows for more flexibility, as objects can be composed at runtime, and can be easily swapped out for others.
 * Class composition tends to be more functional-friendly technique as it allows for more flexibility and less rigid
 * class hierarchies.
 *
 * So, in short, class composition is not per se functional but it is more functional-friendly technique.
 *
 * PS: Notice that this makes the code easily testable since we depend on an interface.
 * */
public class Main {
    public static void main(String[] args) {
        Wolf whiteTooth = new Wolf();
        System.out.println(whiteTooth.makeSound());

        System.out.println("-----------------");

        Direwolf ghost = new Direwolf(new Wolf()); // compose Wolf into Direwolf
        System.out.println(ghost.makeSound());
        System.out.println(ghost.announceOwner());

        // upcasting
        Wolf someWolf = ghost.asWolf();
        System.out.println(someWolf.makeSound());
        // someWolf.announceOwner(); // this won't compile, there is no such method in Wolf (information hiding)
    }
}