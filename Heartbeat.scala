import scala.actors.Actor
import scala.actors.Actor._  
import java.util.concurrent._ 
import java.lang.Runnable  

object HearbeatSig{}

class Heartbeat(dispatcher: Dispatcher){
  
  private val scheduler = Executors.newSingleThreadScheduledExecutor()  
  
  private val callback = new Runnable{  
    def run = dispatcher ! HearbeatSig
  }

  scheduler.scheduleAtFixedRate(callback, 0, 20000, TimeUnit.MILLISECONDS)  

}
