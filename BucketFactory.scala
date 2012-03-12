import scala.collection.mutable.HashMap;

object BucketFactory { // EPIC WIN FTW! :)

  val buckets = HashMap[String, Bucket]()

  def get(bucket_id: String) : Bucket = {
    // buckets(bucket_id)
    new Bucket(bucket_id)
  }

  def kill(bucket_id: String){
    // suicide function / callback
  }

}