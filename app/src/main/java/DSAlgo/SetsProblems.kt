package DSAlgo

import java.util.HashSet

class SetsProblems {
//    Input: nums = 20,19,20,1,2,3,4,5
//    Output: 54
    fun maximumUniqueSubAraySum(nums: IntArray): Int{
        var i = 0 ; var j = 0
        var maxSum = 0 ; var currSum = 0
        val set: MutableSet<Int> = HashSet()
        while (i < nums.size && j < nums.size) {
            if (!set.contains(nums[j])) {
                currSum += nums[j]
                maxSum = Math.max(maxSum, currSum)
                set.add(nums[j])
                j++
            } else {
                currSum = currSum - nums[i]
                set.remove(nums[i])
                i++
            }
        }
        return maxSum
    }


//    Input: nums = [100,4,200,1,3,2]
//    Output: 4
//    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

    //    Input: nums = [0,3,7,2,5,8,4,6,0,1]
//    Output: 9
    //unsorted array
    fun longestConsecutive(nums: IntArray): Int {
        var length = 0
        val set: MutableSet<Int> = HashSet()
        for (i in nums) set.add(i)
        for (i in nums) {
            var max = 0
            var prevVal = i - 1
            var nextVal = i + 1
            while (set.contains(prevVal)) {
                max++
                set.remove(prevVal--)
            }

            while (set.contains(nextVal)) {
                max++
                set.remove(nextVal++)
            }
            length = Math.max(length, max)
        }
        return length
    }

    fun longestConsecutiveSlightBetter(nums: IntArray): Int {
        var length = 0
        val set: MutableSet<Int> = HashSet()
        for (i in nums) set.add(i)
        for (i in nums) {
            if(!set.contains(i-1)){
                var currentItem = i
                var currentLength = 1
                while(set.contains(currentItem+1)){
                    currentItem++
                    currentLength++
                }
                length = Math.max(length, currentLength)
            }
        }
        return length
    }
}