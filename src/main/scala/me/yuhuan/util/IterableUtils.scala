package me.yuhuan.util

import me.yuhuan.algebra.MultiplicativeMonoid

import scala.collection.{mutable, Iterable, Iterator}

import scala.util.control.Breaks._

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

  def cartesianProduct[X, Y](xs: Iterable[X], ys: Iterable[Y]): Iterable[(X, Y)] = {
    if (xs.isEmpty || ys.isEmpty) Iterable.empty[(X, Y)]
    else new Iterable[(X, Y)] {
      def iterator: scala.Iterator[(X, Y)] = new scala.Iterator[(X, Y)] {

        val xi = xs.iterator
        var yi = ys.iterator

        var x: X = xi.next()

        def next(): (X, Y) = {
          if (!yi.hasNext) {
            yi = ys.iterator
            x = xi.next()
          }
          val y = yi.next()
          (x, y)
        }

        def hasNext: Boolean = xi.hasNext || yi.hasNext
      }
    }
  }


  def domainIterator[X](domains: Seq[Seq[X]]) = {

    trait Enumerator[+T] {
      def advance(): Boolean
      def current: T
    }

    def enumerableAsIterator[T](e: Enumerator[T]): Iterator[T] = new Iterator[T] {
      var nextElem: T = _
      var nextElemFetched: Boolean = false

      def hasNext: Boolean = {
        if (nextElemFetched) true
        else {
          if (e.advance()) {
            nextElem = e.current
            nextElemFetched = true
            true
          }
          else false
        }
      }

      def next() = {
        if (nextElemFetched) {
          nextElemFetched = false
          nextElem
        }
        else {
          e.advance()
          e.current
        }
      }
    }

    def iteratorAsEnumerator[T](xs: Iterator[T]): Enumerator[T] = new Enumerator[T] {
      var current: T = _
      def advance() = {
        if (xs.hasNext) {
          current = xs.next()
          true
        }
        else false
      }
    }


    val result = new Enumerator[Seq[X]] {

      val n = domains.length
      val iters = domains.map(d ⇒ iteratorAsEnumerator(d.iterator)).toArray

      var first = true

      def current: Seq[X] = iters.map(_.current)

      def advance(): Boolean = {
        if (first) {
          first = false
          iters.forall(_.advance())
        }
        else {
          var advanced = false
          breakable {
            for (i ← 0 until n) {
              if (iters(i).advance()) {
                advanced = true;
                break()
              }
              iters(i) = iteratorAsEnumerator(domains(i).iterator)
              iters(i).advance()
            }
          }
          advanced
        }
      }
    }

    enumerableAsIterator(result)

  }

}
