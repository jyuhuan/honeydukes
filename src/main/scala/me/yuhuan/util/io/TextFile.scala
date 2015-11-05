package me.yuhuan.util.io

import java.io._
import java.nio.charset._
import java.nio.file._

import me.yuhuan.util._

object TextFile {
  /**
   * Reads a file into a string.
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
   * @param fn File name
   * @param cs Charset (default value UTF-8)
   * @return A non-strict list of lines.
   */
  def readLines(fn: String, cs: Charset = StandardCharsets.UTF_8): Iterable[String] = new Iterable[String] {
    def iterator = new Iterator[String] {
      val br = Files.newBufferedReader(Paths.get(fn), cs)
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

  def readLines(is: InputStream): Iterable[String] = new Iterable[String] {
    def iterator: Iterator[String] = new Iterator[String] {
      val reader = new BufferedReader(new InputStreamReader(is))
      var line = reader.readLine()

      def hasNext: Boolean = line != null

      def next(): String = {
        line = reader.readLine()
        line
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
