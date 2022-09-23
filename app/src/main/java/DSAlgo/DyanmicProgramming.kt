package DSAlgo

class DyanmicProgramming {
    //    Input: nums = [10,9,2,5,3,7,101,18]
//    Output: 4
//    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

//    Input: nums = [0,1,0,3,2,3]
//    Output: 4

    //    Input: nums = [7,7,7,7,7,7,7]
//    Output: 1
    //DP Problem
    fun longestIncreasingSubSequence(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0
        val dp = IntArray(n)
        var res = 1 //initalization
        for (i in 0 until n) {
            dp[i] = 1
            for (j in 0 until i) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1)
                }
            }
            res = Math.max(res, dp[i])
        }
        return res
    }
}