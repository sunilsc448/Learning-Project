package KotlinSamples

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class ScopeFunctionsSample {
    init {
        /* When to use what */

        /*1. let
        a. Context object "it"
        b. Will execute lambda expression and return the last statement
        ---- use it to execute a lambda expression on a nullable object to avoid null pointer exception
        */
        val labelLet:String? = "sunil"
        val letResult = labelLet?.let {
            println("inside labelLet?.let it.reversed() ${it.reversed()}")//returns last statement
            "Because let's it is a local val"
        }
        println("labelLet unchanged after reversed inside let > $labelLet") //original value doesn't change
        println("letResult > $letResult")

        /* 2. with
        a. Context object "this"
        b. Will execute lambda expression and return the last statement
        c. not applicable on nullable objects
         ---- ---- use it to operate on a non-nullable object
        */
        val person = Person(name = "", age = 0)
        person.name = "Sunil"
        person.age = 30
        println(person.name)
        println(person.age)

        //vs ("with" will be very useful when many params are there and avoid redundant calling of a object.)

        val personWith = Person("", 0)
        val withResult = with(personWith){
            name = "Sunil"
            age = 30
            println(this.name)
            println(this.age)
            "name is $name and age is $age"
        }

        println("withResult's bonus return > $withResult")
        println("personWith's name > ${personWith.name} and personWith's age > ${personWith.age}")

        //Compilation Error : with will fail on nullable objects as null safe can't be applied
//        val nullablePersonWith:Person? = Person("", 0)
//        val withResult2 = with(nullablePersonWith){
//            name = "sunil"
//            age = 33
//            age
//        }

        //another example of with on a list
        val numbersListWith = mutableListOf(1,2,3,4,5)
        println("numbersListWith > $numbersListWith")
        val itemWithResult = with(numbersListWith){
            numbersListWith.removeAt(2)
            numbersListWith.elementAt(2)
        }
        println("numbersListWith's updated 2nd position $itemWithResult as 2nd item is removed inside with")
        println("updated numbersListWith count is ${numbersListWith.size} as one item is removed inside with")


        /* 3. run
        a. Context object "this"
        b. Will execute lambda expression and return the last statement
        c. Applicable on null object as well
        d. Run is combination of "let" and "with"
        ---- Run is used when requirement of both let and with is there
        */

        //No  Compilation Error : with will fail on nullable objects but not run
        val nullablePersonRun:Person? = Person("", 0)
        val runResult = nullablePersonRun?.run{
            name = "sunil"
            age = 33
            "name is $name and age is $age"
        }

        println("runResult's bonus return > $runResult")
        println("nullablePersonRun's name > ${nullablePersonRun?.name} and nullablePersonRun's age > ${nullablePersonRun?.age}")

        /* 4. apply
           a. Context object "this"
           b. Will execute lambda expression and return the Context(will not return last statement)
           c. Help to remove redundant call of object., and is very helpful when many params exist
           ---- Best suitable to initialise or configure the object
         */

        val applyResult = Citizen().apply {
            name = "Sunil"
            age = 33
            nationality = "India"
        }
        println("applyResult > $applyResult")

        /*
        5. also
        a. Context object "it"
        b. return the Context
        c. Best suitable for additional configuration of an object or operation on an object
        ----
         */
        val numbersListAlso = mutableListOf(1,2,3,4,5)
        println("numbersListAlso items before > $numbersListAlso")
        numbersListAlso.also {
            it.replaceAll {num -> num + 1}
        }
        println("numbersListAlso items after also's \"num -> num + 1\" > $numbersListAlso")
    }
}



class Person(var name:String, var age:Int){
    fun print(){
        println("The name is  set to $name of age $age years.")
    }
}

class Citizen{
    var name:String? = null
    var age = 0
    var nationality:String? = null
}