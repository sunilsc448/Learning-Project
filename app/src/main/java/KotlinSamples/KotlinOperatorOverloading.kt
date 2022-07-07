package KotlinSamples

class KotlinOperatorOverloading {
    init {

        val a = 2
        println("unary output ${-a}") //output -2
        //or
        println("unary output ${a.unaryMinus()}") //output -2

        val obj1 = UnaryOpearatorsOverloading("Hello")
        -obj1 //instead of obj.unaryMinus(), we can  call it like this
        println("unary operator overloading output ${obj1.string}") //output "Hell"
        -obj1
        println("unary operator overloading output ${obj1.string}") //output "Hel"
        !obj1
        println("unary operator overloading output ${obj1.string}") //output "leH"

        var obj2 = IncDecOpearatorsOverloading("Hello")
        ++obj2
        println("inc operator overloading output ${obj2.string}") //output "Helloa"
        obj2++
        println("inc operator overloading output ${obj2.string}") //output "Helloaa"
        obj2--
        println("dec operator overloading output ${obj2.string}") //output "Helloa"
        --obj2
        println("dec operator overloading output ${obj2.string}") //output "Hello"

        val hel = "Hel"
        var obj3 = BinaryOperatorsOverLoading(hel)
        obj3+"lo"
        println("binary operator add overloading output $obj3") //output "Hello"
        obj3*3
        println("binary operator multiply overloading output $obj3") //output "HelloHelloHelloHello"
    }
}

//    +x	x.unaryPlus()
//    -x	x.unaryMinus()
//    !x	x.not()
class UnaryOpearatorsOverloading(var string:String) {
    operator fun unaryMinus(){
        string = string.substring(0, string.length-1)
    }

    operator fun not(){
        string = string.reversed()
    }
}

//++x	x.inc()
//– – x	x.dec()
class IncDecOpearatorsOverloading(var string: String){
    operator fun inc():IncDecOpearatorsOverloading{
        val obj = IncDecOpearatorsOverloading(this.string)
        obj.string = obj.string + 'a'
        return obj
    }

    operator fun dec():IncDecOpearatorsOverloading{
        val obj = IncDecOpearatorsOverloading(this.string)
        obj.string = string.substring(0, string.length-1)
        return obj
    }
}

//x1 + x2	x1.plus(x2)
//x1 – x2	x1.minus(x2)
//x1 * x2	x1.times(x2)
//x1/ x2	x1.div(x2)
//x1 % x2	x1.rem(x2)
//x1..x2	x1.rangeTo(x2)
class BinaryOperatorsOverLoading(var a:String){
    operator fun plus(b:String){
        a += b
    }

    operator fun times(b:Int){
        val a_ = a
        for (i in 0 until b){
            a += a_
        }
    }

    override fun toString(): String {
        return a
    }
}
