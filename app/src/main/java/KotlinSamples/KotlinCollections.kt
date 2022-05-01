package KotlinSamples

class KotlinCollections {
    init {
        ArraySample()
        MapsSample()
        SetSample()
    }
}

class ArraySample{
    init {
        val postfixArray = listOf("st", "nd", "rd", "th")
        var array = Array<Int>(2){0}
        array[0] = 1

        var list1 = listOf("xzxzx", "xzxzx")
//        list1[0] = "saSS"  //NOT POSSIBLE

        var list = arrayListOf("one", "two")
        list.add("three")

        var arraylist = ArrayList<Int>(2)

        for (i in 0 until list.size)
        {
            var postfixExp = ""
            if(i < 3){
                postfixExp = postfixArray[i]
                list.add("sunil")
            }else{
                postfixExp = postfixArray[3]
            }
            println("list items > ${(i+1)} $postfixExp item is ${list[i]}")
        }

        println("\n\n")

        for (i in 0 until list.size)
        {
            var postfixExp = ""
            if(i < 3){
                postfixExp = postfixArray[i]
            }else{
                postfixExp = postfixArray[3]
            }
            println("list items > ${(i+1)} $postfixExp item is ${list[i]}")
        }


        var days: MutableList<String> = mutableListOf(
            "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"
        )

        // Printing all the values of MutableList list
        println("\nGiven Mutable List contains:")
        days.forEach{
            println(it)
        }

        println("\n\nMutable List after modification:")
       for (i in days.indices){
           days[i] = days[i] + ","
           println("modified days is ${days[i]}")
        }


        val listNew:List<String> = List<String>(12){""}

        val l1 = listOf("a")
        val l2 = listOf("a")
        var x = (l1 == l2) // => true

        val a1 = arrayOf("a")
        val a2 = arrayOf("a")
        var y = (a1 == a2) //false


    }
}

class MapsSample{
    init {
        val mymap = mapOf(5 to "Yogi", 10 to "Yogi")
        for (key in mymap.keys){
            println("map element for key $key is ${mymap[key]}")
        }
    }
}

class SetSample{
    init {
        var sets = setOf<Int>(1,2,3,4,4,5,6,7,8,8,9)

//        for (item in sets){
//            println("set item: $item")
//        }
//        for(i in 0 until sets.size){
//            println("set item: ${sets.elementAt(i)}")
//        }

        var setsMutable = mutableSetOf<Int>(3,6,7,1,5,9,7,8,0,9,2,4)
//        setsMutable.remove(8)
//        setsMutable.add(0)
        for(i in 0 until setsMutable.size){
            println("set item: ${setsMutable.elementAt(i)}")
        }

        println("\n\n")

        var hashsetsMutable = hashSetOf<Int>(3,6,7,1,5,9,7,8,0,9,2,4)
//        setsMutable.remove(8)
//        setsMutable.add(0)
        for(i in 0 until hashsetsMutable.size){
            println("hash set item: ${hashsetsMutable.elementAt(i)}")
        }
    }
}