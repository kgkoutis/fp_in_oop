package org.course.composability.classcomposition.after;

public final class Direwolf implements Animal {

    private final Wolf origin;

    public Direwolf(final Wolf origin) {
        this.origin = origin;
    }

    @Override
    public String makeSound() {
        return origin.makeSound();
    }

    public Wolf asWolf() {
        return origin;
    }

    public String announceOwner() {
        return "My owner is Jon Snow, Lord Commander of the Night's Watch";
    }
}
