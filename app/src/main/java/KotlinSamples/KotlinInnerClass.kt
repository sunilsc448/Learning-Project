package KotlinSamples

class KotlinInnerClass {
  init {
      KotlinOuterClass().KotlinInnerClass()
  }
}

class KotlinOuterClass{
    val name = "sasa"
    inner class KotlinInnerClass{
        fun m1(){
            println(name)
        }
    }
}