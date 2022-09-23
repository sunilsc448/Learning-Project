package DSAlgo

class BinarySearchProblems {
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

    //1, 2, 4, 5, 6, 7, 8, 9 & target = 3, position = 2
    //1, 2, 3, 4, 5, 6, 7, 9 & target = 8, position = 7
    fun binarySearchPositionToInsert(arr: IntArray, low: Int, high: Int, target: Int): Int {
        if (low >= high) {
            return if (target > arr[low])
                low + 1
            else
                low
        }
        val mid = (low + high) / 2
        if (target == arr[mid])
            return mid + 1
        return if (target > arr[mid])
            binarySearchPositionToInsert(arr, mid + 1, high, target)
        else
            binarySearchPositionToInsert(arr, low, mid - 1, target)
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

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if (target < matrix[0][0])
            return false
        val m = matrix.size
        val n: Int = matrix[0].size
        var i = 0 ; val j = n - 1

        /*Compare last element of each row with the target to identify the row in which the target number might exist */
        while (i < m && matrix[i][j] < target) {
            i++
        }

        //If target is greater than last element then return false
        return if (i == m)
            false
        else //else perform a binary search in the obtained row
            binarySearchBoolean(matrix[i], target)
    }

//    Input: nums = [4,5,6,7,0,1,2], target = 0
//    Output: 4
//    Input: nums = [4,5,6,7,0,1,2], target = 3
//    Output: -1
//    Input: nums = [1], target = 0
//    Output: -1
    fun searchInSortedRotated(nums: IntArray, target: Int): Int {
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

//    Input: piles = [3,6,7,11], h = 8
//    Output: 4
//    Input: piles = [30,11,23,4,20], h = 5
//    Output: 30
//    Input: piles = [30,11,23,4,20], h = 6
//    Output: 23
    fun monkeyBananaProblem(piles: IntArray, h: Int): Int {
        var highest = 0

        //get max
        for (p in piles) {
            highest = Math.max(highest, p)
        }

        //if hours is equal to piles length, then return max
        if (h == piles.size) {
            return highest
        }
        var low = 1 ; var high = highest ;
        var res = highest
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

//    Input: nums = [3,4,5,1,2]
//    Output: 1
//    Explanation: The original array was [1,2,3,4,5] rotated 3 times.

//    Input: nums = [4,5,6,7,0,1,2]
//    Output: 0
//    Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

//    Input: nums = [11,13,15,17]
//    Output: 11
//    Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
    fun searchMinInSortedRotated(nums: IntArray):Int{
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
}