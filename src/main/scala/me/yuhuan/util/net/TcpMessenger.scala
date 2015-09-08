package me.yuhuan.util.net

import java.io._
import java.net.Socket

/**
 * A TCP message manager. Encapsulates message sending/receiving hassles.
 *
 * @param output The output data stream.
 * @param input The input data stream.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
class TcpMessenger(val output: DataOutputStream, val input: DataInputStream) {
  def sendInt(i: Int) = output.writeInt(i)
  def receiveInt = input.readInt()

  def sendString(s: String) = output.writeUTF(s)
  def receiveString = input.readUTF()

  def sendStrings(ss: Seq[String]) = {
    output.writeInt(ss.length)
    ss.foreach(s ⇒ output.writeUTF(s))
  }
  def receiveStrings = {
    val count = input.readInt()
    val result = Array.ofDim[String](count)
    for (i ← 0 until count) result(i) = input.readUTF()
    result
  }
}

object TcpMessenger {
  /**
   * Creates a message manager for a TCP socket, which encapsulates message sending/receiving hassles.
   * @param socket The server or client TCP socket.
   * @return A message manager for the socket provided.
   */
  def apply(socket: Socket): TcpMessenger = new TcpMessenger(new DataOutputStream(socket.getOutputStream), new DataInputStream(socket.getInputStream))
  def apply(host: String, port: Int): TcpMessenger = this(new Socket(host, port))
}