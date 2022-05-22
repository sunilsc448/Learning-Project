package Utility

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class RandomNumberGenerator(){
    private var randomNumber:String? = null
    fun createNumber(range:IntRange):String{
        if(randomNumber == null){
            generateNUmber(range)
        }
        return randomNumber!!
    }

    private fun generateNUmber(range: IntRange) {
        val num = Random.nextInt(range.first, range.last)
        randomNumber = "Random Number: $num"
    }

    fun setrandomNumber(value:String?){
        randomNumber = value
    }
}