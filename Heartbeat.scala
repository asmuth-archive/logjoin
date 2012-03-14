import scala.actors.Actor
import scala.actors.Actor._  
import java.util.concurrent._ 
import java.lang.Runnable  

object HearbeatSig{}

class Heartbeat(dispatcher: Dispatcher){
  
  private val scheduler = Executors.newSingleThreadScheduledExecutor()  
  
  private val callback = new Runnable{  
    def run = { print_stats; dispatcher ! HearbeatSig }
  }

  def print_stats = {
    val stats = Kollekt.stats + (("active_buckets", BucketFactory.buckets.size))
    println(("" /: stats)((str: String, stat: (String, Int)) => {
       stat._1 + ": " + stat._2.toString() + ", " + str
    }))
  }

  scheduler.scheduleAtFixedRate(callback, 0, 500, TimeUnit.MILLISECONDS)

}
