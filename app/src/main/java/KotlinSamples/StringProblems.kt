package KotlinSamples

import java.lang.StringBuilder

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
            sbArray[index].append(s[i])
            if(index == 0){
                incrementer = 1
            }else if(index == numRows - 1){
                incrementer = -1
            }
            index += incrementer
            while (index > numRows- 1){
                index--
            }
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

}