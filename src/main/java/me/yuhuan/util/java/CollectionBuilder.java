package me.yuhuan.util.java;

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 11/3/15.
 */
public abstract class CollectionBuilder<X, C> {
    public abstract void add(X x);

    public void addMany(X... xs) {
        for (X x: xs) add(x);
    }

    public abstract C result();
}
