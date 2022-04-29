package KotlinSamples

class PredicateSample {
    init {
        val persons = listOf(Person("Rama", 10), Person("MP", 20),
            Person("Dhanya", 30), Person("Raghu", 60), Person("Ajja", 80))

        val lambdaPredicate: (Person) -> Boolean = { person:Person -> person.age > 50 }

        val isAllAgeGtrThan50:Boolean = persons.all {person:Person -> person.age > 50 }
        println("are all persons age greater than 50 > $isAllAgeGtrThan50")

        val isAnyPersonAgeGrtThan50:Boolean = persons.any { it.age > 50 }
        println("is any persons age greater than 50 > $isAnyPersonAgeGrtThan50")

        val countofPeopleAgeGrtThan50:Int = persons.count { it.age > 50 }
        println("how many persons age greater than 50 > $countofPeopleAgeGrtThan50")

        val anypersonAgeGrtThan50:Person = persons.find {(it.age > 50) }!!
        println("is any persons age greater than 50 > ${anypersonAgeGrtThan50.name}")

        val isAllAgeGtrThan50_:Boolean = persons.all {lambdaPredicate(it)}
        println("are all persons age greater than 50 > $isAllAgeGtrThan50_")

        val isAnyPersonAgeGrtThan50_:Boolean = persons.any { lambdaPredicate(it) }
        println("is any persons age greater than 50 > $isAnyPersonAgeGrtThan50_")

        val countofPeopleAgeGrtThan50_:Int = persons.count {lambdaPredicate(it) }
        println("how many persons age greater than 50 > $countofPeopleAgeGrtThan50_")

        val anypersonAgeGrtThan50_:Person = persons.find {lambdaPredicate(it)}!!
        println("is any persons age greater than 50 > ${anypersonAgeGrtThan50_.name}")
    }
}