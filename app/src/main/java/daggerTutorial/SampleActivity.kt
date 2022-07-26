package daggerTutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlintutorial.AppClass
import com.squareup.picasso.Picasso
import daggerTutorial.adapter.SimpleAdapter
import daggerTutorial.binds.ISampleBind
import daggerTutorial.component.DaggerDaggerTutorialComponent2
import daggerTutorial.module.SampleActivityModule
import daggerTutorial.repository.GithubRepository
import daggerTutorial.service.GitHubService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SampleActivity : AppCompatActivity(),ISampleBind {
    @Inject lateinit var adapter:SimpleAdapter
    @Inject lateinit var githubRepository: GithubRepository
    @Inject lateinit var picasso:Picasso
    @Inject lateinit var githubService:GitHubService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component=
            DaggerDaggerTutorialComponent2.builder().
            sampleActivityModule(SampleActivityModule(this)).
            daggerTutorialComponent(AppClass.application.getDaggerTutorialComponent()).
            build()
        component.injectSampleActivity(this)
        main()
    }

    fun main() = runBlocking {
        val start = System.currentTimeMillis()
        val jobs = List(10_000){
            launch {
                delay(1000) // delays for 1000 millis
                print(".")
            }
        }
        jobs.forEach { it.join() }
        val end = System.currentTimeMillis()
        println()
        println(end-start)
    }

    override fun sampleMethod() {

    }
}