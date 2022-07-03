package multiThreading

import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

class ThreadPoolSample {
    init {
//        basics()
//        multipleJobs()
        multipleCallableJobs()
    }

    private fun multipleCallableJobs() {
        val jobs = arrayOf(SimpleJobCallable("Anil", 5), SimpleJobCallable("Sunil", 10),
            SimpleJobCallable("Shruthi", 15), SimpleJobCallable("Pooja", 20)
        )
        val totalTime = measureTimeMillis {
            val executorService = Executors.newFixedThreadPool(4)
            println("${jobs.size} Jobs assigned to executor service")
            for (i in jobs.indices){
                val futureResult = executorService.submit(jobs[i])
                println("result of job ${i+1} is ${futureResult.get()}")
                //Here with future result the job's are executed in sequential manner,
                //if we stop asking for result i.e futureResult.get() then the jobs are executed parallel as per thread pool count
            }
            executorService.shutdown()
        }
        println("total time took to complete task is $totalTime")
    }

    private fun multipleJobs() {
        val jobs = arrayOf(SimpleJobRunnable("Anil"), SimpleJobRunnable("Sunil"),
            SimpleJobRunnable("Roopa"), SimpleJobRunnable("Shruthi"), SimpleJobRunnable("Pooja"),
            SimpleJobRunnable("Neksha"), SimpleJobRunnable("Kanvi"), SimpleJobRunnable("Simba"))

        val executorService = Executors.newFixedThreadPool(4)
        println("${jobs.size} Jobs assigned to executor service")
        jobs.forEach {
            executorService.submit(it)
        }
        executorService.shutdown()
    }

    private fun basics() {
        //thread name here is Thread-3
//        val runnable = object:Runnable{
//            override fun run() {
//                println("thread name > ${Thread.currentThread().name}")
//            }
//        }
//        val thread = Thread(runnable)
//        thread.start()

        //thread name here is pool-8-thread-1
        val executorService = Executors.newFixedThreadPool(5)
        executorService.submit(object:Runnable{
            override fun run() {
                println("thread name > ${Thread.currentThread().name}")
            }
        })
    }
}