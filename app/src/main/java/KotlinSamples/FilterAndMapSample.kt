package KotlinSamples

class FilterAndMapSample {
    init{
        val list = listOf<Int>(1,2,3,4,5,6)
        val filteredList = list.filter { it  % 2 == 0 }
        for(i in 0 until filteredList.size)
            println("the new item %2 is ${filteredList[i]}")

        val mappedList = list.map { it * it }
        for(i in 0 until mappedList.size)
            println("the mapped item is ${mappedList[i]}")

        val filterAndMappedList = list.filter { it % 2 == 0 }.map { it * it }
        for(i in 0 until filterAndMappedList.size)
            println("the mapped and filtered item is ${filterAndMappedList[i]}")

        //practical example
        val persons = listOf<Person>(Person("Rama", 10), Person("MP", 20),
            Person("Dhanya", 30), Person("Raghu", 60)
        )
        val filteredAndMappedPersons = persons.filter {it.name.startsWith("R")}.map { it.name }

        for(name in filteredAndMappedPersons)
            println("the mapped and filtered person item is $name")
    }
}
