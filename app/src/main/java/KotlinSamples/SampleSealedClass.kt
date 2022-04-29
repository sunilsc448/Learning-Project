package KotlinSamples

sealed class Shape{
    class Rectangle(length:Int, breadth:Int): Shape()
    class Circle(radius:Float): Shape()
    class Square(side:Int): Shape()
    object NoShape:Shape()
}

fun main(){
    val circle = Shape.Circle(3.5f)
    val rectangle = Shape.Rectangle(5,6)
    val square = Shape.Square(5)
    val noShape = Shape.NoShape
    checkShape(circle)
}

fun checkShape(shape: Shape) {
   when(shape){

   }
}
