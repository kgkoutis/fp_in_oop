package org.course.functionalstyle.patternmatching.after;

public final class PetSoundVisitor implements PetVisitor<String> {
    @Override
    public String visit(final Dog dog) {
        return "Woof";
    }

    @Override
    public String visit(final Parrot parrot) {
        return "Siri, order me 10 boxes of pizza";
    }

    @Override
    public String visit(final Cat cat) {
        return "Meow";
    }
}
