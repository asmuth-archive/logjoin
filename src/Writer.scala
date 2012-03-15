import scala.actors.Actor
import scala.actors.Actor._
import java.io.FileWriter;
import java.io.PrintWriter;

class Writer extends Actor {

  def act(){ 
    Actor.loop{ react{
      case msg: (String, Array[String]) => (persist _) tupled msg
    }}
  }

  def persist(bucket_id: String, data: Array[String]) = {
    Kollekt.stats("buckets_persisted") += 1
    val now = new java.util.Date()

    val csv_head = now.getTime().toString() + ";" + bucket_id;
    val csv_str = (csv_head /: data)(_ + ";" + _)

    append("fixme.dat", csv_str)
  }

  def append(fileName:String, textData:String) =
    using (new FileWriter(fileName, true)){ 
      fileWriter => using (new PrintWriter(fileWriter)) {
        printWriter => printWriter.println(textData)
      }
  }

  def using[A <: {def close(): Unit}, B](param: A)(f: A => B): B =
    try { f(param) } finally { param.close() }

}