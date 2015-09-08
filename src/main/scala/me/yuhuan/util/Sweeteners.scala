package me.yuhuan.util

import me.yuhuan.algebra.MultiplicativeMonoid

import scala.collection._


/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object Sweeteners {
  implicit class IterableIsSweet[X](val xs: Iterable[X]) extends AnyVal {

    def indexed = new Iterator[(Int, X)] {
      var index = -1
      val iter = xs.iterator
      override def hasNext: Boolean = iter.hasNext
      override def next(): (Int, X) = {
        index += 1
        (index, iter.next())
      }
    }

    def split(p: X ⇒ Boolean): Iterable[Iterable[X]] = {
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


    def prod(implicit m: MultiplicativeMonoid[X]) = xs.foldLeft(m.one)((a, b) ⇒ m.mul(a, b))
  }

  implicit class StringIsSweet(val s: String) extends AnyVal {
    def wordWrap(maxLength: Int): String = StringUtils.wordWrap(s, maxLength)
    def wrapWithXmlTag(tagName: String) = StringUtils.wrapWithXmlTag(s, tagName)
    def regularized = StringUtils.regularizeText(s)
  }
}
