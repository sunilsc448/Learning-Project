package KotlinSamples

import java.util.*
import kotlin.collections.ArrayDeque

class QueueSamples {
    init {
        maxSlidingWindow(intArrayOf(4, 1, 3, 5, 1, 2, 3, 2, 1, 1, 5), 11)
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