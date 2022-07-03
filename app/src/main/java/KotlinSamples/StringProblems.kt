package KotlinSamples

import android.util.Log
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class StringProblems {
    init {
//        val str = "paapaap"
//        println("is input string $str a Palindrome >>> ${isPalindrome(str)}")

//        val str = "mdaam"
//        println("result is ${makeAPalindrome(str)}")

//        val str = "GEEKSFORGEEKS"
//        println("Longest unique substring of $str is ${lengthOfLongestSubstring(str.lowercase())}")

//        val str = "baab"
//        val str = "iampaapi"
//        val str = "ca"
//        println("Longest Palindrom of $str is ${longestPalindromeSubString(str)}")

//        stringToInt("9223372036854775808")

//        codingChallenge(arrayOf("baseball", "a,all,b,ball,bas,base,cat,code,d,e,quit,z"))
//        codingChallenge(arrayOf("abcgefd", "a,ab,abc,abcg,b,c,dog,e,efd,zzzz"))
    }

    private fun codingChallengePathFunction(){
        //GIVEN A PATH OF STRING(U,D,L,R) - RETURN THE POSSIBLE SHORTEST PATH
//        val input = "UURRD"
        val input = "URDR"
        var row = 0
        var column = 0
        var result = ""
        for (i in input.indices){
            val char = input[i]
            if(char.equals('U')){
                row++
            }else if(char.equals('D')){
                row--
            } else if(char.equals('R')){
                column++
            }else if(char.equals('L')){
                column--
            }
        }
        if(row < 0){
            while (row < 0){
                result += "D"
                row++
            }
        }else{
            while (row > 0){
                result += "U"
                row--
            }
        }

        if(column < 0){
            while (column < 0){
                result += "L"
            }
        }else{
            while (column > 0){
                result += "R"
                column--
            }
        }

        println("result is ${result}")
    }

    private fun codingChallengeSubStringProblem() {
        //GIVEN A NUMBER, PRINT THE STRING TILL THAT LENGTH REPEATEDLY AND RETURN HOW MANY a's ARE THERE
        val input = "abc"
        val n = 10
        var newString = ""

        val quotient = n/input.length
        val remainder = n%input.length
        for(i in 0 until quotient){
            newString += input
        }

        for(i in 0 until remainder){
            newString += input[i]
        }

        println("newString is $newString")

        var result = 0
        for(i in 0 until newString.length){
            if(newString[i] == 'a'){
                result++
            }
        }

        println("the final output is $result")
    }

    fun makeAPalindrome(input:String):String{
        val map = mutableMapOf<Char, Int>()
//        var map = HashMap<Char, Int>()
        for (char in input){
            if (map.containsKey(char)){
                map.put(char, map.get(char)!! + 1)
            }else{
                map.put(char, 1)
            }
        }
        var oddCount = 0
        //char empty value
        var oddChar:Char = '\u0000'
        map.forEach{
            if(it.value %2 != 0){
                oddCount++
                oddChar = it.key
            }
        }

        if(oddCount > 1 || (oddCount == 1 && input.length %2 == 0)){
            return "Not a Palindrome"
        }


        var leftPart = ""; var rightPart = ""
        map.forEach{
            var i = 0
            var str = ""
            while (i < it.value/2){
                str += it.key
                i++
            }
            leftPart = leftPart + str
            rightPart = str + rightPart
        }

        return if (oddCount == 1)
            leftPart + oddChar + rightPart
        else
            leftPart + rightPart
    }

    fun longestUniqueSubsttr(str: String): Int {
        val n = str.length

        // Result
        var res = 0
        for (i in 0 until n) {
            for (j in i until n) {
                if (areDistinct(str, i, j)) {
                    res = Math.max(res, j - i + 1)
                }
            }
        }
        return res
    }

//    Input: s = "abcabcbb"
//    Output: 3
//    Explanation: The answer is "abc", with the length of 3.

//    Input: s = "bbbbb"
//    Output: 1
//    Explanation: The answer is "b", with the length of 1.

//    Input: s = "pwwkew"
//    Output: 3
//    Explanation: The answer is "wke", with the length of 3.
//    Notice that the answer must be a substring, "pwke" is a
    fun lengthOfLongestSubstring(s: String): Int {
        var result = 0
        val n = s.length
        for(i in 0 until n){
            var visitedArray = BooleanArray(128)
            for(j in i until n){
                val asciival = s.elementAt(j).toInt()
                if(visitedArray[asciival] == true){
                    break;
                }else{
                    result = Math.max(result, j-i+1)
                    visitedArray[asciival] = true
                }
            }
            visitedArray[s.elementAt(i).toInt()] = false
        }
        return result
    }

    private fun areDistinct(inputString:String, i:Int, j:Int) : Boolean{
        var counter = 0
        val visited = BooleanArray(26)
        for (k in i until j){
            println("counter >>> $counter++")
            val ascii = inputString.elementAt(k) - 'a'
            if(visited[ascii]){
                return false
            }

            visited[ascii] = true
        }
        return true
    }

    private fun isPalindrome(input:String):Boolean{
        var left = 0; var right = input.length - 1
        while (left < right){
            if(input[left] != input[right]){
                return false
            }
            left++
            right--
        }
        return true
    }

    //Using boolean matrix >>>> TC : O(n2)   SC : O(n2)
    private fun longestPalindromeSubString(s: String):String {
        val n = s.length
        var maxLength = 1
        var startPos = 0
        val boolArray:Array<Array<Boolean>> = Array(n){Array<Boolean>(n){false} }
        for (i in 0 until n){
            boolArray[i][i] = true
        }

        for (i in 0 until n-1){
            if(s[i] == s[i+1]){
                boolArray[i][i+1] = true
                maxLength = 2
                startPos = i
            }
        }

        for (k in 3 .. n){
            var i = 0
            while(i < n - k + 1){
                val j = i + k -1
                if(boolArray[i + 1][j - 1] == true && s[i] == s[j]){
                    boolArray[i][j] = true
                    if(k > maxLength) {
                        maxLength = k
                        startPos = i
                    }
                }
                i++
            }
        }

        return s.substring(startPos, startPos + maxLength)
    }

//    Example 1:
//    Input: s = "babad"
//    Output: "bab"
//    Explanation: "aba" is also a valid answer.
//    Example 2:
//    Input: s = "cbbd"
//    Output: "bb"
    //Using two pointer approach >>> TC O(n2)   SC: O(1)
    private fun longestPalindromeSubString2(s: String):String {
        val n = s.length
        var low = 0
        var high = 0
        var maxLength = 1
        var startIndex = 0
        for (i in 0 until n){
            low = i - 1
            high = i + 1
            while (high < n && s[high] == s[i]){
                high++
            }
            while (low >= 0 && s[low] == s[i]){
                low --
            }
            while (low >= 0 && high < n && s[high] == s[low]){
                low--
                high++
            }

            val length = high - low - 1

            if (length > maxLength){
                maxLength = length
                startIndex=low+1
            }
        }

        return s.substring(startIndex, startIndex+maxLength)
    }

    //Using String Builders - Better one is using String Builder
    fun zigzagConversionStringBuilder(s: String, numRows: Int):String{
        val insertCount = 0
//        val sb = arrayOfNulls<StringBuilder>(numRows)
        val sbArray:Array<StringBuilder> = Array(numRows){ StringBuilder("") }
        var incrementer = 1
        var index = 0
        for (i in 0 until s.length){
            //index is the decider which will put the value into right substring item
            sbArray[index].append(s[i])

            //if top item
            if(index == 0){
                incrementer = 1 //if bottom item
            }else if(index == numRows - 1){
                incrementer = -1
            }

            index += incrementer
        }

        var resulStr = ""
        sbArray.forEach {
            resulStr += it.toString()
        }

        //PAHNAPLSIIGYIR
        return resulStr
    }

//    1) Input: s = "42" > Output: 42
//    2) Input: s = "   -42" >  Output: -42
//    3) Input: s = "4193 with words" > Output: 4193
//    4) Input: "words and 987" > Output: 0
//    5) Input: "+-12" > Output: 0
//    6) Input: "-91283472332" > Output: -1089159116
    fun stringToInt(s: String): Int {
        if(s.isEmpty())return 0
        val str = s.trim()
        var num:Long = 0
        var isNegative = false
        var index = 0
        if(str[0] == '-'){
            isNegative = true
            index++
        }else if(str[0] == '-'){
            index++
        }

        for (i in index until str.length){
            if(str[i] in '0'..'9'){
                num = num*10 + str[i].toString().toInt()
            }else{
                break
            }
            if(num > Int.MAX_VALUE || num < Int.MIN_VALUE){
                break
            }
        }

        if(isNegative){
            num = -num
        }

        if(num > Int.MAX_VALUE){
            return Int.MAX_VALUE
        }else if(num < Int.MIN_VALUE){
            return Int.MIN_VALUE
        }else{
            return num.toInt()
        }
    }

//    codingChallenge(arrayOf("baseball", "a,all,b,ball,bas,base,cat,code,d,e,quit,z"))
//    output >> "base,ball"
//    codingChallenge(arrayOf("abcgefd", "a,ab,abc,abcg,b,c,dog,e,efd,zzzz"))
//    output >> "abcg,efd"
    private fun codingChallenge(list: Array<String>):String {
        val wordToCompare = list[0]
        val stringDictionary = list[1]
        val singleStrings = stringDictionary.split(',')
        var answerWords = ""

        for (firstWord in singleStrings){
            val splitMainWordArray = wordToCompare.split(firstWord)
            if(splitMainWordArray.size > 0){
                for (word in splitMainWordArray){
                    val joinedWord = firstWord + word
                    val reversedWord = joinedWord.reversed()
                    if(joinedWord == wordToCompare || reversedWord == wordToCompare){
                        answerWords = "$firstWord,$word"
                    }
                }
            }
        }

        return answerWords
    }

//    Letter Combinations of a Phone Number
//    Input: digits = "23"
//    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

//    Input: digits = "2"
//    Output: ["a","b","c"]

//    Input: digits = ""
//    Output: []
    fun letterCombinations(digits: String): List<String> {
        if(digits.isNullOrEmpty())
            return emptyList()
        val map = mapOf(
            '2' to listOf("a", "b", "c"),
            '3' to listOf("d", "e", "f"),
            '4' to listOf("g", "h", "i"),
            '5' to listOf("j", "k", "l"),
            '6' to listOf("m", "n", "o"),
            '7' to listOf("p", "q", "r", "s"),
            '8' to listOf("t", "u", "v"),
            '9' to listOf("w", "x", "y", "z")
        )

        var rsltList = map.get(digits[0])!!
        if(digits.length == 1){
            return rsltList
        }
        var index = 1
        while (index < digits.length){
            val list = map.get(digits[index])!!
            rsltList = rsltList.flatMap { a ->list.flatMap { b -> arrayListOf(a + b)}}
            index++
        }

        return rsltList
    }

//    Valid Anagram
//    Input: s = "anagram", t = "nagaram"
//    Output: true
//    Input: s = "rat", t = "car"
//    Output: false
//    TC: O(n)  Memory O(n)
    fun isAnagram(s: String, t: String): Boolean {
        val map = HashMap<Char, Int>()

        s.forEach{
            if(map.get(it) == null){
                map.put(it, 1)
            }else{
                map.put(it, map.get(it)!! + 1)
            }
        }

        t.forEach{
            if(map.contains(it)){
                val mapVal = map.get(it)
                if(mapVal!! <= 1){
                    map.remove(it)
                }else{
                    map.put(it, mapVal-1)
                }
            }else{
                map.put(it, 1)
            }
        }

        return map.count() == 0
    }

//    TC : O(nLogn) Memory O(1)
    fun isAnagram_sortApproach(s: String, t: String): Boolean {
        val sList = s.toCharArray().sorted()
        val tList = t.toCharArray().sorted()
        return sList == tList
    }

//    Group Anagrams
//    Input: strs = ["eat","tea","tan","ate","nat","bat"]
//    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
//    Input: strs = [""]
//    Output: [[""]]
//    Input: strs = ["a"]
//    Output: [["a"]]
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = HashMap<String, ArrayList<String>>()
        strs.forEach {
            val sortedString = it.toCharArray().sorted().joinToString("")
            if(map.containsKey(sortedString)){
                val list = map.get(sortedString)!!.add(it)
            }else{
                map.put(sortedString, arrayListOf(it))
            }
        }

        val outPutList = MutableList<List<String>>(0){ emptyList()}
        map.values.forEach {
            outPutList.add(it)
        }
        return outPutList
    }

