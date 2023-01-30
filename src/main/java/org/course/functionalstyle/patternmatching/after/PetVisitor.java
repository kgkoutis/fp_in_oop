package org.course.functionalstyle.patternmatching.after;

public interface PetVisitor<T> {
    T visit(Dog dog);
    T visit(Parrot parrot);
    T visit(Cat cat);

    default T visit(final Pet pet) {
        if (pet instanceof Dog) {
            return visit((Dog) pet);
        } else if (pet instanceof Parrot) {
            return visit((Parrot) pet);
        } else if (pet instanceof Cat) {
            return visit((Cat) pet);
        } else {
            throw new IllegalArgumentException("Unknown pet type");
        }
    }
}
