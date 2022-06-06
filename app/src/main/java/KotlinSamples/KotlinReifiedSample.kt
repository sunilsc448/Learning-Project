package KotlinSamples

class KotlinReifiedSample {

    init {
        val s = readValue<String>(ByteArray(10))
        myGeneric<String>(arrayOf("sas","Sas"))
    }

    inline fun <reified T> readValue(data: ByteArray) = myGeneric<String>(arrayOf("sas","Sas")).readlastValueType()
}

class myGeneric<T>(val array: Array<T>){
    fun getObject():T = array.get(0)
    fun  readlastValueType(): T = array.get(array.size - 1)
}

