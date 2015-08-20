package me.yuhuan.util

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object Regex {
  val whitespaces = """[ \t]+"""
  val whitespace = """\s"""
  val leftParenthesis = """\("""
  val rightParenthesis = """\)"""
  val allBrackets = """(\()|(\))"""

  val betweenTags = """>[^<>]+</"""
}
