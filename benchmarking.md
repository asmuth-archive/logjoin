benchmarking kollekt
====================

sadly, the ruby producer is not fast enough to benchmark anything. so we have to use tcpreplay...

### create the pcap dump
 sudo tcpdump -i eth0 udp and port 2323 -w kollekt_testdump.pcap
ruby test_emitter.rb 192.168.2.123 2323

### replay the tcp dump at max speed
sudo tcpreplay --topspeed --intf1=eth0 kollekt_testdump.pcap

### replay the tcp dump at 100 packets per seconds
sudo tcpreplay --pps=100 --intf1=eth0 kollekt_testdump.pcap
