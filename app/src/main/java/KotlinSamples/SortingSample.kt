package KotlinSamples

class SortingSample {
    init {
//        bubbleSort(intArrayOf(1, 9, 6, 7, 11, 2, 5, 3))
        findUnsortedSubarray(intArrayOf(1,2,3,4))
    }

    fun bubbleSort(nums:IntArray){
         for(i in 0 until nums.size - 1){
             for(j in i+1 until nums.size){
                 if(nums[j] < nums[i]){
                     val temp = nums[i]
                     nums[i] = nums[j]
                     nums[j] = temp
                 }
             }
         }
        println("items are bubble sorted as >>> ")
        nums.forEach {
          print("\t $it")
        }
        println()
    }

    fun selectionSort(nums:IntArray){
         for(i in 0 until nums.size - 1){
             var min = i
             for(j in i+1 until nums.size){
                 if(nums[j] < nums[min]){
                     min = j
                 }
             }
             val temp = nums[i]
             nums[i] = nums[min]
             nums[min] = temp
         }
    }

    fun insertionSort(nums:IntArray){
         for(i in 1 until nums.size){
             val key = nums[i]
             var j = i - 1
             while(j >= 0 && nums[j] > key){
                 nums[j + 1] = nums[j]
                 j--
             }
             nums[j + 1] = key
         }
    }

    fun shellSort(nums:IntArray){
         val n = nums.size
         var gap = n/2
         while(gap > 0){
             for(i in gap until n){
                 val key = nums[i]
                 var j = i
                 while(j >= gap && nums[j - gap] > key){
                     nums[j] = nums[j - gap]
                     j = j - gap
                 }
                 nums[j] = key
             }
             gap = gap/2
         }
    }

    fun mergeSort(nums:IntArray){
         mergeSort(nums, 0, nums.size-1)
    }

    fun quickSort(nums:IntArray){
        val low = 0
        val high = nums.size - 1
        quickSort(nums, low, high)
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
    fun quickSort(nums:IntArray, low:Int, high:Int){
        var lowLocal = low
        var highLocal = high
        while(lowLocal < highLocal){    //if(low < high)     ////removed for best optimisation
            val pi = partition(nums, lowLocal, highLocal)
            // quickSort(nums, lowLocal, pi-1) //left partition  //removed for further bettrer optimisation
            // quickSort(nums, pi+1, high) //right partition  //removed for best optimisation
            // lowLocal = pi + 1 //removed for further bettrer optimisation
            if(pi - lowLocal < highLocal - pi){
                quickSort(nums, lowLocal, pi-1)
                lowLocal = pi + 1
            }else{
                quickSort(nums, pi + 1, high)
                highLocal = pi - 1
            }
        }
    }

    fun partition(nums:IntArray, low:Int, high:Int):Int{
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

    fun swap(arr:IntArray, i:Int, j:Int){
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
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