package me.yuhuan.util

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 10/28/15.
 */
object SeqSweeteners {
  implicit class SeqIsSweet[X](val s: Seq[X]) extends AnyVal {
    def withoutItemAt(idx: Int): Seq[X] = SeqUtils.withoutItemAt(s, idx)
//    def cartesianProduct[Y](that: Seq[Y]) = SeqUtils.cartesianProduct(s, that)
  }

}
