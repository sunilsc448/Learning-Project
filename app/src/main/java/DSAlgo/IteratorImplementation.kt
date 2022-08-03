package KotlinSamples

import java.io.Serializable

class IteratorImplementation {
    init {
        val numbers = arrayOf(1, 2, 3, 4, 5)
        val words = arrayOf("Hi", "Hello", "How", "Are", "You")

        //List Type check
        val type:Any = words.firstOrNull() as Any
        when(type){
            is String -> {}
            is Int -> {}
            is Double -> {}
            is Serializable -> {}
        }

        val iterator_numbers = MyIterator(numbers)
        while (iterator_numbers.iterator().hasNext()){
           println("iterator int ${iterator_numbers.iterator().next()}")
        }

        val iterator_string = MyIterator(words)
        while (iterator_string.iterator().hasNext()){
            println("iterator string ${iterator_string.iterator().next()}")
        }
    }
}

class MyIterator<T>(val array: Array<T>):Iterable<T>{
    val currentSize = array.size

    override fun iterator(): Iterator<T> {
       val it = object: Iterator<T>{
           var currentIndex = 0
           override fun hasNext(): Boolean {
                return currentIndex < currentSize && array[currentIndex] != null
           }

           override fun next(): T {
                return array[currentIndex++]
           }
       }
        return it
    }
}

class xx<T>(val array: Array<T>):Iterable<T> {
    val currentSize = array.size
    override fun iterator(): Iterator<T> {
        val itr = object : Iterator<T>{
            var currentIndex = 0
            override fun hasNext(): Boolean {
                return currentIndex < currentSize && array[currentIndex] != null
            }

            override fun next(): T {
                return array[currentIndex++]
            }
        }
        return itr
    }
}