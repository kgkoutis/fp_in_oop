package org.course.typestypestypes.missingabstractions.after;

import java.util.HashMap;

public final class Veterinarian {

    public Veterinarian() {
        this.vetSayings = new HashMap<>();
        this.vetSayings.put(CatOwner.class, "Your cat seems moody today");
        this.vetSayings.put(DogOwner.class, "Who's a good boy??");
        this.vetSayings.put(ParrotOwner.class, "Polly want a cracker?");
    }

    private final HashMap<Class<?>, String> vetSayings;

    void acceptOwner(final PetOwner petOwner) {
        final Class<?> clazz = petOwner.getClass();
        final String vetSaying = vetSayings.get(clazz);
        System.out.println(vetSaying);
    }
}
