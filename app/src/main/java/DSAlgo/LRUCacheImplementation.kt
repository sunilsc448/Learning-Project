package DSAlgo

class LRUCacheImplementation{
    init {
        val lru = LRUCacheLinkedHashmap<String>(4)
        lru.put("1", "Anil")
        lru.put("2", "Sunil")
        lru.put("3", "Roopa")
        lru.put("4", "Neksha")
        lru.put("5", "Kanvi")
        lru.put("2", "Pooja")
        lru.get("3")
        lru.get("4")
        lru.put("7", "Kanviii")
        lru.put("8", "Poojaaa")
        println(lru.dump())
    }
}

//Using Doubly LinkedList
class LRUCacheDoublyLinkedList<K, V>(capacity:Int){
    private val dll = DoublyLinkedList<K, V>(capacity)
    fun get(key:K) = dll.get(key)
    fun put(key: K, value:V){
        dll.put(key, value)
    }

    private class DoublyLinkedList<K, V>(private val capacity: Int){
       private val map = HashMap<K, DLLNode<K, V>>()
       private val head: DLLNode<K, V> = DLLNode(Unit as K, Unit as V)
       private val tail: DLLNode<K, V> = DLLNode(Unit as K, Unit as V)
       private var count = 0

       init {
           head.next = tail
           tail.prev = head
       }

       fun get(key:K):V?{
           map.get(key)?.let {
               remove(it)
               addFirst(it)
               return it.value
           }
           return null
       }

       fun put(key:K, value: V){
          if(map.get(key) == null){
              val node = DLLNode(key, value)
              addFirst(node)
              map.put(key, node)
              count++
              deleteTailOnCondition()
          }else{
              val node =  map.get(key)!!
              node.value = value
              remove(node)
              addFirst(node)
          }
       }

       private fun addFirst(node: DLLNode<K, V>) {
          val next = head.next
          head.next = node
          node.prev = head
          node.next = next
          next?.prev = node
       }

       private fun deleteTailOnCondition() {
           if(count > capacity){
               deleteEnd()
           }
       }

       private fun deleteEnd() {
           tail.prev?.let {
               if(it == head)
                   return
               map.remove(it.key)
               remove(it)
           }
       }

       private fun remove(node: DLLNode<K, V>?) {
           val next = node?.next
           val prev = node?.prev
           prev?.next = next
           next?.prev = prev
           count--
       }
   }

    private class DLLNode<K, V>(val key: K, var value: V, var prev: DLLNode<K, V>? = null, var next: DLLNode<K, V>? = null)
}

//using LinkedHashmap
class LRUCacheLinkedHashmap<T>(capacity: Int){
     val map:MutableMap<String, T> = object:LinkedHashMap<String, T>(0, 0.75f, true){
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, T>?): Boolean {
            return size > capacity
        }
    }

    fun get(key: String): T? {
        return map.get(key)
    }

    fun put(key: String, value: T) {
        map.put(key, value)
    }

    fun remove(key: String){
        map.remove(key)
    }

    fun reset() {
        map.clear()
    }

    fun size(): Long {
        return synchronized(this) {
            map.size.toLong()
        }
    }

    fun dump() {
        println(map)
    }

}



