package com.example.kotlintutorial

import android.app.Application
import component.CarComponent
import component.DaggerCarComponent

class AppClassForComponent:Application() {
    lateinit var carComponent: CarComponent
    override fun onCreate() {
        super.onCreate()
        carComponent = DaggerCarComponent.builder().mileage(18).engineCapacity(1192).build()
    }

    fun getGlobalCarComponent():CarComponent{
        return carComponent
    }
}