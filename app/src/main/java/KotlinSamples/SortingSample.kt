package KotlinSamples

class SortingSample {
    init {

    }

    //merge sort
    fun mergeSort(nums:IntArray, start:Int, end:Int){
        if(start < end){
            val mid = (start + end)/2
            mergeSort(nums, start, mid)
            mergeSort(nums, mid + 1, end)
            merge(nums, start, mid, end)
        }
    }

    fun merge(nums:IntArray, start:Int, mid:Int, end:Int){
        val n1 = mid - start + 1
        val n2 = end - mid
        var startArray = IntArray(n1)
        var endArray = IntArray(n2)

        for(i in 0 until n1){
            startArray[i] = nums[start + i]
            println("startArray ${startArray[i]}")
        }

        for(j in 0 until n2){
            endArray[j] = nums[mid + 1 + j]
            println("endArray ${endArray[j]}")
        }

        var i = 0; var j = 0; var k = start
        while(i < n1 && j < n2){
            if(startArray[i] < endArray[j]){
                nums[k++] = startArray[i++]
            }else{
                nums[k++] = endArray[j++]
            }
        }


        while(i < n1){
            nums[k++] = startArray[i++]
        }

        while(j < n2){
            nums[k++] = endArray[j++]
        }
    }

    //quick sort
    fun quickSort(nums:LongArray, low:Int, high:Int):LongArray{
        var lowLocal = low
        var highLocal = high
        if(low < high){
            val pi = partition(nums, lowLocal, highLocal)
            quickSort(nums, lowLocal, pi-1)
            quickSort(nums, pi+1, high)
        }
        return nums
    }

    fun partition(nums:LongArray, low:Int, high:Int):Int{
        val pivot = nums[high]
        var i = low - 1
        for(j in low until high){
            if(nums[j] < pivot){
                i++
                swap(nums, i, j)
            }
        }
        swap(nums, i+1, high)
        return i + 1
    }

    fun swap(arr:LongArray, i:Int, j:Int){
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
}