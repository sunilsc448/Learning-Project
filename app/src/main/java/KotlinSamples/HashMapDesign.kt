package KotlinSamples

class HashMapKeySample(var key: String) {
    init {
        println("Hashkey is ${key[0].toInt()}")
    }
    override fun hashCode(): Int {
        return key[0].toInt()
    }

    override fun equals(obj: Any?): Boolean {
        return key == obj as String?
    }
}