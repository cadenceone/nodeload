println "Test Name, Num of Transactions, Num of Iterations, Num of Threads, Total Time (ms), Mean Transaction Time (ms), Transactions per Second, Errors"


[5050:"Benchmark Load Test", 1337: "Node HTTP Client",1338:"Node Http Proxy", 1339:"Node Rest Client"].each{ port, name ->
    performanceOf(name, times=3 , threads=500){

        "http://localhost:$port/withdelay/10".toURL().text

    }
}

def performanceOf ( String test, int times,  int threads,  Closure cl){

    def errors = []
    long total = times * threads

    print "$test,"

    print " $total,$times,$threads,"

    long start = System.nanoTime()

    times.times {

        def performanceThreads = []

        threads.times { performanceThreads << new Thread({
            try{
                cl()
            }
            catch(Exception e){
                errors.add(e)
            }
        } as Runnable) }

        performanceThreads*.start()

        performanceThreads*.join()

    }

    long elapsed = millis(System.nanoTime() - start)

    def mean = elapsed / total

    def tps = total / secs(elapsed)

    println "$elapsed,$mean,$tps,${errors.size()}"


}

def millis(nano){

    nano / 1000000

}

def secs(millis){

    millis / 1000

}