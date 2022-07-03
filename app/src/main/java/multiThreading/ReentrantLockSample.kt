package multiThreading

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

class ReentrantLockSample {
    init {
//      basics()
      reentrantReplacingSyncd()
    }

    private fun reentrantReplacingSyncd() {
        val displayClass = DisplayClass()
        val thread1 = ReentrantSampleThread(displayClass, "Ganguly")
        val thread2 = ReentrantSampleThread(displayClass, "Dravid")
        thread1.start()
        thread2.start()
    }

    private fun basics() {
        val l = ReentrantLock()
        l.lock()
        l.lock()
        println("hold count ${l.holdCount}") //2
        println("is fair ?  ${l.isFair}") //false, we haven't set fairpolicy = true and by default is fasle
        println("is locked ${l.isLocked}") //true
        println("is held by current thread ${l.isHeldByCurrentThread}") //true
        println("is held by current thread ${l.queueLength}") //0 , no other threads are waiting to get the lock
        l.unlock()
        println("hold count ${l.holdCount}") //1
        println("is locked ${l.isLocked}") //true
        l.unlock()
        println("hold count ${l.holdCount}") //0
        println("is locked ${l.isLocked}") //false
    }
}

class ReentrantSampleThread(val displayClass: DisplayClass, val displayName: String): Thread() {
    override fun run() {
        super.run()
        displayClass.displayName(displayName)
    }
}

class DisplayClass{
    private val l = ReentrantLock()

    fun displayName(name:String){

        //Both Threads one by one acquire lock and hence regular output
//        l.lock()
//        for (i in 1..10) {
//            println("Good Morning: $name")
//            try {
//                Thread.sleep(500)
//            } catch (e: InterruptedException) {
//            }
//        }
//        l.unlock()

        //Both Threads one by one try to acquire lock. One thread will get other will not
        // and hence other can perform some other operation without waiting
//        if(l.tryLock()) { //this will make sure, thread will not go into waiting state
//            println("$name ${Thread.currentThread().name} got the lock")
//            for (i in 1..10) {
//                println("Good Morning: $name")
//                try {
//                    Thread.sleep(500)
//                } catch (e: InterruptedException) {
//                }
//            }
//            l.unlock()
//        }else{
//            println("$name thread ${Thread.currentThread().name} didn't get the lock")
//            println("$name thread ${Thread.currentThread().name} can perform some alternate operations")
//        }

        //Both Threads one by one try to acquire lock. One thread will get other will wait for mentioned time to get the lock
        // if it get's the lock within time it will perform operation otherwise can perform some other operation without waiting
        //Here second thread gets lock once first thread release the lock after 5 seconds operation
//        if(l.tryLock(6, TimeUnit.SECONDS)) {
//            println("$name ${Thread.currentThread().name} got the lock")
//            for (i in 1..10) {
//                println("Good Morning: $name")
//                try {
//                    Thread.sleep(500)
//                } catch (e: InterruptedException) {
//                }
//            }
//            l.unlock()
//        }else{
//            println("$name thread ${Thread.currentThread().name} didn't get the lock")
//            println("$name thread ${Thread.currentThread().name} can perform some alternate operations")
//        }


        //Both Threads one by one try to acquire lock. One thread will get other will wait for mentioned time to get the lock
        // if it get's the lock within time it will perform operation otherwise can perform some other operation without waiting
        //Here second thread waits for 4 seconds and go to else as first thread holds the lock for 5 seconds
//        if(l.tryLock(4, TimeUnit.SECONDS)) {
//            println("$name ${Thread.currentThread().name} got the lock")
//            for (i in 1..10) {
//                println("Good Morning: $name")
//                try {
//                    Thread.sleep(500)
//                } catch (e: InterruptedException) {
//                }
//            }
//            l.unlock()
//        }else{
//            println("$name thread ${Thread.currentThread().name} didn't get the lock")
//            println("$name thread ${Thread.currentThread().name} can perform some alternate operations")
//        }

        //Better Approach
        //Both Threads one by one try to acquire lock
        //Here first thread gets the lock, finish the operation and release the lock after 5 seconds
        //second thread will keep on trying for lock every second
        var count = 1
        do {
            if(l.tryLock(1, TimeUnit.SECONDS)) {
                println("$name ${Thread.currentThread().name} got the lock")
                for (i in 1..10) {
                    println("Good Morning: $name")
                    try {
                        Thread.sleep(500)
                    } catch (e: InterruptedException) { }
                }
                l.unlock()
                break
            }else{
                println("$name thread ${Thread.currentThread().name} didn't get the lock for $count time")
            }
            count++
        }while (true)
    }
}