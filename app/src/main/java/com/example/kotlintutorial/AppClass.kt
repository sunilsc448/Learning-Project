package com.example.kotlintutorial

import movies.component.DaggerMoviesComponent
import movies.component.MoviesComponent
import movies.modules.AppModule
import movies.modules.MoviesApiModule
import android.app.Application
import daggerTutorial.component.DaggerDaggerTutorialComponent
import daggerTutorial.component.DaggerTutorialComponent
import daggerTutorial.module.ContextModule
import movies.modules.RoomModule

class AppClass:Application() {
    private lateinit var moviesComponent: MoviesComponent
    private lateinit var daggerTutorialComponent: DaggerTutorialComponent
    override fun onCreate() {
        super.onCreate()
        application = this
        initDaggerTutorialComponent(this)
        moviesComponent = initMoviesComponent(this)
        SampleJavaClass.SampleInnerClass().method1()
    }

    private fun initDaggerTutorialComponent(appClass: AppClass) {
        daggerTutorialComponent = DaggerDaggerTutorialComponent.builder().
                contextModule(ContextModule(appClass)).build()

        val daggerTutorialComponent2 = DaggerDaggerTutorialComponent.builder().
        contextModule(ContextModule(appClass)).build()

        val githubservice1 = daggerTutorialComponent.getGithubService()
        val picasso1 = daggerTutorialComponent.getPicasso()

        val githubservice2 = daggerTutorialComponent.getGithubService()
        val picasso2 = daggerTutorialComponent.getPicasso()

        val githubservice3 = daggerTutorialComponent2.getGithubService()
        val picasso3 = daggerTutorialComponent2.getPicasso()

        println("githubservice1 > $githubservice1 & picasso1 > $picasso1")
        println("githubservice2 > $githubservice2 & picasso2 > $picasso2")
        println("githubservice3 > $githubservice3 & picasso3 > $picasso3")
    }

    private fun initMoviesComponent(appClass: AppClass):MoviesComponent{
        return  DaggerMoviesComponent.builder().
                appModule(AppModule(appClass)).
                roomModule(RoomModule()).
                moviesApiModule(MoviesApiModule()).
                build()
    }

    fun getDaggerTutorialComponent():DaggerTutorialComponent = daggerTutorialComponent

    fun getMoviesComponent():MoviesComponent = moviesComponent

    companion object{
        lateinit var application:AppClass
        private set
    }
}