package KotlinSamples

import java.math.BigInteger
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ArrayPrograms {
    init {
//        val x = 1..5
//        println("the X is $x")
//
//        maxOfTwoNums(5,10)
//
//        val num = 1000000
//        var fibnum = fibonacciRec(num, BigInteger("1"), BigInteger("0"))
//        println("fibonacci number of ${num} is $fibnum")
//
//        findMedianSortedArrays(nums1 =  intArrayOf(1,2), nums2 = intArrayOf(3,4))

        nextGreatestElement(intArrayOf(4, 5, 2, 25))
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

    tailrec fun fibonacciRec(n:Int, a: BigInteger, b: BigInteger): BigInteger {
        if(n == 0){
            return b
        }else{
            return fibonacciRec(n-1, a+b, a)
        }
    }

    private fun maxOfTwoNums(a:Int, b:Int, c:Int = 0):Int = if(a > b) {
            a
        }
        else {
            b
        }

    fun printMedian(arr: IntArray, n: Int) {
        val output = IntArray(arr.size)
        var outputCount = 0
        var i: Int
        var j: Int
        var pos: Int
        var num: Int
        var count = 1
        println(
            """Median after reading ${arr[0]} is $count 
"""
        )
        output[outputCount++] = count
        i = 1
        while (i < n) {
            var median: Float
            j = i - 1
            num = arr[i]

            // find position to insert current element in sorted
            // part of array
            pos = binarySearch(arr, num, 0, j)

            // move elements to right to create space to insert
            // the current element
            while (j >= pos) {
                arr[j + 1] = arr[j]
                j--
            }
            arr[j + 1] = num

            // increment count of sorted elements in array
            count++

            // if odd number of integers are read from stream
            // then middle element in sorted order is median
            // else average of middle elements is median
            median = if (count % 2 != 0) {
                arr[count / 2].toFloat()
            } else {
                ((arr[count / 2 - 1] + arr[count / 2]) / 2).toFloat()
            }
            output[outputCount++] = median.toInt()
            println(
                """Median after reading ${i + 1} is $median 
"""
            )
            i++
        }
    }

    fun binarySearch(arr: IntArray, item: Int, low: Int, high: Int): Int {
        if (low >= high) {
            return if (item > arr[low]) low + 1 else low
        }
        val mid = (low + high) / 2
        if (item == arr[mid]) return mid + 1
        return if (item > arr[mid]) binarySearch(arr, item, mid + 1, high) else binarySearch(
            arr,
            item,
            low,
            mid - 1
        )
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

    fun areDistinct(arr: IntArray, target: Int): Int {
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

    fun containerMostWater(arr: IntArray){
        var Area = 0
        for (i in 0 until arr.size){
            for (j in i+1 until arr.size){
                Area = Math.max(Area, Math.min(arr[i], arr[j]) * (j - i))
            }
        }
        println("printing Area >>>"+Area)
    }

    //Two Pointer Approach
    fun containerMostWaterBetterApproach(arr:IntArray){

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
        return nums[nums.size - k]
    }

    //13, 7, 6, 12
    //4, 5, 2, 25
    fun nextGreatestElement(arr:IntArray){
        val size = arr.size
        for (i in 0 until size){
            var next = -1
            for (j in i+1 until size){
                if(arr[j] > arr[i]){
                    next = arr[j]
                    break
                }
            }
            println("${arr[i]}'s next element is $next")
        }
    }

    private fun maximumSumSlidingProblem(arr: IntArray, m: Int): Int {
        var maxSum = 0
        for (i in 0 until arr.size - m) {
            var sum = 0
            for (j in i until i + m) {
                sum += arr[j]
            }
            if (sum > maxSum) {
                maxSum = sum
            }
        }
        return maxSum
    }

    private fun maximumSumSlidingProblemBetterApproach(arr: IntArray, m: Int): Int {
        var maxSum = 0
        var windowSum = 0
        for (i in 0 until m) {
            maxSum += arr[i]
        }
        windowSum = maxSum
        for (i in m until arr.size) {
            windowSum = windowSum + (arr[i] - arr[i - m])
            maxSum = Math.max(windowSum, maxSum)
        }
        return maxSum
    }

    fun gameOfLife(arr: Array<IntArray>) {
        val m = arr.size
        val n: Int = arr[0].size
        val changed = Array(m) {
            BooleanArray(
                n
            )
        }
        for (i in 0 until m) {
            for (j in 0 until n) {
                var currentVal = arr[i][j]
                val countOfLives = count(arr, i, j, m, n, changed) //count live neighbours
                if (currentVal == 0) {
                    if (countOfLives == 3) {
                        changed[i][j] = true
                        currentVal = 1
                    }
                } else {
                    if (countOfLives < 2) {
                        changed[i][j] = true
                        currentVal = 0
                    } else if (countOfLives > 3) {
                        changed[i][j] = true
                        currentVal = 0
                    }
                }
                arr[i][j] = currentVal
            }
        }
    }

    //function for counting the live neighbours of current cell
    fun count(arr: Array<IntArray>, i: Int, j: Int, m: Int, n: Int, changed: Array<BooleanArray>?): Int {
        val dis = arrayOf(
            intArrayOf(-1, -1),
            intArrayOf(-1, 0),
            intArrayOf(-1, 1),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
            intArrayOf(1, -1),
            intArrayOf(1, 0),
            intArrayOf(1, 1)
        )
        var cnt = 0
        for (k in 0..7) {
            val x = i + dis[k][0]
            val y = j + dis[k][1]
            if (x >= 0 && y >= 0 && x < m && y < n && arr[x][y] == 1) {
                cnt++
            }
        }
        return cnt
    }
}