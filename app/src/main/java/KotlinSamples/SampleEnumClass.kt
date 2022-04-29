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
