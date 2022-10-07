package DSAlgo

import java.util.*

class Neetcode {
    /**************************************************************************************************************/
    /***** Arrays and Hashing
     ****/
    /**************************************************************************************************************/
    class ArraysAndHashing{
        /*
        Contains Duplicate
        1. Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
        Input: nums = [1,2,3,1]
        Output: true
        Input: nums = [1,2,3,4]
        Output: false
        Input: nums = [1,1,1,3,3,4,3,2,4,2]
        Output: true
        */
        fun containsDuplicate(nums: IntArray): Boolean {
            val set = HashSet<Int>()
            nums.forEach{
                if(set.contains(it)){
                    return true
                }
                set.add(it)
            }
            return false
        }

        /*
        Valid Anagram
        2.Given two strings s and t, return true if t is an anagram of s, and false otherwise.
        An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
        Input: s = "anagram", t = "nagaram"
        Output: true
        Input: s = "rat", t = "car"
        Output: false
        */
        fun isAnagram(s: String, t: String): Boolean {
           if (s.length != t.length) return false
           val visited = IntArray(26)
           for(i in s.indices){
               visited[s[i] - 'a']++
               visited[t[i] - 'a']--
           }
           visited.forEach{
               if(it != 0)
                   return false
           }
           return true
       }
        fun isAnagram2(s: String, t: String): Boolean {
             val map = HashMap<Char, Int>()

             s.forEach{
                 if(map.get(it) == null){
                     map.put(it, 1)
                 }else{
                     map.put(it, map.get(it)!! + 1)
                 }
             }

             t.forEach{
                 if(map.contains(it)){
                     val mapVal = map.get(it)
                     if(mapVal!! <= 1){
                         map.remove(it)
                     }else{
                         map.put(it, mapVal-1)
                     }
                 }else{
                     map.put(it, 1)
                 }
             }

             return map.isEmpty()
         }

