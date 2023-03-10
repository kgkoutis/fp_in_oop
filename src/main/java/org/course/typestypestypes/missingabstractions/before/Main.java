package org.course.typestypestypes.missingabstractions.before;

/**
 * OOP and FP agree on the following:
 * it's all about abstractions. The design of your system can suffer, especially if you miss an important abstraction.
 * <p>
 * Here it seems we missed the opportunity to introduce an important abstraction.
 * Where does this happen and how can we fix it?
 */
public class Main {
    public static void main(final String[] args) {
        final Veterinarian vet = new Veterinarian();
        final Person owner = new Person(true, false, false);
        vet.acceptOwner(owner);
    }
}