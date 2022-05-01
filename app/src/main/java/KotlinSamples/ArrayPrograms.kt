package KotlinSamples

import java.math.BigInteger
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ArrayPrograms {
    init {
        codingChallengePathFunction()

        val x = 1..5
        println("the X is $x")

        maxOfTwoNums(5,10)

        val num = 1000000
        var fibnum = fibonacciRec(num, BigInteger("1"), BigInteger("0"))
        println("fibonacci number of ${num} is $fibnum")

        findMedianSortedArrays(nums1 =  intArrayOf(1,2), nums2 = intArrayOf(3,4))
        println("result is ${makeAPalindrome("mdaam")}")

        subStringProblem()
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

    private fun codingChallengePathFunction(){
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

    fun makeAPalindrome(input:String):String{
        val map = mutableMapOf<Char, Int>()
//        var map = HashMap<Char, Int>()
        for (char in input){
            if (map.containsKey(char)){
                map.put(char, map.get(char)!! + 1)
            }else{
                map.put(char, 1)
            }
        }
        var oddCount = 0
        var oddChar:Char = '\u0000'
        map.forEach{
            if(it.value %2 != 0){
                oddCount++
                oddChar = it.key
            }
        }

        if(oddCount > 1 || (oddCount == 1 && input.length %2 == 0)){
            return "Not a Palindrome"
        }


        var leftPart = ""; var rightPart = ""
        map.forEach{
            var i = 0
            var str = ""
            while (i < it.value/2){
                str += it.key
                i++
            }
            leftPart = leftPart + str
            rightPart = str + rightPart
        }

        return if (oddCount == 1)
            leftPart + oddChar + rightPart
        else
            leftPart + rightPart
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

    private fun areDistinct(inputString:String, i:Int, j:Int) : Boolean{
        var counter = 0
        val visited = BooleanArray(26)
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
}