        /*
        Two Sum
        Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
        You may assume that each input would have exactly one solution, and you may not use the same element twice.
        You can return the answer in any order.
        Input: nums = [2,7,11,15], target = 9
        Output: [0,1]
        Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
        Input: nums = [3,2,4], target = 6
        Output: [1,2]
        Input: nums = [3,3], target = 6
        Output: [0,1]
        */
        fun twoSum(nums: IntArray, target: Int): IntArray {
            val outputArray = IntArray(2)
            val map:HashMap<Int, Int> = HashMap()
            for(i in nums.indices){
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

        /*
        Group Anagrams
        Given an array of strings strs, group the anagrams together. You can return the answer in any order.
        An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
        Input: strs = ["eat","tea","tan","ate","nat","bat"]
        Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
        Input: strs = [""]
        Output: [[""]]
        Input: strs = ["a"]
        Output: [["a"]]
        */
        fun groupAnagrams(strs: Array<String>): List<List<String>> {
            val map = HashMap<String, ArrayList<String>>()
            strs.forEach {
                val sortedString = it.toCharArray().sorted().joinToString("")
                if(map.containsKey(sortedString)){
                    val list = map.get(sortedString)!!.add(it)
                }else{
                    map.put(sortedString, arrayListOf(it))
                }
            }

            val outPutList = ArrayList<List<String>>()
            map.values.forEach {
                outPutList.add(it)
            }
            return outPutList
        }

        /*
        Top K Frequent Elements
        Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
        Input: nums = [1,1,1,2,2,3], k = 2
        Output: [1,2]
        Input: nums = [1], k = 1
        Output: [1]
        */
        fun topKFrequent(nums: IntArray, k: Int): IntArray {
            val map = HashMap<Int, Int>()
            for (element in nums) {
                if (map.contains(element)) {
                    map.put(element, map[element]!! + 1)
                } else {
                    map.put(element, 1)
                }
            }
            val subMap = map.toList().sortedByDescending { it.second }
            return subMap.subList(0, k).map { it.first }.toIntArray()
        }

        /*
        Product of Array Except Self
        Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
        The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
        You must write an algorithm that runs in O(n) time and without using the division operation.
        Input: nums = [1,2,3,4]
        Output: [24,12,8,6]
        Input: nums = [-1,1,0,-3,3]
        Output: [0,0,9,0,0]

        using division is easy, but this has to be done without division as mentioned in problem statement
        */
        fun productExceptSelf(nums: IntArray):IntArray{
            val n = nums.size
            val leftProductArray = IntArray(n)
            val rightProductArray = IntArray(n)
            val productArray = IntArray(n)

            leftProductArray[0] = 1
            for (i in 1 until n){
                leftProductArray[i] = leftProductArray[i-1] * nums[i-1]
            }

            rightProductArray[n-1] = 1
            for (i in n-2 downTo 0){
                rightProductArray[i] = rightProductArray[i+1] * nums[i + 1]
            }

            for (i in 0 until n){
                productArray[i] = leftProductArray[i] * rightProductArray[i]
            }

            return productArray
        }
        fun productExceptSelfBetterApproach(nums: IntArray): IntArray {
            var productArray = IntArray(nums.size)
            var product = 1
            for(i in nums.indices){
                productArray[i] = product
                product *= nums[i]
            }

            product = 1
            for(i in nums.size-1 downTo 0){
                productArray[i] = productArray[i] * product
                product *= nums[i]
            }
            return productArray
        }

        /*
        Valid Sudoku
        Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
        Each row must contain the digits 1-9 without repetition.
        Each column must contain the digits 1-9 without repetition.
        Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
        Note:A Sudoku board (partially filled) could be valid but is not necessarily solvable.Only the filled cells need to be validated according to the mentioned rules.
        Input: board =
        [["5","3",".",".","7",".",".",".","."]
        ,["6",".",".","1","9","5",".",".","."]
        ,[".","9","8",".",".",".",".","6","."]
        ,["8",".",".",".","6",".",".",".","3"]
        ,["4",".",".","8",".","3",".",".","1"]
        ,["7",".",".",".","2",".",".",".","6"]
        ,[".","6",".",".",".",".","2","8","."]
        ,[".",".",".","4","1","9",".",".","5"]
        ,[".",".",".",".","8",".",".","7","9"]]
        Output: true

        Input: board =
        [["8","3",".",".","7",".",".",".","."]
        ,["6",".",".","1","9","5",".",".","."]
        ,[".","9","8",".",".",".",".","6","."]
        ,["8",".",".",".","6",".",".",".","3"]
        ,["4",".",".","8",".","3",".",".","1"]
        ,["7",".",".",".","2",".",".",".","6"]
        ,[".","6",".",".",".",".","2","8","."]
        ,[".",".",".","4","1","9",".",".","5"]
        ,[".",".",".",".","8",".",".","7","9"]]
        Output: false
        Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
        */
        fun isValidSudoku(board: Array<CharArray>): Boolean {
            val boardLength = 9
            val m = board.size
            val n = board[0].size
            if(m < boardLength || n < boardLength)
                return false

            //all rows check
            for(i in 0 until boardLength){
                val visited = Array(10){false}
                for(j in 0 until boardLength){
                    val item = board[i][j]
                    if (item != '.') {
                        val numItem = item - '0'
                        if (visited[numItem]) {
                            return false
                        }else{
                            visited[numItem] = true
                        }
                    }
                }
            }

            //all columns check
            for(i in 0 until boardLength){
                val visited = Array(10){false}
                for(j in 0 until boardLength){
                    val item = board[j][i]
                    if (item != '.') {
                        val numItem = item - '0'
                        if (visited[numItem]) {
                            return false
                        }else{
                            visited[numItem] = true
                        }
                    }
                }
            }

            //all 3X3 array check
            var p = 0; var q = 0
            while(p < n) {
                val visited = Array(10) { false }
                for (i in p until p+3) {
                    for (j in q until q+3) {
                        val item = board[i][j]
                        if (item != '.') {
                            val numItem = item - '0'
                            if (visited[numItem]) {
                                return false
                            }else{
                                visited[numItem] = true
                            }
                        }
                    }
                }
                q+=3
                if(q == 9) {
                    q = 0
                    p+=3
                }
            }

            return true
        }

        /*
        Encode and Decode Strings
        Design an algorithm to encode a list of strings to a string.
        The encoded string is then sent over the network and is decoded back to the original list of strings.
        Input: ["lint","code","love","you"]
        Output: ["lint","code","love","you"]
        Explanation:
        One possible encode method is: "4#lint4#code4#love3#you"
        Input: ["we", "say", ":", "yes"]
        Output: ["we", "say", ":", "yes"]
        Explanation:
        One possible encode method is: "2#we3#say3#yes"
        */
        fun encode(input:List<String>):String{
            val sb = StringBuilder()
            input.forEach {
                sb.append(it.length).append("#").append(it)
            }
            return sb.toString()
        }
        fun decode(str:String):List<String>{
            val arr = mutableListOf<String>()
            var index = 0
            while(index < str.length){
                val length = str[index].digitToInt()
                index++ //skip length char
                index++ //skip # char
                val itemSb = StringBuilder()
                for (i in index until index+length){
                    itemSb.append(str[i])
                    index++
                }
                arr.add(itemSb.toString())
            }
            return arr
        }

        /*
        Longest Consecutive Sequence
        Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
        You must write an algorithm that runs in O(n) time.
        Input: nums = [100,4,200,1,3,2]
        Output: 4
        Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
        Input: nums = [0,3,7,2,5,8,4,6,0,1]
        Output: 9
        */
        fun longestConsecutive(nums: IntArray): Int {
            val set = HashSet<Int>()
            nums.forEach {
                set.add(it)
            }

            var maxLength = 0
            nums.forEach {
                if(!set.contains(it - 1)){
                    var length = 1
                    var currentItem = it
                    while (set.contains(currentItem + 1)){
                        length++
                        currentItem++
                    }
                    maxLength = Math.max(maxLength, length)
                }
            }
            return maxLength
        }

        /*
        Find Unsorted Sub Array length
        [2,6,4,8,10,9,15] > 6,4,8,10,9 > 5
        [2,1] > 2
        [5,4,3,2,1] > 5
        [1,2,3,4] > 0

         */
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
            return if (start == -1) 0 else end - start + 1
        }

        /*
        Running Median - Unsorted Array
        input = 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        output = 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6
        input = 5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4
        output = 5, 10, 5, 4, 3, 4, 5, 6, 7, 6, 7, 6
         */
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

        /*
        Count maximum number of cars parked at the same time
        Given a 2d array arr[][] with each row representing a pair representing entry and exit time of a car in a parking lot,
        the task is to calculate the maximum number of cars that can be parked at the same time.
        Input: arr[][] = {{1012, 1136}, {1317, 1417}, {1015, 1020}}
        Output: 2
        Explanation:
        1st car entered at 10:12 and exits at 11:36 and 3rd car entered at 10:15 and exits at 10:20.
        Therefore, 1st and 3rd car are present at the same time.
        Input: arr[][] = {{1120, 1159}, {1508, 1529}, {1508, 1527}, {1503, 1600}, {1458, 1629}, {1224, 1313}}
        Output: 4
        Explanation: 2nd, 3rd, 4th and 5th cars are present at the same time.
        */
        fun maxCars(arrivals:IntArray, departures:IntArray):Int{
            val pairArray = Array<Pair<Int, Boolean>?>(2*arrivals.size){null}
            for (i in arrivals.indices){
                pairArray[2*i] = Pair(arrivals[i], true)
                pairArray[2*i+1] = Pair(departures[i], false)
            }
            Arrays.sort(pairArray) { p1, p2 -> p1?.first!! - p2?.first!! }
            var currMax = 0
            var max = 0

            for(i in pairArray.indices){
                if (pairArray[i]!!.second){
                    currMax++
                }else{
                    if (currMax > max) {
                        max = currMax
                    }
                    currMax--
                }
            }
            return max
        }
    }

    /**************************************************************************************************************/
    /***** Two Pointer Approach
    ****/
    /**************************************************************************************************************/
    class TwoPointerApproach{
        /*
        Valid Palindrome
        A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters,
        it reads the same forward and backward. Alphanumeric characters include letters and numbers.
        Given a string s, return true if it is a palindrome, or false otherwise.
        Input: s = "A man, a plan, a canal: Panama"
        Output: true
        Explanation: "amanaplanacanalpanama" is a palindrome.
        Input: s = "race a car"
        Output: false
        Explanation: "raceacar" is not a palindrome.
        Input: s = " "
        Output: true
        Explanation: s is an empty string "" after removing non-alphanumeric characters.
        Since an empty string reads the same forward and backward, it is a palindrome.
        */
        fun isPalindrome(s: String): Boolean {
            val inputSb = StringBuilder()
            val range1 = ' '..'/'
            val range2 = ':'..'@'
            val range3 = '['..'`'
            val range4 = '{'..'~'
            s.forEach {
                 if(!(it in range1 || it in range2 || it in range3 || it in range4)) {
                     inputSb.append(it)
                 }

                 if(!(it in range1 || it in range2 || it in range3 || it in range4)) {
                     inputSb.append(it)
                 }
            }

            val inputString = inputSb.toString().toLowerCase()
            var left = 0; var right = inputString.length - 1
            while (left < right){
                if(!inputString[left].equals(inputString[right])) {
                    return false
                }
                left++;right--
            }
            return true
        }
        fun isPalindromeBetterApproach(s: String): Boolean {
            var left = 0; var right = s.length - 1
            while (left < right){
                if(!Character.isLetterOrDigit(s[left])){
                    left++
                    continue
                }
                if(!Character.isLetterOrDigit(s[right])){
                    right--
                    continue
                }
                if(!s[left].toLowerCase().equals(s[right].toLowerCase())) {
                    return false
                }

                left++
                right--
            }
            return true
        }

