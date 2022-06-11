package KotlinSamples

import java.math.BigInteger
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.sign

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

//        nextGreatestElement(intArrayOf(4, 5, 2, 25))

//        containerMostWaterBetterApproach(intArrayOf(1, 2, 4, 3))
//        minimumOperationsToZero(intArrayOf(3,2,20,1,1,3), 10)
    }

    fun moveAllZerosToEnd(arr: IntArray){
        var pivot = 0
        for (i in arr.indices){
            if(arr[i] != 0){
                val temp = arr[pivot]
                arr[pivot] = arr[i]
                arr[i] = temp
                pivot++
            }
        }
        println(arr)
    }

    fun removeDuplicatesSortedArray(arr: IntArray){
        var pivot = 0
        for (i in arr.indices){
            if(arr[i] > arr[pivot]){
                arr[++pivot] = arr[i]
            }
        }

        println(arr.copyOfRange(0, pivot+1))
    }

    fun sortAsWaveArray(arr: IntArray) {
        for (i in 0 until arr.size step 2){
            if(i > 0 && arr[i] < arr[i - 1]){
                swap(arr, i, i - 1)
            }
            if(i < arr.size - 1 && arr[i] < arr[i + 1]){
                swap(arr, i, i + 1)
            }
        }
        println("wave sorted array")
        arr.forEach {
            println("$it \t")
        }
    }

    //87, 68, 91, 56, 43, 98, 6, 40
    //o/p (98 - 43) = 55
    //87, 68, 91, 56, 43, 98, 4, 60
    //o/p (98 - 43) = 56
    fun findMaximumDiffInIncreasingElements(arr: IntArray):Int{
       var min = Integer.MAX_VALUE
       var result = 0
       for (i in arr.indices){
           if(arr[i] < min){
               min = arr[i]
           }else{
               result = Math.max(result, arr[i] - min)
           }
       }
        return  result
    }

    private fun swap(arr: IntArray, a: Int, b: Int) {
        val temp = arr[a]
        arr[a] = arr[b]
        arr[b] = temp
    }

    //2, 6, 9, 11, 19, 21, 23
    //o/p > [{9,11}, {19,21}, {21,23}]
    fun minimumAbsoluteDifferencePairs(array: IntArray):List<List<Int>>{
        array.sort()
        val outPut = mutableListOf<List<Int>>()
        var diff = Int.MAX_VALUE
        for (i in 1 until array.size){
            val absVal = Math.abs(array[i] - array[i-1])
            if(absVal < diff){
                diff = absVal
                outPut.clear()
                outPut.add(listOf(array[i-1], array[i]))
            }else if(diff == absVal){
                outPut.add(listOf(array[i-1], array[i]))
            }
        }
        return outPut
    }

    fun reverseArrayInPlace(arr: IntArray):IntArray{
        var l = 0; var r= arr.size - 1
        while(l < r){
            swap(arr, l, r)
            l++
            r--
        }
        return arr
    }

