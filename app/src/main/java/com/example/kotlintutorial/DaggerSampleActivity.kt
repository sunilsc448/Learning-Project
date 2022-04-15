package com.example.kotlintutorial

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.internal.DaggerCollections
import dagger.internal.DaggerGenerated
import component.DaggerCarComponent
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import modules.DieselEngineModule
import modules.PetrolEngineModule
import modules.WheelsModule
import pojos.Car
import pojos.Engine
import pojos.Wheels
import java.lang.Exception
import javax.inject.Inject

class DaggerSampleActivity : AppCompatActivity() {
    private val RESULT1 = "Result #1"
    private val RESULT2 = "Result #2"

    @Inject
    lateinit var car: Car
    @Inject
    lateinit var car1: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        isVersionEligible("10.0.10", "10.1.0")
        isTravelH5SegmentedUser("12345678911")

        btn.setOnClickListener {
            CoroutineScope(IO).launch {
                fakeAPICall()
            }
        }

        //Constructor level Injection
        //constructorInjection()

        //Field level Injection
        fieldInjection()



    }

    private fun fieldInjection() {
//        var carComponent = DaggerCarComponent.builder().build()
//        var carComponent = DaggerCarComponent.builder().petrolEngineModule(PetrolEngineModule(12)).build()
        var carComponent1 = (application as AppClassForComponent).getGlobalCarComponent()
        carComponent1.inject(this)
        car.start()
        car1.start()
    }

    private fun constructorInjection() {
        car = DaggerCarComponent.builder().build().getCar()
        car.start()
    }

    fun isVersionEligible(currentVersion: String, targetedVersion: String): Boolean {
        val currentVersionFormatted = currentVersion.split("\\.".toRegex()).toTypedArray()
        val targetedVersionFormatted = targetedVersion.split("\\.".toRegex()).toTypedArray()
        val length = Math.max(currentVersionFormatted.size, targetedVersionFormatted.size)
        var result:Boolean = true
        for (i in 0 until length) {
            val cv = if (i < currentVersionFormatted.size) currentVersionFormatted[i].toInt() else 0
            val tv = if (i < targetedVersionFormatted.size) targetedVersionFormatted[i].toInt() else 0
            if(cv < tv){
                result = false
                break;
            }else if(cv > tv){
                break;
            }
        }
        return result
    }

    private fun isTravelH5SegmentedUser(userID: String): Boolean {
        var isSegmentedUser = false
        try {
            val userIdNumber = userID.toLong()
            val lastTwoDigits = userIdNumber % 100
            val rolloutPercent: Int = 5
            if (lastTwoDigits < rolloutPercent) {
                isSegmentedUser = true
            }
        } catch (ignore: Exception) {
            println(""+ignore)
        }
        return isSegmentedUser
    }

    private suspend fun setTextOnMainThread(input:String){
        CoroutineScope(Main).launch{
            setText(input)
        }
    }

    private fun setText(input:String){
        val newText = txt.text.toString()+"\n$input"
        txt.text = newText
    }

    private suspend fun fakeAPICall(){
        val result1 = getResult1FromApi()
        println("debug: $result1")
        setTextOnMainThread(result1)

        val result2 = getResult2FromApi(result1)
        println("debug: $result2")
        setTextOnMainThread(result2)
    }

    private suspend fun getResult1FromApi():String{
        logThread("getResult1FromApi")
        delay(2000)
        return RESULT1
    }

    private suspend fun getResult2FromApi(result1: String):String{
        logThread("getResul2tFromApi")
        delay(2000)
        return RESULT2
    }

    private fun logThread(methodName:String){
        println("debug: ${methodName}: ${Thread.currentThread().name}")
    }
}