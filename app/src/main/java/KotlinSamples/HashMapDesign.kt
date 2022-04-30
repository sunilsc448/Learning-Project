package KotlinSamples

import java.lang.StringBuilder
import java.util.*

class HashMapSample(){
    init {
        val aHashMap:HashMapCustom<String, String> = HashMapCustom()

        aHashMap.put("AA", "AAgastya")
        aHashMap.put("BB", "BBahubali")
        aHashMap.put("CC", "CChanakya")
        aHashMap.put("DD", "DDharma")
        aHashMap.put("A", "Alpha")
        aHashMap.put("B", "Bravo")
        aHashMap.put("C", "Charlie")
        aHashMap.put("D", "Delta")
        aHashMap.put("E", "Echo")
        aHashMap.put("F", "Foxtrot")
        aHashMap.put("G", "Golf")
        aHashMap.put("H", "Hotel")
        aHashMap.put("I", "India")
        aHashMap.put("J", "July")
        aHashMap.put("K", "Kilo")
        aHashMap.put("L", "Lima")
        aHashMap.put("M", "Mike")
        aHashMap.put("N", "November")
        aHashMap.put("O", "Oscar")
        aHashMap.put("P", "Papa")
        aHashMap.put("Q", "Quebec")
        aHashMap.put("R", "Romeo")
        aHashMap.put("S", "Sierra")
        aHashMap.put("T", "Tango")
        aHashMap.put("U", "Uniform")
        aHashMap.put("V", "Victor")
        aHashMap.put("W", "Whiskey")
        aHashMap.put("X", "X-Ray")
        aHashMap.put("Y", "Yankee")
        aHashMap.put("Z", "Zulu")
        aHashMap.put("A", "Agastya")
        aHashMap.put("B", "Bahubali")
        aHashMap.put("C", "Chanakya")
        aHashMap.put("D", "Dharma")

//        for (key in 'A' .. 'Z'){
//            println("key is $key and value is ${aHashMap.get(key.toString())}")
//        }
        println(aHashMap.toString())
    }
}

class HashMapCustom<K, V> {
    private var arraySize = 16
    private var numberOfEntries = 0
    private var loadFactor = 0.75
    //Before Collision
//   private val entries:Array<Entry<K, V>?> = arrayOfNulls(arraySize)

    //After Collision
    private var entries:Array<LinkedList<Entry<K,V>>> = Array(arraySize){ LinkedList<Entry<K,V>>() }

    fun get(key: K):V?{
       val index = calculateHashCode(key)
       //Before Collision
//       return entries[index]?.value

       //After Collision
       val slotslinkedList = entries[index]
       return slotslinkedList.find { it.key == key }?.value
    }

    fun remove(key: K){
        numberOfEntries --
       val index = calculateHashCode(key)
       //Before Collision
//       entries[index] = null

       //After Collision
       entries[index].clear()
    }

    fun calculateHashCode(key: K):Int{
       val code = key.hashCode() // or key.toString().first().code
       return code % arraySize
   }

    override fun toString(): String {
        val sb = StringBuilder()
        var count = 1
        entries.forEach {
            if(it.isNotEmpty()){
                sb.append(it).append(count).append("\n")
                count++
            }
        }
        return sb.toString()
     }

    fun put(key:K, value:V){
        numberOfEntries++

        if (numberOfEntries > arraySize * loadFactor) {
            increaseCapacity()
        }

        put(key, value, entries)
    }

    fun put(key: K, value: V, localEntries: Array<LinkedList<Entry<K, V>>>) {
        val index = calculateHashCode(key)
        val newEntry = Entry(key, value)
        //Before Collision
//       entries[index] = newEntry

        //After Collision
        val slotslinkedList = localEntries[index]
        val indexLinkedList = slotslinkedList.indexOfFirst {it.key == key}
        if(indexLinkedList >= 0){
            slotslinkedList[indexLinkedList] = newEntry
        }else{
            slotslinkedList.offer(newEntry)
        }
    }

    private fun increaseCapacity(){
        numberOfEntries = 0
        arraySize *= 2
        val newEntries:Array<LinkedList<Entry<K, V>>> = Array(arraySize){ LinkedList<Entry<K, V>>() }
        entries.forEach {
            it.forEach { entry ->
                put(entry.key, entry.value, newEntries)
            }
        }
        entries = newEntries
    }
}

data class Entry<K, V>(val key:K, val value:V)