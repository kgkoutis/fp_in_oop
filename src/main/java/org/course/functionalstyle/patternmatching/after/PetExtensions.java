package org.course.functionalstyle.patternmatching.after;

public final class PetExtensions {
    public static String getSound(final Pet pet)
    {
        return new PetSoundVisitor().visit(pet);
    }
}
