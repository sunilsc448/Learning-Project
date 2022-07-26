package daggerTutorial.module

import dagger.Module
import dagger.Provides
import daggerTutorial.scope.GithubAppScope
import daggerTutorial.service.GitHubService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module(includes = [NetworkModule::class])
class GithubServiceModule {
    @Provides
    @GithubAppScope
//    @Named("GitHubServiceRetrofit")
    fun getRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder().baseUrl("https://www.google.com").
               client(okHttpClient).
               addConverterFactory(GsonConverterFactory.create()).
               build()
    }

    @Provides
    @GithubAppScope
    fun getGitGubServive(retrofit:Retrofit): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }
}