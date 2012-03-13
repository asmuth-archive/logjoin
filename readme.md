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

every message sent to the udp socket should be utf-8 encoded.

the message format is:
    
    bucket_id;data

example:

    session123;keyword1
    session123;keyword2
    session456;otherkeyword



Output Format
-------------

all "closed" buckets are written per-timespan files. the default file length is 30 minutes. the created files will look like this:

    ./dump/1331597057.csv
    ./dump/1331598459.csv
    ./dump/1331510059.csv

the csv format is 

    time_of_dump;bucket_id;data1;data2;data3;data4...

example:

    $ cat ./dump/1331597057.csv
    1331597057.0012;session123;keyword1;keyword2
    1331597057.0843;session751;otherkeyword1;mysearch;moredata



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
