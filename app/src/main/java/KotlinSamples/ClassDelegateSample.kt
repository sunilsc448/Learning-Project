package KotlinSamples

class ClassDelegateSample {
    init {
        val kgf = KGF()
        println("The hero ${kgf.getHeroType()} attacks the villian with ${kgf.getAttackType()}")

        val kgf2 = KGF2()
        println("The hero ${kgf2.getHeroType()} attacks the villian with ${kgf2.getAttackType()}")
    }
}

interface AttackType{
    fun getAttackType():String
}

interface HeroType{
    fun getHeroType():String
}

open class HammerAttack:AttackType{
    override fun getAttackType(): String {
        return "Hammer Shot"
    }
}

open class Rocky:HeroType{
    override fun getHeroType(): String {
        return "Rocky Bhai"
    }
}

open class RockyClass:HammerAttack(){
    open fun getHeroType(): String {
        return "Rocky Bhai"
    }
}

class KGF:RockyClass()

class KGF2:AttackType by HammerAttack(), HeroType by Rocky()