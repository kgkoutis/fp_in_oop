package org.reusable.lenses;

import org.reusable.maybe.Maybe;
import org.reusable.prisms.Prism;
import org.reusable.tuples.pairs.Pair;
import org.reusable.tuples.pairs.Pairs;
import org.reusable.tuples.quadruples.Quadruple;
import org.reusable.tuples.quadruples.Quadruples;
import org.reusable.tuples.triples.Triple;
import org.reusable.tuples.triples.Triples;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.reusable.lenses.Lens.of;

public class LensExtensions {
    /**
     * Pair two lenses
     */
    public static <A, B, C, D> Lens<Pair<A, B>, Pair<C, D>> pair(final Lens<A, C> First,
                                                                 final Lens<B, D> Second) {
        return of(
                a -> Pairs.of(First.getF.apply(a.first()), Second.getF.apply(a.second())),
                v -> a -> Pairs.of(First.set(v.first(), a.first()), Second.set(v.second(), a.second())));
    }

    /**
     * Triple two lenses
     */
    public static <A, B, C, D, E, F> Lens<Triple<A, B, C>, Triple<D, E, F>> triple(final Lens<A, D> First,
                                                                                   final Lens<B, E> Second,
                                                                                   final Lens<C, F> Third) {
        return of(
                a -> Triples.of(First.getF.apply(a.first()), Second.getF.apply(a.second()), Third.getF.apply(a.third())),
                v -> a -> Triples.of(First.set(v.first(), a.first()), Second.set(v.second(), a.second()), Third.set(v.third(), a.third())));
    }

    /**
     * Applies {@code pred} to the source.
     * If {@code true}, {@code Then} is selected.
     * If {@code false}, {@code Else} is selected.
     */
    public static <A, B> Lens<A, B> cond(final Function<A, Boolean> pred,
                                         final Lens<A, B> Then,
                                         final Lens<A, B> Else) {
        final Function<A, Lens<A, B>> choose = a -> pred.apply(a) ? Then : Else;

        return of(
                a -> choose.apply(a).getF.apply(a),
                v -> a -> choose.apply(a).set(v, a));
    }

    /**
     * Gets/sets the fst element in a pair
     */
    public static <A, B> Lens<Pair<A, B>, A> firstFromPair() {
        return of(
                Pair::first,
                v -> ab -> Pairs.of(v, ab.second()));
    }

    /**
     * Gets/sets the fst element in a triple
     */
    public static <A, B, C> Lens<Triple<A, B, C>, A> firstFromTriple() {
        return of(
                Triple::first,
                v -> ab -> Triples.of(v, ab.second(), ab.third()));
    }


    /**
     * Gets/sets the fst element in a quadruple
     */
    public static <A, B, C, D> Lens<Quadruple<A, B, C, D>, A> firstFromQuadruple() {
        return of(
                Quadruple::first,
                v -> ab -> Quadruples.of(v, ab.second(), ab.third(), ab.fourth()));
    }


    /**
     * Gets/sets the snd element in a pair
     */
    public static <A, B> Lens<Pair<A, B>, B> sndFromPair() {
        return of(
                Pair::second,
                v -> ab -> Pairs.of(ab.first(), v));
    }

    /**
     * Gets/sets the snd element in a triple
     */
    public static <A, B, C> Lens<Triple<A, B, C>, B> sndFromTriple() {
        return of(
                Triple::second,
                v -> ab -> Triples.of(ab.first(), v, ab.third()));
    }

    /**
     * Gets/sets the snd element in a quadruple
     */
    public static <A, B, C, D> Lens<Quadruple<A, B, C, D>, B> sndFromQuadruple() {
        return of(
                Quadruple::second,
                v -> ab -> Quadruples.of(ab.first(), v, ab.third(), ab.fourth()));
    }


    /**
     * Gets/sets the thrd element in a triple
     */
    public static <A, B, C> Lens<Triple<A, B, C>, C> thrdFromTriple() {
        return of(
                Triple::third,
                v -> ab -> Triples.of(ab.first(), ab.second(), v));
    }

    /**
     * Gets/sets the thrd element in a quadruple
     */
    public static <A, B, C, D> Lens<Quadruple<A, B, C, D>, C> thrdFromQuadruple() {
        return of(
                Quadruple::third,
                v -> ab -> Quadruples.of(ab.first(), ab.second(), v, ab.fourth()));
    }

    /**
     * Identity lens
     */
    public static <A> Lens<A, A> identity() {
        return of(
                a -> a,
                v -> a -> v);
    }

    /**
     * Creates a lens that maps the given lens in a list
     */
    public static <A, B> Lens<List<A>, List<B>> enumMap(final Lens<A, B> la) {
        return of(
                lst -> lst.stream().map(la.getF).collect(Collectors.toList()),
                v -> lst -> ListExtensions.zip(lst.stream(), v.stream(), (a, b) -> la.set(b, a))
                        .collect(Collectors.toList()));
    }

    /// <summary>
    /// Convert a Lens<A,B> to a Prism<A,B>
    /// </summary>
    public static <A, B> Prism<A, B> toPrism(final Lens<A, B> la) {
        return Prism.of(la);
    }

    /// <summary>
    /// Convert a Lens<A, Option<B>> to a Prism<A,B>
    /// </summary>
    public static <A, B> Prism<A, B> mToPrism(final Lens<A, Maybe<B>> la) {
        return Prism.mof(la);
    }

    private static class ListExtensions {
        // zip 2 streams
        public static <A, B, C> Stream<C> zip(final Stream<? extends A> a,
                                              final Stream<? extends B> b,
                                              final BiFunction<? super A, ? super B, ? extends C> zipper) {
            Objects.requireNonNull(zipper);
            final Spliterator<? extends A> aSpliterator = Objects.requireNonNull(a).spliterator();
            final Spliterator<? extends B> bSpliterator = Objects.requireNonNull(b).spliterator();

            // Zipping looses DISTINCT and SORTED characteristics
            final int characteristics = aSpliterator.characteristics() & bSpliterator.characteristics() &
                    ~(Spliterator.DISTINCT | Spliterator.SORTED);

            final long zipSize = ((characteristics & Spliterator.SIZED) != 0)
                    ? Math.min(aSpliterator.getExactSizeIfKnown(), bSpliterator.getExactSizeIfKnown())
                    : -1;

            final Iterator<A> aIterator = Spliterators.iterator(aSpliterator);
            final Iterator<B> bIterator = Spliterators.iterator(bSpliterator);
            final Iterator<C> cIterator = new Iterator<C>() {
                @Override
                public boolean hasNext() {
                    return aIterator.hasNext() && bIterator.hasNext();
                }

                @Override
                public C next() {
                    return zipper.apply(aIterator.next(), bIterator.next());
                }
            };

            final Spliterator<C> split = Spliterators.spliterator(cIterator, zipSize, characteristics);
            return (a.isParallel() || b.isParallel())
                    ? StreamSupport.stream(split, true)
                    : StreamSupport.stream(split, false);
        }
    }
}