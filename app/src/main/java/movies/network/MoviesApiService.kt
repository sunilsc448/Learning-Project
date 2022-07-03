package movies.network

import movies.models.MoviesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/popular")
    fun getPopularMoviesAsync():Call<MoviesResponse>

    @GET("search/movie")
    fun getMoviesbyQueryAsync(@Query("query")query:String):Call<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies():Response<MoviesResponse>
}