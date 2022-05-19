package com.example.kotlintutorial.KotlinSamples

class LeetCodeIteratorSample {
    init {
//        // [[1,1],2,[1,1]]
//        val nestedInteger1 = NestedIntegerClass()
//        nestedInteger1.add(NestedIntegerClass(1))
//        nestedInteger1.add(NestedIntegerClass(1))
//
//        val nestedInteger2 = NestedIntegerClass(2)
//
//        val nestedInteger3 = NestedIntegerClass()
//        nestedInteger3.add(NestedIntegerClass(1))
//        nestedInteger3.add(NestedIntegerClass(1))
//
//        val finalList1:List<NestedInteger> = listOf(nestedInteger1, nestedInteger2, nestedInteger3)
//
//        // [1,[4,[6]]]
//        val nestedInteger4 = NestedIntegerClass(1)
//        val nestedInteger5 = NestedIntegerClass()
//        nestedInteger5.add(NestedIntegerClass(4))
//        val nestedInteger6 = NestedIntegerClass()
//        nestedInteger6.add(NestedIntegerClass(6))
//        nestedInteger5.add(nestedInteger6)
//
//        val finalList2:List<NestedInteger> = listOf(nestedInteger4, nestedInteger5)
//
//        val obj = NestedIterator(finalList2)
//        var param_1 = obj.next()
//        var param_2 = obj.hasNext()
//        println(param_1)
    }
}

class NestedIterator(nestedList: List<NestedInteger>) {
    var list = ArrayList<Int?>()
    var current = 0

    init{
        nestedList.forEach{
            addItemToList(it)
        }
    }

    fun addItemToList(item:NestedInteger){
        if(item.isInteger()){
            list.add(item.getInteger())
        }else{
            val subList = item.getList()
            if(subList != null){
                for (i in subList.indices){
                    addItemToList(subList.get(i))
                }
            }
        }
    }

    fun next(): Int {
        return list.elementAt(current)!!
    }

    fun hasNext(): Boolean {
        return current < list.size
    }
}

class NestedIntegerClass(): NestedInteger() {
    private var isSingleInt = false
    private var singleInt:Int? = null
    private var list:ArrayList<NestedInteger>? = ArrayList<NestedInteger>()

    constructor(value: Int): this(){
        list = null
        isSingleInt = true
        singleInt = value
    }

    override fun isInteger(): Boolean {
        return isSingleInt
    }

    override fun getInteger(): Int? {
        return singleInt
    }

    override fun setInteger(value: Int) {
        list?.add(NestedIntegerClass(value))
    }

    override fun add(ni: NestedInteger) {
        list?.add(ni)
    }

    override fun getList(): List<NestedInteger>? {
        return list
    }
}

abstract class NestedInteger {
     // Constructor initializes an empty nested list.
     constructor()

     // Constructor initializes a single integer.
     constructor(value: Int)

     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     abstract fun isInteger(): Boolean

     // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
     abstract fun getInteger(): Int?

     // Set this NestedInteger to hold a single integer.
     abstract fun setInteger(value: Int): Unit

     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
     abstract fun add(ni: NestedInteger): Unit

     // @return the nested list that this NestedInteger holds, if it holds a nested list
     // Return null if this NestedInteger holds a single integer
    abstract fun getList(): List<NestedInteger>?
}