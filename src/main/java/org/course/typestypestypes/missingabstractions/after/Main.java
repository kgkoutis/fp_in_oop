package org.course.typestypestypes.missingabstractions.after;

/**
 * The important abstraction was basically PetOwner and its hierarchy of subclasses.
 *
 * In an FP language we would introduce some type as follows:
 *      data PetOwner = CatOwner | DogOwner | Parrot
 * More on this in the discriminated union section.
 *
 * Notice also that we realised that the class Person was actually a misnomer for the class PetOwner and lead us
 * away from the important abstraction.
 * */
public class Main {
    public static void main(String[] args) {
        Veterinarian vet = new Veterinarian();
        PetOwner petOwner = new CatOwner();
        vet.acceptOwner(petOwner);
    }
}