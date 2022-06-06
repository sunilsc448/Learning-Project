package KotlinSamples

import android.os.Environment
import pojos.Player
import java.io.*
import java.util.*

class PracticePitch {
    init {
//        IteratorImplementation()
//        StackSamples()
//        val map = HMCustom<String, String>()
//        map.put("A", "Anna")
//        map.put("B", "Baaleyele")
//        map.put("C", "Chitranna")
//        map.put("D", "Dose")
//        map.put("E", "Elakki")

        val input2DArray = arrayOf(intArrayOf(5, 0, 7), intArrayOf(3, 8, 1), intArrayOf(4, 9, 6))

        val inputArray = intArrayOf(4, 1, 3, 5, 1, 2, 3, 2, 1, 1, 5)
//        ArrayPrograms().sortAsWaveArray(inputArray)

        val arrayWithOnesAndZeros = arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0 ,1), intArrayOf(1, 1, 1))
//        MatrixSamples().zeroMatrix(arrayWithOnesAndZeros)

//        MatrixSamples().rotateArrayBy90ClockWise(input2DArray)
//        MatrixSamples().rotateArrayBy90AntiClockWise(input2DArray)

//        ArrayPrograms().minimumAbsoluteDifferencePairs(inputArray)
//        ArrayPrograms().containerMostWaterBetterApproach(intArrayOf(6, 9, 3, 6, 13))
//        ArrayPrograms().containerMostWaterBetterApproach(intArrayOf(3, 1, 2, 4, 5))

//        ArrayPrograms().minAndMax(inputArray)

//        val op = ArrayPrograms().reverseArrayInPlace(inputArray)
//        println(op)

//        val sequenceArray = intArrayOf(5,4,1,2)
//        ArrayPrograms().findMissingNumberinSequenceUnsortedArray(sequenceArray)

//        ArrayPrograms().maximumSumSlidingProblemBetterApproach(inputArray, 3)

//          ArrayPrograms().slidingWindowMaximumUSingDeque(inputArray, 3)
//        findKthLargest()
//        fibo(5)
//        val fibResult = fibRec(1,0,5)
//        println(fibResult)

//        val factResult = factorial(5)
//        val factResult = ArrayPrograms().factorialRec(6)
//        println(factResult)
//        ArrayPrograms().nextGreatestElement(intArrayOf(13, 7, 6, 12))
//        ArrayPrograms().minimumAbsoluteDifferencePairs(intArrayOf(2, 6, 9, 11, 19, 21, 23))

//        ArrayPrograms().moveAllZerosToEnd(intArrayOf(1, 0, 2, 0, 0, 9, 7, 0))
//        ArrayPrograms().removeDuplicatesSortedArray(intArrayOf(1, 2, 2, 3, 4, 5, 5 ,7))

//        val arr:IntArray = IntArray(10)
//        val arr1:Array<Int> = Array(10){-1}

//        ArrayPrograms().findMaximumDiffInIncreasingElements(intArrayOf(87, 68, 91, 56, 43, 98, 4, 60))

//        ArrayPrograms().towerOfHanoi('A', 'C', 'B', 3)

//        ArrayPrograms().minAndMax(intArrayOf(4, 9, 6, 90))
//        ArrayPrograms().runningMedian(intArrayOf(48, 56, 71, 79, 87, 91, 97), 7);

//        val result = bs(intArrayOf(48, 56, 71, 79, 87, 91, 97), 98)
//        println("result$result")

//        val list1 = arrayListOf("1212","34242")
//        list1.add("aa")
//        val list2 = ArrayList<String>(10)
//        list2.add("aa")
//        val list = List<String?>(10){null}

//        FilterAndMapSample()
//        SetSample()
//        MapSample()
    }

    fun findKthLargest(){
        val inputArray = arrayOf("44", "3", "55", "2", "11", "5")
        Arrays.sort(inputArray){b,a ->
            if (a.length !== b.length) {
                b.length - a.length
            }
            else {
                b.compareTo(a)
            }
        }
        println(inputArray)
    }

    fun fibo(n:Int):Int{
        if(n <= 2)
            return 1

        var a = 1;var b = 1; var result = 0
        for (i in 2 until n){
            result = a + b
            a = b
            b = result
        }
        return result
    }

    fun fibRec(a: Int, b: Int, n: Int):Int{
        if(0 == n)return b
        return fibRec(a+b, a,n-1)
    }

    fun bs(arr:IntArray, key:Int):Int{
        return bsRec(arr, 0, arr.size-1, key)
    }

    private fun bsRec(arr: IntArray, low: Int, high: Int, key: Int):Int {
        if(high >= low){
            val mid = (low+high)/2
            if(arr[mid] == key)
                return mid + 1 //returns position from index
            if(key > mid){
                return bsRec(arr, mid + 1, high, key)
            }else{
                return bsRec(arr, low, mid - 1, key)
            }
        }
        return -1
    }
}


