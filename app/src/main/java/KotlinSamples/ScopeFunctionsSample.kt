package KotlinSamples

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class ScopeFunctionsSample {
    init {
        val person = Person("",0)
        val numbersList = mutableListOf(1,2,3,4,5)
        val label:String? = "sunil"

        //with Scope function
        val lakshman:Person = Person("", 0)

        val ageUpdated = with(lakshman){
            name = "Lakshmana" //  this.name = "Lakshmana"
            age = 28 //  this.age = 28
            print() //  this.print()
            age + 5 //returns last statement
            "you changed last statement to String" //returns last statement
        }

        println("Lakshman's age is updated "+lakshman) //not possible with "with"

        val item = with(numbersList){
            numbersList.removeAt(2)
        }
        println("Lakshman last number "+item)
        println("Lakshman number list count "+numbersList.size)

        label?.apply {
            println("Lakshman inside with null safe "+label)
        }

        //apply Scope function
        val shatrugna = person.apply {
            age = 24
            name = "Shatrugna"
            print()
        }
        println("shatrugna name is updated "+shatrugna.name)

        label?.apply {
            println("Lakshman inside apply null safe "+label)
        }

        //also Scope function - it can be used inside also
       numbersList.also {
           it.replaceAll {num -> num + 1}
       }

        label?.also {
            println("Lakshman inside also label null safe "+it)
        }

        println("Lakshman the numbers length after also operation is "+numbersList.size)

        println("Lakshman 0th item is updated "+numbersList.elementAt(3))

        //let Scope function - it can be used inside also
        label?.let {
            it.reversed()
            it.length
            println("Lakshman inside let > "+it.reversed()) //returns last statement
        }

        //run Scope function
        val nullperson:Person? = null
        val result = nullperson?.run {
            age = 60
            name = "Rama"
            print()
            age++ //returns last statement
        }
    }
}



class Person(var name:String, var age:Int){
    fun print(){
        println("The name is  set to $name of age $age years.")
    }
}