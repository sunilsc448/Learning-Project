package KotlinSamples

import java.util.*
import kotlin.collections.ArrayDeque

class QueueSamples {
    init {
        MyQueueUseCase()
//        maxSlidingWindow(intArrayOf(4, 1, 3, 5, 1, 2, 3, 2, 1, 1, 5), 11)
    }

    private fun MyQueueUseCase() {
        val queue = MyQueue<String>()
        queue.enqueue("anil")
        queue.enqueue("sunil")
        queue.enqueue("roopa")
        println("front item is ${queue.dequeue()}")
        queue.enqueue("neksha")
        queue.enqueue("kanvi")
        println("front item is ${queue.dequeue()}")
        queue.enqueue("simba")
        queue.enqueue("xxxxx")
        println("front item is ${queue.dequeue()}")
    }

    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        var priorityQueue = PriorityQueue<Int>(k, Collections.reverseOrder())

        var resultCounter = 0
        var resultArray = IntArray(nums.size-k+1)
        var i = 0
        while (i < k){
            priorityQueue.add(nums[i])
            i++
        }
        resultArray[resultCounter++] = priorityQueue.peek()!!
        priorityQueue.remove(nums[0])

        while (i < nums.size){
            priorityQueue.add(nums[i])
            resultArray[resultCounter++] = priorityQueue.peek()!!
            priorityQueue.remove(nums[i-k+1])
            i++
        }
        return resultArray
    }

    fun maxSlidingWindowDeque(nums: IntArray, k: Int): IntArray {
        var priorityQueue = ArrayDeque<Int>()
        var resultArray = IntArray(nums.size-k+1)
        var resultCounter = 0
        var i = 0
        while (i < k){
            while (!priorityQueue.isEmpty() && nums[i] >= nums[priorityQueue.last()]){
                priorityQueue.removeLast()
            }
            priorityQueue.addLast(i)
            i++
        }

        //[0,1,2,3,4,5,6,7,8,9,10]
        //[4,1,3,5,1,2,3,2,1,1,5]

        while (i < nums.size){
            resultArray[resultCounter++] = nums[priorityQueue.first()]
            while (!priorityQueue.isEmpty() && priorityQueue.first() <= i-k){
                priorityQueue.removeFirst()
            }

            while (!priorityQueue.isEmpty() && nums[i] >= nums[priorityQueue.last()]){
                priorityQueue.removeLast()
            }
            priorityQueue.addLast(i)
            i++
        }

        resultArray[resultCounter++] = nums[priorityQueue.first()]

        return resultArray
    }
}

class MyQueue<T>{
    private var size = 16
    private var count = 0
    private var front:Int = 0
    private var rear:Int = -1
    private var array:Array<T?> = arrayOfNulls<Any>(size) as Array<T?>
    fun enqueue(value:T){
        if(isFull()){
            doubleTheQueue()
        }
        rear = ++rear % size
        array[rear] = value
        count++
    }

    fun dequeue():T?{
        if(isEmpty())
            return null

        val data = array[front]
        front = ++front % size
        count--
        return data
    }

    fun isEmpty():Boolean{
        return count == 0
    }

    private fun isFull():Boolean{
        return count == size
    }

    private fun doubleTheQueue() {
        size *= 2
        array = Arrays.copyOf(array, size)
    }
}