package KotlinSamples

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import java.util.logging.Handler
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

class CouroutineSamples {
    init{
//        println("CoroutineExperiment: before lengthyJob ${Thread.currentThread().name}")
//        println("CoroutineExperiment: lengthyJob started")
//        threadExp()
//        couroutineExp()
//        couroutineExpWithDelay()
//        threadOutOfMemoryExp()
//        couroutineNoOutOfMemoryExp()
//        couroutineNoOutOfMemoryExpWithDelay()
//        runBlockingSample()
//        jobAndJoinLaunch()
//        jobAndCancelLaunch()
//        jobAndJoinGlobalLaunch()
//        jobAndCancelGlobalLaunch()
//        deferredAsyncAndAwait()
//        deferredGlobalAsyncAndAwait()
//          handlingCoroutineExceptions()
//        CoroutineScope(Main).launch {
//            coroutineWithContext()
////            coroutineWithContextVSAsyncAwait()
//        }

//        coroutinewithTimeout()
//        coroutinewithTimeoutOrNull()

//        sequential execution inside coroutine (normalMainThread vs suspendFunction)
//          normalMainThread()
//          suspendFunction()

//        concurrentSample()
//        concurrentLazySample()

//        blockMainThread()

//         childCoroutines()

//          dispatchers()


//        println("CoroutineExperiment: lengthyJob finished")
//        println("CoroutineExperiment: after lengthyJob ${Thread.currentThread().name}")

        example1()
    }

    private fun example1() {
        GlobalScope.launch(Dispatchers.Main) {
//            val userOne = async(Dispatchers.IO) { fetchFirstUser() }
//            val userTwo = async(Dispatchers.IO) { fetchSecondUser() }
            val userOne = withContext(Dispatchers.IO) { fetchFirstUser() }
            val userTwo = withContext(Dispatchers.IO) { fetchSecondUser() }
            showUsers(userOne, userTwo) // back on UI thread
        }
    }

    private fun showUsers(ip1: String, ip2: String) {
        Thread.sleep(5000)
        println("showing users")
    }

    private suspend fun fetchFirstUser():String{
        delay(5000)
        println("op1")
        return "op1"
    }

    private suspend fun fetchSecondUser():String{
        delay(5000)
        println("op2")
        return "op2"
    }


    //Task1 and Task2 are serially executed
    private suspend fun coroutineWithContext() {
        var resultOne = "GFG"
        var resultTwo = "Is Best"
        Log.i("withContext", "Before")
        resultOne = withContext(Dispatchers.Main) { function1() }
        resultTwo = withContext(Dispatchers.Main) { function2() }
        Log.i("withContext", "After")
        val resultText = resultOne +" "+ resultTwo
        Log.i("withContext", resultText)
    }

    private suspend fun coroutineWithContextVSAsyncAwait() {
        //Async await
        //Task1 and Task2 are parallelly executed
        val resultOneDeferred = CoroutineScope(Dispatchers.Main).async { function1() }
        val resultTwoDeferred = CoroutineScope(Dispatchers.Main).async { function2() }
        val combinedResult = resultOneDeferred.await() +" "+ resultTwoDeferred.await()
        Log.i("asyn await", combinedResult)

        //withContext
        //Task1 and Task2 are serially executed
//        var resultOne = "GFG"
//        var resultTwo = "Is Best"
//        Log.i("withContext", "Before")
//        resultOne = withContext(Dispatchers.IO) { function1() }
//        resultTwo = withContext(Dispatchers.IO) { function2() }
//        Log.i("withContext", "After")
//        val resultText = resultOne + resultTwo
//        Log.i("withContext", resultText)
    }

    suspend fun function1(): String {
        delay(5000L)
        val message = "function1"
        Log.i("withContext", message)
        return message
    }

    suspend fun function2(): String {
        delay(1000L)
        val message = "function2"
        Log.i("withContext", message)
        return message
    }

