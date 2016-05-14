package me.yuhuan.honeydukes.java;

import java.util.function.Function;

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 11/3/15.
 */
public abstract class ListFactory<L> extends CollectionFactory<L> {
    public <X> L fill(int n, X x) {
        CollectionBuilder<X, L> builder = newBuilder();
        int i = n;
        while (i > 0) {
            builder.add(x);
            i -= 1;
        }
        return builder.result();
    }

    public <X> L tabulate(int n, Function<Integer, X> f) {
        CollectionBuilder<X, L> builder = newBuilder();
        int i = n;
        while (i > 0) {
            builder.add(f.apply(i));
            i -= 1;
        }
        return builder.result();
    }
}
