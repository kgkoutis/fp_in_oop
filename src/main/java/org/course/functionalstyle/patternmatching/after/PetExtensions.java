package org.course.functionalstyle.patternmatching.after;

public class PetExtensions {
    public static String getSound(Pet pet)
    {
        return new PetSoundVisitor().visit(pet);
    }
}
