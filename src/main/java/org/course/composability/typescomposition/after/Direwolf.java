package org.course.composability.typescomposition.after;

public final class Direwolf implements Animal {

    private final Animal origin;
    // OR.... private final Dog origin;

    public Direwolf(final Animal origin) {
        this.origin = origin;
    }
    // OR....  public Direwolf(Dog origin) { this.origin = origin; }

    @Override
    public String makeSound() {
        return origin.makeSound();
    }

    public Wolf asWolf() {
        return (Wolf) origin;
    }

    public String announceOwner() {
        return "My owner is Jon Snow, Lord Commander of the Night's Watch";
    }
}
