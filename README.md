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
language, which means that it tries to eliminate side effects as much as possible. This is a very powerful concept, but it is also more
difficult to use. Most FP languages do push for function purity, but every language follows this at all costs. 
The most popular FP language that is not pure is probably Scala, which is a hybrid FP language that can run on the JVM
and can interoperate with Java.

As an object-oriented developer, you are used to OOP and imperative programming:
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
In fact Java, as so many other languages the last decade, already has lended several features from FP. By exercising FP, we will make our code more concise,
more readable (more often than not), more maintainable and more testable.

The course is named "Functional Programming in OOP" because we are trying to show
that FP concepts can be used in OOP languages, and that they
**don't** have to come into conflict with OOP concepts. We are not trying to
showcase how the two thought systems can coexist symbiotically. Basically, we are
trying to reach the point where we get the best of both worlds.

The course is revolving around two main concepts: adopting FP style for OOP code
tends to create 2 broad categories of code: Code that is "functional-must" and code
that is "functional-friendly". What this means to say is that there are some patterns and
code concepts that we are going to cover that i) FP languages themselves absolutely need them
in order to be functional and ii) there are other concept and patterns that FP languages can do without, but 
OOP languages can't when writing in FP style(*). The following table shows the two categories:

|               | "Functional-must" code                       | "Functional-friendly" code                                        |
|---------------|----------------------------------------------|-------------------------------------------------------------------|
| FP Languages  | Absolutely necessary                         | Unnecessary, or at least optional                                 |
| OOP Languages | Necessary when writing in FP style           | Helps a lot when writing in FP style                              |
| Examples      | function composition, pattern matching, etc. | Builders, Factories, types composition, primitive obsession, etc. |

This distinction is *completely our own*, and it is not a standard one out in the world. But practical experience has shown us that
it makes sense to present both categories, to reinforce the sentiment that some OOP techniques and patterns are still useful
when writing in FP style. 

Of course there are other concepts that are Functional-must but OOP languages don't *really* need them, like e.g recursion.
These concepts are therefore not covered in this course.

(*) they can't do without most of the time, at least. Every language has its own quirks and limitations.

## Material not covered in this course
There are various topics that we could cover revolving around FP such as
recursion, tail call optimization, folds, going deeper into the theory of FP (lambda calculus, category theory, etc.) to present concepts such as monoids, semigroups, functors, applicatives, monads etc. and many more, 
or how FP plays with other important topics like concurrency, parallelism, application testing, application architecture, system architecture etc.

We chose specifically a subset of FP concepts to present something coherent that the user might find useful in his/her daily work.

If you need to learn more about FP for OOP languages, we recommend the following series of Manning books:

* [Functional Programming in Java](https://www.manning.com/books/functional-programming-in-java)
* [Functional Programming in C#](https://www.manning.com/books/functional-programming-in-c-sharp)
* [Functional Programming in Scala](https://www.manning.com/books/functional-programming-in-scala)
* [Functional Programming in Kotlin](https://www.manning.com/books/functional-programming-in-kotlin)
* [Functional Programming in Javascript](https://www.manning.com/books/functional-programming-in-javascript)

..etc, whatever suits your needs, plus one more (upcoming) book for Java, that we took inspiration from for this course:
* [A Functional Approach to Java](https://learning.oreilly.com/library/view/a-functional-approach/9781098109912/)

If you want to solidify your knowledge about OOP, we (highly) recommend the following book 
(it will help you even if you are not a Java developer per se):
* [Effective Java 3rd edition](https://www.amazon.com/Effective-Java-Joshua-Bloch/dp/0134685997)

and if you want to go deeper to learning FP languages, we recommend the following books:
* [Haskell Programming from first principles](https://haskellbook.com/) (Haskell)
* [Programming in Scala 3rd edition](https://www.amazon.com/Programming-Scala-Martin-Odersky/dp/0981531644) (Scala)
* [Get Programming with F#: A guide for .NET developers ](https://www.amazon.com.au/Get-Programming-guide-NET-developers/dp/1617293997) (F#)

and many more.. A few clicks on your search engine will present you a ton of options. 

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

## Parting words

TODO :) 