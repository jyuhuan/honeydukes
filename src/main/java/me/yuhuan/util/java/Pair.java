package me.yuhuan.util.java;

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 11/2/15.
 */
public class Pair<X, Y> {

    public X x;
    public Y y;

    private Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public static <X, Y> Pair<X, Y> of(X x, Y y) {
        return new Pair<>(x, y);
    }
}
