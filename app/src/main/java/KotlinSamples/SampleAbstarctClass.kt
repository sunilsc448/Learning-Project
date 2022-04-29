package KotlinSamples

abstract class A1 {
    abstract fun m1()
    fun m3(){}
}

abstract class A2:A1(){
    abstract override fun m1()
    abstract fun m2()
}

class C1:A2(){
    override fun m1() {

    }

    override fun m2() {

    }

}