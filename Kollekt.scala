import scala.collection.mutable.HashMap

object Kollekt{

  val config = HashMap[String, Int](
    "bucket_timeout" -> 20,  // buckets time out after two minutes of inactivity
    "bucket_maxsize" -> 50,   // max 50 items per bucket
    "bucket_maxage" -> 3600,  // buckets time out after one hour
    "store_deadlist" -> 0    // do not store a list of dead buckets by default
  )

  val stats = HashMap[String, Int](
    "buckets_persisted" -> 0,
    "buckets_killed_maxsize" -> 0,
    "buckets_killed_maxage" -> 0,
    "dropped_messages" -> 0
  )


  val writer = new Writer  
  val dispatcher = new Dispatcher
  val heartbeat = new Heartbeat(dispatcher)
  val listener = new Listener(dispatcher)

  writer.start
  dispatcher.start


  def main(args : Array[String]) : Unit = 
    listener.listen("localhost", 2323)

}


