package KotlinSamples

import kotlin.reflect.full.createInstance
import kotlin.reflect.full.primaryConstructor

class KotlinReflections {
    init {
        val reflectionClass1Type = ReflectionClass1::class
        println("reflectionClass1Type > isAbstract > ${reflectionClass1Type.isAbstract}")
        println("reflectionClass1Type > isCompanion > ${reflectionClass1Type.isCompanion}")
        println("reflectionClass1Type > isData > ${reflectionClass1Type.isData}")
        println("reflectionClass1Type > isFinal > ${reflectionClass1Type.isFinal}")
        println("reflectionClass1Type > isSealed > ${reflectionClass1Type.isSealed}")
        println("reflectionClass1Type > visibility > ${reflectionClass1Type.visibility}")
        val reflectionClass1 = reflectionClass1Type.createInstance()
        reflectionClass1.helloMethod()

        val reflectionClass1_2 = reflectionClass1Type.primaryConstructor
        val object_reflectionClass1_2 = reflectionClass1_2?.call()
        object_reflectionClass1_2?.helloMethod()

        val reflectionClass2Type = ReflectionClass2::class
//        val reflectionClass2 = reflectionClass2Type.createInstance()
        //Error : Class should have a single no-arg constructor: class KotlinSamples.ReflectionClass1
//        reflectionClass2.helloMethod()
    }
}

class ReflectionClass1{
    fun helloMethod() = println("Hello ji")
}

class ReflectionClass2(val name:String){
    constructor(name: String, age:Int):this(name)
    fun helloMethod() = println("Hello ji")
}