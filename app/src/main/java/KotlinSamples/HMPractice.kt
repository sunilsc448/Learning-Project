package KotlinSamples

import java.util.*

class HMPractice<K, V>{
    private var entriesSize = 4
    private var counter = 0
    private var entries:Array<Entry<K, V>?> = arrayOfNulls(entriesSize)
    private var entries_linkedlist:Array<LinkedList<Entry<K, V>>> = Array(entriesSize){LinkedList<Entry<K ,V>>()}
    val loadFactor = 0.9

    /* Collision issue
     fun get(key: K):V?{
        val hash = getHashKey(key)
        return  entries[hash]?.value
    }

    fun remove(key: K){
        val hash = getHashKey(key)
        entries[hash] = null
    }

    fun put(key:K, value:V){
        val newEntry = Entry(key, value)
        val hash = getHashKey(key)
        entries[hash] = newEntry
    }
   * */

    fun put(key:K, value:V){
        counter ++
        if(counter  > (entriesSize * loadFactor)){
            doubleTheSize()
        }
        put(key, value, entries_linkedlist)
    }

    fun put(key: K, value: V, newEntries: Array<LinkedList<Entry<K, V>>>){
        if(counter == entriesSize){
            doubleTheSize()
        }
        val hash = getHashKey(key)
        val newEntry = Entry(key, value)
        val linkedList = newEntries[hash]
        val entryIndex = linkedList.indexOfFirst {it == key}
        if(entryIndex >= 0){
            linkedList[entryIndex] = newEntry
        }else{
            linkedList.offer(newEntry)
        }
        counter++
    }

    fun get(key: K):V? {
        val hash = getHashKey(key)
        val linkedList = entries_linkedlist[hash]
        return linkedList.first{it.key == key}.value
    }

    fun remove(key: K){
        val hash = getHashKey(key)
        val linkedList = entries_linkedlist[hash]
        val entry = linkedList.first{it.key == key}
        linkedList.remove(entry)
        counter--
    }

    private fun getHashKey(key:K):Int{
        return key.hashCode() % entriesSize
    }

    private fun doubleTheSize(){
        entriesSize *= 2
//        entries_linkedlist = Arrays.copyOf(entries_linkedlist, entriesSize)

        val newEntries = Array(entriesSize){LinkedList<Entry<K, V>>()}
        entries_linkedlist.forEach {
            it.forEach {
                put(it.key, it.value, newEntries)
            }
        }
        entries_linkedlist = newEntries
    }

    override fun toString(): String {
        var sb = StringBuilder()
        entries_linkedlist.forEach {
            sb.append(it).append("\n")
        }
        return sb.toString()
    }
}