    //lazy load blocking main thread
    private fun blockMainThread() {
        println("CoroutineExperiment: job started")

        //This will freeze the Main Thread as Lazy Loading var inside async coroutine never used
        runBlocking {
            val msg1: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessage11() }
            val msg2: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessage22() }
        }

        //        println("CoroutineExperiment: the final message is: ${msg1.await() + msg2.await()}")

        println("CoroutineExperiment: job finished")
    }

    private fun dispatchers() = runBlocking{
        //Confined
        launch {
            println("CoroutineExperiment: C1 ${Thread.currentThread().name}") //main thread
            delay(100)
            println("CoroutineExperiment: C1 after delay ${Thread.currentThread().name}") //main thread
        }

        launch(Dispatchers.Default) { //This is equivalent to GlobalScope.launch
            println("CoroutineExperiment: C2 ${Thread.currentThread().name}") //T1 thread
            delay(100)
            println("CoroutineExperiment: C2 after delay ${Thread.currentThread().name}") //T1 or some other thread
        }

        launch(Dispatchers.Unconfined) { //This is equivalent to GlobalScope.launch
            println("CoroutineExperiment: C3 ${Thread.currentThread().name}") //main thread
            delay(100)
            println("CoroutineExperiment: C3 after delay ${Thread.currentThread().name}")//some other thread

            //launch(coroutineContext)  will inherit the properties of immediate parent and behaves like Confined
            //it will inherit //some other thread i.e latest
            launch(coroutineContext) { //This is equivalent to GlobalScope.launch
                println("CoroutineExperiment: C4 ${Thread.currentThread().name}") //main thread
                delay(100)
                println("CoroutineExperiment: C4 after delay ${Thread.currentThread().name}")//some other thread
            }
        }

        //launch(coroutineContext)  will inherit the properties of immediate parent
        GlobalScope.async {
            launch(coroutineContext) { //This is equivalent to GlobalScope.launch
                println("CoroutineExperiment: C5 ${Thread.currentThread().name}") //main thread
                delay(100)
                println("CoroutineExperiment: C5 after delay ${Thread.currentThread().name}")//some other thread
            }
        }

    }

    private fun childCoroutines() = runBlocking{
        GlobalScope.launch{
            println("CoroutineExperiment: $this context ${coroutineContext}")

            launch {
                println("CoroutineExperiment: child $this ${coroutineContext}")
            }

            for (i in 1..4){
               launch {
                   println("CoroutineExperiment loop: child loop $this ${coroutineContext}")
               }
            }

            for (i in 1..2){
                async {
                    println("CoroutineExperiment:child loop async $this ${coroutineContext}")
                }
            }
        }
    }

    private fun concurrentLazySample() = runBlocking{
        //the thread will be blocked forever until the Lazy Coroutines are initiated
        val msg1:Deferred<String> = async(start = CoroutineStart.LAZY){getMessage11()}
        val msg2:Deferred<String> = async(start = CoroutineStart.LAZY){getMessage22()}

        //coroutines won't be initialised until used
//        println("CoroutineExperiment: the final message is: ${msg1.await() + msg2.await()}")

        println("CoroutineExperiment: end of method concurrentLazySample")
    }

    private fun concurrentSample() {
        val time = measureTimeMillis {
            suspendFunction()
        }
        println("CoroutineExperiment: the time taken is $time")

        val timeConcurrent = measureTimeMillis {
            suspendFunctionConcurrent()
        }
        println("CoroutineExperiment: the time taken is $timeConcurrent")
    }

    private fun normalMainThread() {
        val msg1 = getMessage1()
        val msg2 = getMessage2()
        println("CoroutineExperiment : the final message is: ${msg1 + msg2}")
    }

    private fun getMessage1():String {
        Thread.sleep(1000)
        println("CoroutineExperiment: inside getMessage1")
        return "Hello "
    }

    private fun getMessage2():String {
        Thread.sleep(1000)
        println("CoroutineExperiment: inside getMessage2")
        return "World"
    }

    private fun suspendFunction() = runBlocking{
        val msg1 = getMessage11()
        val msg2 = getMessage22()
        println("CoroutineExperiment: the final message is: ${msg1 + msg2}")
    }

    private fun suspendFunctionConcurrent() = runBlocking{
        val msg2:Deferred<String> = async{getMessage22()}
        val msg1:Deferred<String> = async{getMessage11()}
        println("CoroutineExperiment: the final message is: ${msg1.await() + msg2.await()}")

        //also can be acheived from launch and global launch coroutine builder
//        launch { getMessage11() }
//        launch { getMessage22() }
    }


    suspend fun getMessage11():String{
        delay(1000)
        println("CoroutineExperiment: inside getMessage11")
        return "Hello "
    }

    suspend fun getMessage22():String{
        delay(1000)
        println("CoroutineExperiment: inside getMessage22")
        return "World"
    }

    private fun coroutinewithTimeout() = runBlocking {
        try {
            withTimeout(3000){
                for (i in 1..100){
                    println("CoroutineExperiment: item is $i and thread:${Thread.currentThread().name}")
                    delay(50)
                }
                "CoroutineExperiment : Timedout"
            }
        }catch (e:TimeoutCancellationException){
            println("CoroutineExperiment: TimeoutCancellationException caught")
        }
    }

    private fun coroutinewithTimeoutOrNull() = runBlocking {
        val startTime = System.currentTimeMillis()
        val result = withTimeoutOrNull(5300){
            for (i in 1..100){
                println("CoroutineExperiment: item is $i and thread:${Thread.currentThread().name}")
                delay(50)
            }
            "CoroutineExperiment : Succesfully Completed by time"
        }
        println("CoroutineExperiment : result is $result")
        val endtime =  System.currentTimeMillis()
        println("System time elapsed is ${(endtime - startTime)}")
    }


    private fun handlingCoroutineExceptions() = runBlocking{
        val job:Job = launch(Dispatchers.Default) {
            try {
                for (i in 1..1000){
                    println("CoroutineExperiment: $i sleep between lengthyJob ${Thread.currentThread().name} and isActive $isActive")
                    delay(50)
//                yield()
                }
            }catch (e:CancellationException){
                //runs a separate coroutine
                withContext(NonCancellable) {
                    delay(50)
                }
                println("CoroutineExperiment: CancellationException caught safely, msg is \"${e.message}\"")
            }finally {
                //runs a separate coroutine
                withContext(NonCancellable){
                    delay(50)
                }
                println("CoroutineExperiment: CancellationException final")
            }

            println("CoroutineExperiment: Job Finished "+Thread.currentThread().name)
        }
        delay(100)
        println("CoroutineExperiment: Job is cancelling "+Thread.currentThread().name)
        job.cancel(CancellationException("Finally cancelled a Coroutine"))
        println("CoroutineExperiment: Job Cancelled "+Thread.currentThread().name)
    }

    private fun jobAndCancelLaunch() = runBlocking{
        val job:Job = launch(Dispatchers.Default) {
            println("CoroutineExperiment: Job Started "+Thread.currentThread().name)
            for (i in 1..1000){
                if(!isActive){
                    println("CoroutineExperiment: $i sleep between lengthyJob ${Thread.currentThread().name} and isActive $isActive")
                    return@launch
                }
                println("CoroutineExperiment: $i sleep between lengthyJob ${Thread.currentThread().name} and isActive $isActive")
//                Thread.sleep(100)
                delay(100)
//                yield()
            }
            println("CoroutineExperiment: Job Finished "+Thread.currentThread().name)
        }
        println("CoroutineExperiment: Job to be Cancelled within 2 secs "+Thread.currentThread().name)
        delay(100)
        println("CoroutineExperiment: Job is cancelling "+Thread.currentThread().name)
//        Thread.currentThread().join()
        job.cancelAndJoin()
        println("CoroutineExperiment: Job Cancelled "+Thread.currentThread().name)
    }

    private fun jobAndCancelGlobalLaunch() = runBlocking{
        val job:Job = GlobalScope.launch {
            println("CoroutineExperiment: Job Started "+Thread.currentThread().name)
            lengthyJob()
            println("CoroutineExperiment: Job Finished "+Thread.currentThread().name)
        }
        println("CoroutineExperiment: Job to be Cancelled within 2 secs "+Thread.currentThread().name)
        delay(2000)
        println("CoroutineExperiment: Job is cancelling "+Thread.currentThread().name)
        job.cancel()
        println("CoroutineExperiment: Job Cancelled "+Thread.currentThread().name)
    }

    private fun jobAndJoinLaunch() = runBlocking{
        val job:Job = launch {
            println("CoroutineExperiment: Job Started "+Thread.currentThread().name)
            lengthyJob()
            println("CoroutineExperiment: Job Finished "+Thread.currentThread().name)
        }
        println("CoroutineExperiment: Job Joining in 2 secs "+Thread.currentThread().name)
//        delay(2000)
        println("CoroutineExperiment: Job is Joining "+Thread.currentThread().name)
        job.join()
        println("CoroutineExperiment: Job Joined "+Thread.currentThread().name)
    }

    private fun jobAndJoinGlobalLaunch() = runBlocking{
        val job:Job = GlobalScope.launch {
            println("CoroutineExperiment: Job Started "+Thread.currentThread().name)
            lengthyJob()
            println("CoroutineExperiment: Job Finished "+Thread.currentThread().name)
        }
        println("CoroutineExperiment: Job to be Joined within 2 secs "+Thread.currentThread().name)
        delay(2000)
        println("CoroutineExperiment: Job is Joining "+Thread.currentThread().name)
        job.join()
        println("CoroutineExperiment: Job Joined "+Thread.currentThread().name)
    }

    private fun deferredAsyncAndAwait() = runBlocking{
        val deferred:Deferred<String> = async{
            println("CoroutineExperiment: Deferred Started "+Thread.currentThread().name)
            lengthyJob()
            println("CoroutineExperiment: Deferred Finished "+Thread.currentThread().name)
            "await task done"
        }
        println("CoroutineExperiment: Deferred to be delayed for 2 secs "+Thread.currentThread().name)
        delay(20000)
        println("CoroutineExperiment: Deferred delay complete "+Thread.currentThread().name)
        val result = deferred.await() //"await task done" will be returned here
        println("CoroutineExperiment: Deferred result "+result)
    }

    private fun deferredGlobalAsyncAndAwait() = runBlocking{
        val deferred:Deferred<String> = GlobalScope.async{
            println("CoroutineExperiment: Deferred Started "+Thread.currentThread().name)
            lengthyJob()
            println("CoroutineExperiment: Deferred Finished "+Thread.currentThread().name)
            "await task done"
        }
        println("CoroutineExperiment: Deferred to be Cancelled within 2 secs "+Thread.currentThread().name)
        delay(2000)
        println("CoroutineExperiment: Deferred is cancelling "+Thread.currentThread().name)
        val result = deferred.await() //"await task done" will be returned here
        println("CoroutineExperiment: Deferred Cancelled "+Thread.currentThread().name)
    }

    private fun threadExp() {
        thread {
            lengthyJob()
        }
    }

    private fun couroutineExp() {
        GlobalScope.launch {
            println("CoroutineExperiment: before delay lengthyJob")
            lengthyJob()
            println("CoroutineExperiment: after delay lengthyJob")
        }
    }

    private fun couroutineExpWithDelay() {
        GlobalScope.launch {
            println("CoroutineExperiment: before delay lengthyJob")
            delay(5000)
            lengthyJob()
            println("CoroutineExperiment: after delay lengthyJob")
        }
    }

    private fun couroutineNoOutOfMemoryExp() {
        //no out of memory
        for (i in 0 until 10000) {
            GlobalScope.launch {
                lengthyJobParam(this)
            }
        }
    }

    private fun couroutineNoOutOfMemoryExpWithDelay() {
        //no out of memory
        for (i in 0 until 1) {
            GlobalScope.launch {
                println("CoroutineExperiment: before delay lengthyJob")
                delay(1000)
                lengthyJob()
                println("CoroutineExperiment: after delay lengthyJob")
            }
        }
    }

    private fun threadOutOfMemoryExp() {
        //out of memory
        for (i in 0 until 10000) {
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
       for (i in 1..50){
           Thread.sleep(100)
           println("CoroutineExperiment: $i sleep between lengthyJob ${Thread.currentThread().name}")
        }
    }
    fun lengthyJobParam(scope:CoroutineScope){
        for (i in 1..50){
            Thread.sleep(2000)
            println("CoroutineExperiment: $i sleep between lengthyJob Thread:${Thread.currentThread().name}  Scope:$scope")
        }
    }

}
