package KotlinSamples

class Dog(var dogColor: String): Animal(dogColor){
    var dogBreed:String = "Pug"
    constructor(color:String, dogBreed:String):this(color){
        dogColor = color
        this.dogBreed = dogBreed
        println("the dog breed is $dogBreed and its color is $dogColor")

        val student1: Student = Student(name = "Sunil", id = 1)
        val student2: Student = Student(name = "Pooja", id = 2)
        val student3 = student1.copy(id = 3)
    }

    constructor(color:String, dogBreed:String, i:Int):this(color, dogBreed){

    }

    override fun test() {
        super.test()
    }
}

open class Animal{
    var color:String = "Black"
    constructor(color:String){
        this.color = color
    }

    open fun test(){

    }
}

data class Student(var name:String, var id:Int){}
