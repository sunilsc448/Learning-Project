package KotlinSamples

class KotlinInfixSample {
   init {
       val warrior1 = Warrior(100, 20)
       val warrior2 = Warrior(100, 30)
       warrior1.attack(warrior2)
       println("warrior2's updated XP ${warrior2.xp}")
       warrior1 attackInfix  warrior2
       println("warrior2's updated XP ${warrior2.xp}")
   }
}

class Warrior(var xp:Int, var hp:Int){
    fun attack(warrior:Warrior){
        warrior.xp -= hp
    }

    infix fun attackInfix(warrior:Warrior){
        warrior.xp -= hp
    }
}
