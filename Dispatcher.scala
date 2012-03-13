import scala.actors.Actor
import scala.actors.Actor._

class Dispatcher extends Actor {

  def act() = {
    Actor.loop{ react{
      case msg: String => parseAndRoute(msg)
      case HearbeatSig => heartbeat
    }}
  }

  def route(bucket_id: String, msg: String) = 
    BucketFactory.deliver(bucket_id, msg)


  def parse(msg: String) : (String, String) =
    ((p: Array[String]) => ((p(0), p(1)))) (msg split ";")


  def parseAndRoute(msg: String) =
    (route _) tupled parse(msg)

  
  def heartbeat() =
    BucketFactory.broadcast(HearbeatSig)
    
}