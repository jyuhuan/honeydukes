package me.yuhuan.utility

import me.yuhuan.algebra.MultiplicativeMonoid

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object CollectionSweeteners {
  implicit class IterableIsSweet[X](val xs: Iterable[X]) extends AnyVal {
    def prod(implicit m: MultiplicativeMonoid[X]) = xs.foldLeft(m.one)((a, b) ⇒ m.mul(a, b))
  }
}
