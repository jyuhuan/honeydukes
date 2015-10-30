package me.yuhuan.util

import scala.collection._

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 10/28/15.
 */
object SeqUtils {
  def withoutItemAt[X](s: Seq[X], idx: Int): Seq[X] = s.view.slice(0, idx) ++ s.view.slice(idx + 1, s.length)

//  def cartesianProduct[X, Y](xs1: Seq[X], xs2: Seq[Y]): Seq[(X, Y)] = new Iterable[(X, Y)] {
//    def iterator: scala.Iterator[(X, Y)] = new scala.Iterator[(X, Y)] {
//      var p1 = 0
//      var p2 = -1
//
//      def next(): (X, Y) = {
//        p2 += 1
//        if (p2 == xs2.length) {
//          p2 = 0
//          p1 += 1
//        }
//        (xs1(p1), xs2(p2))
//      }
//
//      def hasNext: Boolean = !(p1 == xs1.length - 1 && p2 == xs2.length - 1)
//    }
//  }.toSeq


}
