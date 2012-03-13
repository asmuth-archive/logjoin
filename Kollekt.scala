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
  val heartbeat = new Heartbeat(dispatcher)
  val listener = new Listener(dispatcher)
  dispatcher.start


  def main(args : Array[String]) : Unit = 
    listener.listen("localhost", 2323)

}


