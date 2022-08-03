package DSAlgo

class MapsSample {
    init {
//        maxOperations(intArrayOf(2,5,4,4,1,3,4,4,1,4,4,1,2,1,2,2,3,2,4,2), 3)
    }

    fun maxOperations(nums: IntArray, k: Int): Int {
        val map = HashMap<Int, Int>()
        var count = 0
        for(i in 0 until nums.size){
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

    //using Set
    fun isDuplicateItem2(nums: IntArray):Boolean{
       return nums.size != nums.toSet().size
    }
}