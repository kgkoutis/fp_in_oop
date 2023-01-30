package org.course.typestypestypes.primitiveobsession.after;

import org.reusable.tuples.pair.Pair;
import org.reusable.tuples.pair.Pairs;

/**
 * We turned the DepartmentCode class into an important abstraction which is an IMMUTABLE VALUE OBJECT, otherwise known as RECORD
 * wrapping the primitive String that represents the actual code.
 *
 * All validation is happening during construction of the value object so that everything afterwards is safe to use.
 * All necessary methods are provided by the value object (equals, hashCode, toString) so that we can use the type directly.
 * The same happened with the business logic that returns the location of a department according to it's code.
 *
 * Notice that some of the logic (i.e printCode) stayed out of the class code.
 * "Bruteforcing" all logic into the class is not always the best idea (why? check below).
 *
 * Is this code "functional"?
 *
 * Short answer: DepartmentCode could be written as a record in later Java versions (with almost all the boilerplate code removed). FP languages were the first to
 * introduce records, so the class is indeed functional. If you want to see a functional version of the code, check below.
 *
 * Long answer:
 * Maybe it doesn't feel like it, because we enhanced a class with behavior and that feels object-oriented. Functional Programming tends to SEPARATE behavior from data.
 * But the truth is that even in FP languages, i) we always try to refine our model and introduce new types, avoiding working with primitives
 * to work on a higher abstraction level and ii) we work in modules that introduce many functions mostly geared to specific types, so there is the notion
 * of encapsulating behavior, maybe not inside a type but inside a module.
 *
 * So the final answer is that, avoiding primitive types for immutable value objects, without any side effects in their methods,
 * although it fits the OOP mantra (i.e marry behavior with data), is also compatible to functional programming.
 * */
public class Main {
    public static void main(String[] args) {

        final Pair<DepartmentCode, DepartmentCode> codes = createCodes();
        final DepartmentCode code1 = codes.first();
        final DepartmentCode code2 = codes.second();

        printCode(code1, "code1");
        printCode(code2, "code2");

        final boolean areEqual = compareCodes(code1, code2);
        System.out.println("code1 and code2 are equal: " + areEqual);

        final DepartmentCode code3 = copyCode(code1);
        System.out.println("Printing copy of code1");
        printCode(code3, "code3");

        final Location location = getLocationFromCode(code1);
        System.out.println("Location of code1 is: " + location);
    }
    private static Location getLocationFromCode(DepartmentCode code) {
        return code.getLocation();
    }

    private static DepartmentCode copyCode(DepartmentCode code) {
        return code.copy();
    }
    private static void printCode(DepartmentCode code, String name) {
        System.out.printf("The IBM department " + name + " is: %s\n", code);
    }

    private static boolean compareCodes(DepartmentCode code1, DepartmentCode code2) {
        return code1.equals(code2);
    }

    private static Pair<DepartmentCode, DepartmentCode> createCodes() {
        System.out.println("code1 and code2 are created");

        // create code1 and code2 using the factory method, validation is ensured
        DepartmentCode code1 = DepartmentCode.of("112334");
        DepartmentCode code2 = DepartmentCode.of("456789");
        return Pairs.of(code1, code2);
    }
}

// ---------------------------------------------

/**
 *
 * a) Combining behavior and data is the main goal in OOP.
 * When these two are married correctly, we can create a lot of value.
 * Instead of having an "anemic model" (i.e. a class with mostly getters and setters where the logic is spread out over the application)
 * we have a "rich model" (i.e. a class with a lot of behavior and data that is easy to use and understand).
 *
 * Unfortunately out there are "true OOP" believers that overuse this principle.
 * Overencapsulating behavior in a class can introduce significant coupling, can make the code untestable and without using tricks
 * like e.g. reflection can make it impossible to extend.
 * Thankfully, the decline of popularity in OOP due to the rise of FP has made this problem less common.
 *
 * b) It might lead "feature envy" which is an anti-pattern when one class basically has "stolen" logic that should have belonged to another class.
 *
 * THEREFORE: Be judicious of your design and use every design style with moderation.
 * */

// ---------------------------------------------

/**
 * This code is functional because it introduces a value object (DepartmentCode) that is immutable (so thread-safe!),
 * and basically aligns with the semantics of an FP language record.
 *
 * E.g. In Haskell we would write:
 *
 *      newtype DepartmentCode = DepartmentCode { code :: String } deriving (Eq, Ord, Show)
 *
 * where "deriving" takes care all the "equality, ordering and printing" semantics for us. For the rest.
 *
 *      isSixDigit :: String -> Bool
 *      isSixDigit s = length s == 6 && all Data.Char.isDigit s
 *
 *      mkDepartmentCode :: String -> Maybe DepartmentCode
 *      mkDepartmentCode s = if isSixDigit s then Just (DepartmentCode s) else Nothing
 *
 *      data Location = Amsterdam | Groningen deriving (Eq, Ord, Show)
 *
 *      getLocation :: DepartmentCode -> Location
 *      getLocation (DepartmentCode s) = if s < "600000" then Amsterdam else Groningen
 *
 * Also take notice the word "record". Newer versions of Java, C#, Python, Kotlin etc. have introduced their own concept of "records" which is super similar
 * to the one of FP languages like Haskell and saves us from writing a ton of boilerplate.
 */