//    Input: s = "cbaebabacd", p = "abc"
//    Output: [0,6]
//    Explanation:
//    The substring with start index = 0 is "cba", which is an anagram of "abc".
//    The substring with start index = 6 is "bac", which is an anagram of "abc".
//
//    Input: s = "abab", p = "ab"
//    Output: [0,1,2]
//    Explanation:
//    The substring with start index = 0 is "ab", which is an anagram of "ab".
//    The substring with start index = 1 is "ba", which is an anagram of "ab".
//    The substring with start index = 2 is "ab", which is an anagram of "ab".
    fun findAnagrams(s: String, p: String): List<Int> {
        val n = s.length
        val m = p.length
        val sortedP = p.toCharArray().sorted()
        val outputList = ArrayList<Int>()
        for (i in 0 until n-m+1){
            var str:String = ""
            for (j in i until i+m){
                str += s[j]
            }
            val sortedStr = str.toCharArray().sorted()
            if(sortedP.equals(sortedStr)){
                outputList.add(i)
            }
        }
        return outputList
    }

    fun findAnagrams_slidingWindow(s:String, p:String): List<Int> {
        val charCounts = IntArray(26) { 0 }
        var startIdx = 0
        var endIdx = p.length - 1
        val result = mutableListOf<Int>()
        // initial count
        for (i in 0 until p.length-1) {
            charCounts[s[i] - 'a']++
        }

        while (endIdx < s.length) {
            charCounts[s[endIdx]-'a']++
            if (isValidAnagram(charCounts.clone(), p)) {
                result.add(startIdx)
            }
            charCounts[s[startIdx]-'a']--
            endIdx++
            startIdx++
        }
        return result
    }

    private fun isValidAnagram(charCounts: IntArray, p: String): Boolean {
        // check if valid
        for (i in p.indices) {
            val idx = p[i] - 'a'
            charCounts[idx]--
            if (charCounts[idx] < 0)
                return false
        }
        return true
    }
}