package KotlinSamples

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlin.concurrent.thread

class CouroutineSamples {
    init {
        println("ThreadExperiment: before lengthyJob ${Thread.currentThread().name}")
        println("ThreadExperiment: lengthyJob started")
//        threadExp()
//        couroutineExp()
//        couroutineExpWithDelay()
//        threadOutOfMemoryExp()
//        couroutineNoOutOfMemoryExp()
//        couroutineNoOutOfMemoryExpWithDelay()
        runBlockingSample()
        println("ThreadExperiment: lengthyJob finished")
        println("ThreadExperiment: after lengthyJob ${Thread.currentThread().name}")
    }

    private fun threadExp() {
        thread {
            lengthyJob()
        }
    }

    private fun couroutineExp() {
        GlobalScope.launch {
            println("ThreadExperiment: before delay lengthyJob")
            delay(10000)
            lengthyJob()
            println("ThreadExperiment: after delay lengthyJob")
        }
    }

    private fun couroutineExpWithDelay() {
        GlobalScope.launch {
            println("ThreadExperiment: before delay lengthyJob")
            delay(5000)
            lengthyJob()
            println("ThreadExperiment: after delay lengthyJob")
        }
    }

    private fun couroutineNoOutOfMemoryExp() {
        //no out of memory
        for (i in 0 until 10) {
            GlobalScope.launch {
                lengthyJob()
            }
        }
    }

    private fun couroutineNoOutOfMemoryExpWithDelay() {
        //no out of memory
        for (i in 0 until 1) {
            GlobalScope.launch {
                println("ThreadExperiment: before delay lengthyJob")
                delay(1000)
                lengthyJob()
                println("ThreadExperiment: after delay lengthyJob")
            }
        }
    }

    private fun threadOutOfMemoryExp() {
        //out of memory
        for (i in 0 until 10) {
            thread {
                lengthyJob()
            }
        }
    }

    private fun runBlockingSample() = runBlocking {
        println("runblocking: first statement ${Thread.currentThread().name}")
        delay(5000)
        println("runblocking: last statement ${Thread.currentThread().name}")
    }

    fun lengthyJob(){
       for (i in 1..10){
           Thread.sleep(10)
           println("ThreadExperiment: $i sleep between lengthyJob ${Thread.currentThread().name}")
        }
    }
}
