package org.course.typestypestypes.primitiveobsession.before;

import org.course.typestypestypes.primitiveobsession.before.Helpers.*;
import org.reusable.tuples.pair.Pair;
import org.reusable.tuples.pair.Pairs;

/**
 * There are 2 big issues (that cause all sort of code smells) in this codebase. It can be said that it is simply a mess.
 *
 * The first issue is the fact that we keep working with Strings.
 * Check for example the input parameters of the methods (:compareCodes), (:getLocationFromCode).
 * Working with the lower-level types makes as avoid working with Domain-level types and
 * leading to needless validation and conversion code all over the place. It can also lead to silly errors when you
 * mistake one String argument for another, e.g. when a function wants 3 strings and you place the first one in the slot of the 2nd one.
 *
 * The 2nd issue is the fact that all the functionality for DepartmentCode and Location, that could have been part of these 2 classes,
 * is spread across multiple classes (take a look in Helpers folder).
 *
 * This is not always a bad thing, especially if the helper class mutates the object it is working with.
 * But i) all the helpers here are leaving the inner state of the DepartmentCode and Location objects intact
 * and ii) they correspond to standard informational methods a class could possess on its own (equals, hashCode, toString, compareTo, copy, etc.).
 *
 * What is needed is that we encapsulate all the validation logic into the DepartmentCode class so that when an DepartmentCode object is created
 * it is guaranteed to be valid. We also need to move as much functionality as possible that is related to DepartmentCode and Location into the body of these classes.
 *
 * How do we do all of that?
 *
 * SIDENOTE: We assume that the DepartmentCode as concept is a 6-digit code of the form "123456" where if the first digit is less than 6
 * then the corresponding Location is Amsterdam, otherwise Groningen.
 */
public class Main {
    public static void main(String[] args) {
        final Pair<String, String> codes = createCodes();
        final String code1 = codes.first();
        final String code2 = codes.second();

        final DepartmentCodePrinter printer = new DepartmentCodePrinter();
        printer.print(code1, "code1");
        printer.print(code2, "code2");

        final boolean areEqual = compareCodes(code1, code2);
        System.out.println("code1 and code2 are equal: " + areEqual);

        final DepartmentCode code3 = copyCode(code1);

        System.out.println("Printing copy of code1");
        printer.print(code3.getCode(), "code3");

        final Location location = getLocationFromCode(code1);
        System.out.println("Location of code1 is: " + location);
    }

    private static Location getLocationFromCode(String code1) {
        final LocationPicker locationPicker = new LocationPicker();
        return locationPicker.getLocation(code1);
    }

    private static DepartmentCode copyCode(String code1) {
        return DepartmentCodeCopier.copy(code1);
    }

    private static boolean compareCodes(String code1, String code2) {
        final DepartmentCodeEqualityComparer comparer = new DepartmentCodeEqualityComparer();
        return comparer.areEqual(code1, code2);
    }

    private static Pair<String, String> createCodes() {
        final DepartmentCodeValidator validator = new DepartmentCodeValidator();

        // validate code1
        final String code1 = "112334";
        if (!validator.isValid(code1)) {
            throw new IllegalArgumentException("Invalid department code1");
        }

        // validate code2
        final String code2 = "456789";
        if (!validator.isValid(code2)) {
            throw new IllegalArgumentException("Invalid department code2");
        }

        System.out.println("code1 and code2 are created");
        return Pairs.of(code1, code2);
    }
}

