import scala.collection.mutable.HashMap


object Kollekt{

  var output_dir = "/tmp/kollekt/"

  val config = HashMap[String, Int](
    "bucket_timeout" -> 120,   // buckets time out after two minutes of inactivity
    "bucket_maxsize" -> 1024,  // max 1024 items per bucket
    "bucket_maxage"  -> 86400, // buckets time out after one day
    "store_deadlist" -> 0      // do not store a list of dead buckets by default
  )

  val stats = HashMap[String, Int](
    "buckets_persisted" -> 0,
    "buckets_killed_maxsize" -> 0,
    "buckets_killed_maxage" -> 0,
    "dropped_messages" -> 0
  )

  def usage() = {
    println("usage: kollekt [-lpxh] [options] /path/to/out_dir")
    println("")
    println("  -l, --listen")
    println("    listen on udp for tuples on this address")
    println("")
    println("  -p, --port")
    println("    listen on udp for tuples on this address")
    println("")
    println("  -x, --keep-deadlist")
    println("    keep a list of killed buckets in mem (ensure bucket uniqueness)")
    println("")
    println("  -t, --bucket-timeout")
    println("    flush buckets to disk after N seconds of inactivity (default: 2min)")
    println("")
    println("  -s, --bucket-maxsize")
    println("    flush buckets to disk when they reach N items (default: 1024)")
    println("")
    println("  -a, --bucket-maxage")
    println("    flush buckets to disk after at most N seconds (default: 1day)")
    println("")
    println("  -h, --help")
    println("    print this message")
  }

  val writer = new Writer  
  val dispatcher = new Dispatcher
  
  def main(args : Array[String]) : Unit = {
    if (args.length == 0) return usage

    output_dir = args(args.length-1)

    println(config + (("output_dir", output_dir)))

    val heartbeat = new Heartbeat(dispatcher)

    dispatcher.start
    writer.start

    val listener = new Listener(dispatcher)
    listener.listen("localhost", 2323)
  }
    

}

