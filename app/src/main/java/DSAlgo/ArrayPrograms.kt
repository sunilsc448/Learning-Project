package DSAlgo

import java.math.BigInteger
import java.util.*
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

//        nextGreatestElement(intArrayOf(4, 5, 2, 25))

//        containerMostWaterBetterApproach(intArrayOf(1, 2, 4, 3))
//        minimumOperationsToZero(intArrayOf(3,2,20,1,1,3), 10)
    }

    fun isPowerOfFour(n: Int): Boolean {
        if(n == 0) return false
        if(n == 1) return true
        if(n % 4 != 0) return false
        return isPowerOfFour(n/4)
    }

//    Input: nums = [4,5,6,7,0,1,2]
//    Output: nums = [0,1,2,4,5,6,7]
    fun rotateArray(nums: IntArray): IntArray {
        val n = nums.size
        var rotatedIndex = 0
        for (i in 1 until n){
            if(nums[i] < nums[i - 1]){
                rotatedIndex = i
                break
            }
        }
        val sortedArray = IntArray(n)
        for (i in rotatedIndex until n){
            sortedArray[i-rotatedIndex] = nums[i]
        }

        for (i in 0 until rotatedIndex){
            sortedArray[n-rotatedIndex + i] = nums[i]
        }

        return sortedArray
    }

    fun rotateArrayInPlace(nums: IntArray): IntArray {
        val n = nums.size
        var rotatedIndex = 0
        for (i in 1 until n){
            if(nums[i] < nums[i - 1]){
                rotatedIndex = i
                break
            }
        }

        val positionsToRotate = n - rotatedIndex

        var k = 0
        while (k < positionsToRotate) {
            val last = nums[nums.size - 1]
            for (i in nums.size - 2 downTo 0) {
                nums[i+1] = nums[i]
            }
            nums[0] = last
            k++
        }

        return nums
    }

//    Input: nums = [9,0,0,6,0,2,4]
//    Output: nums = [9,6,2,4,0,0,0]
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

//    Input: nums = [3,4,4,5,6,6,7]
//    Output: nums = [3,4,5,6,7,4,6]
    fun removeDuplicatesSortedArray(arr: IntArray){
        var pivot = 0
        for (i in arr.indices){
            if(arr[i] > arr[pivot]){
                arr[++pivot] = arr[i]
            }
        }

        println(arr.copyOfRange(0, pivot+1))
    }