        /*
        Two Sum II
        Sorted Array
        Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
        find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2]
        where 1 <= index1 < index2 <= numbers.length.
        Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
        The tests are generated such that there is exactly one solution. You may not use the same element twice.
        Your solution must use only constant extra space.
        Input: numbers = [2,7,11,15], target = 9
        Output: [1,2]
        Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
        Input: numbers = [2,3,4], target = 6
        Output: [1,3]
        Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
        Input: numbers = [-1,0], target = -1
        Output: [1,2]
        Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
        */
        //This can be done using Hashmap for un sorted array
        fun twosSum(numbers: IntArray, target: Int): IntArray {
            val outputArray = IntArray(2)
            var left = 0 ; var right = numbers.size - 1
            while (left < right){
                val sum = numbers[left] + numbers[right]
                if(target == sum) {
                    outputArray[0] = left
                    outputArray[1] = right
                    break
                }else if(target > sum){
                    left++
                }else{
                    right--
                }
            }
            return outputArray
        }

        /*
        3Sum or 3Sum to zero
        Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
        and nums[i] + nums[j] + nums[k] == 0.
        Notice that the solution set must not contain duplicate triplets.
        Input: nums = [-1,0,1,2,-1,-4]
        Output: [[-1,-1,2],[-1,0,1]]
        Explanation:
        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
        The distinct triplets are [-1,0,1] and [-1,-1,2].
        Notice that the order of the output and the order of the triplets does not matter.
        Input: nums = [0,1,1]
        Output: []
        Explanation: The only possible triplet does not sum up to 0.
        Input: nums = [0,0,0]
        Output: [[0,0,0]]
        Explanation: The only possible triplet sums up to 0.
        */
        fun threeSum(numbers: IntArray):List<List<Int>>{
            numbers.sort()
            val target = 0
            val list = mutableListOf<List<Int>>()
            for (i in 0 until numbers.size - 2){
                val currentItem = numbers[i]
                var left = i + 1
                var right = numbers.size - 1
                if(i > 0 && numbers[i] == numbers[i-1]){
                    continue
                }
                while (left < right) {
                    val sum = currentItem + numbers[left] + numbers[right]
                    if(sum == target){
                        list.add(listOf(numbers[left], numbers[right], currentItem))
                        left++
                        while (numbers[left] ==  numbers[left - 1] && left < right){
                            left++
                        }
                    }else if(sum > target){
                        right--
                    }else{
                        left++
                    }
                }
            }
            return list
        }

        /*
        Container with Most Water
        You are given an integer array height of length n.
        There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
        Find two lines that together with the x-axis form a container, such that the container contains the most water.
        Return the maximum amount of water a container can store.
        Notice that you may not slant the container.
        Input: height = [1,8,6,2,5,4,8,3,7]
        Output: 49
        Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
        In this case, the max area of water (blue section) the container can contain is 49.
        Input: height = [1,1]
        Output: 1
        */
        fun maxArea(height: IntArray): Int {
            var left = 0; var right = height.size - 1
            var max = 0
            while(left < right){
                val length = Math.min(height[left], height[right]) * (right-left)
                max = Math.max(max, length)
                if(height[left] < height[right]) {
                    left++
                }else {
                    right--
                }
            }
            return max
        }

        /*
        Trapping Rain Water
        Given n non-negative integers representing an elevation map where the width of each bar is 1,
        compute how much water it can trap after raining.
        Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
        Output: 6
        Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
        In this case, 6 units of rain water (blue section) are being trapped.
        Input: height = [4,2,0,3,2,5]
        Output: 9
        */
        fun trap(height: IntArray): Int {
            var sum = 0
            var left = 0; var right = 0
            var leftMax = 0; var rightMax = 0
            while (left < right){
                if(height[left] < height[right]){
                    if(height[left] > leftMax){
                        leftMax = height[left]
                    }else{
                        sum += leftMax - height[left]
                    }
                    left++
                }else{
                    if(height[right] > rightMax){
                        rightMax = height[right]
                    }else{
                        sum += rightMax - height[right]
                    }
                    right--
                }
            }
            return sum
        }

        /*Separate odd and even numbers in array
        *
         */
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
        private fun swap(arr: IntArray, a: Int, b: Int) {
            val temp = arr[a]
            arr[a] = arr[b]
            arr[b] = temp
        }
    }

    /**************************************************************************************************************/
    /***** Sliding Window
     ****/
    /**************************************************************************************************************/
    class SlidingWindow{
        /*
        Best Time to Buy & Sell Stock
        You are given an array prices where prices[i] is the price of a given stock on the ith day.
        You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
        Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
        Input: prices = [7,1,5,3,6,4]
        Output: 5
        Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
        Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
        Input: prices = [7,6,4,3,1]
        Output: 0
        Explanation: In this case, no transactions are done and the max profit = 0.
        */
        //using max j-1 logic
        fun maxProfit(prices: IntArray): Int {
            var min = Int.MAX_VALUE; var maxProfit = 0
            for(item in prices){
                if(item < min){
                    min = item
                }else{
                    maxProfit = Math.max(maxProfit, item - min)
                }
            }
            return maxProfit
        }
        fun maxProfit2(prices: IntArray): Int {
            var maxProfit = 0
            var left = 0; var right = 1
            while(right < prices.size){
                if(prices[right] > prices[left]){
                    maxProfit = Math.max(maxProfit, prices[right] - prices[left])
                }else{
                    left = right
                }
                right++
            }
            return maxProfit
        }

        /*
        Longest Substring Without Repeating Characters
        Given a string s, find the length of the longest substring without repeating characters.
        Input: s = "abcabcbb"
        Output: 3
        Explanation: The answer is "abc", with the length of 3.
        Input: s = "bbbbb"
        Output: 1
        Explanation: The answer is "b", with the length of 1.
        Input: s = "pwwkew"
        Output: 3
        Explanation: The answer is "wke", with the length of 3.
        Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
        */
        //using visited array
        fun lengthOfLongestSubstring(s: String): Int {
            var result = 0
            val n = s.length
            for(i in 0 until n){
                val visitedArray = BooleanArray(128)
                for(j in i until n){
                    val asciiVal = s.elementAt(j).code
                    if(visitedArray[asciiVal]){
                        break;
                    }else{
                        result = Math.max(result, j-i+1)
                        visitedArray[asciiVal] = true
                    }
                }
                visitedArray[s.elementAt(i).toInt()] = false
            }
            return result
        }
        //using sub string functions
        fun lengthOfLongestSubstring2(s: String): Int {
            var maxLength = 0
            var auxString = ""
            val n = s.length
            for(i in 0 until n){
                val currentItem = s[i]
                if(auxString.contains(currentItem)){
                    auxString = auxString.substring(auxString.indexOf(currentItem) + 1)
                }
                auxString += currentItem
                maxLength = Math.max(maxLength, auxString.length)
            }
            return maxLength
        }
        //using set
        fun lengthOfLongestSubstring3(s: String): Int{
            val set = HashSet<Char>()
            var left = 0
            var maxLength = 0
            for(right in s.indices){
                while (set.contains(s[right])){
                    set.remove(s[left])
                    left++
                }
                maxLength = Math.max(maxLength, right - left + 1)
                set.add(s[right])
            }
            return maxLength
        }

