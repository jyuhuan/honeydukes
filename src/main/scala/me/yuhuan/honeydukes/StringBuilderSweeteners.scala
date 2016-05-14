package me.yuhuan.honeydukes

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
object StringBuilderSweeteners {
  implicit class StringBuilderIsSweet(val self: StringBuilder) extends AnyVal {
    def ++=(xs: TraversableOnce[Char]): StringBuilder = self append xs
    def ++=(xs: Array[Char]): StringBuilder = self append xs

    def +=(x: Any): StringBuilder = self append x
    def +=(x: String): StringBuilder = self append x
    def +=(x: StringBuilder): StringBuilder = self append x
    def +=(x: Boolean): StringBuilder = self append x
    def +=(x: Byte): StringBuilder = self append x
    def +=(x: Short): StringBuilder = self append x
    def +=(x: Int): StringBuilder = self append x
    def +=(x: Long): StringBuilder = self append x
    def +=(x: Float): StringBuilder = self append x
    def +=(x: Double): StringBuilder = self append x
    def +=(x: Char): StringBuilder = self append x

    def str: String = self.toString()
  }
}

private object StringBuilderSweetenerTest extends App {
  import me.yuhuan.honeydukes.StringBuilderSweeteners._
  val sb = new StringBuilder
  sb += "a"
  sb += 2.9
  sb += true
  val s = sb.str
}