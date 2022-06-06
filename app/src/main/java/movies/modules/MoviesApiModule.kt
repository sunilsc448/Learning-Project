package movies.modules

import movies.network.MoviesApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY_KEY = "api_key"
private const val API_KEY = "f71f07603a3973d3d11fd834e8a0f0c8"
@Module
class MoviesApiModule {

    @Provides
    @Singleton
    fun getInterceptor():Interceptor{
        return Interceptor { chain ->  
            val newUrl = chain.request().url().newBuilder().addQueryParameter(API_KEY_KEY, API_KEY).build()
            val newRequest = chain.request().newBuilder().url(newUrl).build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun getHttpClient(interceptor: Interceptor):OkHttpClient{
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getService(retrofit: Retrofit):MoviesApiService{
        return retrofit.create(MoviesApiService::class.java)
    }
}