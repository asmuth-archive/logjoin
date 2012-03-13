import scala.actors.Actor
import scala.actors.Actor._
import java.net.DatagramPacket 
import java.net.DatagramSocket

class Listener(dispatcher: Dispatcher){

  def dispatch(packet: DatagramPacket) =
    dispatcher ! new String(packet.getData, 0, packet.getLength)


  def listen(host: String, port: Int) = {
    val sock = new DatagramSocket(port) 

    val buffer_size = 1024
    val buffer = new Array[Byte](buffer_size) 
    val packet = new DatagramPacket(buffer, buffer_size)  

    while (true) { 
      sock.receive(packet)
      dispatch(packet)
    } 
  }

}