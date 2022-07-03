package multiThreading

import kotlin.jvm.Throws

class SimpleJobRunnable(private val displayName:String) : Runnable {
    override fun run() {
        println("Job started by ${Thread.currentThread().name} thread for $displayName")
        try{
            Thread.sleep(5000)
        }catch (e:InterruptedException){}
        println("Job completed by ${Thread.currentThread().name} thread for $displayName")
    }
}