@file:JvmName("KotlinSampleFile")
package pojos

import java.util.*

class KotlinTestFile: SecondaryConstructor("sasas") {
    fun add(a:Int, b:Int, c:Int){
        println(a + b + c)
    }
    companion object{
        @JvmStatic
        fun sub(a:Int, b:Int){

        }
    }
}

fun KotlinTestFile.isDivisibleby5(num:Int):Boolean{
    return num % 5 == 0
}

fun main(args:Array<String>){

}



