package KotlinSamples

class MiscPrograms {
    init {
//        reverse(-1563847412)
//        reverse(1563847412)
//        reverse(123)
//        intToRoman(1994)
//        romanToInt("LVIII")
    }

    //-1563847412
    fun reverse(x: Int): Int {
        if(x > Int.MAX_VALUE || x <= Int.MIN_VALUE){
            return 0
        }
        var num = x
        var sum:Long = 0

        while (num != 0){
            val rem = num % 10
            num /= 10
            sum = (sum*10) + rem
        }
//        sum += getSum(x, sum)
        if(sum > Int.MAX_VALUE || sum <= (Int.MIN_VALUE)){
            return 0
        }else {
            return sum.toInt()
        }
    }

    private fun getSum(x:Int, sum:Long):Long{
        if(x == 0)
            return sum
        val qtnt = x/10
        val rem = x % 10
        val newSum = (sum*10) + rem
        return getSum(qtnt, newSum)
    }

    private fun intToRoman(num: Int):String{
        val ones = arrayOf("","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
        val tens = arrayOf("","X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
        val hundreds = arrayOf("","C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
        val thousands = arrayOf("","M", "MM", "MMM")
        val result = thousands[num/1000] + hundreds[(num%1000)/100] + tens[num%100/10] + ones[num%10]
        return result
    }

    //LIX > 59
    //MCDXCVIII > 1498
    //CMXVI > 916
    fun romanToDecimal(str: String): Int {
        var decimal = 0
        var i = 0
        while (i < str.length) {
            val val1 = JavaSamples.getValueForRomanChar(str[i])
            if (i == str.length - 1) {
                decimal += val1
            } else {
                val val2 = JavaSamples.getValueForRomanChar(str[i + 1])
                if (val1 < val2) {
                    decimal += val2 - val1
                    i++
                } else {
                    decimal += val1
                }
            }
            i++
        }
        return decimal
    }

    //McMXCIX" > 1999
    //XXXVII" > 37
    //"LVIII" > 58
    //CMXVI >  916
    fun romanToInt(s: String):Int{
        var sum = 0
        var i = s.length - 1
        while (i >= 0){
            if(i == 0){
                sum = sum + getNumberFromRomanChar(s[i])
                break;
            }else{
                val num1 = getNumberFromRomanChar(s[i])
                val num2 = getNumberFromRomanChar(s[i-1])
                if(num1 > num2){
                    sum = sum + (num1 - num2)
                    i--
                }else{
                    sum = sum + num1
                }
            }

            i--
        }
        return sum
    }

    private fun getNumberFromRomanChar(c:Char):Int{
        return when(c){
            'M' -> 1000
            'D' -> 500
            'C' -> 100
            'L' -> 50
            'X' -> 10
            'V' -> 5
            'I' -> 1
            else -> 0
        }
    }
}