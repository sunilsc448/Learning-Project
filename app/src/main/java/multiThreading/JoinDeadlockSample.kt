package multiThreading

class JoinDeadlockSample() :Thread(){
    override fun run() {
        super.run()
        for (i in 0 until 10){
            sleep(1000)
            println("Thread1 ${currentThread().name} $i")
        }
    }
}

class AuxThread():Thread(){
    override fun run() {
        super.run()
        for (i in 0 until 10){
            sleep(1000)
            println("Thread2 ${currentThread().name} $i")
        }
    }
}