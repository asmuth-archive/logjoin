import scala.collection.mutable.HashMap;

object BucketFactory { // EPIC WIN FTW! :)

  val buckets = HashMap[String, Bucket]()

  def get(bucket_id: String) : Bucket = {
    if (buckets.isDefinedAt(bucket_id))
      buckets(bucket_id)
    else
      create(bucket_id)
  }

  def kill(bucket_id: String) = {
    // suicide function / callback
  }

  private def create(bucket_id: String) : Bucket = {
    buckets += ((bucket_id, new Bucket(bucket_id)))
    buckets(bucket_id).start
    buckets(bucket_id)
  }

}