package KotlinSamples

class LazyInitilisationSample {
    private val radius:Float by lazy { 3.0f }
    private val pi:Float by lazy { 3.142f }
    init {
        val circumference = getArea(radius, pi)
        println("circumference os the circle is >> $circumference")
    }

    private fun getArea(pi:Float, r:Float):Float{
        return 2 * pi * r
    }
}