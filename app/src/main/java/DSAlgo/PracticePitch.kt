package DSAlgo

import KotlinSamples.StructuralAndReferralEquitySample
import android.os.Build
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.kotlintutorial.AppClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
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

//        val input2DArray = arrayOf(intArrayOf(5, 0, 7), intArrayOf(3, 8, 1), intArrayOf(4, 9, 6))

//        val inputArray = intArrayOf(4, 1, 3, 5, 1, 2, 3, 2, 1, 1, 5)
//        ArrayPrograms().sortAsWaveArray(inputArray)

//        val arrayWithOnesAndZeros = arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0 ,1), intArrayOf(1, 1, 1))
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
//        MatrixSamples().numIslands(arrayOf(charArrayOf('1', '1', '0', '0', '0'), charArrayOf('1', '1', '0', '0', '0'),
//            charArrayOf('0', '0', '1', '0', '0'),  charArrayOf('0', '0', '0', '1', '1')))

//          MatrixSamples().generatePascalTriangle(5)

//        ArrayPrograms().tripletSum(intArrayOf(1,1,2,2,3,3), 6)


//        StringProblems().zigzagConversionStringBuilder("PAYPALISHIRING", 5)

//        ArrayPrograms().findUnsortedSubarray(intArrayOf(2, 6, 4, 8, 10, 9, 15))

//        ArrayPrograms().TrappingRainWater(intArrayOf(3, 0, 2, 0, 4))

//        StringProblems().lengthOfLongestSubstring("abcabcbb")

//        ArrayPrograms().minimumOperationsToZero1(intArrayOf(5207,5594,477,6938,8010,7606,2356,6349,3970,751,5997,6114,9903,3859,6900,7722,2378,1996,
//                8902,228,4461,90,7321,7893,4879,9987,1146,8177,1073,7254,5088,402,4266,6443,3084,1403,5357,
//                2565,3470,3639,9468,8932,3119,5839,8008,2712,2735,825,4236,3703,2711,530,9630,1521,2174,5027,
//                4833,3483,445,8300,3194,8784,279,3097,1491,9864,4992,6164,2043,5364,9192,9649,9944,7230,7224,
//                585,3722,5628,4833,8379,3967,5649,2554,5828,4331,3547,7847,5433,3394,4968,9983,3540,9224,6216,
//                9665,8070,31,3555,4198,2626,9553,9724,4503,1951,9980,3975,6025,8928,2952,911,3674,6620,3745,6548,
//                4985,5206,5777,1908,6029,2322,2626,2188,5639),31841)

//        CouroutineSamples()
//          ArrayPrograms().maximumUniqueSubarray(intArrayOf(10000,1,10000,1,1,1,1,1,1))
//          ArrayPrograms().maximumUniqueSubAraySum(intArrayOf(5,2,1,2,5,2,1,2,5))
//        StringProblems().isAnagram_sortApproach("anagram", "nagaram")
//        StringProblems().groupAnagrams(arrayOf("eat","tea","tan","ate","nat","bat"))
//        StringProblems().findAnagrams("abab", "ab")
//        StringProblems().findAnagrams_slidingWindow("cbaebabacd", "abc")
//        NetoworkLibUseCase()
//        QueueSamples()
//        LRUCacheImplementation()
//        getNumberOfShifts()
//        CouroutineSamples()
//        KotlinOperatorOverloading()
//        ExtensionFunctionSample()
//          SampleSealedClass()
//        KotlinVaragsExample()
//        MapSample()
//        KotlinInfixSample()
//        ClassDelegateSample()
//        SingeltonExperiment.getInstance().hello()
//        KotlinDataClassSample()
//        KotlinDestructionDeclarationSamples()
//        KotlinReflections()
//        KotlinAnnotations()
//        ScopeFunctionsSample()
//        KotlinCPS()
//        CouroutineSamples()
//        FilterAndMapSample()
//        ArrayPrograms().nextGreatestElement(intArrayOf(4, 5, 2, 25))
//        StringProblems().longestSubStringWithoutDuplicate("pwwkew")
//        val projects = intArrayOf(1,1)
//        val bids = intArrayOf(4,7)
//        ArrayPrograms().projectBids(projects, bids)
//        JavaSamples.reverseWords("i.like.this.program.very.much")
//        JavaSamples.longestCommonPrefix(arrayOf("geeks", "geeksforgeeks", "geeker", "geek"), 4) //output "d"
//        JavaSamples.romanToDecimal("CMXVI")
//        JavaSamples.convertToRoman(5)
//        JavaSamples.shortestDistance(arrayListOf("the", "quick", "brown", "fox", "quick"), "the", "fox")
//        JavaSamples.shortestDistance(arrayListOf("geeks", "for", "geeks", "contribute", "practice"), "geeks", "practice")
//        JavaSamples.shortestDistance(arrayListOf("rn", "kl", "bk", "ypo", "bk"), "kl", "rn")
//        JavaSamples.shortestDistance(arrayListOf("jd", "lzt", "kym", "ky", "gdf", "gdf", "jd"), "kym", "gdf");
//        JavaSamples.shortestDistance(arrayListOf("axa", "ffn", "ty", "bs", "oin", "bs", "axa"), "bs", "axa");
//        JavaSamples.isdivisible7("8955795758")

