package KotlinSamples

class SlidingWindowProblems {
    init {

    }

//    Input: prices = [7,1,5,3,6,4]
//    Output: 5
//    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

//    Input: prices = [7,6,4,3,1]
//    Output: 0
//    Explanation: In this case, no transactions are done and the max profit = 0.
    fun maxProfit(prices: IntArray): Int {
        var left = 0; var right = prices.size - 1
        var maxProfit = 0
        while(left < right){
            val profit = prices[right] - prices[left]
            maxProfit = Math.max(profit, maxProfit)
            val nextProfitLeft = prices[right] - prices[left + 1]
            val nextProfitRight = prices[right-1] - prices[left]
            if(nextProfitRight > nextProfitLeft && nextProfitRight > maxProfit){
                right--
            }else{
                left++
            }

        }
        return maxProfit
    }

    //abcbde > cbde > 4
    //pwwkew > wke > 3
    fun longestSubStringWithoutDuplicate(s:String):Int{
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

    fun longestSubStringWithoutDuplicateAnotherApproach(s: String): Int {
        var test = ""
        var maxLength = 0
        for (char in s){
            val current: String = char.toString()
            if(test.contains(current)){
                test = test.substring(test.indexOf(current) + 1)
            }

            test += current
            maxLength = Math.max(test.length, maxLength)
        }
        return maxLength
    }

    fun longestSubStringSlidingWindow(s:String):Int{
        val chars = IntArray(128)
        var left = 0
        var right = 0
        var res = 0
        while (right < s.length) {
            val r = s[right]
            chars[r - 'a']++
            while (chars[r - 'a'] > 1) {
                val l = s[left]
                chars[l - 'a']--
                left++
            }
            res = Math.max(res, right - left + 1)
            right++
        }
        return res
    }
}