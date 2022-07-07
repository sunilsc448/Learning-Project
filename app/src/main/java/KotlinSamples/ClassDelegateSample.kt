package KotlinSamples

class ClassDelegateSample {
    init {
        val kgf = KGF()
        println("The hero ${kgf.getHeroType()} attacks the villian with ${kgf.getAttcakType()}")
    }
}

interface AttackType{
    fun getAttcakType():String
}

interface HeroType{
    fun getHeroType():String
}

class HammerAttack:AttackType{
    override fun getAttcakType(): String {
        return "Hammer Shot"
    }
}

class Rocky:HeroType{
    override fun getHeroType(): String {
        return "Rocky Bhai"
    }
}

class KGF:AttackType by HammerAttack(), HeroType by Rocky()