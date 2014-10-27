nodeload
========

Prereqs: groovy, npm

```
npm install http-proxy
npm install node-rest-client#1.4.1


groovy Ratpack.groovy

node nodeHttpClient.js
node nodeHttpProxy.js
node nodeRestClient.js

groovy load.groovy
```

```
unknown:huttup phil$ time groovy load.groovy 
Test Name, Num of Transactions, Num of Iterations, Num of Threads, Total Time (ms), Mean Transaction Time (ms), Transactions per Second, Errors
Benchmark Load Test, 1500,3,500,31677,21.118,47.3529690312,0
Node HTTP Client, 1500,3,500,31853,21.2353333333,47.0913257778,0
Node Http Proxy, 1500,3,500,34391,22.9273333333,43.6160623419,0
Node Rest Client, 1500,3,500,31646,21.0973333333,47.3993553688,0

real	2m10.635s
user	0m10.336s
sys	0m3.082s
```
