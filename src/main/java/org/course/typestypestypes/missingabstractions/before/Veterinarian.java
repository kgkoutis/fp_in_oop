package org.course.typestypestypes.missingabstractions.before;

import java.util.HashMap;

public class Veterinarian {

    public Veterinarian() {
        this.vetSayings = new HashMap<>();
        this.vetSayings.put("cat", "Your cat seems moody today");
        this.vetSayings.put("dog", "Who's a good boy??");
        this.vetSayings.put("parrot", "Polly want a cracker?");
    }

    private final HashMap<String, String> vetSayings;

    void acceptOwner(Person p) {
        if (p.HasCat)
            System.out.println(vetSayings.get("cat"));
        if (p.HasDog)
            System.out.println(vetSayings.get("dog"));
        if (p.HasParrot)
            System.out.println(vetSayings.get("parrot"));
        /*...(more if's)...*/
    }
}
