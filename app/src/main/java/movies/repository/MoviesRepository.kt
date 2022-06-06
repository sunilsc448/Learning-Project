package movies.repository

import movies.models.Movie
import movies.models.MoviesResponse
import movies.network.MoviesApiService
import javax.inject.Inject
import javax.security.auth.callback.Callback

class MoviesRepository @Inject constructor(private val apiService:MoviesApiService) {
    fun fetchPopularMovies(callback:retrofit2.Callback<MoviesResponse>){
        val call = apiService.getPopularMoviesAsync()
        call.enqueue(callback)
    }

    fun fetchMovieByQuery(query:String, callback:retrofit2.Callback<MoviesResponse>){
        val call = apiService.getMoviesbyQueryAsync(query)
        call.enqueue(callback)
    }
}