package org.course.functionalstyle.patternmatching.after;

public class PetSoundVisitor implements PetVisitor<String> {
    @Override
    public String visit(Dog dog) {
        return "Woof";
    }

    @Override
    public String visit(Parrot parrot) {
        return "Siri, order me 10 boxes of pizza";
    }

    @Override
    public String visit(Cat cat) {
        return "Meow";
    }
}
