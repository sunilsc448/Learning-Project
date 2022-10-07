package DSAlgo

import java.util.*

class QueueSamples {
    init {
//        myQueueUseCase()
          myDequeueUseCase()
//        maxSlidingWindow(intArrayOf(4, 1, 3, 5, 1, 2, 3, 2, 1, 1, 5), 11)
    }

    private fun myDequeueUseCase() {
        val dq = MyDequeue<Int>()
        dq.addLast(0)
        dq.removeLast()
        dq.addLast(1)
        dq.addLast(2)
        dq.getFirst()
        dq.addLast(3)
        dq.removeFirst()
        println("dequeue >>$dq")
    }

    private fun myQueueUseCase() {
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

    fun maxSlidingWindowDeque(nums: IntArray, k: Int): IntArray {
        val dq = ArrayDeque<Int>()
        val resultArray = IntArray(nums.size-k+1)
        var resultCounter = 0
        var i = 0
        while (i < k){
            while (!dq.isEmpty() && nums[i] >= nums[dq.last()]){
                dq.removeLast()
            }
            dq.addLast(i)
            i++
        }

        while (i < nums.size){
            resultArray[resultCounter++] = nums[dq.first()]
            while (!dq.isEmpty() && dq.first() <= i-k){
                dq.removeFirst()
            }

            while (!dq.isEmpty() && nums[i] >= nums[dq.last()]){
                dq.removeLast()
            }
            dq.addLast(i)
            i++
        }

        resultArray[resultCounter] = nums[dq.first()]
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

//Dequeue(double sided queue)
class MyDequeue<T>{
    private val size = 6
    private val array = Array<Any>(size){}
    private var rear = 0
    private var front = -1

    fun addFirst(input:T){
        if(isFull()) {
            return
        }

        if(front == -1){
            front = 0
            rear = 0
        }else if(front == 0){
            front = size - 1
        }else{
            front--
        }

        array[front] =  input as Any
    }

    fun removeFirst(){
        if(isEmpty()){
            return
        }

        if(front == rear){
            front = -1
            rear = -1
        } else if(front == size - 1){
            front = 0
        }else{
            front++
        }
    }

    fun addLast(input:T){
        if(isFull())
            return

        if(front == -1){
            front = 0
            rear = 0
        }else if(rear == size-1){ //mostly unwanted check
            rear = 0
        }else{
            rear++
        }

        array[rear] = input as Any
    }

    fun removeLast(){
        if(isEmpty()){
            return
        }

        if(front == rear){
            front = -1
            rear = -1
        }else if(rear == 0){ //mostly unwanted check
            rear = size - 1
        }else{
            rear--
        }
    }

    private fun isEmpty(): Boolean {
        return (front == -1)
    }

    private fun isFull(): Boolean {
        return ((front == 0 && rear == size - 1) || front == rear + 1)
    }

    fun getFirst():T{
        if(!isEmpty()){
            return array[front] as T
        }else{
            throw StackOverflowError("Queue is Empty")
        }
    }

    fun getLast():T{
        if(!isEmpty()){
            return array[rear] as T
        }else{
            throw StackOverflowError("Queue is Empty")
        }
    }
}