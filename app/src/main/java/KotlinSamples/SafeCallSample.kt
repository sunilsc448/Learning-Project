package KotlinSamples

class SafeCallSample {
    init {

        //safe operator ?
        val name:String? = "Bahubali"
        println("the length of the name is ${name?.length}")

        //safe call with let > if null it never execute let block
        name?.let {
            println("inside let the length of the name is ${name.length}")
        }

        //elvis operator > ?:
        val length = if(name != null)
                        name.length
                    else
                        -1
        val lengthelvis = name?.length ?: -1
        println("the length of the name as per elvis is $lengthelvis")
        name?.length?.let {
            println("inside let the elvis length of the name is ${lengthelvis}")
        }

        //Non null assertion operators
        //throws kotlin null pointer exception
        println("the length of the name as per elvis is ${name!!.length}")


    }
}