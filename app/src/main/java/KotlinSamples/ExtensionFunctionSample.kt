package KotlinSamples

import pojos.KotlinTestFile

class ExtensionFunctionSample {
    init {
        KotlinTestFile.sub(b=2,a=3)
        val file = KotlinTestFile()
        val s1 = "How you "
        val s2 = "doing?"
        val s3 = "Hey, "
        println(s3.add(s1, s2))
        val num = 100
        println("is $num modulus of 10? and the answer is ${num.isModulusOfTen()}")

        val obj = SampleObject("Sunil", 31)
    }
    //Extension functions
    fun String.add(s1:String, s2:String):String{
        return this + s1 + s2
    }

    fun Int.isModulusOfTen():Boolean{
        return this % 10 == 0
    }
}

data class SampleObject(val name:String, val age:Int)