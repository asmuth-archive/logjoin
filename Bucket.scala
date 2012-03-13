import scala.actors.Actor
import scala.actors.Actor._

class Bucket(bucket_id: String) extends Actor {

  println("new bucket: " + bucket_id)

  def act() = { 
    Actor.loop{ react{
      case HearbeatSig => heartbeat
      case msg: String => println("bucket add:" + msg)
    }}
  }

  def heartbeat() =
    println("heartbeat")

  def killed() =
  	println("BUCKET KILLED: " + bucket_id)
 
}