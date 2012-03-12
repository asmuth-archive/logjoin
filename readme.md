kollekt
=======

...listens on a redis pubsub channel and collects pieces of information in buckets (grouped by session/bucket-id). 
the buckets are collected in ram and eventually persisted to disk. After a bucket has been persisted all 
appends to this bucket_id are discarded. A bucket is persisted as soon as:
  
  
  + no appends to the bucket for N seconds (bucket_timeout)
  + or: the maximum bucket size is reached 
  + or: the maximum bucket lifetime is reached


Message Format
--------------

a publish on the the redis channel should look like this:

    format: bucket_id|||data

    e.g.    session123|||keyword1
            session123|||keyword2
            session456|||otherkeyword


License
-------

Copyright (c) 2011 Paul Asmuth

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to use, copy and modify copies of the Software, subject 
to the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
