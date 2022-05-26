package viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class Activity4ViewModel: ViewModel() {
    private val TAG = this.javaClass.name
    var randomNumber:MutableLiveData<String>? = null
        get() = field

    init {
        fakeAPICall()
    }

    private fun fakeAPICall() {
        Log.i(TAG, "API called")
        CoroutineScope(IO).launch {
            val job = async {
                makeApiCall()
            }
            val response = job.await()
//            CoroutineScope(Main).launch {
                randomNumber?.postValue("Random Number: $response")
//            }
//            EventBus.getDefault().post(MessageEvent(response))
        }
    }

    private suspend fun makeApiCall():String {
        Log.i(TAG, "waiting for API response")
        delay(5000)
        Log.i(TAG, "Got the API response")
        return "11"
    }

    fun createNumber(range:IntRange):String{
        if(randomNumber == null){
            randomNumber = MutableLiveData()
            generateNUmber(range)
        }
        return randomNumber?.value!!
    }

    private fun generateNUmber(range: IntRange) {
        val num = Random.nextInt(range.first, range.last)
        randomNumber?.value = "Random Number: $num"

        Thread.sleep(10000)
    }

    //this event is ondestroy + isChangingConfigurations = false
    override fun onCleared() {
        Log.i(this.javaClass.name, "ACTIVITY4 COMPLETELY DESTROYED")
        super.onCleared()
    }

    fun setrandomNumber(value:String?){
        randomNumber?.value = value
    }
}