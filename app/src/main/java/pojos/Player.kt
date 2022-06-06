package pojos

open class Player(val name:String, @Transient val level: Int) {
    var lives = 3
    var score = 0

    fun show(){
        println("""
            name: $name
            level: $level
            lives: $lives
            score: $score
        """)

        println("name: $name lives: $lives level: $level score: $score")
    }
}