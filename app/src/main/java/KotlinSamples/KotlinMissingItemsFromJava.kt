package KotlinSamples

class KotlinMissingItemsFromJava {
    init {

        //this is coercion
        //Java code works fine
        /*
        int x = 10;
        long y = x;
        double z = y;
         */

        //Kotlin's lack of type coercions
        val x = 10
        val y = x.toLong()
        val z = y.toDouble()

//        Java is Muti type system, Primitives and Reference Types
//        Kotlin is almost Single Type system

        //static variables
    }
}