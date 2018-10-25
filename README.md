# crs-hsys
crs-hsys

lsof -i TCP:8888 | grep java | grep LIST

https://stackoverflow.com/questions/12737293/how-do-i-resolve-the-java-net-bindexception-address-already-in-use-jvm-bind

1. netstat -ao | grep 8080
  TCP    pc-icanales:8080       pc-icanales:0          LISTENING
3932

This prints out a process number at the end.
2. taskkill /pid 3932
