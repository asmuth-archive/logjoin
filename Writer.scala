import scala.actors.Actor
import scala.actors.Actor._

class Writer extends Actor {

  def act(){ 
    Actor.loop{ react{
      case msg: (String, Set[String]) => (persist _) tupled msg
    }}
  }


  def persist(bucket_id: String, data: Set[String]) =
    println(("FIXPAUL:PERSIST", bucket_id, data))


}