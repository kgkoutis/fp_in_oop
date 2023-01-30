package org.reusable.lenses;

import static org.reusable.lenses.Lens.of;

public class LensCompositions {

    /**
     * Sequentially composes two lenses
     */
    public static <A, B, C> Lens<A, C> lens(Lens<A, B> la, Lens<B, C> lb) {
        return of(
                a -> lb.getF.apply(la.getF.apply(a)),
                v -> la.update(lb.setF.apply(v)));
    }

    /**
     * Sequentially composes three lenses
     */
    public static <A, B, C, D> Lens<A, D> lens(Lens<A, B> la, Lens<B, C> lb, Lens<C, D> lc) {
        return of(
                a -> lc.getF.apply(lb.getF.apply(la.getF.apply(a))),
                v -> la.update(lb.update(lc.setF.apply(v))));
    }

    /**
     * Sequentially composes four lenses
     */
    public static <A, B, C, D, E> Lens<A, E> lens(
            Lens<A, B> la, Lens<B, C> lb,
            Lens<C, D> lc, Lens<D, E> ld) {
        return of(a -> ld.getF.apply(lc.getF.apply(lb.getF.apply(la.getF.apply(a)))),
                v -> la.update(lb.update(lc.update(ld.setF.apply(v)))));
    }

    /**
     * Sequentially composes five lenses
     */
    public static <A, B, C, D, E, F> Lens<A, F> lens(
            Lens<A, B> la, Lens<B, C> lb,
            Lens<C, D> lc, Lens<D, E> ld,
            Lens<E, F> le) {
        return
                of(
                        a -> le.getF.apply(ld.getF.apply(lc.getF.apply(lb.getF.apply(la.getF.apply(a))))),
                        v -> la.update(lb.update(lc.update(ld.update(le.setF.apply(v))))));
    }

    /**
     * Sequentially composes six lenses
     */
    public static <A, B, C, D, E, F, G> Lens<A, G> lens(
            Lens<A, B> la, Lens<B, C> lb,
            Lens<C, D> lc, Lens<D, E> ld,
            Lens<E, F> le, Lens<F, G> lf) {
        return
                of(
                        a -> lf.getF.apply(le.getF.apply(ld.getF.apply(lc.getF.apply(lb.getF.apply(la.getF.apply(a)))))),
                        v -> la.update(lb.update(lc.update(ld.update(le.update(lf.setF.apply(v)))))));
    }

    /**
     * Sequentially composes seven lenses
     */
    public static <A, B, C, D, E, F, G, H> Lens<A, H> lens(
            Lens<A, B> la, Lens<B, C> lb,
            Lens<C, D> lc, Lens<D, E> ld,
            Lens<E, F> le, Lens<F, G> lf,
            Lens<G, H> lg) {
        return
                of(
                        a -> lg.getF.apply(lf.getF.apply(le.getF.apply(ld.getF.apply(lc.getF.apply(lb.getF.apply(la.getF.apply(a))))))),
                        v -> la.update(lb.update(lc.update(ld.update(le.update(lf.update(lg.setF.apply(v))))))));
    }

    /**
     * Sequentially composes eight lenses
     */
    public static <A, B, C, D, E, F, G, H, I> Lens<A, I> lens(
            Lens<A, B> la, Lens<B, C> lb,
            Lens<C, D> lc, Lens<D, E> ld,
            Lens<E, F> le, Lens<F, G> lf,
            Lens<G, H> lg, Lens<H, I> lh) {
        return
                of(
                        a -> lh.getF.apply(lg.getF.apply(lf.getF.apply(le.getF.apply(ld.getF.apply(lc.getF.apply(lb.getF.apply(la.getF.apply(a)))))))),
                        v -> la.update(lb.update(lc.update(ld.update(le.update(lf.update(lg.update(lh.setF.apply(v)))))))));
    }

    /**
     * Sequentially composes nine lenses
     */
    public static <A, B, C, D, E, F, G, H, I, J> Lens<A, J> lens(
            Lens<A, B> la, Lens<B, C> lb,
            Lens<C, D> lc, Lens<D, E> ld,
            Lens<E, F> le, Lens<F, G> lf,
            Lens<G, H> lg, Lens<H, I> lh,
            Lens<I, J> li) {
        return
                of(
                        a -> li.getF.apply(lh.getF.apply(lg.getF.apply(lf.getF.apply(le.getF.apply(ld.getF.apply(lc.getF.apply(lb.getF.apply(la.getF.apply(a))))))))),
                        v -> la.update(lb.update(lc.update(ld.update(le.update(lf.update(lg.update(lh.update(li.setF.apply(v))))))))));
    }

    /**
     * Sequentially composes ten lenses
     */
    public static <A, B, C, D, E, F, G, H, I, J, K> Lens<A, K> lens(
            Lens<A, B> la, Lens<B, C> lb,
            Lens<C, D> lc, Lens<D, E> ld,
            Lens<E, F> le, Lens<F, G> lf,
            Lens<G, H> lg, Lens<H, I> lh,
            Lens<I, J> li, Lens<J, K> lj) {
        return
                of(
                        a -> lj.getF.apply(li.getF.apply(lh.getF.apply(lg.getF.apply(lf.getF.apply(le.getF.apply(ld.getF.apply(lc.getF.apply(lb.getF.apply(la.getF.apply(a)))))))))),
                        v -> la.update(lb.update(lc.update(ld.update(le.update(lf.update(lg.update(lh.update(li.update(lj.setF.apply(v)))))))))));
    }
}
