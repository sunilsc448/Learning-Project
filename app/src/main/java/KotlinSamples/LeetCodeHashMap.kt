package KotlinSamples

import java.util.*

class LeetCodeHashMap{
    init {
        val myHashMap = MyHashMap()
        myHashMap.put(1, 1); // The map is now [[1,1]]
        myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
        myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
        myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
        myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
        myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
        myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
        myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
    }
}


class MyHashMap() {
    private var arraySize = 16
    private var numberOfEntries = 0
    private var loadFactor = 0.75
    private var entries:Array<LinkedList<Entry1>> = Array(arraySize){ LinkedList<Entry1>()}
    fun put(key: Int, value: Int) {
        numberOfEntries++

        if (numberOfEntries > arraySize * loadFactor) {
            increaseCapacity()
        }

        put(key, value, entries)
    }

    fun put(key: Int, value: Int, localEntries: Array<LinkedList<Entry1>>) {
        val index = calculateHashCode(key)
        val newEntry = Entry1(key, value)
        val slotslinkedList = localEntries[index]
        val indexLinkedList = slotslinkedList.indexOfFirst {it.key == key}
        if(indexLinkedList >= 0){
            slotslinkedList[indexLinkedList] = newEntry
        }else{
            slotslinkedList.offer(newEntry)
        }
    }

    fun get(key: Int): Int {
        val index = calculateHashCode(key)
        val slotslinkedList = entries[index]
        val value:Int? = slotslinkedList.find { it.key == key }?.value
        return if(value == null) -1 else value
    }

    fun remove(key: Int) {
        numberOfEntries --
        val index = calculateHashCode(key)
        val linkedList = entries[index]
        val entry =  linkedList.find { key == it.key }
        linkedList.remove(entry)
    }

    private fun increaseCapacity(){
        numberOfEntries = 0
        arraySize *= 2
        val newEntries:Array<LinkedList<Entry1>> = Array(arraySize){ LinkedList<Entry1>() }
        entries.forEach {
            it.forEach { entry ->
                put(entry.key, entry.value, newEntries)
            }
        }
        entries = newEntries
    }

    fun calculateHashCode(key: Int):Int{
        val code = key.hashCode() // or key.toString().first().code
        return code % arraySize
    }
}

data class Entry1(val key:Int, val value:Int)