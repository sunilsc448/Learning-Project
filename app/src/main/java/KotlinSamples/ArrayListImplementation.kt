package KotlinSamples

import java.lang.IndexOutOfBoundsException
import java.util.*

class ArrayListImplementation<E> {
    private var size = 0
    private val DEFAULT_CAPACITY = 10
    private var elements: Array<Any?> = Array(DEFAULT_CAPACITY){}

    fun MyList() {
        elements = arrayOfNulls(DEFAULT_CAPACITY)
    }

    fun add(e: E?) {
        if (size == elements.size) {
            doubleTheSize()
        }
        elements[size++] = e
    }

    fun remove(e:E){
      for (i in 0 until elements.size){
          elements[i]?.let {
              if(it.equals(e)){
                  elements[i] = null
              }
          }
      }
    }

    fun remove(i:Int){
        isinValid(i)
        elements[i] = null
    }


    private fun doubleTheSize() {
        val newSize = elements.size * 2
        elements = Arrays.copyOf(elements, newSize)
    }

    operator fun get(i: Int): E? {
        isinValid(i)
        return elements[i] as E
    }

    private fun isinValid(i: Int) {
        if (i >= size || i < 0) {
            throw IndexOutOfBoundsException("Index: $i, Size $i")
        }
    }
}