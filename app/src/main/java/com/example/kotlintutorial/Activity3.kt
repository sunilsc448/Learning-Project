package com.example.kotlintutorial

import KotlinSamples.CouroutineSamples
import KotlinSamples.PracticePitch
import RXJava.RXJavaActivity
import android.Manifest
import movies.view.MoviesActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_3.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Dispatchers.IO
import java.util.*
import kotlin.system.measureTimeMillis
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import android.app.Activity
import android.os.*
import firebase.NotificationActivity
import lifecycle.activities.launchModes.FirstActivity
import multiThreading.MultiThreadingActivity
import pojos.Player
import services.ServiceActivity
import workManager.WorkManagerActivity
import java.io.*
import java.text.SimpleDateFormat


@RequiresApi(Build.VERSION_CODES.O)
class Activity3 : AppCompatActivity() {

    private val RESULT1 = "Result #1"
    private val RESULT2 = "Result #2"
    private val TIME_OUT = 1900L

    private val PROGRESS_MAX = 100
    private val PROGRESS_START = 0
    private val JOB_TIME = 4000L
    private lateinit var job:CompletableJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        firstFunction()
        verifyStoragePermissions(this)
    }

    private fun fileSample() {
        val filename = "suniltxt.txt"

        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var file: File? = null
        try {
            file = File.createTempFile("suniltxt", ".txt", storageDir)
        } catch (e: IOException) {
            e.printStackTrace()
        }


//        val file = File(Environment.getExternalStorageDirectory().toString() + "/" + File.separator + filename)
//        file.createNewFile()

        val fopStream = FileOutputStream(filename)
        val objopStream = ObjectOutputStream(fopStream)
        objopStream.writeObject(Player("sunil", 10))
        objopStream.flush()
        objopStream.close()
        fopStream.close()

        val fipStream = FileInputStream(filename)
        val objipStream = ObjectInputStream(fipStream)
        val player = objipStream.readObject() as Player
        println(player)
    }

    private fun firstFunction() {
//          stopWatch()
//        ArrayPrograms()
//        BinarySearchTree()
//        KotlinCollections()
//        HashMapKeySample("sunil")
//        FilterAndMapSample()
//        PredicateSample()
//        SafeCallSample()
//        LateinitSample()
//        BackingFieldSample()
//        LazyInitilisationSample()
//        HighLevelFunctions()
//        ScopeFunctionsSample()
//          StructuralAndReferralEquitySample()
//        LeetCodeHashMap()
//        LeetCodeHashSet()
//        LeetCodeBSTIterator()
//        StringProblems()
//        MatrixSamples()
//        SortingSample()
//        MapsSample()
//        MiscPrograms()
//        StackSamples()
//        StringProblems()
//        PermutationCombinationSamples()
//          stopWatch()
//          CoroutineScope(Main).launch {
//              SomeClass().printVarName()
//          }

//          SingletonSampleClass.printVarName()

        //Coroutines
//        Handler().postDelayed(Runnable {
//            CouroutineSamples()
//        }, 1000)

//        androidCoroutineSample()
//        androidCoroutineSample_timeout()
//        androidCoroutineProgressSample()
//          androidCoroutineParallelExecutionSample()
//        androidCoroutineSequentialExecutionSample()
//        freezeUISample()
//        runBlockingSample()
//        globalScopeSamples()
//        structuredConcurrencySample()
//        miscellaneousEg()
//        creating_10k_Thread()
//        LinkedListImplementation()

//          InOutSamples()

//         RandomNumberActivityOnClick()
//        launchListActivityOnClick()
//          moviesActivityOnClick()
//        ServiceActivityOnClick()
//        workManagerActivityOnClick()
//        rxJavaActivity()
          launchModesActivity()
//        launchNotificationActivity()
//        launchMultiThreadingActivity()

        PracticePitch()
    }

    private fun launchNotificationActivity() {
        btn.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }
    }

    private fun launchMultiThreadingActivity() {
        btn.setOnClickListener {
            startActivity(Intent(this, MultiThreadingActivity::class.java))
        }
    }

    private fun launchModesActivity() {
        btn.setOnClickListener {
            startActivity(Intent(this, FirstActivity::class.java))
        }
    }

    private fun rxJavaActivity() {
        btn.setOnClickListener {
            startActivity(Intent(this, RXJavaActivity::class.java))
        }
    }

    private fun workManagerActivityOnClick() {
        btn.setOnClickListener {
            startActivity(Intent(this, WorkManagerActivity::class.java))
        }
    }

    private fun ServiceActivityOnClick() {
        btn.setOnClickListener {
            startActivity(Intent(this, ServiceActivity::class.java))
        }
    }

    private fun moviesActivityOnClick() {
        btn.setOnClickListener {
            startActivity(Intent(this, MoviesActivity::class.java))
        }
    }

    private fun launchListActivityOnClick() {
        btn.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }

    private fun RandomNumberActivityOnClick() {
        btn.setOnClickListener {
            startActivity(Intent(this, RandomNumberActivity::class.java))
        }
    }

    private fun miscellaneousEg() {
        CoroutineScope(Dispatchers.Default).launch {
            println("debug: Thread 1 is started ${Thread.currentThread().name}")
            delay(3000)
            println("debug: Completed async operation")
            launch(Main) {
                println("debug: Thread is ${Thread.currentThread().name} completionHandler")
                println("debug: Running after async opearion")
                txtVw.text = "Async operation completed" //ui thread
            }
            println("debug: Thread 1 is ended ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.Default).launch {
            println("debug: Thread 2 is started ${Thread.currentThread().name}")
            launch(Main) {
                txtVw.text = "Async operation completed 2" //ui thread
            }
            println("debug: Thread 2 is ended ${Thread.currentThread().name}")
        }
    }

    fun creating_10k_Thread() {
        val time = measureTimeMillis {
            for(i in 1..10000) {
//                val runnable = Runnable {
//                    Thread.sleep(1)
//                }
//                Thread(runnable).run()
                Thread(Runnable {Thread.sleep(1) }).run()
            }
        }
        println("debug: time taken to create 10k threads $time")


        fun creating10kCoroutines(){
            val time = measureTimeMillis {
                runBlocking {
                    for(i in 1..10000) {
                        launch {
                            delay(1)
                        }
                    }
                }
            }
            println("debug: time taken to create 100k coroutines $time")
        }

        creating10kCoroutines()
    }

    private fun structuredConcurrencySample() {
        btn.setOnClickListener {
//            structuredConcurrency()
//            structuredConcurrency2()
            structuredConcurrencyUsingSupervisor()
        }
    }

    private fun structuredConcurrencyUsingSupervisor() {
        //Handling the errors with Try catch
        val parentJob = CoroutineScope(IO).launch {
           supervisorScope {
               val job1 = launch {
                   val result1 = getResult(1)
                   println("debug: result is $result1")
               }
               job1.invokeOnCompletion { throwable ->
                   //if throwable null means job completed successfully, else exception encountered
                   if(throwable != null){
                       println("debug: Error in completing the job1")
                   }
               }

               val job2 = launch(handler) {
                   val result2 = getResult(2)
                   println("debug: result is $result2")
               }
               job2.invokeOnCompletion { throwable ->
                   //if throwable null means job completed successfully, else exception encountered
                   if(throwable != null){
                       println("debug: Error in completing the job2")
                   }
               }

               val job3 = launch {
                   val result3 = getResult(3)
                   println("debug: result is $result3")
               }
               job3.invokeOnCompletion { throwable ->
                   //if throwable null means job completed successfully, else exception encountered
                   if(throwable != null){
                       println("debug: Error in completing the job3, message: ${throwable.message}")
                   }
               }
           }
        }

        parentJob.invokeOnCompletion { throwable ->
            //if throwable null means job completed successfully, else exception encountered
            if(throwable != null){
                println("debug: Error in completing the parent job, Error: ${throwable.message}")
            }else{
                println("debug: parent job successful")
            }
        }
    }

    //Handling the errors with Try catch
    private fun structuredConcurrency() {
        CoroutineScope(Main).launch {
            launch {
                doJobExceptionHandled(1, this)
            }

            launch {
                doJobExceptionHandled(2, this)
            }

            launch {
                doJobExceptionHandled(3, this)
            }
        }
    }

    //Handling the errors with Invoke Completion
    private fun structuredConcurrency2() {
        //Handling the errors with Try catch
        val parentJob = CoroutineScope(IO).launch(handler) {
            val job1 = launch {
                doJob(1)
            }
            job1.invokeOnCompletion { throwable ->
                //if throwable null means job completed successfully, else exception encountered
                if(throwable != null){
                    println("debug: Error in completing the job1")
                }
            }

            val job2 = launch {
                doJob(2)
            }
            job2.invokeOnCompletion { throwable ->
                //if throwable null means job completed successfully, else exception encountered
                if(throwable != null){
                    println("debug: Error in completing the job2")
                }
            }

            val job3 = launch {
                doJob(3)
            }
            job3.invokeOnCompletion { throwable ->
                //if throwable null means job completed successfully, else exception encountered
                if(throwable != null){
                    println("debug: Error in completing the job3, message: ${throwable.message}")
                }
            }
        }

        parentJob.invokeOnCompletion { throwable ->
            //if throwable null means job completed successfully, else exception encountered
            if(throwable != null){
                println("debug: Error in completing the parent job, Error: ${throwable.message}")
            }else{
                println("debug: parent job successful")
            }
        }
    }

    private var handler:CoroutineExceptionHandler = CoroutineExceptionHandler{_,excdeption ->
        println("debug: Exception thrown in one of the children tasks")
    }

    private fun globalScopeSamples() {
        globalScope()
        btn.setOnClickListener {
           parentJob.cancel("Someone cancelled the job in between!")
        }
    }

    lateinit var parentJob:Job
    private fun globalScope() {
        //The job doesn't get cancelled in  a GlobalScope bcoz it calls invokeOnCompletion immediately without waiting for task to finish.
        //The GlobalScope will be in sync with the thread it is operating in
        //changinng from GlobalScope to normal Coroutine Scope will not cause this issue
        parentJob = CoroutineScope(Main).launch {
            GlobalScope.launch {
                val time1 = measureTimeMillis {
                    doJob(1)
                }
                println("debug: Time taken to finish the job1 is $time1")
            }

            GlobalScope.launch {
                val time2 = measureTimeMillis {
                    doJob(2)
                }
                println("debug: Time taken to finish the job2 is $time2")
            }
        }

        parentJob.invokeOnCompletion{ throwable ->
            if(throwable != null) {
                val msg = throwable.message ?: "debug: Job cancelled for some reason"
                println("debug: $msg")
            }else{
                println("debug: the invoke completion triggered with null throwable")
            }
        }
    }

    private fun runBlockingSample() {
        btn.setOnClickListener {
//            runBlocking1()
            runBlocking2()
        }
    }

    private fun runBlocking1() {
        CoroutineScope(Main).launch {
            println("debug: Job#1 Executing")
            delay(1000)
            println("debug: Job#1 Complete")
            println("debug: Job#2 Executing")
            delay(1000)
            println("debug: Job#2 Complete")
            println("debug: Job#3 Executing")
            delay(1000)
            println("debug: Job#3 Complete")
            println("debug: Job#4 Executing")
            delay(1000)
            println("debug: Job#4 Complete")
        }

        //This coroutine runblocking blocked entire main thread
        CoroutineScope(Main).launch {
            runBlocking {
                println("debug: Job#5 Executing")
                delay(10000)
                println("debug: Job#5 Complete")
            }
        }
    }

    private fun runBlocking2() {
        CoroutineScope(IO).launch {
            println("debug: Job#1 Executing")
            delay(2000)
            println("debug: Job#1 Complete")

            println("debug: Job#4 Executing")
            delay(2000)
            println("debug: Job#4 Complete")
        }

        CoroutineScope(IO).launch {
            println("debug: Job#2 Executing")
            delay(2000)
            println("debug: Job#2 Complete")
        }

        CoroutineScope(IO).launch {
            println("debug: Job#3 Executing")
            delay(2000)
            println("debug: Job#3 Complete")
        }

        //This coroutine runblocking blocked entire main thread
        CoroutineScope(IO).launch {
//            delay(2000)
            runBlocking {
                println("debug: Job#5 Executing")
                delay(5000)
                println("debug: Job#5 Complete")
            }
        }
    }

    private fun freezeUISample() {
        btn.setOnClickListener {
            freezeUI()
        }
    }

    private fun freezeUI() {
        val couroutineSamples = CouroutineSamples()
        CoroutineScope(Main).launch {
            println("debug: Current Thread is >> ${Thread.currentThread().name}")
//            delay(3000) //This will not freeze UI
//            Thread.sleep(3000) //This will because Coroutine is part of Main Thread, not entire main thread
            println("debug: after delay. Current Thread is >> ${Thread.currentThread().name}")

            //blocking using coroutines
            for (i in 1..100) {
                CoroutineScope(Main).launch {
                    couroutineSamples.lengthyJob()
                }
            }
        }
    }

    private fun androidCoroutineSequentialExecutionSample() {
        btn.setOnClickListener {
            androidCoroutineSequentialExecution()
        }
    }

    private fun androidCoroutineSequentialExecution() {
        CoroutineScope(Dispatchers.IO).launch {
            val totaltime = measureTimeMillis {
                val result1 = async {
                    getResult1FromApi()
                }.await()
                setTextOnMainThread("Got the result >> ${result1}")
                val result2:String = async {
                    try{
                        getResult2FromApi(result1)
                    }catch (e:CancellationException){
                        e.message!!
                    }

                }.await()

                setTextOnMainThread("Got the result >> ${result2}")
            }
            println("debug: total time taken by Job is $totaltime")
        }
    }

    private fun androidCoroutineParallelExecutionSample() {
        btn.setOnClickListener {
//            androidCoroutineParallelExecution1()
            androidCoroutineParallelExecution2()
        }
    }

    private fun androidCoroutineParallelExecution1() {
        val starttime = System.currentTimeMillis()
        val parentJob = CoroutineScope(Dispatchers.IO).launch {
            val job1 = launch {
                val time1 = measureTimeMillis {
                    println("debug: Launching Job#1 in thread: ${Thread.currentThread().name}")
                    val result1 = getResult1FromApi()
                    setTextOnMainThread("Got the $result1")
                }
                println("debug: Completed Job in $time1 ms")
            }

            val job2 = launch {
                val time2 = measureTimeMillis {
                    println("debug: Launching Job#2 in thread: ${Thread.currentThread().name}")
                    val result2 = getResult2FromApi("")
                    setTextOnMainThread("Got the $result2")
                }
                println("debug: Completed Job in $time2 ms")
            }
        }

        parentJob.invokeOnCompletion {
            println("debug: total time taken by Parent job $parentJob is ${System.currentTimeMillis() - starttime}")
        }
    }

    private fun androidCoroutineParallelExecution2() {
        CoroutineScope(Dispatchers.IO).launch{
            val parentJobTime = measureTimeMillis {
                val result1:Deferred<String> =  async { getResult1FromApi() }
                val result2:Deferred<String> =  async { getResult2FromApi("") }
                setTextOnMainThread("Got the ${result1.await()}")
                setTextOnMainThread("Got the ${result2.await()}")
            }
            println("debug: total time taken by Parent job is $parentJobTime")
        }
    }

    private fun androidCoroutineProgressSample() {
        btn_pb.setOnClickListener {
            if(!::job.isInitialized){
                initJob()
            }
            pb.StartJobOrCancel(job)
        }
    }

    private fun initJob() {
        btn_pb.setText("Start Job #1")
        txt_pb.setText("")
        job = Job()
        job.invokeOnCompletion {
            it?.message.let{
                var msg = it
                if(msg.isNullOrBlank()){
                    msg = "Unknown cancellation error"
                }
                println("$Job was cancelled. Reason: $msg" )
                showToast(msg)
            }
        }
        pb.max = PROGRESS_MAX
        pb.progress = PROGRESS_START
    }

    fun ProgressBar.StartJobOrCancel(job:Job){
       if(this.progress > 0){
           println("debug: $job is already active. Cancelling..")
           resetJob()
       }else{
           btn_pb.setText("Cancel Job #1")
           CoroutineScope(Dispatchers.IO + job).launch{
                println("debug: Coroutine $this is activated with job $job")

               for (i in PROGRESS_START..PROGRESS_MAX){
                   delay(JOB_TIME/PROGRESS_MAX)
                   this@StartJobOrCancel.progress = i
               }
               updateJobCompleteText("Job is complete")
           }
       }
    }

    private fun updateJobCompleteText(input:String){
        GlobalScope.launch(Dispatchers.Main) {
            txt_pb.setText(input)
        }
    }

    private fun resetJob() {
        if(job.isActive || job.isCompleted){
            job.cancel(CancellationException("Resetting Job"))
        }
        initJob()
    }

    private fun showToast(text:String){
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(this@Activity3, text, Toast.LENGTH_SHORT).show()
        }
    }

    private fun androidCoroutineSample() {
        btn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                setButtonEnable(true)
                fakeAPICall()
            }
        }
    }

    private fun androidCoroutineSample_timeout() {
        btn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val job = withTimeoutOrNull(TIME_OUT){
                    setButtonEnable(false)
                    fakeAPICall()
                }
                if(job == null){
                    setTextOnMainThread("API Timeout")
                }
            }
        }
    }

    private suspend fun setButtonEnable(isEnable:Boolean) {
        withContext(Dispatchers.Main) {
            btn.isEnabled = isEnable
        }
    }

    private suspend fun fakeAPICall(){
        val result1 = getResult1FromApi()
        println("debug: $result1")
        setTextOnMainThread(result1)

        val result2 = getResult2FromApi(result1)
        println("debug: $result2")
        setTextOnMainThread(result2)

        withContext(Dispatchers.Main){
            btn.isEnabled = true
        }
    }

    private suspend fun getResult1FromApi():String{
        logThread("getResult1FromApi")
        delay(1000)
        return RESULT1
    }

    private suspend fun getResult2FromApi(result1: String):String{
        logThread("getResul2tFromApi")
        delay(1000)
        if(result1.equals(RESULT1)){
            return RESULT2
        }
        throw CancellationException("Result1 is not correct")
    }

    private fun logThread(methodName:String){
        println("debug: ${methodName}: ${Thread.currentThread().name}")
    }

    private suspend fun setTextOnMainThread(input:String){
        withContext(Dispatchers.Main){
            setText(input)
        }
//        CoroutineScope(Dispatchers.Main).launch{
//            setText(input)
//        }
    }

    private fun setText(input:String){
        val newText = txtVw1.text.toString()+"\n$input"
        txtVw1.text = newText
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun countDownTimer() {
        var time = 1
        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txtVw.setText(time.toString())
                time++
            }

            override fun onFinish() {
                txtVw.setText("Done = ${time-1}")
            }
        }.start()
    }

    private suspend fun method1(){
        for (i in 1..50){
            delay(100)
            println("debug: scope1 item is $i and thread: ${Thread.currentThread().name}")
        }
    }

    private suspend fun method2(){
        for (i in 1..100){
            delay(200)
            println("debug: scope2 item is $i and thread: ${Thread.currentThread().name}")
        }
    }

    private suspend fun getResult(input: Int):Int{
        println("debug: job$input is started")
        delay(3000)
        if (input == 2){
            throw Exception("Custom Error")
//            throw CancellationException("Custom Error")
        }

        println("debug: job$input is complete")
        return input * 2
    }

    private suspend fun doJob(input: Int){
        println("debug: job$input is started")
        delay(5000)
        if (input == 2){
//            throw Exception("Custom Error")
            throw CancellationException("Custom Error")
        }

        println("debug: job$input is complete")
    }

    @Throws(NoSuchFieldException::class)
    private suspend fun doJobExceptionHandled(input: Int, coroutineScope: CoroutineScope){
        try {
            println("debug: job$input is started")
            delay(5000)
            if (input == 3)
                throw NoSuchFieldException()
            println("debug: job$input is complete")
        }catch (e:NoSuchFieldException){
            println("debug: job$input is cancelled as it encountered exception : $e")
            coroutineScope.cancel()
        }
    }

    fun stopWatch(){
        object : CountDownTimer(1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                TODO("Not yet implemented")
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }

        }.start()

        object : sampleAbs(1000, 1000) {

        } .start()

        val sam = sampleAbs(1000, 1000)
        sam.start()

        Timer().scheduleAtFixedRate(object :TimerTask(){
            override fun run() {

            }

        }, Date(), 1000)
    }

    fun verifyStoragePermissions(activity: Activity?) {
        // Check if we have write permission
        val permission = ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                activity,
                arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        }
    }
}



open class sampleAbs(val millisInFuture:Long, val countdown:Long ): CountDownTimer(millisInFuture, countdown) {
    override fun onTick(millisUntilFinished: Long) {
        TODO("Not yet implemented")
    }

    override fun onFinish() {
        TODO("Not yet implemented")
    }

}


