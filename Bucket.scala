import scala.actors.Actor
import scala.actors.Actor._

object KilledSig{}

class Bucket(bucket_id: String) extends Actor {

  println("new bucket: " + bucket_id)

  def act() = { 
    Actor.loop{ react{
      case HearbeatSig => heartbeat
      case KilledSig   => killed
      case msg: String => println("bucket add:" + msg)
    }}
  }

  def heartbeat() =
    BucketFactory.kill(bucket_id) // commit sucide as often as we can... :)


  def killed() =
  	println("BUCKET KILLED: " + bucket_id)
 
}