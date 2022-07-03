package multiThreading

class YieldSampleThread : Thread() {
    override fun run() {
        super.run()
        for (i in 0 until 10){
            println("Child Thread ${Thread.currentThread().name} $i")
            yield() //This is giving control for other threads to execute and going to ready/runnable state
        }
    }
}