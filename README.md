## Introduction to the course

What is functional programming (FP) and why should you care?

Most simply put, it is a family of concepts that leads to treat computation as the evaluation of mathematical functions.
It leads to an alternative way of creating programs by passing application state exclusively through functions that
combine and transform it. This combination of functions is called function composition.

The historical roots of functional programming are in lambda calculus, a formal system that was developed in the 1930s
by Alonzo Church and others. It is a formal system that is based on the concept of functions as mathematical objects.
The first programming language that was based on lambda calculus was Lisp, which was developed in the 1950s.
Lisp is still used today, but it is not the most popular FP language. The most popular functional
programming language is probably Haskell, which was developed in the 1980s. Haskell is a pure FP
language, which means that it does not have any side effects. This is a very powerful concept, but it is also very
difficult to use. Most FP languages are therefore not pure. The most popular FP language that is not pure functional
programming language is probably Scala, which is a hybrid FP language that is based on Java.

As an object-oriented developer, you are used to imperative programming:
by defining a series of statements, you are telling the computer what to do
to accomplish a particular task with a sequence of statements.
For a programming language to be considered functional, a declarative style
to express the logic of computations without describing their actual control flow
needs to be achievable. In such a declarative programming style, you describe the
outcome and how your program should work with expressions, not what it should
do with statements.

Besides a declarative style, FP languages have a few other
characteristics that make them different from imperative programming languages.

1. Simplicity: Without state and side effects, your functions tend to be smaller, doing “just what they are supposed to do.”
2. Consistency: Immutable data structures are reliable and consistent. No more worries about program state changes without you knowing.
3. (Mathematical) Correctness: Simpler code with consistent data structures will automatically lead to “more correct” code with a smaller bug surface. The “purer” your code, the easier it will be to reason with, leading to simpler debugging and testing.
4. Safer Concurrency: Concurrency is one of the most challenging tasks to do right in “classical” Java. Functional concepts allow you to eliminate many headaches and gain safer parallel-processing (almost) for free.
5. Modularity: Small and independent functions lead to simpler reusability and modularity. Combined with functional composition and partial application, you have powerful tools to build more complex tasks out of these smaller parts easily.

Some other opt-in features of FP languages are:
Function purity, Laziness, Strong type systems, Tail recursion, Higher-kinded types, automatic memory management and much more.

## What is this course about?

This course is about FP in OOP, by using the Java programming language, as we assume that you are already familiar with it.
Java is not primarily a FP language, but we can use FP concepts to make our Java code more functional.
In fact Java already has lended several features from FP. By exercising FP, we will make our code more concise,
more readable (more often than not), more maintainable and more testable.

The course is named "Functional Programming in OOP" because we are trying to show
that FP concepts can be used in OOP languages, and that they
**don't** have to come into conflict with OOP concepts. We are not trying to
showcase how the two thought systems can coexist symbiotically. Basically, we are
trying to reach the point where we get the best of both worlds.

## Order of the chapters

1. typescomposition (exercise)
2. functioncomposition
3. dependencyinjection/functionalfactories
4. dependencyinjection/partialapplication
5. handling_void_and_no_input (exercise)
6. loanpattern
7. sideeffects

---

8. missingabstractions (exercise)
9. primitiveobsession
10. buildingpatterns

---

11. extendingtypes (exercise)
12. streams
13. immutability (exercise)
14. patternmatching
15. lazyevaluation

---

16. discriminatedunions
17. exceptionhandling
18. lenses
