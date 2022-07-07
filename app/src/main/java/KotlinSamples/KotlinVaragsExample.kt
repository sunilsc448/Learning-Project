package KotlinSamples

class KotlinVaragsExample {
    init {
        val list = arrayListOf(1, 2, 3, 4)
        getAverage(list)

        //same type function using varags
        val nums = intArrayOf(1, 2, 3, 4)
        getAverageVarags(1,2,3,4)//can be called like this
        getAverageVarags(1,2,3,4, *nums)//even like this

        val list_mix = arrayListOf(1.2f, 2.2f, 3.3f, 4.4f, "sunil")
        val list_numbers = intArrayOf(7, 8, 5, 9)
        val output = asList<Int>(1, 2, 3, 4)
        println(output)
    }

    fun getAverage(numbersList:ArrayList<Int>):Float{
        var sum = 0.0f
        for (item in numbersList) {
            sum += item
        }
        return (sum / numbersList.size)
    }

    fun getAverageVarags(vararg input:Int):Float{
        var sum = 0.0f
        for (item in input) {
            sum += item
        }
        return (sum / input.size)
    }

    fun <T> asList(vararg input: T): List<T> {
        val result = ArrayList<T>()
        for (item in input) // input is an Array
            result.add(item)
        return result
    }
}