        /*
        Longest Repeating Character Replacement
        You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character.
        You can perform this operation at most k times.
        Return the length of the longest substring containing the same letter you can get after performing the above operations.
        Input: s = "ABAB", k = 2
        Output: 4
        Explanation: Replace the two 'A's with two 'B's or vice versa.
        Input: s = "AABABBA", k = 1
        Output: 4
        Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
        The substring "BBBB" has the longest repeating letters, which is 4.
         */
        fun characterReplacement(s: String, k: Int): Int {
            val arr = IntArray(26)
            var ans = 0
            var max = 0
            var i = 0
            for (j in s.indices) {
                arr[s[j] - 'A']++
                max = Math.max(max, arr[s[j] - 'A'])
                if (j - i + 1 - max > k) {
                    arr[s[i] - 'A']--
                    i++
                }
                ans = Math.max(ans, j - i + 1)
            }
            return ans
        }

        /*
        Permutation in String
        Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
        In other words, return true if one of s1's permutations is the substring of s2.
        Input: s1 = "ab", s2 = "eidbaooo"
        Output: true
        Explanation: s2 contains one permutation of s1 ("ba").
        Input: s1 = "ab", s2 = "eidboaoo"
        Output: false
        */
        fun checkInclusion(s1: String, s2: String): Boolean {
            if(s1.isEmpty())return true
            val m = s1.length; val n = s2.length
            if(m > n)return false
            val visitedArray = IntArray(26)
            for (i in 0 until m){
                visitedArray[s1[i] - 'a']++
                visitedArray[s2[i] - 'a']--
            }
            if(isPermutationMatch(visitedArray)){
                return true
            }

            for(j in m until n){
                visitedArray[s2[j] - 'a']--
                visitedArray[s2[j-m] - 'a']++
                if(isPermutationMatch(visitedArray)){
                    return true
                }
            }
            return false
        }
        private fun isPermutationMatch(visitedArray: IntArray):Boolean{
            visitedArray.forEach {
                if(it != 0)return false
            }
            return true
        }

        /*
        Minimum Window Substring
        Given two strings s and t of lengths m and n respectively,
        return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
        If there is no such substring, return the empty string "".
        The testcases will be generated such that the answer is unique.
        A substring is a contiguous sequence of characters within the string.
        Input: s = "ADOBECODEBANC", t = "ABC"
        Output: "BANC"
        Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
        Input: s = "a", t = "a"
        Output: "a"
        Explanation: The entire string s is the minimum window.
        Input: s = "a", t = "aa"
        Output: ""
        Explanation: Both 'a's from t must be included in the window.
        Since the largest window of s only has one 'a', return empty string.
        */
        fun minWindow(s: String, t: String): String {
            val map: MutableMap<Char, Int> = HashMap()
            var counter = t.length
            var begin = 0
            var end = 0
            var min = Int.MAX_VALUE
            var head = 0
            for (i in 0 until counter) {
                val cnt = map.getOrDefault(t[i], 0)
                map[t[i]] = cnt + 1
            }
            while (end < s.length) {
                val c = s[end]
                val cnt = map.getOrDefault(c, 0)
                if (cnt > 0) {
                    counter--
                }
                map[c] = cnt - 1
                end++
                while (counter == 0) {
                    if (end - begin < min) {
                        min = end - begin
                        head = begin
                    }
                    val ch = s[begin]
                    val count = map.getOrDefault(ch, 0)
                    if (count == 0) {
                        counter++
                    }
                    map[ch] = count + 1
                    begin++
                }
            }
            return if (min == Int.MAX_VALUE) "" else s.substring(head, head + min)
        }

        /*
        Sliding Window Maximum
        You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
        You can only see the k numbers in the window. Each time the sliding window moves right by one position.
        Return the max sliding window.
        Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
        Output: [3,3,5,5,6,7]
        Input: nums = [1], k = 1
        Output: [1]
        */
        fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
            val outputArray = IntArray(nums.size - k + 1)
            for (i in 0..nums.size - k) {
                var max = nums[i]
                for (j in i+1 until i + k) {
                    max = Math.max(max, nums[j])
                }
                outputArray[i] = max
            }
            return outputArray
        }
        fun maxSlidingWindowUsingPriorityQueue(nums: IntArray, k: Int): IntArray {
            val outputArray = IntArray(nums.size - k + 1)
            var outputArrayIndex = 0
    //        val pq = PriorityQueue<Int>(k) //By default pq is min heap
            val pq = PriorityQueue<Int>(k, Collections.reverseOrder()) //For max heap
            var i = 0
            while(i < k){
                pq.add(nums[i])
                i++
            }
            outputArray[outputArrayIndex++] = pq.peek()!!
            pq.remove(nums[0])
            while(i < nums.size){
                pq.add(nums[i])
                outputArray[outputArrayIndex++] = pq.peek()!!
                pq.remove(nums[i-k+1])
                i++
            }
            return outputArray
        }
        fun maxSlidingWindowUsingDeQueue(nums: IntArray, k: Int): IntArray {
            val outputArray = IntArray(nums.size - k + 1)
            var outputArrayIndex = 0
            val dq = ArrayDeque<Int>()
            var i = 0
            while(i < k){
                while(!dq.isEmpty() && nums[i] >= nums[dq.last()]){
                    dq.removeLast()
                }
                dq.addLast(i)
                i++
            }

            while(i < nums.size){
                outputArray[outputArrayIndex++] = nums[dq.first()]
                while(!dq.isEmpty() && dq.first() <= i-k){
                    dq.removeFirst()
                }

                while(!dq.isEmpty() && nums[i] >= nums[dq.last()]){
                    dq.removeLast()
                }
                dq.addLast(i)
                i++
            }

            outputArray[outputArrayIndex] = nums[dq.first()]

            return outputArray
        }

