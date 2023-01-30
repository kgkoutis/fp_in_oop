package org.course.typestypestypes.missingabstractions.after;

import java.util.HashMap;

final public class Veterinarian {

    public Veterinarian() {
        this.vetSayings = new HashMap<>();
        this.vetSayings.put(CatOwner.class, "Your cat seems moody today");
        this.vetSayings.put(DogOwner.class, "Who's a good boy??");
        this.vetSayings.put(ParrotOwner.class, "Polly want a cracker?");
    }

    private final HashMap<Class<?>, String> vetSayings;

    void acceptOwner(PetOwner petOwner) {
        Class<?> clazz = petOwner.getClass();
        String vetSaying = vetSayings.get(clazz);
        System.out.println(vetSaying);
    }
}
