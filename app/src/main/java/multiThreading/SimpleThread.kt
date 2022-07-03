package multiThreading

class SimpleThread:Thread() {
    override fun start() {
        super.start()

        //Anything in start() will be executed by  a main thread
        for (i in 1 until 10){
            println("start() child thread ${currentThread().name} $i")
        }
    }

    override fun run() {
        super.run()
        //Anything in run() will be executed by  a child thread created
        //child thread will be only created if thread.start() is called
        for (i in 1 until 10){
            println("run() child thread ${currentThread().name} $i")
        }
    }
}