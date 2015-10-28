package me.yuhuan.util

import me.yuhuan.algebra.MultiplicativeMonoid

import scala.collection.{mutable, Iterable, Iterator}

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 10/7/15.
 */
object IterableUtils {

  def indexed[X](xs: Iterable[X]) = new Iterator[(Int, X)] {
    var index = -1
    val iter = xs.iterator
    override def hasNext: Boolean = iter.hasNext
    override def next(): (Int, X) = {
      index += 1
      (index, iter.next())
    }
  }

  def split[X](xs: Iterable[X], p: X ⇒ Boolean): Iterable[Iterable[X]] = {
    val result = mutable.ArrayBuffer[Iterable[X]]()

    var cur = mutable.ArrayBuffer[X]()

    for (x ← xs) {
      if (p(x)) {
        result += cur
        cur = mutable.ArrayBuffer[X]()
      }
      else cur += x
    }
    result
  }

  def prod[X](xs: Iterable[X])(implicit m: MultiplicativeMonoid[X]) = xs.foldLeft(m.one)((a, b) ⇒ m.mul(a, b))

}
