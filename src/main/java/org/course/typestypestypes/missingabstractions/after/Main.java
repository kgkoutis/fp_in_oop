package org.course.typestypestypes.missingabstractions.after;

/**
 * The important abstraction was basically PetOwner and its hierarchy of subclasses.
 * <p>
 * In an FP language we would introduce some type as follows:
 *         data PetOwner = CatOwner | DogOwner | Parrot
 * More on this in the discriminated union section.
 * <p>
 * Notice also that we realised that the class Person was actually a misnomer for the class PetOwner and lead us
 * away from the important abstraction.
 */
public class Main {
    public static void main(final String[] args) {
        final Veterinarian vet = new Veterinarian();
        final PetOwner petOwner = new CatOwner();
        vet.acceptOwner(petOwner);
    }
}