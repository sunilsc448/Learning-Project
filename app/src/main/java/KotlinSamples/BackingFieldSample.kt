package KotlinSamples

class BackingFieldSample {
    init {
        val human = Human()
        for (i in 1 ..5){
            human.age = i+1
            println("get age is ${human.age}")
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

class Human{
    var age = 20
        get(){
            println("the age is $field")
            return field
        }set(value){
            field = value
        }
}