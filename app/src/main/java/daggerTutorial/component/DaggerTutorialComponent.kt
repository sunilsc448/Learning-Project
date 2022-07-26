package daggerTutorial.component

import com.squareup.picasso.Picasso
import dagger.Component
import daggerTutorial.module.GitHubOtherServiceModule
import daggerTutorial.service.GitHubService
import daggerTutorial.module.GithubServiceModule
import daggerTutorial.module.PicassoModule
import daggerTutorial.scope.GithubAppScope

@GithubAppScope
@Component(modules = [GithubServiceModule::class,GitHubOtherServiceModule::class, PicassoModule::class])
interface DaggerTutorialComponent {
    fun getGithubService(): GitHubService
    fun getPicasso():Picasso
}