        /*
        Minimum Operations to Reduce X to Zero
        You are given an integer array nums and an integer x.
        In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x.
        Note that this modifies the array for future operations.
        Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.
        Input: nums = [1,1,4,2,3], x = 5
        Output: 2
        Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

        Input: nums = [5,6,7,8,9], x = 4
        Output: -1

        Input: nums = [3,2,20,1,1,3], x = 10
        Output: 5

        [5207,5594,477,6938,8010,7606,2356,6349,3970,751,5997,6114,9903,3859,6900,7722,2378,1996,
        8902,228,4461,90,7321,7893,4879,9987,1146,8177,1073,7254,5088,402,4266,6443,3084,1403,5357,
        2565,3470,3639,9468,8932,3119,5839,8008,2712,2735,825,4236,3703,2711,530,9630,1521,2174,5027,
        4833,3483,445,8300,3194,8784,279,3097,1491,9864,4992,6164,2043,5364,9192,9649,9944,7230,7224,
        585,3722,5628,4833,8379,3967,5649,2554,5828,4331,3547,7847,5433,3394,4968,9983,3540,9224,6216,
        9665,8070,31,3555,4198,2626,9553,9724,4503,1951,9980,3975,6025,8928,2952,911,3674,6620,3745,6548,
        4985,5206,5777,1908,6029,2322,2626,2188,5639], x = 565610
        Output: -1
        */
        fun minimumOperationsToZero(nums: IntArray, x: Int):Int{
            var sum = 0
            nums.forEach{
                sum += it
            }
            val remainingSum = sum - x
            var left = 0; var right = 0
            var currentSum = 0; var maxLength = -1
            while(right < nums.size){
                currentSum += nums[right]
                while (left <= right &&  currentSum > remainingSum){
                    currentSum -= nums[left]
                    left++
                }
                if(currentSum == remainingSum){
                    maxLength = Math.max(maxLength, right - left + 1)
                }
                right++
            }
            return if(maxLength == -1)
                -1
            else
                nums.size - maxLength
        }

        /*
        Find all Anagrams
        Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
        An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
        typically using all the original letters exactly once.
        Input: s = "cbaebabacd", p = "abc"
        Output: [0,6]
        Explanation:
        The substring with start index = 0 is "cba", which is an anagram of "abc".
        The substring with start index = 6 is "bac", which is an anagram of "abc".

        Input: s = "abab", p = "ab"
        Output: [0,1,2]
        Explanation:
        The substring with start index = 0 is "ab", which is an anagram of "ab".
        The substring with start index = 1 is "ba", which is an anagram of "ab".
        The substring with start index = 2 is "ab", which is an anagram of "ab".
        */
        fun findAnagramsSlidingWindow(s:String, p:String): List<Int> {
            if (p.length > s.length) return emptyList()
            val result = mutableListOf<Int>()
            val visited = IntArray(26)
            var left = 0
            var right = p.length - 1

            //fill initial s items as visited to p.length - 1
            for(i in 0 until right){
                visited[s[i] - 'a']++
            }

            while (right < s.length){
                visited[s[right] - 'a']++
                if(isAnagram(visited.clone(), p)){
                    result.add(left)
                }
                visited[s[left] - 'a']--
                right++
                left++
            }
            return result
        }
        private fun isAnagram(visitedArray: IntArray, p: String): Boolean {
            for(i in p.indices){
                visitedArray[p[i] - 'a']--
                if(visitedArray[p[i] - 'a'] < 0)
                    return false
            }
            return true
        }

        /*
        Shortest Unsorted Continuous Subarray
        Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order,
        then the whole array will be sorted in ascending order.
        Return the shortest such subarray and output its length.
        Input: nums = [2,6,4,8,10,9,15]
        Output: 5
        Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
        Input: nums = [1,2,3,4]
        Output: 0
        Input: nums = [1]
        Output: 0
        */
        fun findUnsortedSubArray(nums: IntArray): Int {
            val len = nums.size
            var min = Int.MAX_VALUE; var max = Int.MIN_VALUE
            var start = -1; var end = -1
            for (i in 0 until len) {
                max = Math.max(max, nums[i]) //from left to right, search the current max
                min = Math.min(min, nums[len - i - 1]) //from right to left, search the current min
                if (nums[i] < max)
                    end = i
                if (nums[len - i - 1] > min)
                    start = len - i - 1
            }
            return if (start == -1) 0 else end - start + 1
        }
    }

    /**************************************************************************************************************/
    /***** Stacks
     ****/
    /**************************************************************************************************************/
    class Stacks{
        /*
        Valid Parentheses
        Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
        An input string is valid if:
        Open brackets must be closed by the same type of brackets.
        Open brackets must be closed in the correct order.
        Every close bracket has a corresponding open bracket of the same type.
        Input: s = "()"
        Output: true
        Input: s = "()[]{}"
        Output: true
        Input: s = "(]"
        Output: false
        */
        fun isValid(s: String): Boolean {
            val stack = Stack<Char>()
            s.forEach {
                if(it == '(' || it == '{' || it == '['){
                    stack.push(it)
                }else if(it == ')' || it == '}' || it == ']'){
                    if(stack.isEmpty()){
                        return false
                    }else{
                        if(!isPairMatching(stack.pop(), it)){
                            return false
                        }
                    }
                }
            }
            return stack.isEmpty()
        }
        private fun isPairMatching(symbol1: Char, symbol2: Char): Boolean {
            return (symbol1 == '(' && symbol2 == ')') || (symbol1 == '{' && symbol2 == '}') || (symbol1 == '[' && symbol2 == ']')
        }

        /*
        Min Stack
        Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
        Implement the MinStack class:
        MinStack() initializes the stack object.
        void push(int val) pushes the element val onto the stack.
        void pop() removes the element on the top of the stack.
        int top() gets the top element of the stack.
        int getMin() retrieves the minimum element in the stack.
        You must implement a solution with O(1) time complexity for each function.
        Input
        ["MinStack","push","push","push","getMin","pop","top","getMin"]
        [[],[-2],[0],[-3],[],[],[],[]]
        Output
        [null,null,null,null,-3,null,0,-2]
        Explanation
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin(); // return -3
        minStack.pop();
        minStack.top();    // return 0
        minStack.getMin(); // return -2
        */
        class MinStack() {
            var top: MinStackNode? = null
            fun push(`val`: Int) {
                val min: Int = top?.min?.let { if (`val` < it) `val` else it } ?: `val`
                val node = MinStackNode(`val`, min)
                node.next = top
                top = node
            }

            fun pop() {
                top = top?.next
            }

            fun top(): Int {
                return top?.value ?: throw IllegalStateException("Stack is empty.")
            }

            fun getMin(): Int {
                return top?.min ?: throw IllegalStateException("Stack is empty.")
            }
        }
        data class MinStackNode(val value: Int, val min: Int,var next: MinStackNode? = null)

