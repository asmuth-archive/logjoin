import scala.actors.Actor
import scala.actors.Actor._
import java.net.DatagramPacket 
import java.net.DatagramSocket

object Kollekt{

  val dispatcher = new Dispatcher

  def main(args : Array[String]) : Unit = 
    listen("localhost", 2323)

  def listen(host: String, port: Int) = {
    val sock = new DatagramSocket(port) 

    // buffers for receiving data, default size is 1024
    val buffer_size = 1024
    val buffer = new Array[Byte](buffer_size) 
    val packet = new DatagramPacket(buffer, buffer_size)  

    while (true) { 
      // block until the next packet was received
      sock.receive(packet)

      // process the received packet
      received(packet)
    } 
  }

  def received(packet: DatagramPacket) = {
    // cast the received data to a string
    val data_str = new String(packet.getData(), 0, packet.getLength())

    println("received -> " + data_str) 
  }

}


