import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pojos.Player

fun main(args: Array<String>) {
    println("Hello World")

    val rama = getRamaObject()
    rama.score = 50
    rama.lives = 2

    rama.show()

    println(">>>>>> ${extendString("sunil", 32)}")

    calculateArea(0.5)

    coroutineMethod()
}

fun coroutineMethod() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(1000)
        }

        // Collect the flow
        simple().collect { value -> println(value) }
    }
//    println("coroutine Hi") // main coroutine continues while a previous one is delayed
}

fun simple(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(1000) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}



private suspend fun coroutinneSubMethod() {
    delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
    println("Hello coroutine") // print after delay
}

fun calculateArea(radius: Double): Double {
    require(radius > 0, { "Radius must be greater than 0" })
    return Math.PI * Math.pow(radius, 2.0)
}

fun extendString(name: String, age: Int) : String {
    val another : String.(Int) -> String = { "is $this $it years old" }
    return name.another(age)
}

fun yearString(num: Int) : String {
    val another : (Int) -> String = { "$it years old" }
    return another(num)
}

fun getRamaObject() =  Player("Rama", 1)

