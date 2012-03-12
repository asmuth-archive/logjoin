kollekt
=======

it listens on a redis pubsub channel and collects pieces of 
information in buckets (sessions).

each bucket is collected in ram and persisted to disk if:
  
  + the maximum bucket size is reached 
  + no appends to the bucket for N seconds (bucket_timeout)
  + the maximum bucket lifetime is reached


after a bucket has been persisted to disk. all append to this
bucket_id are discarded.

a publish on the the redis channel should look like this:

    format: bucket_id|||data

    e.g.    session123|||keyword1
            session123|||keyword2
            session456|||otherkeyword