//    for sequence 0......n
//    for sequence 1......n   >   n = n + 1
    fun findMissingNumberinSequenceUnsortedArray(arr: IntArray):Int{
        val n = arr.size + 1
        val expectedSum = (n * (n+1))/ 2
        var sum = 0
        arr.forEach { sum += it}
        return expectedSum - sum
    }

    fun factorial(n:Int):Int{
        var result = 1
        for (i in n downTo 1){
            result *= i
        }
        return result
    }

    fun factorialRec(n:Int):Int{
        if(n == 0)return 1
        return n * factorial(n-1)
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

    fun maximumSumSlidingProblem(arr: IntArray, m: Int): Int {
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

    fun maximumSumSlidingProblemBetterApproach(arr: IntArray, m: Int): Int {
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

    //4, 1, 3, 5, 1, 2, 3, 2, 1, 1, 5
    fun slidingWindowMaximumUSingDeque(arr: IntArray, k: Int):IntArray{
        val n = arr.size
        val resultArray = IntArray(arr.size - k + 1)
        var resultCounter = 0
        val dq = ArrayDeque<Int>(k)
        var i = 0
        while (i < k){
            while (!dq.isEmpty() && arr[i] > arr[dq.last()]){
                dq.removeLast()
            }
            dq.addLast(i)
            i++
        }

        while (i < n){
            resultArray[resultCounter++] = arr[dq.first()]
            while (!dq.isEmpty() && dq.first <= i-k){
                dq.removeFirst()
            }
            while (!dq.isEmpty() && arr[i] > arr[dq.last()]){
                dq.removeLast()
            }
            dq.addLast(i)
            i++
        }

        resultArray[resultCounter++] = arr[dq.first]
        return resultArray
    }


    fun containerMostWater(arr: IntArray):Int{
        var maxArea = 0
        for (i in 0 until arr.size){
            for (j in i+1 until arr.size){
                maxArea = Math.max(maxArea, Math.min(arr[i], arr[j]) * (j - i))
            }
        }
        println("printing Area >>>"+maxArea)
        return maxArea
    }

    //Two Pointer Approach
    fun containerMostWaterBetterApproach(arr:IntArray):Int{
        var l = 0; var r = arr.size - 1
        var maxArea = 0
        while (l < r){
            val min = Math.min(arr[l], arr[r])
            maxArea = Math.max(maxArea, min*(r-l))
            if(arr[l] < arr[r]){
                l++
            }else{
                r--
            }
        }
        println("printing Area >>>"+maxArea)
        return maxArea
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

    fun towerOfHanoi(from:Char, to:Char, aux:Char, n:Int){
        if(n == 0)
            return
        towerOfHanoi(from, aux, to, n-1)
        println("move the $n block from $from to $to")
        towerOfHanoi(aux, to, from, n-1)
    }

    fun minAndMax(arr: IntArray):Pair<Int, Int>{
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





    //1, 4, 2, 10, 2, 3, 1, 0, 20
    fun runningMedian(arr: IntArray, n: Int) {
        val output = IntArray(arr.size)
        var outputCount = 0
        var i: Int; var j: Int; var pos: Int; var num: Int; var count = 1;
        println("""Median after reading >>>  ${arr[0]} is $count """)
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
            println("""Median after reading ${i + 1} is $median 
""")
            i++
        }
        println(output)
    }

    fun findMedianOfSortedArraysLength(nums1: IntArray, nums2: IntArray): Double {
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

    fun binarySearch(arr: IntArray, item: Int): Int {
        return binarySearch(arr, item, 0, arr.size-1)
    }

    fun binarySearch(arr: IntArray, item: Int, low: Int, high: Int): Int {
        if (low >= high) {
            return if (item > arr[low])
                low + 1
            else
                low
        }

        val mid = (low + high) / 2

        if (item == arr[mid])
            return mid + 1 //returns position from index, so plus one

        return if (item > arr[mid])
            binarySearch(arr, item, mid + 1, high)
        else
            binarySearch(arr, item, low, mid - 1)
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

    //3Sum With Multiplicity
//    Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
//    Output: 20
//    Explanation:
//    Enumerating by the values (arr[i], arr[j], arr[k]):
//    (1, 2, 5) occurs 8 times;
//    (1, 3, 4) occurs 8 times;
//    (2, 2, 4) occurs 2 times;
//    (2, 3, 3) occurs 2 times.

//    Input: arr = [1,1,2,2,2,2], target = 5
//    Output: 12
//    Explanation:
//    arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
//    We choose one 1 from [1,1] in 2 ways,
//    and two 2s from [2,2,2,2] in 6 ways.

//    Input: arr = [2,1,3], target = 6
//    Output: 1
//    Explanation: (1, 2, 3) occured one time in the array so we return 1.
    fun tripletSum(arr: IntArray, target: Int): Int {
        val mod = 1000000007
        var sum = 0
        var map = HashMap<Int, Int>()
        map.put(arr[0], 1)
        for(i in 1 until arr.size){
            for(j in i+1 until arr.size){
                val diff = target - (arr[i] + arr[j])
                if(map.containsKey(diff)){
                    sum = sum + map.get(diff)!!
//                    sum = ((sum % mod) + (map.get(diff)!! % mod)) % mod
                }
            }

            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])!! + 1)
            }else{
                map.put(arr[i], 1)
            }
        }
        return sum
    }

    //CountTriplets Better Approach O(n2)
    //    i < j < k and a[k] < a[i] < a[j]
    fun countTriplets(arr: IntArray):Int{
        var tripletCounter = 0
        for (i in 0 until arr.size){
            var counter = 0
            for (j in i+1 until arr.size){
                if(arr[j] > arr[i]){
                    counter++
                }else{
                    tripletCounter += counter
                }
            }
        }
        return tripletCounter
    }

    //CountTriplets O(n3)
    //    i < j < k and a[k] < a[i] < a[j]
    fun countTripletsBruteForce(arr: IntArray): Int {
        val n = arr.size
        var cnt = 0
        for (i in 0 until n)
            for (j in i + 1 until n)
                for (k in j + 1 until n) { // If it satisfy the
                    // given conditions
                    if (arr[k] < arr[i] && arr[i] < arr[j]) {
                        cnt++
                    }
                }

        // Return the final count
        return cnt
    }

//    Input: nums = [2,6,4,8,10,9,15]
//    Output: 5
//    Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

//    Input: nums = [1,2,3,4]
//    Output: 0
//    array is already sorted
    fun findUnsortedSubarray(nums: IntArray): Int {
        val len = nums.size
        var max = Int.MIN_VALUE ; var min = Int.MAX_VALUE
        var start = -1 ; var end = -1
        for (i in 0 until len) {
            max = Math.max(max, nums[i]) //from left to right, search the current max
            min = Math.min(min, nums[len - i - 1]) //from right to left, search the current min
            if (nums[i] < max)
                end = i
            if (nums[len - i - 1] > min)
                start = len - i - 1
        }
        return if (start == -1)
            0
        else
            end - start + 1
    }

//    Input: nums = [1,2,3,4], k = 5
//    Output: 2
//    Explanation: Starting with nums = [1,2,3,4]:
//    - Remove numbers 1 and 4, then nums = [2,3]
//    - Remove numbers 2 and 3, then nums = []
//    There are no more pairs that sum up to 5, hence a total of 2 operations.

//    Input: nums = [3,1,3,4,3], k = 6
//    Output: 1
//    Explanation: Starting with nums = [3,1,3,4,3]:
//    - Remove the first two 3's, then nums = [1,4,3]
//    There are no more pairs that sum up to 6, hence a total of 1 operation.
    fun maxOperationsKSumPair(nums: IntArray, k: Int): Int {
        val map = HashMap<Int, Int>()
        var count = 0
        for (i in 0 until nums.size) {
            if (map.containsKey(nums[i])) {
                count++
                map.remove(nums[i])
            } else {
                map.put(k - nums[i], 0)
            }
        }
        return count
    }

    fun maxOperationsKSumPair2(nums: IntArray, k: Int): Int {
        val map = HashMap<Int, Int>()
        var count = 0
        for(i in 0 until nums.size){
            if(map.containsKey(nums[i])){
                count++
                val value = map.get(nums[i])!!
                map.remove(nums[i])
                if(value > 1){
                    map.put(nums[i], value - 1)
                }
            }else{
                if(map.containsKey(k-nums[i])){
                    map.put(k-nums[i], map.get(k-nums[i])!! + 1)
                }else{
                    map.put(k-nums[i], 1)
                }
            }
        }
        return count
    }

//    Input: x = 123
//    Output: 321

//    Input: x = -123
//    Output: -321

//    Input: x = 120
//    Output: 21
    //reverseTheInteger
    fun reverseTheInteger(x: Int): Int {
        if(x > Int.MAX_VALUE || x <= Int.MIN_VALUE){
            return 0
        }
        var num = x
        var sum:Long = 0

        while (num != 0){
            val rem = num % 10
            num /= 10
            sum = (sum*10) + rem
        }

        if(sum > Int.MAX_VALUE || sum <= (Int.MIN_VALUE)){
            return 0
        }else {
            return sum.toInt()
        }
    }

//    Input: s = "42"
//    Output: 42
//
//    Input: s = "   -42"
//    Output: -42
//
//    Input: s = "4193 with words"
//    Output: 4193
    fun stringToInteger(s: String): Int {
        val str = s.trim()
        if(str.isEmpty())return 0
        var num:Long = 0
        var isNegative = false
        var index = 0
        if(str[0] == '-'){
            isNegative = true
            index++
        }else if(str[0] == '+'){
            index++
        }

        for (i in index until str.length){
            if(str[i] in '0'..'9'){
                num = num*10 + str[i].toString().toInt()
            }else{
                break;
            }
            if(num > Int.MAX_VALUE || num < Int.MIN_VALUE){
                break
            }
        }

        if(isNegative){
            num = -num
        }

        if(num > Int.MAX_VALUE){
            return Int.MAX_VALUE
        }else if(num < Int.MIN_VALUE){
            return Int.MIN_VALUE
        }else{
            return num.toInt()
        }
    }

//    Input: x = 121
//    Output: true
//
//    Input: x = -121
//    Output: false

//    Input: x = 10
//    Output: false
    fun isPalindrome(x: Int): Boolean {
        if(x < 0){
            return false
        }

        var xVal = x

        var rhs = 0
        while(xVal != 0){
            rhs = rhs * 10 + (xVal % 10)
            xVal = xVal/10
        }

        return x == rhs
    }



//    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//    Output: 6
//    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
//
//    Input: height = [4,2,0,3,2,5]
//    Output: 9
    fun TrappingRainWater(height: IntArray): Int {
//        approach 1
         val size = height.size
         var sum = 0
         for (i in 1 until size-1){
            var left = height[i]
            for (j in 0 until i){
                left = Math.max(left, height[j])
            }
            var right = height[i]
            for (j in i + 1 until size){
                right = Math.max(right, height[j])
            }
            sum += Math.min(left, right) - height[i]
         }
         return sum


        //approach2
//         val size = height.size
//         var sum = 0

//         //fill left array
//         val leftArray = Array(size){Int.MIN_VALUE}
//         leftArray[0] = height[0]
//         for (i in 1 until size){
//             leftArray[i] = Math.max(leftArray[i - 1], height[i])
//         }

//         //fill right array
//         val rightArray = Array(size){Int.MIN_VALUE}
//         rightArray[size - 1] = height[size - 1]
//         for (i in size-2 downTo 0){
//             rightArray[i] = Math.max(rightArray[i+1], height[i])
//         }

//         for (i in 1 until size){
//             sum += Math.min(leftArray[i], rightArray[i]) - height[i]
//         }
//         return sum


        //approach3
        // val size = height.size
        // var sum = 0
        // var left = 0; var right = size-1
        // var left_max = 0; var right_max = 0
        // while (left < right){
        //     if(height[left] < height[right]){
        //         if(height[left] > left_max){
        //             left_max = height[left]
        //         }else{
        //             sum += left_max - height[left]
        //         }
        //         left++
        //     }else{
        //         if(height[right] > right_max){
        //             right_max = height[right]
        //         }else{
        //             sum += right_max - height[right]
        //         }
        //         right--
        //     }
        // }
        // return sum

        //aproach4
//        val size = height.size
//        var sum = 0
//        val stack = Stack<Int>()
//        for(i in 0 until size){
//            while(stack.isNotEmpty() && height[i] >  height[stack.peek()]){
//                val pop_height = height[stack.pop()]
//                if(stack.isEmpty()){
//                    break;
//                }
//                val dist = i - stack.peek() - 1
//                val min_height = Math.min(height[stack.peek()], height[i]) - pop_height
//                sum += (min_height * dist)
//            }
//            stack.push(i)
//        }
//        return sum
    }

//    Minimum Operations to Reduce X to Zero
//    Input: nums = [1,1,4,2,3], x = 5
//    Output: 2
//    Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

//    Input: nums = [5,6,7,8,9], x = 4
//    Output: -1

//    Input: nums = [3,2,20,1,1,3], x = 10
//    Output: 5

    //this test case failed
//    [5207,5594,477,6938,8010,7606,2356,6349,3970,751,5997,6114,9903,3859,6900,7722,2378,1996,
//    8902,228,4461,90,7321,7893,4879,9987,1146,8177,1073,7254,5088,402,4266,6443,3084,1403,5357,
//    2565,3470,3639,9468,8932,3119,5839,8008,2712,2735,825,4236,3703,2711,530,9630,1521,2174,5027,
//    4833,3483,445,8300,3194,8784,279,3097,1491,9864,4992,6164,2043,5364,9192,9649,9944,7230,7224,
//    585,3722,5628,4833,8379,3967,5649,2554,5828,4331,3547,7847,5433,3394,4968,9983,3540,9224,6216,
//    9665,8070,31,3555,4198,2626,9553,9724,4503,1951,9980,3975,6025,8928,2952,911,3674,6620,3745,6548,
//    4985,5206,5777,1908,6029,2322,2626,2188,5639], x = 565610
//    Output: -1
    fun minimumOperationsToZero(nums: IntArray, x: Int):Int{
        var x_op = x
        var count = 0
        var left = 0; var right = nums.size - 1
        while (left <= right && x_op != 0){
            val left_op = x_op- nums[left]
            val right_op = x_op- nums[right]
            if(left_op == 0 || right_op == 0){
                count++
                return count
            } else if(left_op >= 0 && left_op <= right_op){
                x_op -= nums[left]
                count++
                left++
            }else if (right_op >= 0){
                x_op -= nums[right]
                count++
                right--
            }else{
                return -1
            }
        }
        return if(x_op == 0)
                    count
                else
                    -1
    }

    //all test cases passed
    fun minimumOperationsToZero1(nums: IntArray, x: Int):Int{
        var sum = 0
        nums.forEach{
            sum += it
        }
        val sumMinusX = sum - x

        var l = 0; var r = 0; var currentSum = 0; var maxLength = -1
        while(r < nums.size){
            currentSum += nums[r]
            while(l<=r && currentSum >sumMinusX){
                currentSum -= nums[l++]
            }
            if(currentSum == sumMinusX){
                maxLength = Math.max(maxLength, r-l+1)
            }
            r++
        }
        return if(maxLength == -1)
            -1
        else
            nums.size - maxLength
    }
}