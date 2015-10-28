package me.yuhuan.util

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object Regex {
  val Whitespaces = """\s+"""
  val Whitespace = """\s"""
  val SimpleSpace = " "
  val NewLine = """\n"""

  @deprecated val whitespaces = """[ \t]+"""
  @deprecated val whitespace = """\s"""
  @deprecated val leftParenthesis = """\("""
  @deprecated val rightParenthesis = """\)"""
  @deprecated val allBrackets = """(\()|(\))"""
  @deprecated val betweenTags = """>[^<>]+</"""
}
