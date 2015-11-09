package me.yuhuan

/**
  * @author Yuhuan Jiang (jyuhuan@gmail.com).
  */
package object util {
  @inline def default[T]: T = {
    class Default {
      var default: T = _
    }
    (new Default).default
  }
}
