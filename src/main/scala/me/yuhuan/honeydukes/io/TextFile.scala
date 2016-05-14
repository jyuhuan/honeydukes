package me.yuhuan.honeydukes.io

import java.io._
import java.nio.charset._
import java.nio.file._

import me.yuhuan.honeydukes._
import me.yuhuan.honeydukes.value.StandardStrings

/**
  * @author Tongfei Chen (ctongfei@gmail.com)
  * @author Yuhuan Jiang (jyuhuan@gmail.com)
  */
object TextFile {
  /**
   * Reads a file into a string.
 *
   * @param fn File name
   * @param cs Charset (default value UTF-8)
   * @return
   */
  def readAll(fn: String, cs: Charset = StandardCharsets.UTF_8) = new String(Files.readAllBytes(Paths.get(fn)), cs)

  def writeAll(fn: String)(ss: Any*) = {
    val pw = new PrintWriter(fn)
    for (s ← ss) pw.write(s.toString)
    pw.close()
  }

  /**
   * Reads a file, line by line, lazily.
 *
   * @param fn File name
   * @param cs Charset (default value UTF-8)
   * @return A non-strict list of lines.
   */
  def readLinesFromFileName(fn: String, cs: Charset = StandardCharsets.UTF_8): Iterable[String] = {
    readLines(Files.newInputStream(Paths.get(fn)), cs)
  }

  def readLines(is: InputStream, cs: Charset = StandardCharsets.UTF_8): Iterable[String] = new Iterable[String] {
    def iterator: Iterator[String] = new Iterator[String] {
      val br = new BufferedReader(new InputStreamReader(is, cs))

      var nextLine: String = null
      def hasNext = {
        if (nextLine != null) true
        else {
          nextLine = br.readLine()
          nextLine != null
        }
      }
      def next() = {
        if ((nextLine != null) || hasNext) {
          val line = nextLine
          nextLine = null
          line
        }
        else throw new NoSuchElementException
      }
    }
  }

  def readAll(is: InputStream): String = {
    readLines(is).mkString(StandardStrings.NewLine)
  }

  def writeLines(fn: String)(s: TraversableOnce[String]) = {
    val bw = Files.newBufferedWriter(Paths.get(fn))
    for (line ← s) bw.write(line + "\n")
    bw.close()
  }

  def deleteIfExists(fn: String) = {
    Files.deleteIfExists((new File(fn)).toPath)
  }
}
