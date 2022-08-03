package multiThreading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlintutorial.R
import android.os.StrictMode
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


class MultiThreadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_threading)

//        val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder()
//            .detectAll()
//            .detectNetwork()
//            .detectDiskReads()
//            .penaltyDeathOnNetwork()
//            .build()
//        StrictMode.setThreadPolicy(policy)

//        initiateTheChildThread()
//        initRunnableAndAttachToThread()
//          yieldThreadSample()
//          joinThreadSample()
//            joinDeadlockSample()
//          yieldandJoinThreadSample()
        interruptThreadSample()
//        synchronizedSample()
//        synchronizedSample2()
//        threadProblemStatement1()
//        deadLockSample()
//        ReentrantLockSample()
//        ThreadPoolSample()
//        ThreadLocalSample()
//        VolatileSample()
    }

    private fun makeANetworkCall() {
        val client = OkHttpClient()
        val urlBuilder = HttpUrl.parse("https://www.example.com")?.newBuilder()
        val url = urlBuilder?.build().toString()
        val request: Request = Request.Builder().url(url).build()
        val response: Response = client.newCall(request).execute()
        println(response)
    }

    private fun deadLockSample() {
        println(Thread.currentThread().threadGroup.parent.name)
        val deadLockClass1 = DeadLockClass1()
        val deadLockClass2 = DeadLockClass2()
        val deadLockThread1 = DeadLockThread1(deadLockClass1, deadLockClass2)
        deadLockThread1.start()
        val deadLockThread2 = DeadLockThread2(deadLockClass1, deadLockClass2)
        deadLockThread2.start()
    }

    private fun threadProblemStatement1() {
//        There are two threads(here Main and childThread). Child thread is doing some operation operation and storing the result
//        in some variable inside child thread. Main thread is publishing the result
//        val thread:ThreadProblemStatement1 = ThreadProblemStatement1()
//        thread.start()

//         approach 1
//        println("the result is ${thread.result}")
        //here the output is 5050 if child thread gets the chance first
        //output is 0 if main thread get the chance first
        //irregular output if child gets first and main print before child's execution

//        approach2
//        Thread.sleep(1000)
//        println("the result is ${thread.result}")
        //In this approach we never know how much time to be waited and also should not halt main thread

//        approach3
//        thread.join()
//        println("the result is ${thread.result}")
        //The problem is here, what if the thread has job to execute lakhs of lines and unncessarily this thread task
        //to wait for it for lengthy time


//        approach4
        //thread wait and notify fom child child particular task in bertween lakhs lined code is done
//        SampleJavaThreadUseCase().SampleThreadUseCase()
    }

    private fun synchronizedSample2() {
        //If display.displayNumber and display.displayChar are not synchronised methods, then we will have irregular o/p
        //If display.displayNumber and display.displayChar are synchronised methods, then we will have regular o/p
        val display = SynchronisedDisplay()
        val thread1 = SynchronisedDiaplyThread1(display)
        val thread2 = SynchronisedDiaplyThread2(display)
        thread1.start()
        thread2.start()
    }

    private fun synchronizedSample() {
        //Here thread1  and thread2 both want to acquires lock of synchronisedSampleClass.
        // So declaring Synchronised method either of the thread gets a lock execute and other thread has to wait.
        //Here we get regular output
        val synchronisedSampleClass = SynchronisedSampleClass()
        val thread1 = SyncronisedSampleThread(synchronisedSampleClass, "Sachin")
        val thread2 = SyncronisedSampleThread(synchronisedSampleClass, "Dhoni")
        thread1.start()
        thread2.start()

        //Here thread1 acquires lock of synchronisedSampleClass1
        //Here thread2 acquires lock of synchronisedSampleClass2
        //Both threads can run parallely
        //when two separate objects are created, even the method is synchronised, we get irregular output
        //if multiple threads are operating on same object, then only synchronised is useful
//        val synchronisedSampleClass1 = SynchronisedSampleClass()
//        val synchronisedSampleClass2 = SynchronisedSampleClass()
//        val thread1 = SyncronisedSampleThread(synchronisedSampleClass1, "Dhoni")
//        val thread2 = SyncronisedSampleThread(synchronisedSampleClass2, "Sachin")
//        thread1.start()
//        thread2.start()

        //Static synchronised vs synchronised methods > check SynchronisedSampleClass
    }

    private fun interruptThreadSample() {
        val thread = InterruptSampleThread()
        thread.start()
        thread.interrupt() //interrupts only sleeping thread or waiting thread
        for (i in 1 until 10){
            println("Main Thread ${Thread.currentThread().name} $i")
        }
    }

    private fun joinDeadlockSample() {
        val thread1 = JoinDeadlockSample()
        thread1.start()
        thread1.join()

        val thread2 = AuxThread()
        thread2.start()
        thread2.join()
    }

    private fun joinThreadSample() {
//        val childThread = JoinThreadSample()
//        childThread.start()
//        for (i in 1 until 10){
//            childThread.join() //This is waiting for child thread(childThread) to complete it's execution
//            println("Main Thread ${Thread.currentThread().name} $i")
//        }

        val childThread = JoinThreadSample()
        childThread.start()
        childThread.join(5000) //This is waiting for child thread(childThread) for a defined time to complete it's execution
        for (i in 1 until 10){
            println("Main Thread ${Thread.currentThread().name} $i")
        }
    }

    private fun yieldandJoinThreadSample() {
        val childThread = YieldSampleThread()
        childThread.start()
        for (i in 1 until 10){
            childThread.join() //This is waiting for child thread(childThread) to complete it's execution
            println("Main Thread ${Thread.currentThread().name} $i")
        }
    }

    private fun yieldThreadSample() {
        val childThread = YieldSampleThread()
        childThread.start()
        for (i in 1 until 10){
            println("Main Thread ${Thread.currentThread().name} $i")
        }
    }

    private fun initRunnableAndAttachToThread() {
        val runnable = SimpleJobRunnable("Vijay")
        val thread = Thread(runnable)
        thread.start()

        for (i in 1 until 10){
            println("Main Thread ${Thread.currentThread().name} $i")
        }
    }

    private fun initiateTheChildThread() {
        val thread = SimpleThread()
        thread.start() //child thread will be only created if thread.start() is called
        thread.run() //this will be just executed like a normal method of a class

        for (i in 1 until 10){
            println("Main Thread ${Thread.currentThread().name} $i")
        }
    }
}