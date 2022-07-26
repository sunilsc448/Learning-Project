package KotlinSamples

class KotlinLocalFunctions {
    init {
        fizzBuzzWithLocal()
    }

    private fun fizzBuzz() {
        for (i in 1 until 10){
            if(i % 3 == 0 && i % 5 == 0){
                println("fizzbuzz")
            }else if(i % 3 == 0){
                println("buzz")
            }else if(i % 5 == 0){
                println("buzz")
            }else{
                print(i)
            }
        }
    }

    private fun fizzBuzzWithLocal() {
        fun isFizz(input:Int):Boolean = input % 3 == 0
        fun isBuzz(input:Int):Boolean = input % 5 == 0
        for (i in 1 until 10){
            if(isFizz(i) && isBuzz(i)){
                println("fizzbuzz")
            }else if(isFizz(i)){
                println(isBuzz(i))
            }else if(isBuzz(i)){
                println("buzz")
            }else{
                print(i)
            }
        }
    }
}