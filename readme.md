kollekt
=======

kollekt listens on a udp socket and collects pieces of information in buckets (grouped by session/bucket-id). 
the buckets are collected in ram and eventually persisted to disk. After a bucket has been persisted all 
appends to this bucket_id are discarded. A bucket is persisted as soon as:
  
  + no appends to the bucket for N seconds (bucket_timeout)
  + or: the maximum bucket size is reached 
  + or: the maximum bucket lifetime is reached


Message Format
--------------

every message sent to the udp socket should be utf-8 encoded and look like this:

    format: bucket_id|||data

    e.g.    session123|||keyword1
            session123|||keyword2
            session456|||otherkeyword


Output Format
-------------

all "closed" buckets are dumped to simple files with the name `%timestamp%_%bucket_id%.dat` e.g.
    
    1331597057_bucketfoo.dat
    1331597057_bucketsnafu.dat
    1331597058_bucketfnord.dat
    1331597059_bucketblubb.dat
    1331597059_bucketbar.dat
    1331597059_bucketshmoo.dat


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
