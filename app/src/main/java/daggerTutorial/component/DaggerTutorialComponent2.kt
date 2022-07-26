package daggerTutorial.component

import com.squareup.picasso.Picasso
import dagger.Component
import daggerTutorial.SampleActivity
import daggerTutorial.SampleBindModule
import daggerTutorial.adapter.SimpleAdapter
import daggerTutorial.binds.ISampleBind
import daggerTutorial.module.SampleActivityModule
import daggerTutorial.scope.SampleActivityScope
import daggerTutorial.service.GitHubService

@Component(modules = [SampleActivityModule::class, SampleBindModule::class], dependencies = [DaggerTutorialComponent::class])
@SampleActivityScope
interface DaggerTutorialComponent2 {
    fun injectSampleActivity(activity:SampleActivity)
    fun getGithubService():GitHubService
    fun getPicasso():Picasso
}