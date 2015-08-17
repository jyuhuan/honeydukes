package me.yuhuan.util

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object RegularExpressions {
  val whitespaces = """[ \t]+""" r
  val leftParenthesis = """\(""" r
  val rightParenthesis = """\)""" r
  val allBrackets = """(\()|(\))""" r

  val betweenTags = """>[^<>]+</""".r
}