        /*
        Evaluate Reverse Polish Notation
        Evaluate the value of an arithmetic expression in Reverse Polish Notation.
        Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
        Note that division between two integers should truncate toward zero.
        It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result,
        and there will not be any division by zero operation.
        Input: tokens = ["2","1","+","3","*"]
        Output: 9
        Explanation: ((2 + 1) * 3) = 9
        Input: tokens = ["4","13","5","/","+"]
        Output: 6
        Explanation: (4 + (13 / 5)) = 6
        Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
        Output: 22
        Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
        = ((10 * (6 / (12 * -11))) + 17) + 5
        = ((10 * (6 / -132)) + 17) + 5
        = ((10 * 0) + 17) + 5
        = (0 + 17) + 5
        = 17 + 5 = 22
        */
        fun evalRPN(tokens: Array<String>): Int {
            val stack = Stack<String>()
            fun isOperator(str:String):Boolean{
                return str == "+" || str == "-" || str == "*" ||  str == "/"
            }
            tokens.forEach{
                if(!isOperator(it)){
                    stack.push(it)
                }else{
                    val pop1 = stack.pop()!!.toInt()
                    val pop2 = stack.pop()!!.toInt()
                    when(it){
                        "+" -> stack.push((pop1 + pop2).toString())
                        "-" -> stack.push((pop2 - pop1).toString())
                        "*" -> stack.push((pop2 * pop1).toString())
                        "/" -> stack.push((pop2 / pop1).toString())
                    }
                }
            }

            return if(!stack.isEmpty()){
                stack.pop().toInt()
            }else{
                -1
            }
        }

        /*
        Generate Parentheses
        Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
        Example 1:
        Input: n = 3
        Output: ["((()))","(()())","(())()","()(())","()()()"]
        Example 2:
        Input: n = 1
        Output: ["()"]
        */
        var res: MutableList<String> = mutableListOf()
        fun generateParenthesis(n: Int): List<String> {
            backtrack(1, 0, n, "(")
            return res
        }
        private fun backtrack(l:Int, r:Int, n:Int, str:String) {
            if(str.length == n * 2){
                res.add(str)
                return
            }
            if(l < n){
                backtrack(l + 1, r, n, "$str(")
            }
            if(r < l){
                backtrack(l, r + 1, n, "$str)")
            }
        }

        /*
        Daily Temperatures
        Given an array of integers temperatures represents the daily temperatures,
        return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
        If there is no future day for which this is possible, keep answer[i] == 0 instead.
        Input: temperatures = [73,74,75,71,69,72,76,73]
        Output: [1,1,4,2,1,1,0,0]
        Input: temperatures = [30,40,50,60]
        Output: [1,1,1,0]
        Input: temperatures = [30,60,90]
        Output: [1,1,0]
        */
        fun dailyTemperatures(temperatures: IntArray): IntArray {
            val outputArray = IntArray(temperatures.size)
            val stack = Stack<Int>()
            for(i in temperatures.size-1 downTo 0){
                var output = 0
                while(!stack.isEmpty()){
                    val peek = stack.peek()
                    if(temperatures[peek] > temperatures[i]){
                        output = peek - i
                        break
                    }else{
                        stack.pop()
                    }
                }
                outputArray[i] = output
                stack.push(i)
            }
            return outputArray
        }

        /*
        Car Fleet
        There are n cars going to the same destination along a one-lane road. The destination is target miles away.
        You are given two integer array position and speed, both of length n,
        where position[i] is the position of the ith car and speed[i] is the speed of the ith car (in miles per hour).
        A car can never pass another car ahead of it, but it can catch up to it and drive bumper to bumper at the same speed.
        The faster car will slow down to match the slower car's speed.
        The distance between these two cars is ignored (i.e., they are assumed to have the same position).
        A car fleet is some non-empty set of cars driving at the same position and same speed.
        Note that a single car is also a car fleet.
        If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
        Return the number of car fleets that will arrive at the destination.
        Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
        Output: 3
        Explanation:
        The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12.
        The car starting at 0 does not catch up to any other car, so it is a fleet by itself.
        The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6.
        The fleet moves at speed 1 until it reaches target.
        Note that no other cars meet these fleets before the destination, so the answer is 3.
        Input: target = 10, position = [3], speed = [3]
        Output: 1
        Explanation: There is only one car, hence there is only one fleet.
        Input: target = 100, position = [0,2,4], speed = [4,2,1]
        Output: 1
        Explanation:
        The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The fleet moves at speed 2.
        Then, the fleet (speed 2) and the car starting at 4 (speed 1) become one fleet,
        meeting each other at 6. The fleet moves at speed 1 until it reaches target.
        */
        fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
            val carsInfo = Array(position.size){IntArray(2)}
            for(i in position.indices){
                carsInfo[i][0] = position[i]
                carsInfo[i][1] = speed[i]
            }

            // For Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
            //combined array = [[10,2],[8,4],[0,1],[5,1],[3,3]]
            Arrays.sort(carsInfo) { x, y -> y[0] - x[0] }
            //sorting -> 2-10=-8, 4-8=-4, 1-5=-4, 3-3=0, 1-0=1
            //After sort combined array = [[10,2],[8,4],[5,1],[3,3],[0,1]]

            val stack = Stack<Int>()
            stack.push(0)
            for (i in 1 until carsInfo.size) {
                val peek = stack.peek()
                if (((target - carsInfo[peek][0]).toFloat() / carsInfo[peek][1]) < ((target - carsInfo[i][0]).toFloat() / carsInfo[i][1])) {
                    stack.push(i)
                }
            }
            return stack.size
        }

        /*
        Largest Rectangle in Histogram
        https://leetcode.com/problems/largest-rectangle-in-histogram/ - for picture explanation
        Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
        return the area of the largest rectangle in the histogram.
        Input: heights = [2,1,5,6,2,3]
        Output: 10
        Explanation: The above is a histogram where width of each bar is 1.
        The largest rectangle is shown in the red area, which has an area = 10 units.
        Input: heights = [2,4]
        Output: 4
        */
        fun largestRectangleArea(heights: IntArray): Int {
            val n = heights.size
            val stack = Stack<Int>()
            var maxArea = 0
            var i = 0
            while(i < n){
                if(stack.isEmpty() || heights[i] > heights[stack.peek()]){
                    stack.push(i++)
                }else{
                    maxArea = getMaxArea(heights, stack, maxArea, i)
                }
            }

            while(!stack.isEmpty()){
                maxArea = getMaxArea(heights, stack, maxArea, i)
            }

            return maxArea
        }
        private fun getMaxArea(heights: IntArray, stack:Stack<Int>, maxArea:Int, i:Int):Int{
            val peek = stack.peek()
            stack.pop()
            val updatedIndex = if(stack.isEmpty()) i else i - stack.peek() - 1
            val area = heights[peek] *  updatedIndex
            return if(area > maxArea)
                area
            else
                maxArea
        }

