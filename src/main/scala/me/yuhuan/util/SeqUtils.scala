package me.yuhuan.util

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 10/28/15.
 */
object SeqUtils {
  def withoutItemAt[X](s: Seq[X], idx: Int): Seq[X] = s.view.slice(0, idx) ++ s.view.slice(idx + 1, s.length)
}
