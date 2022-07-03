package memeoryLeaks

import android.content.Context

class SampleLeakSingelton private constructor(context: Context){
    companion object{
        @Volatile private var INSTANCE: SampleLeakSingelton? = null
        @Synchronized fun getInstance(context: Context):SampleLeakSingelton = INSTANCE ?: SampleLeakSingelton(context).also { INSTANCE = it }
    }
}