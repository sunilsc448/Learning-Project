package KotlinSamples

class HighLevelFunctions {
    var result = 0
    init {
        val lambda = {num:Int ->
            println("The number from lambda expression is $num")
            num.toFloat()
        }
        println("The float number from lambda expression is ${lambda(11)}")
        println("The float number from lambda expression is ${lambda.invoke(11)}")

        val lambda2: (Int, Int) -> Int =  {
            num1:Int, num2:Int ->
            result = 1 //can be modified inside lambda
            num1 + num2
        }
        result = highLevelFunction(2, 3, lambda2)

        val lambda3:(Int) -> Float = {it.toFloat()}
        val capture:Float = highLevelFunction2(2, lambda3)

        sampleInline { println("inline function exceuted") }

        //class literal syntax :: and it is also used in reflections
//        Class Reference val myClass = MyClass::class
//        Function Reference this::isEmpty
//        Property Reference ::someVal.isInitialized
//        Constructor Reference ::MyClass
        SqrtClass(::sq_rt)
    }

    fun sq_rt(value:Int) = println("Square root of number $value is ${value * 2}")

    fun highLevelFunction(a:Int, b:Int,lambda: (Int, Int) -> Int):Int{
        return lambda.invoke(a,b)
    }

    fun highLevelFunction2(a:Int, lambda: (Int) -> Float):Float{
        return lambda.invoke(a)
    }

    inline fun sampleInline(crossinline body: () -> Unit) {
        body()
//        val f = object: Runnable {
//            override fun run(){
//                body()
//            }
//        }.run()
    }
}

class SqrtClass(var printSqrt:(Int) -> Unit){
    fun sqrt(`val`:Int){
        printSqrt(`val`)
    }
}

