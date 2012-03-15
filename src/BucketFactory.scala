import scala.collection.mutable.HashMap;

object BucketFactory { // EPIC WIN FTW! :)

  val buckets = HashMap[String, Bucket]()
  var killed_buckets = Set[String]()


  def deliver(bucket_id: String, msg: Any) =
    if (killed_buckets contains bucket_id unary_!)
      get(bucket_id) ! msg
    else
      Kollekt.stats("dropped_messages") += 1
      

  def broadcast(msg: Any) = 
    buckets.foreach((cur: (String, Bucket)) => cur._2 ! msg)
    
  
  def get(bucket_id: String) : Bucket =
    if (buckets isDefinedAt bucket_id)
      buckets(bucket_id)
    else
      create(bucket_id)


  def kill(bucket_id: String) : Unit = {
    if (buckets contains bucket_id unary_!) return ()
    val bucket = buckets(bucket_id)

    if (Kollekt.config("store_deadlist") == 1) {
      killed_buckets += bucket_id
    }

    buckets -= bucket_id
    bucket ! KilledSig
  }

  private def create(bucket_id: String) : Bucket = {
    buckets += ((bucket_id, new Bucket(bucket_id)))
    buckets(bucket_id).start
    buckets(bucket_id)
  }

}