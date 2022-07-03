package multiThreading

class JoinThreadSample:Thread() {
    override fun run() {
        super.run()
        for (i in 0 until 10){
            sleep(1000)
            println("Child Thread ${Thread.currentThread().name} $i")
        }
    }
}