        /*
        Reverse the Stack in place
        stack = {1, 2, 3, 4, 5} > {5, 4, 3, 2, 1}
        */
        fun reverseTheStack(stack:Stack<Int>){
            if(stack.isEmpty())
                return
            val data = stack.pop()
            reverseTheStack(stack)
            insertAtBottom(stack, data)
        }
        private fun insertAtBottom(stack: Stack<Int>, data: Int) {
           if(stack.isEmpty()){
               stack.push(data)
               return
           }
           val temp = stack.pop()
           insertAtBottom(stack, data)
           stack.push(temp)
        }

        /*
        Find the Middle of the Stack
        input : stack = {1, 2, 3, 4, 5}
        outoput: 3
        */
        fun middleOfStack(stack: Stack<Int>):Int{
            return stackMiddle(stack, stack.size, 0)
        }
        private fun stackMiddle(stack: Stack<Int>, size:Int, currentIndex:Int):Int{
            if(currentIndex == size/2){
                return stack.pop()
            }
            stack.pop()
            return stackMiddle(stack, size, currentIndex + 1)
        }

        /*
        Delete Middle of the Stack
        stack = {1, 2, 3, 4, 5} > {1, 2, 4, 5}
        */
        fun deleteMiddleOfStack(stack: Stack<Int>){
            deleteMidStack(stack, stack.size, 0)
        }
        private fun deleteMidStack(stack: Stack<Int>, size:Int, currentIndex:Int){
            if(stack.isEmpty() || currentIndex == size){
                return
            }
            val poppedItem = stack.pop()
            deleteMidStack(stack, size, currentIndex + 1)
            if(currentIndex != size/2){
                stack.push(poppedItem)
            }
        }

        /*
        The Stock Span Problem
        A financial problem where we have a series of n daily price quotes for a stock and
        we need to calculate the span of the stock’s price for all n days.
        The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days just before the given day,
        for which the price of the stock on the current day is less than its price on the given day.
        Input: N = 7, price[] = [100 80 60 70 60 75 85]
        Output: 1 1 1 2 1 4 6
        Explanation: Traversing the given input span for 100 will be 1, 80 is smaller than 100 so the span is 1,
        60 is smaller than 80 so the span is 1, 70 is greater than 60 so the span is 2 and so on. Hence the output will be 1 1 1 2 1 4 6.

        Input: N = 6, price[] = [10 4 5 90 120 80]
        Output:1 1 2 4 5 1
        Explanation: Traversing the given input span for 10 will be 1, 4 is smaller than 10 so the span will be 1,
        5 is greater than 4 so the span will be 2 and so on. Hence, the output will be 1 1 2 4 5 1.
        */
        fun findingSpans(arr:IntArray):IntArray{
            val spans = IntArray(arr.size)
            val stack = Stack<Int>()
            for (i in spans.indices){
                while (!stack.isEmpty() && arr[i] > arr[stack.peek()]){
                    stack.pop()
                }
                var p = -1
                if(!stack.isEmpty()){
                   p = stack.peek()
                }
                spans[i] = i - p
                stack.push(i)
            }
            return spans
        }

        /*
        Next Greater Element (NGE) for every element in given Array
        The Next greater Element for an element x is the first greater element on the right side of x in the array.
        Elements for which no greater element exist, consider the next greater element as -1.
        Input: arr[] = [ 4 , 5 , 2 , 25 ]
        Output:
        4      –>   5
        5      –>   25
        2      –>   25
        25     –>   -1
        Explanation: except 25 every element has an element greater than them present on the right side

        Input: arr[] = [ 13 , 7, 6 , 12 ]
        Output:
        13      –>    -1
        7       –>     12
        6       –>     12
        12      –>     -1
        Explanation: 13 and 12 don’t have any element greater than them present on the right side
        */
        /*
        //13, 7, 6, 12 InCorrect order output
        // output
        //    6 -> 12
        //    7 -> 12
        //    12 -> -1
        //    13 -> -1
         */
        private fun nextGreatestElement(arr: IntArray) {
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
        /*
        //13, 7, 6, 12  Correct order output
        // output
        //    13 -> -1
        //    7 -> 12
        //    6 -> 12
        //    12 -> -1
        */
        private fun nextGreatestElementCorrectOrder(arr: IntArray){
            val size = arr.size
            val outputArray = Array<Int>(size){0}
            val stack = Stack<Int>()
            for (i in size-1 downTo 0){
                while (stack.isNotEmpty() && arr[i] > stack.peek()) {
                    stack.pop()
                }
                outputArray[i] = if(stack.isEmpty()) -1 else stack.peek()
                stack.push(arr[i])
            }

            for (i in outputArray.indices){
                println("${arr[i]}'s next greatest element is ${outputArray[i]}")
            }
        }

        /*
        132 Pattern
        Given an array of n integers nums, a 132 pattern is a subsequence of three integers
        nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
        Return true if there is a 132 pattern in nums, otherwise, return false.
        Input: nums = [1,2,3,4]
        Output: false
        Explanation: There is no 132 pattern in the sequence.
        Input: nums = [3,1,4,2]
        Output: true
        Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
        Input: nums = [-1,3,2,0]
        Output: true
        Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
        */
        fun find132pattern(nums: IntArray): Boolean {
            val size = nums.size
            var thirdElement = Int.MIN_VALUE
            val stack = Stack<Int>()
            for (i in size-1 downTo 0){
                if(nums[i] < thirdElement)
                    return true

                while (stack.isNotEmpty() && nums[i] > stack.peek()) {
                    thirdElement = stack.pop()
                }
                stack.push(nums[i])
            }
            return false
        }
    }

    /**************************************************************************************************************/
    /***** Binary Search
     ****/
    /**************************************************************************************************************/
    class BinarySearch{
        /** Binary Search
        Given an array of integers nums which is sorted in ascending order, and an integer target,
        write a function to search target in nums.
        If target exists, then return its index. Otherwise, return -1.
        You must write an algorithm with O(log n) runtime complexity.
        Input: nums = [-1,0,3,5,9,12], target = 9
        Output: 4
        Explanation: 9 exists in nums and its index is 4
        Input: nums = [-1,0,3,5,9,12], target = 2
        Output: -1
        Explanation: 2 does not exist in nums so return -1
         */
        fun binarySearch(nums: IntArray, target: Int): Int {
            var left = 0; var right = nums.size - 1
            while(left <= right){
                val mid = (left + right) / 2
                if(nums[mid] == target)
                    return mid
                else if(nums[mid] > target){
                    right = mid - 1
                }else if(nums[mid] < target){
                    left = mid + 1
                }
            }
            return -1
        }

        /*
        Search a 2D Matrix
        Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
        This matrix has the following properties:
        Integers in each row are sorted from left to right.
        The first integer of each row is greater than the last integer of the previous row.
        Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
        Output: true
        Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
        Output: false
         */
        fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
            if (target < matrix[0][0])
                return false
            val m = matrix.size
            val n: Int = matrix[0].size
            var i = 0 ; val j = n - 1

