package org.course.functionalstyle.streams.before;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * List Processing is a very common and fundamental task in FP. LISP took its name for it, the pioneer of FP programming. It allows the programmer to express
 * the computations he/she wants to perform without writing for loops, while loops, if statements, etc and other low level imperative code.
 * Nowadays, all languages have some kind of list processing capabilities, in the form of map, filter, reduce, etc.
 *
 * Streams are a new abstraction introduced in Java 8. They are a sequence of elements on which you can perform various operations.
 * They involve a lot of functional programming concepts like declarative coding, immutability, laziness, function purity, "ease" of parallelization etc.
 * They go a level above list processing since they can be applied to any Collection, not just Lists.
 *
 * To build a stream you need a source. A source can be a collection, an array, a file, a generator function (for infinite streams), etc.
 * Afterwards you can apply various intermediate operations to the stream.
 * Finally, you can collect the stream into a result. This is the terminal operation.
 *
 * Though streams are a totally Java concept but, other OOP/imperative languages have now very similar concepts (see below for fun).
 *
 * Streams however don't work good with checked exceptions (which is only a Java concept).
 *
 * Below we are trying to group strings by length. We are using a method that sometimes throws an IOException.
 * How would we handle this in code that uses streams?
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        try {
            List<String> animals = List.of("Dog","Cat","Bird","Fish","Snake","Lizard","Turtle","Rabbit","Horse","Cow");

            // group strings by length imperatively
            Map<Integer, List<String>> map = new HashMap<>();
            for (String animal : animals) {
                // 1. pass animal via `sometimesThrowsIOException` method
                String safeAnimal = sometimesThrowsIOException(animal);

                // 2. get uppercased animal
                String Animal = safeAnimal.toUpperCase();


                // 3. group by length
                int length = Animal.length();
                List<String> listWithLength = map.computeIfAbsent(length, k -> new ArrayList<>());
                listWithLength.add(Animal);
            }

            // 4. print result
            for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        } catch (IOException e) {
            // handle exception, if present
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    private static <T> T sometimesThrowsIOException(T t) throws IOException {
        int min = 0;
        int max = 10;
        int rnd = ThreadLocalRandom.current().nextInt(min, max + 1);
        if (rnd < 1) {
            throw new IOException("Random number goes boom");
        }

        return t;
    }
}

/**
 * For example to group the animal strings by length you would do the following:
 *
 * in Java:         animals.stream().map(String::toUpperCase).collect(groupingBy(String::length))
 * in Scala:        animals.map(_.toUpperCase).groupBy(_.length)
 * in C#:           animals.Select(a => a.ToUpper()).GroupBy(a => a.Length)
 * in Python:       [groupby([a.upper() for a in animals], lambda x: len(x))]  # groupby is an itertools function
 * in JavaScript:   _.groupBy(animals.map(x => x.toUpperCase()), a => a.length) # _.groupBy is a lodash function
 * in Kotlin:       animals.map { it.toUpperCase() }.groupBy { it.length }
 * */
