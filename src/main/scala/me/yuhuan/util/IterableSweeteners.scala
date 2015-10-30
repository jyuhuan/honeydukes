package me.yuhuan.util

import me.yuhuan.algebra.MultiplicativeMonoid

import scala.collection.Iterable

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 10/7/15.
 */
object IterableSweeteners {
  implicit class IterableIsSweet[X](val xs: Iterable[X]) extends AnyVal {
    def indexed = IterableUtils.indexed(xs)
    def split(p: X ⇒ Boolean) = IterableUtils.split(xs, p)
    def prod(implicit m: MultiplicativeMonoid[X]) = IterableUtils.prod(xs)(m)
    def cartesianProduct[Y](that: Iterable[Y]): Iterable[(X, Y)] = IterableUtils.cartesianProduct(xs, that)
  }
}
