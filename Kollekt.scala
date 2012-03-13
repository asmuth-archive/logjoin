import scala.actors.Actor
import scala.actors.Actor._
import java.net.DatagramPacket 
import java.net.DatagramSocket
import scala.collection.mutable.HashMap

object Kollekt{

  val config = HashMap[String, Int](
    "bucket_timeout" -> 600,  // buckets time out after 10 minutes of inactivity
    "bucket_maxsize" -> 50,   // max 50 items per bucket
    "bucket_maxage" -> 3600   // buckets time out after one hour
  )

  val stats = new HashMap[String, Int].withDefault(
    (s: String) => 0
  )


  val dispatcher = new Dispatcher
  dispatcher.start

  val heartbeat = new Heartbeat(dispatcher)


  def main(args : Array[String]) : Unit = 
    listen("localhost", 2323)


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


