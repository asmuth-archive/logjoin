import scala.collection.mutable.HashMap;

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


class Bucket(bucket_id: String) extends Actor {

  println("new bucket: " + bucket_id)

  def act(){ 
    Actor.loop{ react{
      case msg: String => println("bucket add:" + msg)  
    }}
  }

}


object BucketFactory { // EPIC WIN FTW! :)

  val buckets = HashMap[String, Bucket]()

  def get(bucket_id: String) : Bucket = {
    // buckets(bucket_id)
    new Bucket("fnord")
  }

}

class Listener extends Actor {

  def act() = {
    Actor.loop{ react{
      case msg: String => parseAndRoute(msg)  
    }}
  }

  def parseAndRoute(msg: String) = 
    (route _) tupled parse(msg)


  def route(bucket_id: String, msg: String) = 
    BucketFactory.get(bucket_id) ! msg


  def parse(msg: String) : (String, String) = {
    ("bucket_id", "mydata")
  }

}

val listener = new Listener

listener ! "fubar|||mydata1"
listener ! "fubar|||mydata2"
listener ! "fubar|||mydata3"
listener ! "fubar|||mydata4"

listener.start

while (true) {
  //println("sleep")
}
