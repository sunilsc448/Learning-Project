package KotlinSamples

import java.util.*

class LeetCodeHashSet {
    init {
        val myHashSet = MyHashSet()
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(1); // return True
        myHashSet.contains(3); // return False, (not found)
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(2); // return True
        myHashSet.remove(2);   // set = [1]
        myHashSet.contains(2); // return False, (already removed)
    }
}

class MyHashSet{
    private var arraySize = 16
    private var numberOfEntries = 0
    private var loadFactor = 0.75
    private var entries:Array<LinkedListSamples<Int>> = Array(arraySize){ LinkedList<Int>() }

    fun add(key: Int) {
        numberOfEntries++

        if (numberOfEntries > arraySize * loadFactor) {
            increaseCapacity()
        }

        put(key, entries)
    }

    fun put(key: Int, localEntries: Array<LinkedListSamples<Int>>) {
        val index = calculateHashCode(key)
        val slotslinkedList = localEntries[index]
        val indexLinkedList = slotslinkedList.indexOfFirst {it == key}
        if(indexLinkedList == -1){
            slotslinkedList.offer(key)
        }
    }

    fun remove(key: Int) {
        numberOfEntries --
        val index = calculateHashCode(key)
        val linkedList = entries[index]
        val entry:Int? =  linkedList.find { key == it }
        if(entry != null) {
            linkedList.remove(entry)
        }
    }

    fun contains(key: Int): Boolean {
        val index = calculateHashCode(key)
        val slotslinkedList = entries[index]
        val value:Int? = slotslinkedList.find { it == key }
        return value != null
    }

    private fun increaseCapacity(){
        numberOfEntries = 0
        arraySize *= 2
        val newEntries:Array<LinkedListSamples<Int>> = Array(arraySize){ LinkedList<Int>() }
        entries.forEach {
            it.forEach { entry ->
                put(entry, newEntries)
            }
        }
        entries = newEntries
    }

    fun calculateHashCode(key: Int):Int{
        val code = key.hashCode() // or key.toString().first().code
        return code % arraySize
    }
}