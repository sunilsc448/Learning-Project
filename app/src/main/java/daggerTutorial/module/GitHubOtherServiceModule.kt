package daggerTutorial.module

import dagger.Module
import dagger.Provides
import daggerTutorial.qualifiers.GithubOtherServiceRetrofit
import daggerTutorial.scope.GithubAppScope
import daggerTutorial.service.GitHubOtherService
import daggerTutorial.service.GitHubService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module(includes = [NetworkModule::class])
class GitHubOtherServiceModule {
    @Provides
    @GithubAppScope
    @GithubOtherServiceRetrofit
//    @Named("GitHubOtherServiceRetrofit")
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://www.google.com").
        client(okHttpClient).
        addConverterFactory(GsonConverterFactory.create()).
        build()
    }

    @Provides
    @GithubAppScope
    fun getGitGubServive(@GithubOtherServiceRetrofit retrofit: Retrofit): GitHubOtherService {
        return retrofit.create(GitHubOtherService::class.java)
    }
}