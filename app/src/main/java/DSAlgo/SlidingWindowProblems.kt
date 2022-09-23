package DSAlgo

import java.util.ArrayDeque

class SlidingWindowProblems {
    init {

    }

//    Input: prices = [7,1,5,3,6,4]
//    Output: 5
//    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

//    Input: prices = [7,6,4,3,1]
//    Output: 0
//    Explanation: In this case, no transactions are done and the max profit = 0.
    fun maxProfit(prices: IntArray): Int {
        var left = 0; var right = prices.size - 1
        var maxProfit = 0
        while(left < right){
            val profit = prices[right] - prices[left]
            maxProfit = Math.max(profit, maxProfit)
            val nextProfitLeft = prices[right] - prices[left + 1]
            val nextProfitRight = prices[right-1] - prices[left]
            if(nextProfitRight > nextProfitLeft && nextProfitRight > maxProfit){
                right--
            }else{
                left++
            }

        }
        return maxProfit
    }

    //abcbde > cbde > 4
    //pwwkew > wke > 3
    fun longestSubStringWithoutDuplicate(s:String):Int{
        var result = 0
        val n = s.length
        for(i in 0 until n){
            var visitedArray = BooleanArray(128)
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

    fun longestSubStringWithoutDuplicateAnotherApproach(s: String): Int {
        var test = ""
        var maxLength = 0
        for (char in s){
            val current: String = char.toString()
            if(test.contains(current)){
                test = test.substring(test.indexOf(current) + 1)
            }

            test += current
            maxLength = Math.max(test.length, maxLength)
        }
        return maxLength
    }

    fun longestSubStringSlidingWindow(s:String):Int{
        val chars = IntArray(128)
        var left = 0
        var right = 0
        var res = 0
        while (right < s.length) {
            val r = s[right]
            chars[r - 'a']++
            while (chars[r - 'a'] > 1) {
                val l = s[left]
                chars[l - 'a']--
                left++
            }
            res = Math.max(res, right - left + 1)
            right++
        }
        return res
    }

//    Input: s = "ABAB", k = 2
//    Output: 4
//    Explanation: Replace the two 'A's with two 'B's or vice versa.
//
//    Input: s = "AABABBA", k = 1
//    Output: 4
//    Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
//    The substring "BBBB" has the longest repeating letters, which is 4
    fun characterReplacement(s: String, k: Int): Int {
        val map = HashMap<Char, Int>()
        var l = 0; var r = 0; var max = 0; var result = 0
        for(item in s){
            if(map.contains(item)){
                map.put(item, map.get(item)!! + 1)
            }else{
                map.put(item, 1)
            }
            max = Math.max(max, map.get(item)!!)
            if(r - l + 1 - max > k){
                val lItem = s[l]
                map.put(item, map.get(item)!! - 1)
                l++
            }
            result = Math.max(result, r - l + 1)
            r++
        }
        return result
    }

//    Input: s1 = "ab", s2 = "eidbaooo"
//    Output: true
//    Explanation: s2 contains one permutation of s1 ("ba").
//
//    Input: s1 = "ab", s2 = "eidboaoo"
//    Output: false
//
//    "hello"
//    "ooolleoooleh"
//    false
//
//    "adc"
//    "dcda"
//    true
//
//    "ab"
//    "a"
//    false
    fun checkInclusionMyApproach(s1: String, s2: String): Boolean {
        if(s2.length < s1.length)return false
        var map = resetAndGetMap(s1)
        for(i in 0..s2.length - s1.length){
            if(map.contains(s2[i])){
                for(j in i until (i + s1.length)){
                    if(map.contains(s2[j])){
                        val itemValue = map.get(s2[j])!!
                        if(itemValue == 1)
                            map.remove(s2[j])
                        else
                            map.put(s2[j], itemValue - 1)
                    }
                }
                if(map.size == 0)
                    return true
                else
                    map = resetAndGetMap(s1)
            }
        }
        return false
    }

    private fun resetAndGetMap(s1: String):HashMap<Char, Int>{
        val map = HashMap<Char, Int>()
        for(item in s1){
            if(!map.contains(item))
                map.put(item, 1)
            else
                map.put(item, map.get(item)!! + 1)
        }
        return map
    }

    fun checkInclusionSlidingWindow(s1: String, s2: String): Boolean {
        // O(n) time where n is the length of s2
        val m = s1.length
        val n = s2.length
        val counter = IntArray(26) // O(1) space
        if (m == 0) return true
        if (m > n) return false

        // check first m characters in both s1 and s2
        for (i in 0 until m) {
            counter[s1[i] - 'a'] += 1
            counter[s2[i] - 'a'] -= 1
        }
        if (isPermutation(counter)) return true
        for (i in m until n) {
            counter[s2[i] - 'a'] -= 1
            counter[s2[i - m] - 'a'] += 1
            if (isPermutation(counter)) return true
        }
        return false
    }

    private fun isPermutation(counter: IntArray): Boolean {
        for (count in counter) {
            if (count != 0) return false
        }
        return true
    }

    fun minWindow(s: String, t: String): String? {
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

//    Minimum Operations to Reduce X to Zero
//    Input: nums = [1,1,4,2,3], x = 5
//    Output: 2
//    Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

//    Input: nums = [5,6,7,8,9], x = 4
//    Output: -1

//    Input: nums = [3,2,20,1,1,3], x = 10
//    Output: 5

//    [5207,5594,477,6938,8010,7606,2356,6349,3970,751,5997,6114,9903,3859,6900,7722,2378,1996,
//    8902,228,4461,90,7321,7893,4879,9987,1146,8177,1073,7254,5088,402,4266,6443,3084,1403,5357,
//    2565,3470,3639,9468,8932,3119,5839,8008,2712,2735,825,4236,3703,2711,530,9630,1521,2174,5027,
//    4833,3483,445,8300,3194,8784,279,3097,1491,9864,4992,6164,2043,5364,9192,9649,9944,7230,7224,
//    585,3722,5628,4833,8379,3967,5649,2554,5828,4331,3547,7847,5433,3394,4968,9983,3540,9224,6216,
//    9665,8070,31,3555,4198,2626,9553,9724,4503,1951,9980,3975,6025,8928,2952,911,3674,6620,3745,6548,
//    4985,5206,5777,1908,6029,2322,2626,2188,5639], x = 565610
//    Output: -1
    fun minimumOperationsToZero(nums: IntArray, x: Int):Int{
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

    //    Input: s = "cbaebabacd", p = "abc"
//    Output: [0,6]
//    Explanation:
//    The substring with start index = 0 is "cba", which is an anagram of "abc".
//    The substring with start index = 6 is "bac", which is an anagram of "abc".
//
//    Input: s = "abab", p = "ab"
//    Output: [0,1,2]
//    Explanation:
//    The substring with start index = 0 is "ab", which is an anagram of "ab".
//    The substring with start index = 1 is "ba", which is an anagram of "ab".
//    The substring with start index = 2 is "ab", which is an anagram of "ab".
    fun findAnagrams_slidingWindow(s:String, p:String): List<Int> {
        val charCounts = IntArray(26) { 0 }
        var startIdx = 0
        var endIdx = p.length - 1
        val result = mutableListOf<Int>()
        // initial count
        for (i in 0 until p.length-1) {
            charCounts[s[i] - 'a']++
        }

        while (endIdx < s.length) {
            charCounts[s[endIdx]-'a']++
            if (isValidAnagram(charCounts.clone(), p)) {
                result.add(startIdx)
            }
            charCounts[s[startIdx]-'a']--
            endIdx++
            startIdx++
        }
        return result
    }

    private fun isValidAnagram(charCounts: IntArray, p: String): Boolean {
        // check if valid
        for (i in p.indices) {
            val idx = p[i] - 'a'
            charCounts[idx]--
            if (charCounts[idx] < 0)
                return false
        }
        return true
    }

    //[2,6,4,8,10,9,15]
    //[2,1]
    //[5,4,3,2,1]
    //[1,2,3,4]
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
        return if (start == -1) 0 else end - start + 1
    }
}