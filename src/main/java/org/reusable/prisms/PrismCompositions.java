package org.reusable.prisms;

import org.reusable.lenses.Lens;
import org.reusable.maybe.Maybe;

import static org.reusable.prisms.Prism.of;
import static org.reusable.prisms.Prism.mof;

public class PrismCompositions {
    /// <summary>
    /// Convert a lens to a prism
    /// </summary>
    public static <A, B> Prism<A, B> prism(Lens<A, B> la) {
        return of(la);
    }
    /// <summary>
    /// Convert a lens to a prism
    /// </summary>
    public static <A, B> Prism<A, B> mprism(Lens<A, Maybe<B>> la) {
        return mof(la);
    }

    /// <summary>
    /// Sequentially composes two prisms
    /// </summary>
    public static <A, B, C> Prism<A, C> prism(Prism<A, B> pa, Prism<B, C> pb) {
return of(
                a -> pa.getF.apply(a).bind(pb.getF),
                v -> pa.update(pb.setF.apply(v)));
    }

    /// <summary>
    /// Sequentially composes three prisms
    /// </summary>
    public static <A, B, C, D> Prism<A, D> prism(Prism<A, B> pa, Prism<B, C> pb, Prism<C, D> pc) {
        return of(
                a -> pa.getF.apply(a).bind(pb.getF).bind(pc.getF),
                v -> pa.update(pb.update(pc.setF.apply(v))));
    }

    /// <summary>
    /// Sequentially composes four prisms
    /// </summary>
    public static <A, B, C, D, E> Prism<A, E> prism(Prism<A, B> pa, Prism<B, C> pb, Prism<C, D> pc, Prism<D, E> pd) {
        return of(
                a -> pa.getF.apply(a).bind(pb.getF).bind(pc.getF).bind(pd.getF),
                v -> pa.update(pb.update(pc.update(pd.setF.apply(v)))));
    }

    /// <summary>
    /// Sequentially composes five prisms
    /// </summary>
    public static <A, B, C, D, E, F> Prism<A, F> prism(Prism<A, B> pa, Prism<B, C> pb, Prism<C, D> pc, Prism<D, E> pd, Prism<E, F> pe)
    {
        return of(
                a -> pa.getF.apply(a).bind(pb.getF).bind(pc.getF).bind(pd.getF).bind(pe.getF),
                v -> pa.update(pb.update(pc.update(pd.update(pe.setF.apply(v))))));
    }

    /// <summary>
    /// Sequentially composes six prisms
    /// </summary>
    public static <A, B, C, D, E, F, G> Prism<A, G> prism(Prism<A, B> pa, Prism<B, C> pb, Prism<C, D> pc, Prism<D, E> pd, Prism<E, F> pe, Prism<F, G> pf)
    {
        return of(
                a -> pa.getF.apply(a).bind(pb.getF).bind(pc.getF).bind(pd.getF).bind(pe.getF).bind(pf.getF),
                v -> pa.update(pb.update(pc.update(pd.update(pe.update(pf.setF.apply(v)))))));
    }

    /// <summary>
    /// Sequentially composes seven prisms
    /// </summary>
    public static <A, B, C, D, E, F, G, H> Prism<A, H> prism(Prism<A, B> pa, Prism<B, C> pb, Prism<C, D> pc, Prism<D, E> pd, Prism<E, F> pe, Prism<F, G> pf, Prism<G, H> pg) {
        return of(
                a -> pa.getF.apply(a).bind(pb.getF).bind(pc.getF).bind(pd.getF).bind(pe.getF).bind(pf.getF).bind(pg.getF),
                v -> pa.update(pb.update(pc.update(pd.update(pe.update(pf.update(pg.setF.apply(v))))))));
    }

    /// <summary>
    /// Sequentially composes eight prisms
    /// </summary>
    public static <A, B, C, D, E, F, G, H, I> Prism<A, I> prism(Prism<A, B> pa, Prism<B, C> pb, Prism<C, D> pc, Prism<D, E> pd, Prism<E, F> pe, Prism<F, G> pf, Prism<G, H> pg, Prism<H, I> ph)
    {
        return of(
                a -> pa.getF.apply(a).bind(pb.getF).bind(pc.getF).bind(pd.getF).bind(pe.getF).bind(pf.getF).bind(pg.getF).bind(ph.getF),
                v -> pa.update(pb.update(pc.update(pd.update(pe.update(pf.update(pg.update(ph.setF.apply(v)))))))));
    }

    /// <summary>
    /// Sequentially composes nine prisms
    /// </summary>
    public static <A, B, C, D, E, F, G, H, I, J> Prism<A, J> prism(Prism<A, B> pa, Prism<B, C> pb, Prism<C, D> pc, Prism<D, E> pd, Prism<E, F> pe, Prism<F, G> pf, Prism<G, H> pg, Prism<H, I> ph, Prism<I, J> pi)
    {
        return of(
                a -> pa.getF.apply(a).bind(pb.getF).bind(pc.getF).bind(pd.getF).bind(pe.getF).bind(pf.getF).bind(pg.getF).bind(ph.getF).bind(pi.getF),
                v -> pa.update(pb.update(pc.update(pd.update(pe.update(pf.update(pg.update(ph.update(pi.setF.apply(v))))))))));
    }

    /// <summary>
    /// Sequentially composes ten prisms
    /// </summary>
    public static <A, B, C, D, E, F, G, H, I, J, K> Prism<A, K> prism(Prism<A, B> pa, Prism<B, C> pb, Prism<C, D> pc, Prism<D, E> pd, Prism<E, F> pe, Prism<F, G> pf, Prism<G, H> pg, Prism<H, I> ph, Prism<I, J> pi, Prism<J, K> pj)
    {
        return of(
                a -> pa.getF.apply(a).bind(pb.getF).bind(pc.getF).bind(pd.getF).bind(pe.getF).bind(pf.getF).bind(pg.getF).bind(ph.getF).bind(pi.getF).bind(pj.getF),
                v -> pa.update(pb.update(pc.update(pd.update(pe.update(pf.update(pg.update(ph.update(pi.update(pj.setF.apply(v)))))))))));
    }
}
