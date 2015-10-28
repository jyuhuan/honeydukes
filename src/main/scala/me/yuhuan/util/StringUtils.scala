package me.yuhuan.util

import scala.collection._

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object StringUtils {

  def wordWrap(s: String, maxLength: Int): String = {
    s.split(" ").foldLeft(Array(""))( (out, in) => {
      if ((out.last + " " + in).trim.length > maxLength) out :+ in
      else out.updated(out.size - 1, out.last + " " + in)
    }).mkString("\n").trim
  }

  def wrapWithXmlTag(s: String, tagName: String) = s"<$tagName>$s</$tagName>"

  /**
   * Converts all consecutive whitespaces into one simple spaces.
   * Eg:
   *
   *   Original: "a st   with a  \t lot of \n\t white space . "
   *   Regularized: "a st with a lot of white space . "
   *
   * @param text The string to be regularized.
   * @return Tree parts:
   * - A string with all consecutive whitespaces combined into single spaces (i.e., the regularized string).
   * - A string with all whitespaces converted into single spaces (same length as the input string).
   * - A mapping from positions in the regularized string to the original string.
   */
  def regularizeText(text: String): (String, String, Seq[Int]) = {

    var i = 0
    var j = 0

    val positionMapping = mutable.ArrayBuffer[(Int, Int)]()

    val regularizedTextBuilder = new StringBuilder()
    val originalTextBuilder = new StringBuilder()

    var lastCharWasWhitespace = false

    while (i < text.length) {
      val readHead = text(i)
      if (readHead.toString.matches(Regex.whitespace)) {
        originalTextBuilder append ' '
        if (lastCharWasWhitespace) { }
        else {
          regularizedTextBuilder append ' '
          positionMapping += j → i
          j += 1
        }
        lastCharWasWhitespace = true
      }
      else {
        originalTextBuilder append readHead
        lastCharWasWhitespace = false
        regularizedTextBuilder append readHead
        positionMapping += j → i
        j += 1
      }
      i += 1
    }

    val mapping = Array.ofDim[Int](positionMapping.length)
    positionMapping.foreach(p ⇒ mapping(p._1) = p._2)

    (regularizedTextBuilder.toString(), originalTextBuilder.toString(), mapping)
  }


}