            /*Compare last element of each row with the target to identify the row in which the targetnumber might exist */
            while (i < m && matrix[i][j] < target) {
                i++
            }

            //If target is greater than last element then return false
            return if (i == m)
                false
            else //else perform a binary search in the obtained row
                binarySearchBoolean(matrix[i], target)
        }
        fun binarySearchBoolean(nums: IntArray, target: Int): Boolean {
            var left = 0; var right = nums.size - 1
            while(left <= right){
                val mid = (left + right) / 2
                if(nums[mid] == target)
                    return true
                else if(nums[mid] > target){
                    right = mid - 1
                }else if(nums[mid] < target){
                    left = mid + 1
                }
            }
            return false
        }

        /*
        Search in Rotated Sorted Array
        There is an integer array nums sorted in ascending order (with distinct values).
        Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
        such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
        For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2]
        Given the array nums after the possible rotation and an integer target,
        return the index of target if it is in nums, or -1 if it is not in nums.
        You must write an algorithm with O(log n) runtime complexity.
        Input: nums = [4,5,6,7,0,1,2], target = 0
        Output: 4
        Input: nums = [4,5,6,7,0,1,2], target = 3
        Output: -1
        Input: nums = [1], target = 0
        Output: -1
        */
        fun search(nums: IntArray, target: Int): Int {
            val minIndex = minIndex(nums)
            if(minIndex == -1){
                return binarySearch(nums, 0, nums.size-1, target)
            }
            val leftSearch = binarySearch(nums, 0, minIndex - 1, target)
            val rightSearch = binarySearch(nums, minIndex, nums.size - 1, target)
            return if (leftSearch != -1) {
                leftSearch
            } else if (rightSearch != -1) {
                rightSearch
            } else {
                -1
            }
        }
        fun minIndex(nums: IntArray): Int {
            for (i in 1 until nums.size){
                if(nums[i] < nums[i - 1]){
                    return i
                }
            }
            return -1
        }
        fun binarySearch(nums: IntArray, start: Int, end: Int, target: Int): Int {
            var left = start; var right = end
            while(left <= right){
                val mid = (left + right) / 2
                if(nums[mid] == target)
                    return mid
                else if(nums[mid] > target){
                    right = mid - 1
                }else if(nums[mid] < target){
                    left = mid + 1
                }
            }
            return -1
        }

        /*
        Find Minimum in Rotated Sorted Array
        Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example,
        the array nums = [0,1,2,4,5,6,7] might become:
        [4,5,6,7,0,1,2] if it was rotated 4 times.
        [0,1,2,4,5,6,7] if it was rotated 7 times.
        Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1],
        a[0], a[1], a[2], ..., a[n-2]].
        Given the sorted rotated array nums of unique elements, return the minimum element of this array.
        You must write an algorithm that runs in O(log n) time.
        Input: nums = [3,4,5,1,2]
        Output: 1
        Explanation: The original array was [1,2,3,4,5] rotated 3 times.
        Input: nums = [4,5,6,7,0,1,2]
        Output: 0
        Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
        Input: nums = [11,13,15,17]
        Output: 11
        Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
        */
        fun findMin(nums: IntArray): Int {
            var l = 0; var r = nums.size - 1

            while (l <= r) {
                if (nums[l] <= nums[r]) {
                    return nums[l]
                }

                val mid = (l + r) / 2
                if (nums[mid] >= nums[l]) {
                    l = mid + 1
                } else {
                    r = mid
                }
            }
            return 0
        }

        /*
        Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
        The overall run time complexity should be O(log (m+n)).
        Input: nums1 = [1,3], nums2 = [2]
        Output: 2.00000
        Explanation: merged array = [1,2,3] and median is 2.
        Input: nums1 = [1,2], nums2 = [3,4]
        Output: 2.50000
        Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
        */
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

        /*
        Koko Eating Bananas
        Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
        The guards have gone and will come back in h hours.
        Koko can decide her bananas-per-hour eating speed of k. Each hour,
        she chooses some pile of bananas and eats k bananas from that pile.
        If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
        Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
        Return the minimum integer k such that she can eat all the bananas within h hours.
        Input: piles = [3,6,7,11], h = 8
        Output: 4
        Input: piles = [30,11,23,4,20], h = 5
        Output: 30
        Input: piles = [30,11,23,4,20], h = 6
        Output: 23
         */
        fun minEatingSpeed(piles: IntArray, h: Int): Int {
            var high = 0

            //get max
            for (p in piles) {
                high = Math.max(high, p)
            }

            //if hours is equal to piles length, then return max
            if (h == piles.size) {
                return high
            }
            var low = 1
            var res = high
            while (low <= high) {
                val mid = low + (high - low) / 2
                var hours:Long = 0
                //get total hours for particular mid(bananas per hour)
                for (i in piles.indices) {
                    hours += Math.ceil(piles[i].toDouble() / mid).toInt()
                }
                if (hours <= h) {
                    res = Math.min(res, mid)
                    high = mid - 1
                } else if (hours > h) {
                    low = mid + 1
                }
            }
            return res
        }

        /*
        Time Based Key-Value Store
        Design a time-based key-value data structure that can store multiple values for the same key at
        different time stamps and retrieve the key's value at a certain timestamp.
        Implement the TimeMap class: TimeMap() Initializes the object of the data structure.
        void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
        String get(String key, int timestamp) Returns a value such that set was called previously,
        with timestamp_prev <= timestamp. If there are multiple such values,
        it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".

        Example 1:
        Input
        ["TimeMap", "set", "get", "get", "set", "get", "get"]
        [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
        Output
        [null, null, "bar", "bar", null, "bar2", "bar2"]

        Explanation
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
        timeMap.get("foo", 1);         // return "bar"
        timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
        timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
        timeMap.get("foo", 4);         // return "bar2"
        timeMap.get("foo", 5);         // return "bar2"
        */
        class TimeMap() {
            private val map = HashMap<String, ArrayList<TimeMapStore>>()

            fun set(key: String, value: String, timestamp: Int) {
                val store = TimeMapStore(key, value, timestamp)
                map.putIfAbsent(key, ArrayList())
                map.get(key)?.add(store)
            }

            fun get(key: String, timestamp: Int): String {
                val list = map.get(key)
                if(list == null || list.get(0).timestamp > timestamp)
                    return ""
                var left = 0; var right = list.size - 1
                while(left <= right){
                    val mid = (left + right)/2
                    val currentStore = list[mid]
                    if(currentStore.timestamp.equals(timestamp)){
                        return currentStore.value
                    }else if(currentStore.timestamp < timestamp){
                        left = mid + 1
                    }else{
                        right = mid - 1
                    }
                }
                return list[(left + right) / 2].value
            }
        }
        internal data class TimeMapStore(val key: String, val value: String, val timestamp: Int)
    }
}