package KotlinSamples

class KotlinInlineFunctions {
    init {
        main()
    }

    fun main() {
        printHello("Sing")
    }

    inline fun printHello(s: String) {
        print("Hello $s")
    }

    inline fun sampleInline(crossinline body: () -> Unit) {
        body()
    }
}