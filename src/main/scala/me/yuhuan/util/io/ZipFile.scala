package me.yuhuan.util.io

import java.io.InputStream
import java.util.Scanner
import java.util.zip.ZipInputStream

import me.yuhuan.util.value.StandardStrings

import scala.collection.mutable

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
object ZipFile {
  def read(is: InputStream): mutable.HashMap[String, String] = {
    val docs = scala.collection.mutable.HashMap[String, String]()
    val zipInputStream = new ZipInputStream(is)
    var curZipEntry = zipInputStream.getNextEntry
    while (curZipEntry != null) {
      if (!curZipEntry.isDirectory) {
        val zipEntryName = curZipEntry.getName
        val sb = new StringBuilder()
        val scanner = new Scanner(zipInputStream, "UTF-8")
        while (scanner.hasNextLine) {
          sb append scanner.nextLine()
          sb append StandardStrings.NewLine
        }
        docs(zipEntryName) = sb.toString()
      }
      curZipEntry = zipInputStream.getNextEntry
    }
    docs
  }
}
