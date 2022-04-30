package com.example.kotlintutorial

import KotlinSamples.*
import pojos.ListNode
import pojos.TreeNode
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.math.BigInteger
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList


class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        val inputArray:IntArray = intArrayOf(2,2)
//        containerMostWater(inputArray)
//        print("Area is ${getMaxAreaHistogram(inputArray, 4)}")
//        largestElementInAnArray(inputArray)
//        smallestElementInAnArray(inputArray)
//        minAndMax(inputArray)

//        addTwoNumbers(getNode1(), getNode2())
//        println("lengthOfLongestSubstring >>> ${lengthOfLongestSubstring("geeksforgeeks")}")
//        println("lastStoneWeight is >>> ${lastStoneWeight(inputArray)}")
//        calPoints(arrayOf("1"))
//        mergeSort(intArrayOf(5, 3, 2, 1, 4), 0, 4);
//        shiftGrid(arrayOf(intArrayOf(1,2,3),intArrayOf(4,5,6),intArrayOf(7,8,9)), 2)
//        gameOfLife(arrayOf(intArrayOf(0,1,0),intArrayOf(0,0,1),intArrayOf(1,1,1), intArrayOf(0,0,0)))
//        secondHighest("dfa12321afd")
//        val stringArray:Array<String> = arrayOf("683339452288515879","7846081062003424420","4805719838","4840666580043","83598933472122816064","522940572025909479","615832818268861533","65439878015","499305616484085","97704358112880133","23861207501102","919346676","60618091901581","5914766072","426842450882100996","914353682223943129","97","241413975523149135","8594929955620533","55257775478129","528","5110809","7930848872563942788","758","4","38272299275037314530","9567700","28449892665","2846386557790827231","53222591365177739","703029","3280920242869904137","87236929298425799136","3103886291279")
//        val stringArray:Array<String> = arrayOf("2","21","12","1")
//        kthLargestNumberComparatorApproach(stringArray, 3)
//        generateMatrix(3)


