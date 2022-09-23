package DSAlgo

import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class StringProblems{
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

//    Input : "mdaam"
//    Output : "madam" or "amdma"
//    Input : "abb"
//    Output : "bab"
//    Input : "geeksforgeeks"
//    Output : "No Palindrome"
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
            val visitedArray = BooleanArray(128)
            for(j in i until n){
                val asciival = s.elementAt(j).toInt()
                if(visitedArray[asciival] == true){
                    break;
                }else{
                    result = Math.max(result, j-i+1)
                    visitedArray[asciival] = true
                }
            }
        }
        return result
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
    //aebcbda > 3(bcb)
    fun longestPalindromeSubString(s: String):String {
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

        for (k in 3 .. n) {
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
    fun longestPalindromeSubString2(s: String):String {
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


    //    Input: S = "aebcbda" Output: 2, Explanation: Remove characters 'e'and 'd'.
    //    Input: S = "geeksforgeeks" Output: 8, Explanation: One of the possible resultstring can be "eefee", so 13 - 5 = 8.
    fun longestPalindromString_withMinimumDeletions(S: String): Int {
        val length_longest_palindrome = longestPalindromStringLength(S)
        return S.length - length_longest_palindrome
    }

    fun longestPalindromStringLength(str: String): Int {
        val n = str.length

        // Create a table to store
        // results of subproblems
        val bookmarkArray = Array(n){IntArray(n)}

        // Strings of length 1
        // are palindrome of length 1
        for (i in 0 until n) bookmarkArray[i][i] = 1

        // Build the table. Note
        // that the lower diagonal
        // values of table are useless
        // and not filled in the process.
        // c1 is length of substring
        for (k in 2..n) {
            for (i in 0 until n - k + 1) {
                val j = i + k - 1
                val areCharsEqual = (str[i] == str[j])
                if (areCharsEqual && k == 2)
                    bookmarkArray[i][j] = 2
                else if (areCharsEqual)
                    bookmarkArray[i][j] = bookmarkArray[i + 1][j - 1] + 2
                else
                    bookmarkArray[i][j] = Math.max(bookmarkArray[i + 1][j], bookmarkArray[i][j - 1])
            }
        }

        // length of longest
        // palindromic subsequence
        return bookmarkArray[0][n - 1]
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
                num = num*10 + (str[i] - '0')
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

//    1) Input: s = "42" > Output: 42
//    2) Input: s = "-42" >  Output: -42
//    3) Input: s = "+4193" > Output: 4193
//    4) Input: "w987" > Output: -1
    fun atoi(str: String): Int {
        if (str.isEmpty()) return -1
        var isNegative = false
        var result = 0
        var index = 0
        if (str[0] == '-') {
            isNegative = true
            index++
        } else if (str[0] == '+') {
            index++
        }
        while (index < str.length) {
            val value = str[index]
            result = if (value >= '0' && value <= '9') {
                result * 10 + (value.toString() + "").toInt()
            } else {
                return -1
            }
            index++
        }
        if (isNegative) {
            result = -result
        }
        return result
    }

//    Have the function WordSplit(strArr) read the array off strings stored in strArr,
//    which will contain 2 elements: the first element will be a sequence of characters,
//    and the second element will be a long string of comma-seperated words,
//    in alphabetical order, that represents a dictionary of some arbitrary length.
//
//    For example: strArr can be: ["hellocat", "apple, bat,cat,goodbye,hello,yellow,why"].
//
//    Your goal is to determine if the first element in the input can be split into two words,
//    where both words in the dictionary that is provided in the second input. In this example,
//    the firs element can be split into two words: hello and cat because both of those words are in the dictionary.
//
//    Your program should return the two words that exist in the dictionary seperated by a comma.
//    So for the example above, your program should return hello,cat.
//    There will only be one correcy way to split the first element of characters into two words.
//    I f there is no way to split string into two words that exist in the dictionary, return the string not possible.
//    The first element itself will never exist in the dictionary as a real word.

//    codingChallenge(arrayOf("baseball", "a,all,b,ball,bas,base,cat,code,d,e,quit,z"))
//    output >> "base,ball"
//    codingChallenge(arrayOf("abcgefd", "a,ab,abc,abcg,b,c,dog,e,efd,zzzz"))
//    output >> "abcg,efd"
     fun codingChallenge(list: Array<String>):String {
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
                map.get(sortedString)!!.add(it)
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

    //    Two strings are called K-anagrams if both of the below conditions are true.
    //    1. Both have same number of characters.
    //    2. Two strings can become anagram by changing at most K characters in a string.
    //    Input:
    //    str1 = "fodr", str2="gork"
    //    k = 2
    //    Output:1
    //    Explanation: Can change fd to gk
    //input s1 = uovwhqfaemqodyksjj, s2 = swwhzsiowocjfyadvj, k = 18 > output > 1
    fun areKAnagrams(s1: String, s2: String, k: Int): Boolean {
        if (s1.length != s2.length) return false else if (s1.length <= k) {
            return true
        }
        val map: MutableMap<Char, Int> = java.util.HashMap()
        for (i in 0 until s1.length) {
            val key = s1[i]
            if (map.containsKey(key)) {
                map[key] = map[key]!! + 1
            } else {
                map[key] = 1
            }
        }
        var counter = 0
        for (i in 0 until s2.length) {
            val key = s2[i]
            if (map.containsKey(key)) {
                val value = map[key]!!
                if (value == 1) {
                    map.remove(key)
                } else {
                    map[key] = value - 1
                }
            } else {
                counter++
            }
        }
        return counter <= k
    }

    //A pangram is a sentence containing every letter in the English Alphabet.
    fun checkIsPangram(input:String):Boolean{
        val set:Set<Char> = HashSet()
        val lowerCaseStr = input.lowercase()
        val range = 'a'..'z'
        for(i in lowerCaseStr.indices){
            if(lowerCaseStr[i] in range){
                set.plus(lowerCaseStr[i])
            }
        }
        return set.size == 26
    }

    //abcbde > cbde > 4
    //pwwkew > wke > 3
    fun longestSubStringWithoutDuplicate(input:String):Int{
        var test = ""
        var maxLength = 0
        for (char in input){
            val current: String = char.toString()
            if(test.contains(current)){
                test = test.substring(test.indexOf(current) + 1)
            }

            test += current
            maxLength = Math.max(test.length, maxLength)
        }
        return maxLength
    }

//    input > "i.like.this.program.very.much"
//    output > "much.very.program.this.like.i"
    fun reverseWords(S: String): String {
        val sb = StringBuilder()
        val strings = S.split("\\.".toRegex()).toTypedArray()
        for (i in strings.indices.reversed()) {
            sb.append(strings[i])
            if (i != 0) {
                sb.append(".")
            }
        }
        return sb.toString()
    }

//    ("geeks", "geeksforgeeks", "geeker", "geek"), 4) //output "geek"
//    ("d", "d", "d", "d"), 4) //output "d"
//    ("1d", "fd", "ed", "id"), 4) //output "-1"
    fun longestCommonPrefix(arr: Array<String>, n: Int): String {
        var currentIndex = 0
        val sb = StringBuilder()
        while (currentIndex < arr[0].length) {
            var isEqual = true
            for (i in arr.indices) {
                if (currentIndex >= arr[i].length || arr[0][currentIndex] != arr[i][currentIndex]) {
                    isEqual = false
                    break
                }
            }
            if (isEqual) {
                sb.append(arr[0][currentIndex])
            } else {
                break
            }
            currentIndex++
        }
        return if (sb.toString().isEmpty()) {
            "-1"
        } else {
            sb.toString()
        }
    }

    //("the", "quick", "brown", "fox", "quick"), "the", "fox") > output : 3
    //("geeks", "for", "geeks", "contribute", "practice"), "geeks", "practice") > output : 2
    //("rn", "kl", "bk", "ypo", "bk"), "kl", "rn") > output : 1
    //("jd", "lzt", "kym", "ky", "gdf", "gdf", "jd"), "kym", "gdf") > output : 2
    //("axa", "ffn", "ty", "bs", "oin", "bs", "axa"), "bs", "axa") > output : 1
    fun shortestDistance(s: java.util.ArrayList<String>, word1: String, word2: String): Int {
        var word1Index = -1
        var word2Index = -1
        var distance = Int.MAX_VALUE
        for (i in s.indices) {
            if (s[i] == word1) {
                word1Index = i
            }
            if (s[i] == word2) {
                word2Index = i
            }
            if (word1Index != -1 && word2Index != -1) {
                distance = Math.min(distance, Math.abs(word1Index - word2Index))
            }
        }
        return distance
    }

    //"8955795758" > 1
    //1000 > 0
    fun isdivisible7(num: String): Int {
        var i: Int
        var x: Int
        var rem = 0
        val n = num.length
        var m: Int
        i = 0
        while (i < n) {
            x = num[i] - '0'
            m = rem * 10 + x
            rem = m % 7
            i++
        }
        return if (rem == 0) 1 else 0
    }

    //str1 = aab , str2 = xxy > Output: 1 (because x =a, y = b is true for all)
    //str1 = aab , str2 = xyy > Output: 0 (because x =a, y = b is not true for all)
    fun areIsomorphic(str1: String, str2: String): Boolean {
        val map = java.util.HashMap<Char, Char>()
        for (i in 0 until str1.length) {
            val ch1 = str1[i]
            val ch2 = str2[i]
            if (map.containsKey(ch1)) {
                if (map[ch1] !== ch2) {
                    return false
                }
            } else if (map.containsValue(ch2)) {
                return false
            } else {
                map[ch1] = ch2
            }
        }
        return true
    }

    fun distinctSubsequences(input:String):Int{
        val mod = 1000000007
        val n = input.length
        val last = Array(26){-1}
        val dp = Array(n+1){0}
        dp[0] = 1
        for (i in 1..n){
            dp[i] = (2 * dp[i - 1]) % mod
            val item = input[i - 1] - 'a'
            if(last[item] != -1){
                dp[i] = (dp[i] - dp[last[item]] + mod) % mod
            }
            last[item] = i - 1
        }
        return dp[n]
    }

    fun distinctSubsequences_map(input:String):Int{
        val mod = 1000000007
        val n = input.length
        val last = HashMap<Char, Int>()
        val dp = Array(n+1){0}
        dp[0] = 1
        for (i in 1..n){
            dp[i] = (2 * dp[i - 1]) % mod
            val char = input[i-1]
            if(last.containsKey(char)){
                val lastIndex = last.get(char)!! - 1
                dp[i] = (dp[i] - dp[lastIndex] + mod) % mod
            }
            last.put(char, i)
        }
        return dp[n]
    }


//    Input: d = {"ale", "apple", "monkey", "plea"}, S = "abpcplea", Output: "apple"
//    Explanation: After deleting "b", "c" "a" S became "apple" which is present in d.

//    Input: d = {"a", "b", "c"}, S = "abpcplea", Output: "a"
//    Explanation: After deleting "b", "p" "c", "p", "l", "e", "a" S became "a" which is present in d.
    fun findLongestWord(S: String, d: List<String>): String? {
        Collections.sort(d);
        var maxLength = 0
        var result = ""
        for (i in d.indices) {
            val isSubString = isSubString(S, d[i])
            if (isSubString && maxLength < d[i].length) {
                maxLength = d[i].length
                result = d[i]
            }
        }
        return result
    }

    private fun isSubString(s: String, s1: String): Boolean {
        if (s1.length > s.length) return false
        var j = 0
        var i = 0
        while (i < s.length && j < s1.length) {
            if (s[i] == s[i]) {
                j++
            }
            i++
        }
        return j == s1.length
    }

//    Input  : str1 = "3333311111111111",
//    str2 =   "44422222221111"
//    Output : 3377733333332222
//
//    Input  : str1 = "7777555511111111",
//    str2 =    "3332222221111"
//    Output : 7780887733332222
    fun findSumOfTwoLargestStringNumbers(str1: String, str2: String): String? {
        // Before proceeding further, make sure length
        // of str2 is larger.
        var str1 = str1
        var str2 = str2
        if (str1.length > str2.length) {
            val t = str1
            str1 = str2
            str2 = t
        }

        // Take an empty String for storing result
        var str = ""

        // Calculate length of both String
        val n1 = str1.length
        val n2 = str2.length
        val diff = n2 - n1

        // Initially take carry zero
        var carry = 0

        // Traverse from end of both Strings
        for (i in n1 - 1 downTo 0) {
            // Do school mathematics, compute sum of
            // current digits and carry
            val sum = (str1[i] - '0') + (str2[i] - '0') + carry
            val a = (sum % 10)
            str += a.toChar()
            carry = sum / 10
        }

        // Add remaining digits of str2[]
        for (i in n2 - n1 - 1 downTo 0) {
            val sum = (str2[i] - '0') + carry
            str += (sum % 10 + '0'.toInt()).toChar()
            carry = sum / 10
        }

        // Add remaining carry
        if (carry > 0) str += (carry + '0'.toInt()).toChar()

        // reverse resultant String
        val sb = StringBuilder(str)
        var j = sb.length - 1
        while (sb[j] == '0' && j > 0) {
            sb.deleteCharAt(j--)
        }

        // reverse resultant String
        return sb.reverse().toString()
    }

    //    Input : aabcdaabc, Output : 4
    //    The string "aabc" is the longest, prefix which is also suffix.
    //    Input : abcab, Output : 2
    //    Input : aaaa, Output : 2
    fun longestPrefixSuffix(s: String): Int {
        val n = s.length

        // If n is less than 2
        if (n < 2) {
            return 0
        }
        var len = 0
        var i = (n + 1) / 2

        // Iterate i till n
        while (i < n) {

            // If s.charAt(i) is equal to
            // s.charAt(len)
            if (s[i] == s[len]) {
                ++len
                ++i
            } else {
                i = i - len + 1
                len = 0
            }
        }

        // Return len
        return len
    }

    //    Input: abc, k = 2, Output: 2
    //    Possible substrings are {"ab", "bc"}
    //
    //    Input: aba, k = 2, Output: 3
    //    Possible substrings are {"ab", "ba", "aba"}
    //
    //    Input: aa, k = 1, Output: 3
    //    Possible substrings are {"a", "a", "aa"}
    //
    //    Input: abcbaa, k = 3, Output: 8
    //    Possible substrings are {"abc", "abb", "aba" , "aba", "bca", "bca", "cba", "cba"}
    fun countKDist(str: String, k: Int): Int {
        // Initialize result
        var res = 0
        val n = str.length

        // To store seen characters from 'a' to 'z'
        val seen = BooleanArray(26)

        // Consider all substrings beginning with
        // str[i]
        for (i in 0 until n) {
            var distCount = 0
            // mark all chars as unseen
            Arrays.fill(seen, false)
            // Consider all substrings between str[i..j]
            for (j in i until n) {
                // If this is a new character for this
                // substring, increment dist_count.
                if (!seen[str[j] - 'a']) distCount++

                // mark current char as seen
                seen[str[j] - 'a'] = true

                // If distinct character count becomes k,
                // then increment result.
                if (distCount == k) res++
            }
        }
        return res
    }

//    Input  : "zebra", "antelope", "ant", "bear", "lion", "lioness"
//    Output : "ant", "antelope", "bear", "lion", "lioness", "zebra"
    fun sortArrayOfStrings(arr: Array<String>):Array<String>{
        val n = arr.size
        for(i in 0 until n){
            var min = i
            for(j in (i+1) until n){
//                if(arr[j].compareTo(arr[min]) < 0){
                if(arr[j].customCompareTo(arr[min]) < 0){
                    min = j
                }
            }
            if(min != i){
                val temp = arr[i]
                arr[i] = arr[min]
                arr[min] = temp
            }
        }
        return arr
    }

    fun String.customCompareTo(anotherString: String): Int {
        val len1: Int = this.length
        val len2: Int = anotherString.length
        val minLength = Math.min(len1, len2)
        val arrayThis: CharArray = this.toCharArray()
        val anotherArray: CharArray = anotherString.toCharArray()
        var i = 0
        while (i < minLength) {
            val c1 = arrayThis[i]
            val c2 = anotherArray[i]
            if (c1 != c2) {
                return c1 - c2
            }
            i++
        }
        return len1 - len2
    }

//    Input: ["lint","code","love","you"]
//    encoded string: 4#lint4#code4#love3#you
//    Output: ["lint","code","love","you"]
    fun encode(strs: List<String>): String {
        val encodedString = StringBuilder()
        for (str in strs) {
            encodedString.append(str.length).append("#").append(str)
        }
        return encodedString.toString()
    }

    fun decode(str: String): List<String> {
        val list: MutableList<String> = ArrayList()
        var i = 0
        while (i < str.length) {
            var j = i
            while (str[j] != '#') {
                j++
            }
            val length = Integer.valueOf(str.substring(i, j))
            i = j + 1 + length
            list.add(str.substring(j + 1, i))
        }
        return list
    }


}