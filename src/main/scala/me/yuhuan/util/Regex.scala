package me.yuhuan.util

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object Regex {
  val whitespaces = """[ \t]+""" r
  val whitespace = """\s""" r
  val leftParenthesis = """\(""" r
  val rightParenthesis = """\)""" r
  val allBrackets = """(\()|(\))""" r

  val betweenTags = """>[^<>]+</""" r
}
