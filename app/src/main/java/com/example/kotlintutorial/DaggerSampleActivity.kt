package com.example.kotlintutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import component.DaggerCarComponent
import pojos.Car
import java.lang.Exception
import javax.inject.Inject

class DaggerSampleActivity : AppCompatActivity() {
    @Inject
    lateinit var car: Car
    @Inject
    lateinit var car1: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        isVersionEligible("10.0.10", "10.1.0")
        isTravelH5SegmentedUser("12345678911")

        //Constructor level Injection
        //constructorInjection()

        //Field level Injection
        fieldInjection()
    }

    private fun fieldInjection() {
//        var carComponent = DaggerCarComponent.builder().build()
//        var carComponent = DaggerCarComponent.builder().petrolEngineModule(PetrolEngineModule(12)).build()
        var carComponent1 = (application as AppClass).getGlobalCarComponent()
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
}