//        JavaSamples.areKAnagrams("uovwhqfaemqodyksjj", "swwhzsiowocjfyadvj", 17)
//        StringProblems().checkIsPangram("Bawds jog, flick quartz, vex nymph")
//        JavaSamples.lengthOfLongestSubstringOptimised("geeksforgeeks")
//        StringProblems().longestPalindromeSubString("aebcbda")
//        JavaSamples.distinctSubsequences("gfg")
//        StringProblems().distinctSubsequences_map("gfg")
//        JavaSamples.i45yui'
    //        sRotated("amazon", "azonam")
//        JavaSamples.atoi("123")
//          JavaSamples.findSum("0000000", "000000000")
//          ArrayPrograms().getMaxPairjGreaterThani(intArrayOf(2, 6, 9, 11, 19, 21, 23))
//        StringProblems().sortArrayOfStrings(arrayOf("zebra", "antelope", "ant", "bear", "lion", "lioness"))
//        val arr = intArrayOf(1, 2, 3, 4)
//        ArrayPrograms().productExceptSelf(arr)

//       val encodedString = StringProblems().encode(arrayListOf("lint", "code", "love", "you"))
//       StringProblems().decode(encodedString)

//        SetsProblems().longestConsecutive(intArrayOf(100,2,1,4,200,1,3))

//        var arr = intArrayOf(7,6,4,3,1)
//        arr = intArrayOf(7,1,5,3,6,4)
//        arr = intArrayOf(3,2,6,5,0,3)
//        arr = intArrayOf(2,1,2,1,0,1,2)
//        arr = intArrayOf(0,1,1)
//        arr = intArrayOf(0,0,0)
//        ArrayPrograms().threeSumToSumZero(arr)
//          ArrayPrograms().TrappingRainWater_4(arr)
//        SlidingWindowProblems().longestSubStringSlidingWindow("abcbde")
//        SlidingWindowProblems().checkInclusionMyApproach("hello", "ooolleoooleh")
//        SlidingWindowProblems().minWindow("ADOBECODEBANC", "ABC")

//        val matArr = arrayOf(intArrayOf(1,2,3,4), intArrayOf(5,6,7,8), intArrayOf(10,11,12,13))
//        BinarySearchProblems().searchMatrix(matArr, 10)
//        arr = intArrayOf(3,6,7,11)
//        arr = intArrayOfntArrayOf(312884470)
//        BinarySearchProblems().searchInSortedRotated(intArrayOf(4,5,6,7,0,1,2), 0)
//        arr = intArrayOf(3,4,5,1,2)
//        BinarySearchProblems().searchMinInSortedRotated(arr)
//        ArrayPrograms().pascalsTriangle(5)
//        val position = BinarySearchProblems().binarySearchPositionToInsert(intArrayOf(1,2,3,8,9), 0, 4, 7)
//        println(position)
//        SortingSample().mergeSort(intArrayOf(5, 4, 3, 2, 1))
//        ArrayPrograms().findUnsortedSubarray(intArrayOf(2,6,4,8,10,9,15))
//        ArrayPrograms().runningMedian(intArrayOf(5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4), 12)
//        BinarySearchProblems().searchMinInSortedRotated(intArrayOf(3,4,5,1,2))
//        StringProblems().longestPalindromStringLength("aebcba")
//        StringProblems().codingChallenge(arrayOf("baseball", "a,all,b,ball,bas,base,cat,code,d,e,quit,z"))
//        StringProblems().findAnagrams_slidingWindow("cbaebabacd", "abc")
//        StructuralAndReferralEquitySample()
//        StringProblems().findAnagrams_slidingWindow("cbaebabacd", "abc")
//        TreesProblems()
//        StackSamples()
//        BinarySearchProblems().monkeyBananaProblem(intArrayOf(30,11,23,4,20), 6)

//        ArrayPrograms().LongestIncreasingSubSequence(intArrayOf(10,9,2,5,3,7,101,18))
//
//        val sevenNode = TreeNode(7)
//        val sixNode = TreeNode(6)
//        val fiveNode = TreeNode(5)
//        val fourNode = TreeNode(4)
//
//        val threeNode = TreeNode(3, sixNode, sevenNode)
//        val twoNode = TreeNode(2, fourNode, fiveNode)
//        val oneNode = TreeNode(1, twoNode, threeNode)
//        JavaSamples().printAllPathsFromRooToLeaf(oneNode, IntArray(10), 0)

//        SortingSample()
//        ArrayPrograms().pascalsTriangle(5)
//        ArrayPrograms().maximumAscOrDescSubArray(intArrayOf(5,2,1,2,5,2,1,2,5))
//        ArrayPrograms().rotateArrayInPlace(intArrayOf(4,5,6,7,0,1,2))
//        ArrayPrograms().separateEvenAndOddNumbers(intArrayOf(3 ,5, 2, 11, 6, 7, 4, 9, 10))
        StackSamples().nextSmallestElement(intArrayOf(4, 8, 5, 2, 25))
    }
}


