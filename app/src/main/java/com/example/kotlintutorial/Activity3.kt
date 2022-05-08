package com.example.kotlintutorial

import KotlinSamples.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_3.*
import kotlinx.coroutines.Delay
import java.time.DateTimeException
import java.time.LocalDateTime

class Activity3 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
//        stopWatch()
//        val inputArray:IntArray = intArrayOf(2,2)
//        containerMostWater(inputArray)
//        print("Area is ${getMaxAreaHistogram(inputArray, 4)}")
//        largestElementInAnArray(inputArray)
//        smallestElementInAnArray(inputArray)
//        minAndMax(inputArray)
//        addTwoNumbers(getNode1(), getNode2())
//        println("lengthOfLongestSubstring >>> ${lengthOfLongestSubstring("geeksforgeeks")}")
//        println("lastStoneWeight is >>> ${lastStoneWeight(inputArray)}")
//        calPoints(arrayOf("1"))
//        mergeSort(intArrayOf(5, 3, 2, 1, 4), 0, 4);
//        shiftGrid(arrayOf(intArrayOf(1,2,3),intArrayOf(4,5,6),intArrayOf(7,8,9)), 2)
//        gameOfLife(arrayOf(intArrayOf(0,1,0),intArrayOf(0,0,1),intArrayOf(1,1,1), intArrayOf(0,0,0)))
//        secondHighest("dfa12321afd")
//        val stringArray:Array<String> = arrayOf("683339452288515879","7846081062003424420","4805719838","4840666580043","83598933472122816064","522940572025909479","615832818268861533","65439878015","499305616484085","97704358112880133","23861207501102","919346676","60618091901581","5914766072","426842450882100996","914353682223943129","97","241413975523149135","8594929955620533","55257775478129","528","5110809","7930848872563942788","758","4","38272299275037314530","9567700","28449892665","2846386557790827231","53222591365177739","703029","3280920242869904137","87236929298425799136","3103886291279")
//        val stringArray:Array<String> = arrayOf("2","21","12","1")
//        kthLargestNumberComparatorApproach(stringArray, 3)
//        generateMatrix(3)
//        maxSlidingWindowDeque(intArrayOf(4,1,3,5,1,2,3,2,1,1,5),3)
//        trimBST(getBSTree(), -13, 14)
        firstFunction()
    }

    private fun firstFunction() {
//        ArrayPrograms()
//        BinarySearchTree()
//        KotlinCollections()
//        HashMapKeySample("sunil")
//        FilterAndMapSample()
//        PredicateSample()
//        SafeCallSample()
//        LateinitSample()
//        BackingFieldSample()
//        LazyInitilisationSample()
//        HighLevelFunctions()
//        ScopeFunctionsSample()
//        LeetCodeHashMap()
//        LeetCodeHashSet()
//        LeetCodeBSTIterator()
//        StringProblems()
//        MatrixSamples()
//        SortingSample()
//        MapsSample()
//        MiscPrograms()
//        StackSamples()
//        StringProblems()
//        Handler().postDelayed(Runnable {
//            CouroutineSamples()
//        }, 4000)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun stopWatch() {
        var txtVw = findViewById<TextView>(R.id.txtVw)
        var time = 1
        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txtVw.setText(time.toString())
                time++
            }

            override fun onFinish() {
                txtVw.setText("countdown finished ${time-1}")
            }
        }.start()
    }
}


