package daggerTutorial.module

import android.content.Context
import dagger.Module
import dagger.Provides
import daggerTutorial.scope.GithubAppScope
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.File
import javax.inject.Singleton

private const val API_KEY_KEY = ""
private const val API_KEY = ""
private const val CACHE_SIZE:Long = 10 * 1024 * 1024
private const val FILE_NAME = "my_cache_file"

@Module(includes = [ContextModule::class])
class NetworkModule {
    @Provides
    @GithubAppScope
    fun getInterCeptor(): Interceptor {
        return Interceptor { chain ->
            val newUrl = chain.request().url().newBuilder().addQueryParameter(API_KEY_KEY, API_KEY).build()
            val newRequest = chain.request().newBuilder().url(newUrl).build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    @GithubAppScope
    fun getFile(context: Context): File {
        return File(context.cacheDir, FILE_NAME)
    }

    @Provides
    @GithubAppScope
    fun getCache(file: File): Cache {
        return Cache(file, CACHE_SIZE)
    }

    @Provides
    @GithubAppScope
    fun getOkHttpClient(cache: Cache, interceptor: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder().
        cache(cache).
        addInterceptor(interceptor).
        build()
    }

}