package me.yuhuan.util.java;

import java.util.*;

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 11/2/15.
 */
public class Coll {

    public static <X, Y> HashMap<X, Y> HashMap(Pair<X, Y>... pairs) {
        HashMap<X, Y> m = new HashMap<>();
        for (Pair<X, Y> pair: pairs) {
            m.put(pair.x, pair.y);
        }
        return m;
    }

    public static <X, Y> HashSet<X> HashSet(X... xs) {
        HashSet<X> s = new HashSet<>();
        for (X x: xs) {
            s.add(x);
        }
        return s;
    }

    public static <X> ArrayList<X> ArrayList(X... xs) {
        ArrayList<X> a = new ArrayList<>();
        for (X x: xs) {
            a.add(x);
        }
        return a;
    }
}
