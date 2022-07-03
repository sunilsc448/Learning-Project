package multiThreading

import java.util.concurrent.Callable
import kotlin.jvm.Throws

class SimpleJobCallable(private val displayName:String, private val end:Int): Callable<Int>{
    @Throws(Exception::class)
    override fun call(): Int {
        println("Job started by ${Thread.currentThread().name} thread for $displayName")
        var counter = 0
        try{
            for (i in 1..end){
                counter += i
                Thread.sleep(100)
            }
        }catch (e:InterruptedException){}
        println("Job completed by ${Thread.currentThread().name} thread for $displayName")
        return counter
    }
}
