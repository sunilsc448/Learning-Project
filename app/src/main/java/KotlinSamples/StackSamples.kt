package KotlinSamples

import java.util.*

class StackSamples {
    init{
//        val myStack = MyStack2()
//        myStack.push(1)
//        myStack.push(2)
//        myStack.top() // return 2
//        myStack.pop() // return 2
//        myStack.empty() // return False)
//        println()

//        nextGreatestElementUsingStack(intArrayOf(13, 7, 6, 12))
//        nextGreatestElementCorrectOrder(intArrayOf(1, 3, 4, 2))
//        oneThreeTwoPattern(intArrayOf(1,2,3,4))
    }

    //1,2,3,4 -> false as array doesn't have items in i(1) < j(3) > k(2)
    //3,1,4,2 -> true [1,4,2]
    //-1,3,2,0 -> true [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
    private fun oneThreeTwoPattern(arr: IntArray):Boolean {
        val size = arr.size
        var thirdElement = Int.MIN_VALUE
        val stack = Stack<Int>()
        for (i in size-1 downTo 0){
            if(arr[i] < thirdElement)
                return true

            while (stack.isNotEmpty() && arr[i] > stack.peek()) {
                thirdElement = stack.pop()
            }

            stack.push(arr[i])
        }
        return false
    }

    //13, 7, 6, 12
    //output
//    6 -> 7
//    7 -> 12
//    12 -> -1
//    13 -> -1
    private fun nextGreatestElementUsingStack(arr: IntArray) {
        val size = arr.size
        val stack = Stack<Int>()
        stack.push(arr[0])
        for (i in 1 until size){
            while (stack.isNotEmpty() && arr[i] > stack.peek()){
                println("${stack.pop()}'s next greatest element is ${arr[i]}")
            }
            stack.push(arr[i])
        }

        while (stack.isNotEmpty()){
            println("${stack.pop()}'s next greatest element is -1")
        }
    }

    //13, 7, 6, 12
    //output
//    13 -> -1
//    7 -> 12
//    6 -> 12
//    12 -> -1
    private fun nextGreatestElementCorrectOrder(arr: IntArray) {
        val size = arr.size
        val arr_save = Array<Int>(size){0}
        val stack = Stack<Int>()
        for (i in size-1 downTo 0){
//            if(stack.isNotEmpty()) {
                while (stack.isNotEmpty() && arr[i] > stack.peek()) {
                    stack.pop()
                }
//            }
            arr_save[i] = if(stack.isEmpty()) -1 else stack.peek()
            stack.push(arr[i])
        }

       for (i in 0 until size){
            println("${arr[i]}'s next greatest element is ${arr_save[i]}")
        }
    }

    fun getMaxAreaHistogram(hist: IntArray, n: Int): Int {
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        val s: Stack<Int> = Stack()
        var max_area = 0 // Initialize max area
        var tp: Int // To store top of stack
        var area_with_top: Int // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        var i = 0
        while (i < n) {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.empty() || hist[s.peek()] <= hist[i])
            {
                s.push(i++)
            } else {
                tp = s.peek() // store the top index
                s.pop() // pop the top

                // Calculate the area with hist[tp] stack as smallest bar
                area_with_top = hist[tp] * if (s.empty()) i else i - s.peek() - 1

                // update max area, if needed
                if (max_area < area_with_top) max_area = area_with_top
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (!s.empty()) {
            tp = s.peek()
            s.pop()
            area_with_top = hist[tp] * if (s.empty()) i else i - s.peek() - 1
            if (max_area < area_with_top) max_area = area_with_top
        }
        return max_area
    }

//    682. Baseball Game
//    Input: ops = ["5","2","C","D","+"]
//    Output: 30
//    Explanation:
//    "5" - Add 5 to the record, record is now [5].
//    "2" - Add 2 to the record, record is now [5, 2].
//    "C" - Invalidate and remove the previous score, record is now [5].
//    "D" - Add 2 * 5 = 10 to the record, record is now [5, 10].
//    "+" - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
//    The total sum is 5 + 10 + 15 = 30.
    fun calPoints(ops: Array<String>): Int {
        var stackInt = Stack<Int>()
        for(i in 0 until ops.size){
            val str =  ops.get(i)
            when(str){
                "C" -> {
                    stackInt.pop()
                }
                "D" -> {
                    stackInt.push(stackInt.peek()*2)
                }
                "+" ->{
                    val pop1 = stackInt.pop()
                    val pop2 = stackInt.pop()
                    val sum = pop1 + pop2
                    stackInt.push(pop2)
                    stackInt.push(pop1)
                    stackInt.push(sum)
                }else -> {
                stackInt.push(str.toInt())
            }
            }
        }

        var sum = 0
        while (stackInt.isNotEmpty()){
            sum += stackInt.pop()
        }

        return sum
    }
}

class MyStack() {
    private var queue1:Queue<Int> = LinkedList<Int>()
    private var queue2:Queue<Int> = LinkedList<Int>()
    fun push(x: Int) {
        if(queue1.isEmpty()){
            queue2.offer(x)
        }else{
            queue1.offer(x)
        }
    }

    fun pop(): Int {
        if(queue2.isNotEmpty()){
            for (i in 0 until queue2.size - 1){
                queue1.offer(queue2.poll())
            }
            return queue2.remove()
        }else{
            for (i in 0 until queue1.size - 1){
                queue2.offer(queue1.poll())
            }
            return queue1.remove()
        }
    }

    fun top(): Int {
        if(queue2.isNotEmpty()){
            return queue2.peek()
        }else{
            return queue1.peek()
        }
    }

    fun empty(): Boolean {
        return queue2.isEmpty() && queue1.isEmpty()
    }
}

class MyStack2(){
    private var queue:Queue<Int> = LinkedList()
    fun push(x: Int) {
        queue.add(x)
        for (i in 1 until queue.size){
            queue.offer(queue.poll())
        }
    }
    fun pop(): Int {
        return queue.remove()
    }
    fun top(): Int {
        return queue.peek()
    }
    fun empty(): Boolean {
        return queue.isEmpty()
    }
}