@GrabResolver("https://oss.jfrog.org/artifactory/repo") @Grab("io.ratpack:ratpack-groovy:0.9.9")
import java.util.concurrent.atomic.AtomicLong

@GrabResolver("https://oss.jfrog.org/artifactory/repo") @Grab("io.ratpack:ratpack-groovy:0.9.9")
import static ratpack.groovy.Groovy.ratpack
import ratpack.exec.Fulfiller


ratpack {
    AtomicLong requests = new AtomicLong()
    AtomicLong concurrent = new AtomicLong()

    long startup = new Date().time
    Closure<String> time = { "${new Date().time - startup}".padLeft(8) }
    Closure<String> countIn = { "${requests.incrementAndGet()}".padLeft(8) + ' ' + "${concurrent.incrementAndGet()}".padLeft(8) }
    Closure<String> count = { "${requests.incrementAndGet()}".padLeft(8) + ' ' + "${concurrent.get()}".padLeft(8) }
    Closure<String> countOut = { "${requests.get()}".padLeft(8) + ' ' + "${concurrent.decrementAndGet()}".padLeft(8) }
    bindings {
    }
    handlers {
        get('withdelay/:delay:[0-9]*') {

            def id = UUID.randomUUID().toString()[-6..-1]
            def log = { println "$id: ${time()}: $it" }
            log "${countIn()} start"
            int delay = (pathTokens.get('delay') as int) * 1000
            promise { Fulfiller<String> fulfiller ->
                Thread.start {
                    Thread.sleep(delay)
                    fulfiller.success()
                }
            } then {
                response.status 200
                context.render("After $delay ms")
                log "${countOut()} end"
            }

        }
    }
}
