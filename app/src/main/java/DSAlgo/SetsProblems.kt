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

    //    Input: nums = [100,2,1,4,200,1,3]
    //output = 4

    //    Input: nums = [0,3,7,2,5,8,4,6,0,1]
//    Output: 9
    //unsorted array
    fun longestConsecutive(nums: IntArray): Int {
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

    //using Set
    fun isDuplicateItem2(nums: IntArray):Boolean{
        return nums.size != nums.toSet().size
    }

    /*
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
}