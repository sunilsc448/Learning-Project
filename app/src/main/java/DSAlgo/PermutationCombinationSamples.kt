package DSAlgo

import java.util.*

class PermutationCombinationSamples {
    init {
//        permutationOfString("ABC")
//        letterCombinations("578")
//        letterCombinationsRecurrsion("2")
        callCombi()
    }


    fun permutationOfString(input:String){
        permute(input,0, input.length-1)
    }

    private fun permute(input: String, l: Int, r: Int) {
        var inputLocal = input
        if(l == r) {
            println("permuted string is $inputLocal")
        }else {
            for (i in l..r) {
                inputLocal = swapChars(inputLocal, i, l)
                permute(inputLocal,l + 1, r)
                inputLocal = swapChars(inputLocal, i, l)
            }
        }
    }

    private fun swapChars(input: String, i: Int, l: Int):String {
        val charArray = input.toCharArray()
        val temp = charArray[i]
        charArray[i] = charArray[l]
        charArray[l] = temp
        return String(charArray)
    }

    //    Input: digits = "23"
//    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
//    Input: digits = "2"
//    Output: ["a","b","c"]
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
            //list of latest digit
            val list = map.get(digits[index])!!
            //flatmap with prev flat list
            rsltList = rsltList.flatMap { item1 ->list.flatMap { item2 -> arrayListOf(item1 + item2)}}
            index++
        }

        return rsltList
    }

    fun letterCombinationsRecurrsion(digits: String): List<String> {
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

        val rsltList = map.get(digits[0])!!
        if(digits.length == 1){
            return rsltList
        }
        val list = customConcat(digits, map, 1, rsltList)!!
        return list
    }

    private fun customConcat(digits: String, map: Map<Char, List<String>>, index: Int, resultList:List<String>):List<String>? {
        var rsltList =resultList
        if(index < digits.length){
            val list = map.get(digits[index])!!
            rsltList = rsltList.flatMap { a ->list.flatMap { b -> arrayListOf(a + b)}}
            rsltList =  customConcat(digits, map, index + 1, rsltList)!!
        }
        return rsltList
    }

    fun callCombi(){
        val k = 3
        val n = 7
        ans = makeCombi(n, k)
    }

    fun makeCombi(n: Int, k: Int): Vector<Vector<Int>> {
        makeCombiUtil(n, 1, k)
        return ans
    }

    var ans: Vector<Vector<Int>> = Vector<Vector<Int>>()
    var tmp: Vector<Int> = Vector<Int>()
    fun makeCombiUtil(n: Int, left: Int, k: Int) {
        // Pushing this vector to a vector of vector
        if (k == 0) {
            ans.add(tmp)
            for (i in 0 until tmp.size) {
                print("combi item is"+ tmp.get(i).toString() + " ")
            }
            println()
            return
        }

        // i iterates from left to n. First time
        // left will be 1
        for (i in left..n) {
            tmp.add(i)
            makeCombiUtil(n, i + 1, k - 1)

            // Popping out last inserted element
            // from the vector
            tmp.remove(tmp.size - 1)
        }
    }
}