//    Input: nums = [3,4,4,5,6,6,7]
//    Output: nums = [3,4,5,6,7,4,6]
    fun sortAsWaveArray(arr: IntArray) {
        for (i in arr.indices step 2){
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

//    Input: nums = [4,3,5,6,7]
//    Output: nums = [7,6,5,3,4]
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
//    [4,1,3,2,6] = 5 && [4,1,3,2,6,0] = 5
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

        var a = BigInteger("0"); var b = BigInteger("1")
        var c = BigInteger("0")
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


    //1
    //11
    //121
    //1331
    //14641
    fun pascalsTriangle(n:Int){
        val n = 5
        for (i in 0 until n) {
            var number = 1
            for (j in 0..i) {
                print(number)
                number = number * (i - j) / (j + 1)
            }
            println()
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

//    Input: stones = [2,7,4,1,8,1]
//    Output: 1
//    Explanation:
//    We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
//    we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
//    we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
//    we combine 1 and 1 t
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

//    input = ["11","23","13","9","911","44"] , k = 2
//    output >  "44"
//    not important
    fun kthLargestNumberComparatorApproach(nums: Array<String>, k: Int): String {
        Arrays.sort(nums) { a, b ->
            if (a.length != b.length)
                a.length - b.length
            else a.compareTo(b)
        }
        return nums[nums.size - k]
    }

    fun smallestElementInAnArray(arr: IntArray) {
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

    fun largestElementInAnArray(arr: IntArray) {
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

    //13, 7, 6, 12
    //4, 5, 2, 25
    fun nextGreatestElement(arr:IntArray) {
        val size = arr.size
        for (i in 0 until size) {
            var next = -1
            for (j in i + 1 until size) {
                if (arr[j] > arr[i]) {
                    next = arr[j]
                    break

                }
                println("${arr[i]}'s next element is $next")
            }
        }
    }

    fun towerOfHanoi(from:Char, to:Char, aux:Char, n:Int){
        if(n == 0)
            return
        towerOfHanoi(from, aux, to, n-1)
        println("move the $n block from $from to $to")
        towerOfHanoi(aux, to, from, n-1)
    }

    //input = 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    //output = 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6
    //input = 5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4
    //output = 5, 10, 5, 4, 3, 4, 5, 6, 7, 6, 7, 6
    //unsorted
    fun runningMedian(arr: IntArray, n: Int) {
        var i: Int
        var j: Int
        var pos: Int
        var num: Int
        var count = 1

        println("Median after reading 1" + " element is " + arr[0])
        i = 1
        while (i < n) {
            j = i - 1
            num = arr[i]

            // find position to insert current element in sorted
            // part of array
            pos = BinarySearchProblems().binarySearchPositionToInsert(arr, 0, j, num)

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
            var median: Float = if (count % 2 != 0) {
                arr[count / 2].toFloat()
            } else {
                ((arr[count / 2 - 1] + arr[count / 2]) / 2).toFloat()
            }
            println("Median after reading " + (i + 1) + " elements is " + median.toInt())
            i++
        }
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

    fun gameOfLife(arr: Array<IntArray>) {
        val m = arr.size
        val n: Int = arr[0].size
        val changed = Array(m) { BooleanArray(n) }
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
    fun count(arr: Array<IntArray>, i: Int, j: Int, m: Int, n: Int, changed: Array<BooleanArray>): Int {
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
            if (x >= 0 && y >= 0 && x < m && y < n && arr[x][y] == 1 && changed[x][y]) {
                cnt++
                changed[x][y] = true
            }
        }
        return cnt
    }

    //unsorted array
    //################## refer Hashing Samples ##########################

    fun twoSumSortedArray(numbers: IntArray, target: Int): IntArray? {
        val array = IntArray(2)
        var l = 0
        var r = numbers.size - 1
        while (l <= r) {
            val sum = numbers[l] + numbers[r]
            if (sum == target) {
                array[0] = l + 1
                array[1] = r + 1
                break
            } else if (sum > target) {
                r--
            } else {
                l++
            }
        }
        return array
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

    //############ Refer Hashing for better approach ###################

    //CountTriplets Better Approach O(n2)
    //    i < j < k and a[k] < a[i] < a[j]
//    Input: arr[] = {2, 5, 1, 3, 0}  Output: 4
//    Explanation:
//    Below are the triplets (i, j, k) such that i < j < k and a[k] < a[i] < a[j]:
//    1. (0, 1, 2) and arr[2] < arr[0] 1 < 2 < 5.
//    2. (0, 1, 4) and arr[4] < arr[0] 0 < 2 < 5.
//    3. (0, 3, 4) and arr[4] < arr[0] 0 < 2 < 3.
//    4. (2, 3, 4) and arr[4] < arr[2] 0 < 1 < 3.
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


//    Input: nums = [-1,0,1,2,-1,-4]
//    Output: [[-1,-1,2],[-1,0,1]]
//    Explanation:
//    nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
//    nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
//    nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
//    The distinct triplets are [-1,0,1] and [-1,-1,2].
//    Notice that the order of the output and the order of the triplets does not matter.

//    Input: nums = [0,1,1]
//    Output: []
//    Explanation: The only possible triplet does not sum up to 0.

    //    Input: nums = [0,0,0]
//    Output: [[0,0,0]]
//    Explanation: The only possible triplet sums up to 0.
    //unsorted array
    fun threeSumToSumZero(numbers: IntArray): List<List<Int>> {
        //-1, 0, 1, 2, -1, -4
        //-4, -1, -1, 0, 1, 2
        numbers.sort()
        val target = 0
        val list = mutableListOf<List<Int>>()
        //running till last but two is sufficient as last summing with last two items is of no use
        for (i in 0 until numbers.size - 2) {
            val currentItem = numbers[i]
            var l = i + 1
            var r = numbers.size - 1

            //#main logic 1 > to avoid duplicate
            if (i > 0 && currentItem == numbers[i - 1])
                continue

            while (l < r) {
                val sum = numbers[l] + numbers[r] + currentItem
                if (sum == target) {
                    list.add(listOf(numbers[l], numbers[r], currentItem))
                    l++
                    //#main logic 2 > to avoid duplicate
                    while (numbers[l] == numbers[l - 1] && l < r) {
                        l++
                    }
                } else if (sum > target) {
                    r--
                } else {
                    l++
                }
            }
        }
        return list
    }


//    Input: nums = [2,6,4,8,10,9,15]
//    Output: 5
//    Problem explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

//    Input: nums = [1,2,3,4]
//    Output: 0
//    array is already sorted
    fun findUnsortedSubArrayLength(nums: IntArray): Int {
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
//    Output: 321


//    Input: x = 123
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
                break
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

//    Input: nums = [4,2,4,5,6]
//    Output: 17
//    Explanation: The optimal subarray here is [2,4,5,6].

//    Input: nums = [5,2,1,2,5,2,1,2,5]
//    Output: 8
//    Explanation: The optimal subarray here is [5,2,1] or [1,2,5].

//    Input: nums = [10000,1,10000,1,1,1,1,1,1]
//    Output: 10001

    //Refer Sets for Unique sub array sum

    fun maximumAscOrDescSubArray(nums: IntArray): Int {
        var maxSum = 0
        var isAscending = false
        var sum = nums[0]
        for(i in 1 until nums.size){
            if(nums[i-1] < nums[i]){
                if(isAscending){
                    sum += nums[i]
                }else{
                    sum = nums[i] + nums[i-1]
                }
                isAscending = true
            }else if(nums[i-1] > nums[i]){
                if(!isAscending){
                    sum += nums[i]
                }else{
                    sum = nums[i] + nums[i-1]
                }
                isAscending = false
            }else {
                if (isAscending) {
                    sum += nums[i]
                } else {
                    sum = nums[i] + nums[i - 1]
                }
            }

            if(sum > maxSum){
                maxSum = sum
            }
        }
        return maxSum
    }

//    Maximum Erasure Value
//    You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

//    Input: nums = [4,2,4,5,6]
//    Output: 17
//    Explanation: The optimal subarray here is [2,4,5,6].

//    Input: nums = [5,2,1,2,5,2,1,2,5]
//    Output: 8
//    Explanation: The optimal subarray here is [5,2,1] or [1,2,5].

//    Input: nums = [10000,1,10000,1,1,1,1,1,1]
//    Output: 10001

//    Input: nums = [1,2,3,4]
//    Output: [24,12,8,6]

//    Input: nums = [-1,1,0,-3,3]
//    Output: [0,0,9,0,0]
    fun productExceptSelf(nums: IntArray): IntArray {
        var prod = 1
        val ans = IntArray(nums.size) { 1 }
        for(i in 0 until nums.size) {
            ans[i] = prod
            prod *= nums[i]
        }
        prod = 1
        for(i in nums.size-1 downTo 0) {
            ans[i] *= prod
            prod *= nums[i]
        }
        return ans
    }


//    Input: array = [1, 5, 4, 3]
//    Output: 6
//    Explanation : 5 and 3 are distance 2 apart. So the size of the base = 2.Height of container = min(5, 3) = 3.So total area = 3 * 2 = 6

    //    Input: height = [1,8,6,2,5,4,8,3,7]
//    Output: 49
//    Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
//    In this case, the max area of water (blue section) the container can contain is 49.

    //    Input: height = [1,1]
//    Output: 1

//    https://www.geeksforgeeks.org/container-with-most-water/
    fun containerMostWater(arr: IntArray):Int{
        var maxArea = 0
        for (i in arr.indices){
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

//    Input: arr[] = {2, 0, 2}
//    Output: 2
//    We can trap 2 units of water in the middle gap.

//    Input: arr[]   = {3, 0, 2, 0, 4}
//    Output: 7
//    We can trap “3 units” of water between 3 and 2, “1 unit” on top of bar 2 and “3 units” between 2 and 4.

//    Input: arr[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
//    Output: 6
//    Trap “1 unit” between first 1 and 2, “4 units” between first 2 and 3 and “1 unit” between second last 1 and last 2
//
//    Input: height = [4,2,0,3,2,5]
//    Output: 9

//    https://www.geeksforgeeks.org/trapping-rain-water/

//        approach 1
    fun trappingRainWater(height: IntArray): Int {
        val size = height.size
        var sum = 0
        for (i in 1 until size - 1) {
            var left = height[i]
            for (j in 0 until i) {
                left = Math.max(left, height[j])
            }
            var right = height[i]
            for (j in i + 1 until size) {
                right = Math.max(right, height[j])
            }
            sum += Math.min(left, right) - height[i]
        }
        return sum
    }

    //approach2
    fun trappingRainWater2(height: IntArray): Int {
        val size = height.size
        var sum = 0

        //fill left array
        val leftArray = Array(size){Int.MIN_VALUE}
        leftArray[0] = height[0]
        for (i in 1 until size){
            leftArray[i] = Math.max(leftArray[i - 1], height[i])
        }

        //fill right array
        val rightArray = Array(size){Int.MIN_VALUE}
        rightArray[size - 1] = height[size - 1]
        for (i in size-2 downTo 0){
            rightArray[i] = Math.max(rightArray[i+1], height[i])
        }

        for (i in 1 until size){
            sum += Math.min(leftArray[i], rightArray[i]) - height[i]
        }
        return sum
    }

    //approach3
    fun trappingRainWater3(height: IntArray): Int {
        val size = height.size
        var sum = 0
        var left = 0; var right = size-1
        var left_max = 0; var right_max = 0
        while (left < right){
            if(height[left] < height[right]){
                left_max = Math.max(left_max, height[left])
                if(height[left] > left_max){
                    left_max = height[left]
                }else{
                    sum += left_max - height[left]
                }
                left++
            }else{
                if(height[right] > right_max){
                    right_max = height[right]
                }else{
                    sum += right_max - height[right]
                }
                right--
            }
        }
        return sum
    }

    //############### approach4 in stack problems #######################

    fun separateEvenAndOddNumbers(arr: IntArray){
        var left = 0; var right = arr.size - 1
        while(left < right){
            while (arr[left] % 2 == 0){
                left++
            }
            while (arr[right] % 2 != 0){
                right--
            }
            if(left < right){
                swap(arr, left, right)
            }
        }
        println(arr)
    }
}
