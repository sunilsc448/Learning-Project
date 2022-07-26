package KotlinSamples

import kotlinx.coroutines.*

class InOutSamples {
    init {
        CaseIn<Int>().add(10)
        val output = CaseOut<Int>(10).produce()

//        genericsExample<Int>(10)
        GenericsExample<Double>(10.0)
        GenericsExample<String>("10.0")
    }
    // two kotlin suspend functions
// Suppose we have two tasks like below

    private suspend fun doTaskOne(): String {
        delay(2000)
        println("time : task one done")
        return "One"
    }

    private suspend fun doTaskTwo(): String {
        delay(2000)
        println("time : task two done")
        return "Two"
    }
}

class GenericsExample<out T>(val input:Any?) {
    init {
        println("I am getting called with the value "+input)
    }
    //fun getFormattedString():T = "Formatted String is $input" as T
}

class CaseIn<in T>() {
    private val contents = mutableListOf<T>()
    //    fun produce(): T = contents.last()         // Producer: Error
    fun add(item: T) = contents.add(item)  // Consumer: OK
}

class CaseOut<out T>(val input: T) {
    private val contents = mutableListOf<T>()
    fun produce(): T{ // Producer: Ok
        contents.add(input)
        return contents.last()
    }
//    fun consume(item: T) = contents.add(item)  // Consumer: Error
}