import scala.collection.mutable.HashMap;

object BucketFactory { // EPIC WIN FTW! :)

  val buckets = HashMap[String, Bucket]()

  def get(bucket_id: String) : Bucket = {
    // buckets(bucket_id)
    val bucket = new Bucket(bucket_id)
    bucket.start
    return bucket
  }

  def kill(bucket_id: String){
    // suicide function / callback
  }

}