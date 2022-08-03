package DSAlgo

import java.lang.StringBuilder
import java.util.*

class StackSamples {
    init{
        //Design Stacks
//        val myStack = MyStack2()
//        myStack.push(1)
//        myStack.push(2)
//        myStack.top() // return 2
//        myStack.pop() // return 2
//        myStack.empty() // return False)
//        println()

        val stack = MyStack<String>()
//        stack.push("Hi")
//        stack.push("Hello")
//        stack.push("How")
//        stack.push("Are")
//        stack.push("You")
//        println("stack.peek() is ${stack.peek()}")
//        println("stack.pop() is ${stack.pop()}")
//        println("stack.pop() is ${stack.pop()}")
//        while (!stack.isEmpty()){
//            println("stack.item is ${stack.pop()}")
//        }
//        for (i in 1..130){
//            stack.push("Hello $i")
//        }
//        while (!stack.isEmpty()){
//            println("stack.item is ${stack.pop()}")
//        }

        //Two stacks
//        val stack = TwoStacks<Int>()
//        stack.pushStack1(1)
//        stack.pushStack1(2)
//        stack.pushStack2(3)
//        stack.pushStack2(4)
//        println("stack elements\n${stack}")
//        println("stack popStack1 ${stack.popStack1()}")
//        println("stack popStack2 ${stack.popStack2()}")
//        println("stack elements\n${stack}")

//        val stack = DoubleSidedStack<Int>()
//        stack.pushToBack(1)
//        stack.pushToBack(2)
//        stack.pushToBack(3)
//        stack.pushToBack(4)
//        println("stack top ${stack.backTop()}")
//        println("stack after pushes ${stack}")
//        stack.popBack()
//        println("stack top ${stack.backTop()}")
//        stack.pushToFront(5)
//        stack.pushToFront(6)

        //Spans
//          findingSpans_stack(intArrayOf(6, 3, 4, 5 , 2))
//          findingSpans(intArrayOf(6, 3, 4, 5 , 2)) -> (1, 1, 2, 3, 1)
//          findingSpans(intArrayOf(7, 5, 2, 4, 8, 9)) -> (1, 1, 1, 2, 5, 6)
//          println("Maa area of a histogram is ${getMaxAreaHistogram(intArrayOf(3, 2, 5, 6, 1, 4, 4), 7)}")

            //Reverese the Stack without extra memory
//            val stack =  Stack<Int>()
//            stack.push(1); stack.push(2);stack.push(3)
//            println("stack elements before reverse ${stack.toString()}")
//            reverseTheStack(stack)
//            println("stack elements after reverse ${stack.toString()}")

        //Misc Problems
        nextGreatestElementUsingStack(intArrayOf(13, 7, 6, 12))
//        nextGreatestElementCorrectOrder(intArrayOf(1, 3, 4, 2))
//        oneThreeTwoPattern(intArrayOf(1,2,3,4))
    }

    private fun reverseTheStack(stack: Stack<Int>) {
        if (stack.isEmpty())return

        val data = stack.pop()
        reverseTheStack(stack)
        insertAtTheBottom(stack, data)
    }

    private fun insertAtTheBottom(stack: Stack<Int>, data: Int) {
        if (stack.isEmpty()){
            stack.push(data)
            return
        }

        val temp = stack.pop()
        insertAtTheBottom(stack, data)
        stack.push(temp)
    }


    //TC: O(n2) worst complexity
    private fun findingSpans(arr:IntArray):IntArray{
        val spanArray = IntArray(arr.size)
        for (i in 0 until arr.size){
            var j = i
            var spans = 1
            for (j in i-1 downTo 0){
                if (arr[i] < arr[j])
                    break
                spans++
            }
            spanArray[i] = spans
        }
        return spanArray
    }

