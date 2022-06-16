package com.example.kotlintutorial.UT

class SingletonIssue {
    fun getTotalSteps() = StepsCounter.totalCount

    //2 steps added
    fun walk(){
        StepsCounter.walk()
    }

    //4 stels added
    fun run(){
        StepsCounter.run()
    }
}

object StepsCounter{
    var totalCount = 0
    get() = field

    fun walk(){
        totalCount += 2
    }
    fun run(){
        totalCount += 4
    }
}