//        maxSlidingWindowDeque(intArrayOf(4,1,3,5,1,2,3,2,1,1,5),3)
//        trimBST(getBSTree(), -13, 14)
        firstFunction()
    }

    private fun areDistinct(inputString:String, i:Int, j:Int) : Boolean{
        var counter = 0
        val visited:BooleanArray = BooleanArray(26)
        for (k in i until j){
            println("counter >>> $counter++")
            val ascii = inputString.elementAt(k) - 'a'
            if(visited[ascii]){
                return false
            }

            visited[ascii] = true
        }
        return true
    }

    fun longestUniqueSubsttr(str: String): Int {
        var counter = 0
        val n = str.length

        // Result
        var res = 0
        for (i in 0 until n) {
            for (j in i until n) {
                println("counter >>> $counter++")
                if (areDistinct(str, i, j)) {
                    res = Math.max(res, j - i + 1)
                }
            }
        }
        println("RE >>> $counter++")
        return res
    }

    fun lengthOfLongestSubstring(s: String): Int {
        var result = 0
        val n = s.length
        for(i in 0 until n){
            var visitedArray = BooleanArray(256)
            for(j in i until n){
                val asciival = s.elementAt(j).toInt()
                if(visitedArray[asciival] == true){
                    break;
                }else{
                    result = Math.max(result, j-i+1)
                    visitedArray[asciival] = true
                }
            }
            visitedArray[s.elementAt(i).toInt()] = false
        }
        return result
    }

    private fun getNode1(): ListNode {
        var node1 = ListNode(9)
        var node2 = ListNode(9)
        var node3 = ListNode(9)
        var node4 = ListNode(9)
        var node5 = ListNode(9)
        var node6 = ListNode(9)
        var node7 = ListNode(9)
        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node6
        node6.next = node7
        return node1
    }

    private fun getNode2(): ListNode {
        var node1 = ListNode(9)
        var node2 = ListNode(9)
        var node3 = ListNode(9)
        var node4 = ListNode(9)
        node1.next = node2
        node2.next = node3
        node3.next = node4
        return node1
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var list1 = l1; var list2 = l2
        val head = ListNode(0)
        var current: ListNode? = head

        var carry = 0
        while (list1 != null && list2 != null){
            var sum = list1.`val` + list2.`val` + carry
            carry = sum / 10
            sum %= 10
            val newNode = ListNode(sum)
            current?.next = newNode
            current = current?.next
            list1 = list1.next
            list2 = list2.next
        }

        if(list1 != null){
            if(carry != 0){
                current?.next = addTwoNumbers(list1, ListNode(carry))
            }else{
                current?.next = list1
            }
        }else if(list2 != null){
            if(carry != 0){
                current?.next = addTwoNumbers(list2, ListNode(carry))
            }else{
                current?.next = list2
            }
        }else if(carry != 0){
            current?.next = ListNode(carry)
        }

       return head.next
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        var outputArray = IntArray(2)
        var map:HashMap<Int, Int> = HashMap<Int, Int>()
        for(i in 0 until nums.size){
            val diff = target - nums[i]
            if(map.containsKey(diff)){
                outputArray[0] = i
                outputArray[1] = map.get(diff)!!
            }else{
                map.put(nums[i], i)
            }
        }
        return outputArray
    }

    fun threeSumMulti(arr: IntArray, target: Int): Int {
        val mod = 1000000007
        var sum = 0
        var map = HashMap<Int, Int>()
        map.put(arr[0], 1)
        for(i in 1 until arr.size){
            for(j in i+1 until arr.size){
                val diff = target - (arr[i] + arr[j])
                if(map.keys.contains(diff)){
                    sum = ((sum % mod) + (map.get(diff)!! % mod)) % mod
                }
            }

            if(map.keys.contains(arr[i])){
                map.put(arr[i], map.get(arr[i])!! + 1)
            }else{
                map.put(arr[i], 1)
            }
        }
        return sum
    }

    private fun smallestElementInAnArray(arr: IntArray) {
        var smallestElement = 0
        var l = 0
        var r= arr.size - 1
        while (l < r){
            if(arr[l] < arr[r]){
                smallestElement = arr[l]
                r--
            }else{
                smallestElement = arr[r]
                l++
            }
        }
        println("smallest element is >>> $smallestElement")
    }

    private fun largestElementInAnArray(arr: IntArray) {
        var largestElement = 0
        var l = 0
        var r= arr.size - 1
        while (l < r){
            if(arr[l] < arr[r]){
                largestElement = arr[r]
                l++
            }else{
                largestElement = arr[l]
                r--
            }
        }
        println("largest element is >>> $largestElement")
    }

    private fun minAndMax(arr: IntArray):Pair<Int, Int>{
        var largestElement = Int.MIN_VALUE
        var smallestElement = Int.MAX_VALUE
        var l = 0
        var r= arr.size - 1
        while (l < r){
            if(arr[l] < arr[r]){
                smallestElement = arr[l]
                if(arr[r] > largestElement) {
                    largestElement = arr[r]
                }
                r--
            }else{
                smallestElement = arr[r]
                if(arr[l] > largestElement) {
                    largestElement = arr[l]
                }
                l++
            }
        }
        println("largest element is >>> $largestElement")
        println("smallest element is >>> $smallestElement")
        return Pair(smallestElement, largestElement)
    }

    private fun modularDifference(arr: IntArray): List<List<Int>> {
        arr.sort()
        var outputArray = ArrayList<List<Int>>()
        var diff = Int.MAX_VALUE
        for (i in 0..arr.size-2){
            val absval = Math.abs(arr[i] - arr[i-1])
            if(absval < diff){
                outputArray.clear()
                outputArray.add(listOf(arr[i-1], arr[i]))
                diff = absval
            }else if(absval == diff){
                outputArray.add(listOf(arr[i-1], arr[i]))
            }
        }

        return outputArray
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

    fun containerMostWater(arr: IntArray){
        var Area = 0
        for (i in 0 until arr.size){
            for (j in i+1 until arr.size){
                Area = Math.max(Area, Math.min(arr[i], arr[j]) * (j - i))
            }
        }
        println("printing Area >>>"+Area)
    }

    //stones = [2,7,4,1,8,1]
    fun lastStoneWeight(stones: IntArray): Int {
        if(stones.size == 0)
            return 0
        if(stones.size == 1){
            return stones[0]
        }
        var n1 = Double.NEGATIVE_INFINITY
        n1 = stones[0].toDouble()
        var stoneCopy = stones.toMutableList()
        var pair = getMaxPairjGreaterThani(stones)
        if (pair.first > pair.second){
            val value = pair.first - pair.second
            stoneCopy.remove(pair.first)
            stoneCopy.remove(pair.second)
            stoneCopy.add(value)
        }else if (pair.second == pair.first){
            stoneCopy.remove(pair.first)
            stoneCopy.remove(pair.second)
        }
        return lastStoneWeight(stoneCopy.toIntArray())
    }

    //arr = [2,7,4,1,8,1]
    fun findTwoLargestNumbers(arr: IntArray):Pair<Int, Int>{
        var first = Int.MIN_VALUE; var second = Int.MIN_VALUE
        for (i in 0..arr.size-1){
            if(arr[i] > first){
                second = first
                first = arr[i]
            }else if(arr[i] > second && arr[i] != first){
                second = arr[i]
            }
        }
        return Pair(first, second)
    }

    //stones = [2,7,4,1,8,1]
    fun getMaxPairjGreaterThani(arr: IntArray):Pair<Int, Int>{
        var first = Int.MIN_VALUE; var second = Int.MIN_VALUE
        var firstIndex = -1
        for (i in 0..arr.size-1){
            if(arr[i] > first){
                second = first
                first = arr[i]
                firstIndex = i
            }else if(arr[i] >= second && i > firstIndex){
                second = arr[i]
            }
        }
        return Pair(first, second)
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


    fun mergeSort(nums:IntArray, start:Int, end:Int){
        if(start < end){
            val mid = (start + end)/2
            mergeSort(nums, start, mid)
            mergeSort(nums, mid + 1, end)
            merge(nums, start, mid, end)
        }
    }

    fun merge(nums:IntArray, start:Int, mid:Int, end:Int){
        val n1 = mid - start + 1
        val n2 = end - mid
        var startArray = IntArray(n1)
        var endArray = IntArray(n2)

        for(i in 0 until n1){
            startArray[i] = nums[start + i]
            println("startArray ${startArray[i]}")
        }

        for(j in 0 until n2){
            endArray[j] = nums[mid + 1 + j]
            println("endArray ${endArray[j]}")
        }

        var i = 0; var j = 0; var k = start
        while(i < n1 && j < n2){
            if(startArray[i] < endArray[j]){
                nums[k++] = startArray[i++]
            }else{
                nums[k++] = endArray[j++]
            }
        }


        while(i < n1){
            nums[k++] = startArray[i++]
        }

        while(j < n2){
            nums[k++] = endArray[j++]
        }
    }

    fun shiftGrid(grid: Array<IntArray>, k: Int): List<List<Int>> {
        val m = grid.size
        val n = grid.get(0).size

        for(i in 0 until k){
            shift(grid, m, n)
        }

        var output:MutableList<List<Int>> = mutableListOf()
        for (i in 0 until m){
            output.add(grid.get(i).toList())
        }
        return output
    }

    fun shift(grid: Array<IntArray>, m: Int, n:Int){
        var temp = grid[0][0]
        for (i in 0 until m){
            for (j in 0 until n){
                if(i == m-1 && j == n-1){
                    grid[0][0] = temp
                    return
                }
                if(j < n-1){
                    val tempLocal = grid[i][j+1]
                    grid[i][j+1] = temp
                    temp = tempLocal
                }else{
                    val tempLocal = grid[i + 1][0]
                    grid[i + 1][0] = temp
                    temp = tempLocal
                }
            }
        }
    }

    fun gameOfLife(board: Array<IntArray>): Unit {
        val m = board.size
        val n = board.get(0).size
        val visited:Array<BooleanArray> = Array(m){ BooleanArray(n)}

        for(i in 0 until m)
            for(j in 0 until n){
                board[i][j] = game(board, i, j, m, n, visited)
            }
    }

    fun game(board: Array<IntArray>, i:Int, j:Int, m:Int, n:Int, visited: Array<BooleanArray>):Int{
        val directrions = arrayOf(intArrayOf(-1, 0), intArrayOf(-1, -1), intArrayOf(0, -1), intArrayOf(1, -1),
            intArrayOf(1, 0), intArrayOf(1,1), intArrayOf(0, 1), intArrayOf(-1, 1))
        var currentVal = board[i][j]
        var countOfLives = 0
        for(dir in 0..7){
            val newi = i + directrions[dir][0]
            val newj = j + directrions[dir][1]
            if(newi > 0 && newj > 0 && newi < m && newj < n && board[newi][newj] == 1) {
                if(visited[newi][newj]){
                    if(board[newi][newj] == 0)
                        countOfLives++
                }else{
                    if (board[newi][newj] == 1)
                        countOfLives++
                }
                countOfLives++;
            }
        }
        if(currentVal == 0){
            if(countOfLives == 3){
                visited[i][j] = true
                currentVal = 1
            }
        }else{
            if(countOfLives < 2){
                visited[i][j] = true
                currentVal = 0
            }else if(countOfLives > 3){
                visited[i][j] = true
                currentVal = 0
            }
        }
        return currentVal
    }

    fun secondHighest(s: String): Int {
        var max1 = Int.MIN_VALUE
        var max2 = Int.MIN_VALUE
        for(char in s){
            val num = char.toInt()
            if(num in 48..57){
                if(num > max1){
                    max2 = max1
                    max1 = num
                }else if(num > max2 && max2 != max1){
                    max2 = num
                }
            }
        }
        if(max2 == Int.MIN_VALUE)
           return -1
        return Integer.parseInt(max2.toChar()+"")
    }

    fun kthLargestNumberComparatorApproach(nums: Array<String>, k: Int): String {

        Arrays.sort(nums) { a, b ->
            if (a.length !== b.length)
                a.length - b.length
            else a.compareTo(b)
        }

//        var sortedList = nums.sortedWith (object : Comparator <String> {
//                override fun compare (num1: String, num2: String) : Int {
//                    val len1 = num1.length
//                    val len2 = num2.length
//                    return if (len1 > len2) {
//                        1
//                    } else if (len1 < len2) {
//                        -1
//                    } else {
//                        for (i in 0 until len1) {
//                            val c1 = num1[i] - '0'
//                            val c2 = num2[i] - '0'
//                            if (c1 > c2) {
//                                return 1
//                            } else if (c1 < c2) {
//                                return -1
//                            }
//                        }
//                        0
//                    }
//                }
//            })

        return nums[nums.size - k]
    }

    fun generateMatrix(n: Int): Array<IntArray> {
        val rowMax = n
        val colMax = n
        val rowEnd = n-1
        val colEnd = n-1
        var resultArray:Array<IntArray> = Array(n){IntArray(n)}
        val counter = 0
        generateSpiralMatrix(resultArray, n, rowMax, colMax, 0, 0, n-1, n-1, counter)
        return resultArray
    }

    fun generateSpiralMatrix(resultArray: Array<IntArray>, n:Int, rowMax:Int, colMax: Int,
                             rowStart:Int, colStart: Int,rowEnd:Int, colEnd:Int, counter: Int){
        if(colStart > colEnd || rowStart > rowEnd || counter >= (n*n))
            return

        var counterLocal = counter

        //firstrow
        for(i in rowStart..rowEnd){
            resultArray[rowStart][i] = ++counterLocal
        }

        //lastcolumn
        for(i in rowStart+1..rowEnd){
            resultArray[i][colEnd] = ++counterLocal
        }

        //bottomrow
        for(i in colEnd-1 downTo colStart){
            resultArray[rowEnd][i] = ++counterLocal
        }

        //firstcolumn
        var i = rowEnd-1
        while (i > rowStart){
            resultArray[i][colStart] = ++counterLocal
            i--
        }

        generateSpiralMatrix(resultArray, n, rowMax, colMax, rowStart + 1, colStart + 1, rowEnd-1, colEnd - 1, counterLocal)
    }

    fun quickSort(nums:LongArray, low:Int, high:Int):LongArray{
        var lowLocal = low
        var highLocal = high
        if(low < high){
            val pi = partition(nums, lowLocal, highLocal)
            quickSort(nums, lowLocal, pi-1)
            quickSort(nums, pi+1, high)
        }
        return nums
    }

    fun partition(nums:LongArray, low:Int, high:Int):Int{
        val pivot = nums[high]
        var i = low - 1
        for(j in low until high){
            if(nums[j] < pivot){
                i++
                swap(nums, i, j)
            }
        }
        swap(nums, i+1, high)
        return i + 1
    }

    fun swap(arr:LongArray, i:Int, j:Int){
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
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

    fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        if(root == null){
            return root
        }
        if(root?.`val`?.equals(`val`)!!){
            return root
        }
        val leftNode =  searchBST(root?.left,`val`)
        if(leftNode == null){
            val rightNode = searchBST(root?.right,`val`)
            return rightNode
        }
        return leftNode
    }

    fun trimBST(root: TreeNode?, low: Int, high: Int): TreeNode? {
        if(root == null)
            return null

        root.left = trimBST(root.left, low, high)
        root.right = trimBST(root.right, low, high)
        if(root.`val`!! < low){
            return root.right
        }else if(root.`val`!! > high){
            return root.left
        }
        return root
    }

    //    [3,0,4,null,2,null,null,1]
    private fun getBSTree(inputs: IntArray): TreeNode? {
        var root: TreeNode? = null
        for (i in inputs.indices){
            val item = inputs.get(i)
            if(i == 0){
                root= TreeNode(item)
            }else{
                if(item != null)
                    root = root?.insert(root,item)
            }
        }
        return root
    }

    var preOrderCounter = 0
    var preOrderArray:IntArray? = null
    private fun preOrderTraversal(root: TreeNode?) {
        if(root == null)
            return
        preOrderArray!!.set(preOrderCounter++,root.`val`!!)
        preOrderTraversal(root.left)
        preOrderTraversal(root.right)
    }

    private fun createBinaryTree(inputs: IntArray): TreeNode? {
        var root: TreeNode? = TreeNode(inputs[0])
        var queue:Queue<TreeNode> = LinkedList<TreeNode>()
        queue.add(root)

        for (i in 1 until inputs.size - 1 step 2){
            var node = queue.poll()
            val itemLeft = inputs.get(i)
            val itemRight = inputs.get(i + 1)
            var leftNode = TreeNode(itemLeft)
            var rightNode = TreeNode(itemRight)
            queue.add(leftNode)
            queue.add(rightNode)
            node.left = leftNode
            node.right = rightNode
        }
        return root
    }

    private fun maxOfTwoNums(a:Int, b:Int, c:Int = 0):Int = if(a > b) {
            print("dsdsdsd")
            a
        }else{
            print("dsdsdsd")
            b
        }

    var count = 0
    fun kthSmallest(root: TreeNode?, k: Int): Int? {
        val result = kthSmallestFunction(root, k)
        if(result == null){
            return null
        }else{
            return result.`val`
        }
    }

    fun kthSmallestFunction(root: TreeNode?, k: Int): TreeNode? {
        if(root == null)return root
        val left = kthSmallestFunction(root.left, k)
        if(left != null){
            return left
        }
        count++
        if(count == k)
            return root

        return kthSmallestFunction(root.right, k)
    }

    private fun charFunction(){
//        val input = "UURRD"
        val input = "URDR"
        var row = 0
        var column = 0
        var result = ""
        for (i in input.indices){
            val char = input[i]
            if(char.equals('U')){
                row++
            }else if(char.equals('D')){
                row--
            } else if(char.equals('R')){
                column++
            }else if(char.equals('L')){
                column--
            }
        }
        if(row < 0){
            while (row < 0){
                result += "D"
                row++
            }
        }else{
            while (row > 0){
                result += "U"
                row--
            }
        }

        if(column < 0){
            while (column < 0){
                result += "L"
            }
        }else{
            while (column > 0){
                result += "R"
                column--
            }
        }

        println("result is ${result}")
    }

    fun fibonacci(n: Int): BigInteger {
        if(n <= 1)
            return BigInteger(n.toString())

        var a = BigInteger("0"); var b = BigInteger("1"); var c = BigInteger("0")
        if(n == 0)
            return a

        for(i in 2..n){
            c = a + b
            a = b
            b = c
        }
        return b
    }

    tailrec fun fibonacciRec(n:Int, a:BigInteger, b:BigInteger):BigInteger{
         if(n == 0){
             return b
         }else{
             return fibonacciRec(n-1, a+b, a)
         }
    }

    private fun subStringProblem() {
        val input = "abc"
        val n = 10
        var newString = ""

        val quotient = n/input.length
        val remainder = n%input.length
        for(i in 0 until quotient){
            newString += input
        }

        for(i in 0 until remainder){
            newString += input[i]
        }

        println("newString is $newString")

        var result = 0
        for(i in 0 until newString.length){
            if(newString[i] == 'a'){
                result++
            }
        }

        println("the final output is $result")
    }

    //Extension functions
    fun String.add(s1:String, s2:String):String{
        return this + s1 + s2
    }

    fun Int.isModulusOfTen():Boolean{
        return this % 10 == 0
    }

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        var i = 0; var j = 0
        var mergeArray = IntArray(nums1.size + nums2.size)
        var counter = 0
        while(i < nums1.size && j < nums2.size){
            if(nums1[i] < nums2[j]){
                mergeArray[counter++] = nums1[i++]
            }else{
                mergeArray[counter++] = nums2[j++]
            }
        }

        while(i < nums1.size){
            mergeArray[counter++] = nums1[i++]
        }

        while(j < nums2.size){
            mergeArray[counter++] = nums2[j++]
        }

        val middle = (mergeArray.size - 1)/2

        var double = 0.0
        if(mergeArray.size % 2 == 0){
            double = (mergeArray[middle].toDouble() + mergeArray[middle + 1].toDouble())/2
        }else{
            double = mergeArray[middle].toDouble()
        }

        return double
    }

    private fun firstFunction() {
        //trimBST(getBSTree(), -13, 14)
//        charFunction()
//        val smallestElement = kthSmallest(getBSTree(), 6)
//        println(smallestElement)
//       val x = 1..5
//        println("the X is $x")
//        maxOfTwoNums(5,10)
//        KotlinTestFile.sub(b=2,a=3)
//        val file = KotlinTestFile()
//        val s1 = "How you "
//        val s2 = "doing?"
//        val s3 = "Hey, "
//        println(s3.add(s1, s2))
//        val num = 100
//        println("is $num modulus of 10? and the answer is ${num.isModulusOfTen()}")
//        val num = 1000000
//        var fibnum = fibonacciRec(num, BigInteger("1"), BigInteger("0"))
//        println("fibonacci number of ${num} is $fibnum")
//        getBSTree()
//        createBinaryTree(intArrayOf(1,2,3,4,5,6,7, 8, 9, 10, 11))
//        val inputArray = intArrayOf(1,3,2)
//        preOrderArray = IntArray(inputArray.size)
//        preOrderTraversal(createBinaryTree(inputArray))
//        val finalNode = getBSTree(preOrderArray!!)
//        println(finalNode)
//        findMedianSortedArrays(nums1 =  intArrayOf(1,2), nums2 = intArrayOf(3,4))

//         ArraySample()
//        MapsSample()
//         SetSample()
//        subStringProblem()
//        HashMapKeySample("sunil")
//        FilterAndMapSample()
//        PredicateSample()
//        SafeCallSample()
//        LateinitSample()
//          BackingFieldSample()
//          LazyInitilisationSample()
//          HighLevelFunctions()
//        ScopeFunctionsSample()
        HashMapSample()
    }
}


