package KotlinSamples

import android.text.TextUtils.isEmpty
import pojos.KotlinTestFile
import java.lang.StringBuilder

final class ExtensionFunctionSample {
    //Extension variable
    val String.second : Char
        get() {
            if (isEmpty()) throw NoSuchElementException("Char sequence is empty.")
            if (length < 2) throw NoSuchElementException("Char sequence length is less than 2.")
            return this[1]
        }

    val String.MrPersonal : String
    get(){
        val sb = StringBuilder("Mr")
        sb.append(" ")
        sb.append(this)
        return sb.toString()
    }



    init {
        val file = KotlinTestFile()
        val s1 = "How you "
        val s2 = "doing?"
        val s3 = "Hey, "
        println(s3.add(s1, s2))

        //example 2
        val num = 100
        println("is $num modulus of 10? and the answer is ${num.isModulusOfNumber(10)}")

        //example 3
        val str = "Agriculture"
        if (str.isLong()){
            //string is long
        }else if(str.isShort()){
            //string is short
        }

        val name = "Sunil"
        val nameWithTitle = name.MrPersonal

        //Best Extension function example 4
        val connection = Connection(Host("www.google.com"), 80)
        connection.printConnectionInformation()
    }

    fun String.isLong() = this.length > 10
    fun String.isShort() = this.length < 5

    //Extension function 1
    fun String.add(s1:String, s2:String):String{
        return this + s1 + s2
    }

    //Extension function 2
    private fun Int.isModulusOfNumber(modulus:Int):Boolean{
        return this % 10 == 0
    }
}

open class PrintClass{
    open fun printSomething(){}
}

class Host(val url:String){
    fun printHostUrl(){
        println("Host URl is $url")
    }
}

class Connection(val host:Host, val port:Int):PrintClass(){
    private fun printPort(){
        println("Port is $port")
    }

    //Extension function 3
    fun Host.printConnectionInfo(){
        println(toString())
        printHostUrl()
        printPort()
        printSomething()
        println(this@Connection.toString())
    }

    fun printConnectionInformation(){
        host.printConnectionInfo()
    }

    override fun printSomething() {
        println("some logs")
    }
}

class tester{
    init {
        val host = Host(",")
        val conn = Connection(host, 1)
    }
}

data class SampleObject(val name:String, val age:Int)