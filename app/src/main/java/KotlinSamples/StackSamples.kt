package KotlinSamples

import java.util.*

class StackSamples {
    init{

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