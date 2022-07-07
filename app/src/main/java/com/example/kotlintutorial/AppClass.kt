package com.example.kotlintutorial

import movies.component.DaggerMoviesComponent
import movies.component.MoviesComponent
import movies.modules.AppModule
import movies.modules.MoviesApiModule
import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging
import component.CarComponent
import component.DaggerCarComponent
import movies.modules.RoomModule

class AppClass:Application() {
    lateinit var carComponent: CarComponent
    lateinit var moviesComponent: MoviesComponent
    override fun onCreate() {
        super.onCreate()
        application = this
        moviesComponent = initMoviesComponent(this)
        carComponent = initCarComponent()
        SampleJavaClass.SampleInnerClass().method1()
    }

    private fun initCarComponent():CarComponent{
        return DaggerCarComponent.builder().mileage(18).engineCapacity(1192).build()
    }

    private fun initMoviesComponent(appClass: AppClass):MoviesComponent{
        return  DaggerMoviesComponent.builder().
                appModule(AppModule(appClass)).
                roomModule(RoomModule()).
                moviesApiModule(MoviesApiModule()).
                build()
    }

    fun getGlobalCarComponent():CarComponent{
        return carComponent
    }

    companion object{
        lateinit var application:AppClass
        private set
    }
}