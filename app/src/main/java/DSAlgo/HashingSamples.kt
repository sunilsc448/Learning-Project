package DSAlgo

class HashingSamples {
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

    //unsorted array
    fun twoSumUnsortedArray(nums: IntArray, target: Int): IntArray {
        var outputArray = IntArray(2)
        var map:HashMap<Int, Int> = HashMap<Int, Int>()
        for(i in 0 until nums.size){
            val diff = target - nums[i]
            if(map.containsKey(diff)){
                outputArray[0] = i
                outputArray[1] = map.get(diff)!!
                break;
            }else{
                map.put(nums[i], i)
            }
        }
        return outputArray
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
    fun tripletSum(arr: IntArray, target: Int): Int {
//        val mod = 1000000007
        var sum = 0
        var map = HashMap<Int, Int>()
        map.put(arr[0], 1)
        for(i in 1 until arr.size){
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])!! + 1)
            }else{
                map.put(arr[i], 1)
            }
            for(j in i+1 until arr.size){
                val diff = target - (arr[i] + arr[j])
                if(map.containsKey(diff)){
                    sum += map.get(diff)!!
//                    sum = ((sum % mod) + (map.get(diff)!! % mod)) % mod
                }
            }
        }
        return sum
    }

//    Given an integer array nums and an integer k,
//    return the k most frequent elements. You may return the answer in any order.
//    Input: nums = [1,1,1,2,2,3], k = 2
//    Output: [1,2]
//    Input: nums = [1], k = 1
//    Output: [1]
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

    //2,5,4,4,1,3,4,4,1,4,4,1,2,1,2,2,3,2,4,2  k=3
    fun maxOperations(nums: IntArray, k: Int): Int {
        val map = HashMap<Int, Int>()
        var count = 0
        for(i in nums.indices){
            if(map.containsKey(nums[i])){
                count++
                map.remove(map.get(nums[i])!! - 1)
            }else{
                if(map.containsKey(k-nums[i])){
                    map.put(k-nums[i], map.get(k-nums[i])!! + 1)
                }
                map.put(k-nums[i], 1)
            }
        }
        return count
    }

    //    Input: nums = [1,2,3,1]
    //    Output: true
    //
    //    Input: nums = [1,2,3,4]
    //    Output: false

    //using Map or List
    //using sets it is one liner
    fun isDuplicateItem(nums: IntArray):Boolean{
        val map = HashMap<Int, Int>()
        nums.forEach {
            if(map.containsKey(it)){
                return true
            }else{
                map.put(it, it)
            }
        }
        return false

//        val map = ArrayList<Int>()
//        nums.forEach {
//            if(map.contains(it)){
//                return true
//            }else{
//                map.add(it)
//            }
//        }
//        return false
    }
}