object Kollekt extends Application{

  import scala.actors.Actor
  import scala.actors.Actor._

  val listener = new Listener

  listener ! "fubar|||mydata1"
  listener ! "fubar|||mydata2"
  listener ! "fubar|||mydata3"
  listener ! "fubar|||mydata4"

  listener.start

  println("fu")

  while (true) {
    //println("sleep")
  }

}

