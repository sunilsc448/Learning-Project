package KotlinSamples

import pojos.User

class StructuralAndReferralEquitySample {
    init {
        val user1 = Person("Sunil", 10)
        val user2 = Person("Sunil", 10)
        val user3 = user1

//        println("debug: Structural Equity ${user1 == user2}")
//        println("debug: Referential Equity ${user1 === user2}")

        println("debug: Structural Equity ${user1::class == user2::class}")
        println("debug: Referential Equity ${user1::class === user2::class}")

//        println("debug: Structural Equity ${user1 == user3}")
//        println("debug: Referential Equity ${user1 === user3}")
    }
}