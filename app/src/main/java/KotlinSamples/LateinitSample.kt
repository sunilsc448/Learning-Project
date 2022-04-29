package KotlinSamples

class LateinitSample {

    //lateinit can only be used with nullableDataType i.e var
    //lateinit can only be used with non nullable types i.e lateinit var name:String? not allowed
    //lateinit ,ust be initialised before using it otherwise UninitialisedPropertyAccessException will be thrown
    lateinit var name:String
    init {

    }
    fun add(a:Int, b:Int):Int{
        return a + b
    }
}