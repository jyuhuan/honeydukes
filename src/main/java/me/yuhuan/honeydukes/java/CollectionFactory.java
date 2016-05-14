package me.yuhuan.honeydukes.java;

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 11/3/15.
 */
public abstract class CollectionFactory<C> {
    public abstract <X> CollectionBuilder<X, C> newBuilder();

    public <X> C of(X... xs) {
        CollectionBuilder<X, C> b = newBuilder();
        b.addMany(xs);
        return b.result();
    }

    public C empty() {
        return newBuilder().result();
    }

    public <X> C from(Iterable<X> xs) {
        CollectionBuilder<X, C> b = newBuilder();
        for (X x: xs) b.add(x);
        return b.result();
    }
}
