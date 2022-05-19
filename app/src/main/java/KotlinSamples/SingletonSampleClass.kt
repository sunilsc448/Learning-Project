package KotlinSamples

import kotlinx.coroutines.delay

object SingletonSampleClass:SomeClass() {
    init {
        println("debug: Singleton class invoked.")
    }
    var variableName = "debug: I am Veer"

    override suspend fun printVarName(){
        println(variableName)
    }
}


open class SomeClass{
    init {
        println("debug: SomeClass invoked.")
    }

    open suspend fun printVarName(){
        println("debug: I am Var")
        SingletonSampleClass.printVarName()
        SingletonSampleClass.variableName = "debug: I am Veer Savarkar"
        SingletonSampleClass.printVarName()
        delay(1000)
        SingletonSampleClass.printVarName()
        delay(1000)
        SingletonSampleClass.printVarName()
        delay(1000)
        SingletonSampleClass.printVarName()
    }
}