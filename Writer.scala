import scala.actors.Actor
import scala.actors.Actor._

class Writer extends Actor {

  def act(){ 
    Actor.loop{ react{
      case msg: String => persist(msg)  
    }}
  }

  def persist(msg: String){
    println("persisting: " + msg)
  }

}