package KotlinSamples

enum class Metal(name:String){
    SILVER("silver") {
        override fun percent() = 2f
    },
    GOLD("gold") {
        override fun percent() = 3f
    },
    PLATINUM("platinum"){
        override fun percent() = 5f
    };
    abstract fun percent():Float
}

fun main(){
    var gold = Metal.GOLD
    var silver = Metal.SILVER
    var platinum = Metal.PLATINUM
    checkMetal(gold)
}

fun checkMetal(metal: Metal) {
    when(metal){
        Metal.SILVER -> TODO()
        Metal.GOLD -> TODO()
        Metal.PLATINUM -> TODO()
    }
}



fun checkShapeEnum(shape:ShapeEnum):String{
    return when(shape){
        ShapeEnum.RECTANGLE -> "It is a rectangle"
        ShapeEnum.CIRCLE -> "It is a circle"
        ShapeEnum.SQUARE -> "It is a Square"
        ShapeEnum.NO_SHAPE -> "It is a NoShape"
    }
}

enum class ShapeEnum(val input:Int){
    RECTANGLE(1) {
        override fun shapeUse() {
            println(input)
        }
    },
    CIRCLE(2) {
        override fun shapeUse() {
            println(input)
        }
    },
    SQUARE(3) {
        override fun shapeUse() {
            println(input)
        }
    },
    NO_SHAPE(4) {
        override fun shapeUse() {
            println(input)
        }
    };
    abstract fun shapeUse()
}