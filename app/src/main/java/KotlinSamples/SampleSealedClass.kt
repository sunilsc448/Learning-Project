package KotlinSamples

import okhttp3.Response
import java.lang.Exception

class SampleSealedClass {
    /*
    Sealed class rules
    Sealed classes are abstract and can have abstract members.
    Sealed classes cannot be instantiated directly.
    Sealed classes cannot have public constructors (The constructors are private by default).
    Sealed classes can have subclasses, but they must either be in the same file or nested inside of the sealed class declaration.
    Sealed classes subclass can have subclasses outside of the sealed class file.
    */

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
       is Shape.Circle -> TODO()
       Shape.NoShape -> TODO()
       is Shape.Rectangle -> TODO()
       is ShapeChild -> TODO()
       is Shape.Square -> TODO()
       else -> TODO()
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



