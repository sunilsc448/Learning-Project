package KotlinSamples

import okhttp3.Response
import java.lang.Exception

class SampleSealedClass {
    init {
        val circle = Shape.Circle(3.5f)
        val rectangle = Shape.Rectangle(5,6)
        val square = Shape.Square(5)
        val noShape = Shape.NoShape
        val arrayOfShapes = arrayListOf<Shape>(circle, rectangle, square, noShape)
        val arrayOfEnumShapes = arrayListOf<ShapeEnum>(ShapeEnum.CIRCLE, ShapeEnum.RECTANGLE, ShapeEnum.SQUARE, ShapeEnum.NO_SHAPE)
        arrayOfShapes.forEach {
            println("${it.javaClass}  > ${checkShapeSealed(it)}")
        }
        arrayOfEnumShapes.forEach {
            checkShapeEnum(it)
        }

        val shape1 = Shape.Rectangle(length = 2, breadth = 3)

        //actual use case example
        val responseHolder:ResponseStatus = RepoSample().getResponse("fail")
        when(responseHolder){
            is ResponseStatus.ErrorResponseHolder -> {
                println("Error > ${responseHolder.error} & Reponse code > ${responseHolder.responseCode}")
            }
            is ResponseStatus.SuccessResponseHolder -> {
                println("Reponse > ${responseHolder.response} & Reponse code > ${responseHolder.responseCode}")
            }
        }
    }
}

fun checkShapeEnum(shape:ShapeEnum):String{
    return when(shape){
        ShapeEnum.RECTANGLE -> "It is a rectangle"
        ShapeEnum.CIRCLE -> "It is a circle"
        ShapeEnum.SQUARE -> "It is a Square"
        ShapeEnum.NO_SHAPE -> "It is a NoShape"
    }
}

enum class ShapeEnum(val input:Int){
    RECTANGLE(1) {
        override fun shapeUse() {
            println(input)
        }
    },
    CIRCLE(2) {
        override fun shapeUse() {
            println(input)
        }
    },
    SQUARE(3) {
        override fun shapeUse() {
            println(input)
        }
    },
    NO_SHAPE(4) {
        override fun shapeUse() {
            println(input)
        }
    };
    abstract fun shapeUse()
}

fun checkShapeSealed(shape: Shape):String {
   return when(shape){
       is Shape.Rectangle -> {
           shape.area().toString()
       }
       is Shape.Circle ->{
           shape.perimeter().toString()
       }
       is Shape.Square ->{
           shape.area().toString()
       }
       is Shape.NoShape -> {
           shape.doesNothing()
       }
       is ShapeChild -> {
           shape.print()
       }
   }
}

sealed class Shape(){
    class Rectangle(private val length:Int,private val breadth:Int): Shape(){
        fun area():Int{
            val area = length * breadth
            return area
        }
    }
    class Circle(private val radius:Float): Shape(){
        fun perimeter():Double{
            return 2 * Math.PI * radius
        }
    }
    class Square(private val side:Int): Shape(){
        fun area():Int{
            return side*side
        }
    }
    object NoShape:Shape(){
        fun doesNothing():String{
            return "nothing for me"
        }
    }
}

class ShapeChild:Shape(){
    fun print():String{
        return "I am a child of shape"
    }
}


sealed class ResponseStatus{
    class SuccessResponseHolder(val status:String,val response:String, val responseCode:Int):ResponseStatus()
    class ErrorResponseHolder(val status:String, val error:Exception, val responseCode: Int):ResponseStatus()
}

class RepoSample{
    fun getResponse(status: String):ResponseStatus{
        if(status.equals("success")){
            return ResponseStatus.SuccessResponseHolder(status,"Response data", 200)
        }else{
            return ResponseStatus.ErrorResponseHolder(status, Exception("Gateway error"), 502)
        }
    }
}

sealed class DeliveryStatus

open class Dispatched(val trackingId: String) : DeliveryStatus()

class LocallyDispatched : Dispatched("1234") { }
