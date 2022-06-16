package workManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import kotlin.random.Random

class RandomNumberGeneratorJob(context: Context, workParams:WorkerParameters) : Worker(context, workParams){
    private val RANGE = 1..10
    private var mRandomNumber = 0
    private var isRandomNumberGeneraationOn = true
    fun startRandomNumberGeneration(){
        var i = 0
        while(i < 5 && !isStopped){
            try {
                Thread.sleep(1000)
                if (isRandomNumberGeneraationOn) {
                    mRandomNumber = Random.nextInt(RANGE.first, RANGE.last)
                    Log.i("RandomNumberGenJob", Thread.currentThread().id.toString() + " Random Number is " + mRandomNumber)
                    i++
                }
            }catch (e:InterruptedException) {
                Log.i("RandomNumberGenJob", "Thread interupted")
            }
        }
    }

    override fun doWork(): Result {
        startRandomNumberGeneration()
        return Result.success()
    }

    override fun onStopped() {
        Log.i("RandomNumberGenJob", "Worker Job stopped")
        super.onStopped()
    }
}