    private fun findingSpans_stack(arr:IntArray):IntArray{
        val spanArray = IntArray(arr.size)
        val stack = MyStack<Int>()
        for (i in 0 until arr.size){
           while (!stack.isEmpty() && arr[i] > arr[stack.peek()!!]){
               stack.pop()
           }
           var p = -1
           if(!stack.isEmpty()){
               p = stack.peek()!!
           }
           spanArray[i] = i - p
           stack.push(i)
        }
        return spanArray
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
//    6 -> 12
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
                while (stack.isNotEmpty() && arr[i] > stack.peek()) {
                    stack.pop()
                }
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
                if (max_area < area_with_top)
                    max_area = area_with_top
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (!s.empty()) {
            tp = s.peek()
            s.pop()
            area_with_top = hist[tp] * if (s.empty()) i else i - s.peek() - 1
            if (max_area < area_with_top)
                max_area = area_with_top
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

//    Input: height = [4,2,0,3,2,5]
//    Output: 9
    //aproach1 : Sliding window with stack, approac2 and approach3 in arrays
    fun TrappingRainWater_4(height: IntArray): Int {
        val size = height.size
        var sum = 0
        val stack = Stack<Int>()
        for(i in 0 until size){
            while(stack.isNotEmpty() && height[i] >  height[stack.peek()]){
                val pop_height = height[stack.pop()]
                if(stack.isEmpty()){
                    break;
                }
                val dist = i - stack.peek() - 1
                val min_height = Math.min(height[stack.peek()], height[i]) - pop_height
                sum += (min_height * dist)
            }
            stack.push(i)
        }
        return sum
    }
}

class MyStack<T>{
    private var top = -1
    private var arraySize = 16
    private var array:Array<T?> = arrayOfNulls<Any?>(arraySize) as Array<T?>

    fun push(input:T){
        if (isFull())
            doubleStack()

        array[++top] = input
    }

    private fun doubleStack() {
        arraySize *= 2
        array = Arrays.copyOf(array, arraySize)
//        val newArray = arrayOfNulls<Any?>(arraySize) as Array<T?>
//        var count = 0
//        array.forEach {
//            newArray[count] = array[count++]
//        }
//        array = newArray
    }

    private fun isFull() = top == arraySize - 1

    fun pop():T?{
        if(isEmpty())return null
        return array[top--]
    }

    fun peek():T?{
        if(isEmpty())return null
        return array[top]
    }

    fun isEmpty() =  (top == -1)
}

class MyStack2 {
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

class MyStack3{
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

class TwoStacks<T>{
    private var arraySize = 16
    private var array = arrayOfNulls<Any?>(arraySize)
    private var top1 = -1; private  var top2 = arraySize

    fun pushStack1(input:T){
        if(isStackFull()){
           doubleArraySize()
        }
        array[++top1] = input
    }

    fun pushStack2(input:T){
        if(isStackFull()){
            doubleArraySize()
        }
        array[--top2] = input
    }

    fun popStack1(): T?{
        if(isStack1Empty()){
            return null
        }
        return array[top1--] as T
    }

    fun popStack2(): T?{
        if(isStack2Empty()){
            return null
        }
        return array[top2++] as T
    }

    private fun doubleArraySize() {
        arraySize *= 2
        array = Arrays.copyOf(array, arraySize)
    }

    fun isStackFull() = !(top1 < top2 - 1)

    fun isStack1Empty() = (top1 < 0)

    fun isStack2Empty() = (top2 > arraySize - 1)

    override fun toString(): String {
        val sb = StringBuilder()
        array.forEach {
            it?.let {
                sb.append(it).append("\n")
            }
        }
        return sb.toString()
    }
}

class DoubleSidedStack<T>{
   private var head: Node2<T>? = null

    fun pushToBack(input:T){
        val newNode = Node2(input)
        if(head == null){
            newNode.next = head
            newNode.prev = head
            head = newNode
        }else{
           newNode.next = head
            head?.prev?.next = newNode
            newNode.prev = head?.prev
            head?.prev = newNode
        }
    }

    fun pushToFront(value:T){
        pushToBack(value)
        head = head?.prev
    }

    fun popBack():T?{
        if(head?.prev == head){
            val popValue = head?.value
            head = null
            return popValue
        }else{
            val tail = head?.prev?.prev
            val popValue = head?.value
            tail?.next = head
            head?.prev = tail
            return popValue
        }
    }

    fun popFront():T?{
        head = head?.next
        return popBack()
    }

    fun backTop():T?{
        return head?.prev?.value
    }

    fun frontTop():T?{
        return head?.value
    }

    override fun toString(): String {
        val sb = StringBuilder()
        var temp = head
        while (temp?.next != head){
            sb.append(temp?.value).append('\t')
            temp = temp?.next
        }
        return sb.toString()
    }
}