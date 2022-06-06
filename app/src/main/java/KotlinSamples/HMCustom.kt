package KotlinSamples

import java.lang.StringBuilder
import java.util.*

class HMSample{
    init {
        val aHashMap:HMCustom<String, String> = HMCustom()
        aHashMap.put("A", "Apple")
        aHashMap.put("B", "Ball")
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

        println(aHashMap.toString())

        println("value for key Q is ${aHashMap.get("Q")}")

//        aHashMap.remove("A")

//        println(aHashMap.toString())
    }
}

class HMCustom<K, V> {
    var arraySize = 4
    val loadFactor = 0.9
    var itemCount = 0
    var entries:Array<LinkedList<Entri<K, V>>> = Array(arraySize){ LinkedList<Entri<K, V>>() }
    fun put(key:K, value:V){
        itemCount ++
        if(itemCount  > (arraySize * loadFactor)){
            increaseSize()
        }
        put(key, value, entries)
    }

    fun put(key:K, value:V, localEntries:Array<LinkedList<Entri<K, V>>>){
        val newEntri = Entri(key, value)
        val index = createHash(key)
        val linkedList = localEntries[index]
        val linkedListIndex = linkedList.indexOfFirst { keyLocal -> keyLocal ==  key}
        if(linkedListIndex == -1){
            linkedList.offer(newEntri)
        }else{
            linkedList[linkedListIndex] = newEntri
        }
    }

    private fun increaseSize() {
        arraySize *= 2
        val newEntries = Array(arraySize){LinkedList<Entri<K, V>>()}
        entries.forEach {
            it.forEach {
                put(it.key, it.value, newEntries)
            }
        }
        entries = newEntries
    }

    fun get(key:K):V?{
        val index = createHash(key)
        val linkedList = entries[index]
        return linkedList.find { key == it.key }?.value
    }

    fun remove(key: K){
        itemCount --
        val index = createHash(key)
        val linkedList = entries[index]
        val entry =  linkedList.find { key == it.key }
        linkedList.remove(entry)
    }

    fun createHash(key: K):Int{
        return key.toString().first().code % arraySize
    }

    override fun toString(): String {
        val sb = StringBuilder()
        var count = 0
        entries.forEach {
            count++
            sb.append(it).append(count).append("\n")
        }
        return sb.toString()
    }
}

data class Entri<K, V>(val key:K, val value:V)