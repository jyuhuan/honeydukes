package me.yuhuan.util.io

import java.util.Scanner
import java.util.zip.ZipInputStream

import me.yuhuan.util.StandardStrings

import scala.collection.mutable

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
object ZipFile {
  def read(pathToZip: String): mutable.HashMap[String, String] = {
    val docs = scala.collection.mutable.HashMap[String, String]()
    val inputStream = this.getClass.getResourceAsStream(pathToZip)
    val zipInputStream = new ZipInputStream(inputStream)
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
