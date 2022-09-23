package workManager

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class RandomNumberGeneratorJob3(context: Context, workParams: WorkerParameters) : Worker(context, workParams){
    private val RANGE = 1..10
    private var mRandomNumber = 0
    private var isRandomNumberGeneraationOn = true
    fun startRandomNumberGeneration(){
        var i = 0
        while(i < 5 && !isStopped){
            try {
                Thread.sleep(500)
                if (isRandomNumberGeneraationOn) {
                    mRandomNumber = Random.nextInt(RANGE.first, RANGE.last)
                    Log.i("RandomNumberGenJob3", Thread.currentThread().id.toString() + " Random Number is " + mRandomNumber)
                    i++
                }
            }catch (e:InterruptedException) {
                Log.i("RandomNumberGenJob3", "Thread interupted")
            }
        }
    }

    override fun doWork(): ListenableWorker.Result {
        startRandomNumberGeneration()
//        setProgressAsync(Builder().putInt(PROGRESS, 0).build())
        return ListenableWorker.Result.success()
    }

    override fun onStopped() {
        Log.i("RandomNumberGenJob3", "Worker Job stopped")
        super.onStopped()
    }
}