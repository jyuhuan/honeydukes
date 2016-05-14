package me.yuhuan.honeydukes

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object StringSweeteners {
  implicit class StringIsSweet(val s: String) extends AnyVal {
    def wordWrap(maxLength: Int): String = StringUtils.wordWrap(s, maxLength)
    def wrapWithXmlTag(tagName: String) = StringUtils.wrapWithXmlTag(s, tagName)
    def regularized = StringUtils.regularizeText(s)
  }
}
