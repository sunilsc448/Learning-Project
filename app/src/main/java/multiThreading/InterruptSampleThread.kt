package multiThreading

class InterruptSampleThread : Thread(){
    override fun run() {
        super.run()
        try {
            for (i in 1..10) {
                println("run method ${currentThread().name} $i")
                if(i > 5) {
                    sleep(1000)
//                    currentThread().join()
                }
            }
        }catch (e:InterruptedException){
            println("Thread is interrupted ")
        }
    }
}