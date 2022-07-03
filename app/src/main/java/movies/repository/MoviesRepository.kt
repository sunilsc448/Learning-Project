package movies.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import movies.database.MoviesRoomDatabase
import movies.models.Movie
import movies.models.MovieT
import movies.models.MoviesResponse
import movies.network.MoviesApiService
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiService:MoviesApiService, private val roomDatabase: MoviesRoomDatabase) {
    fun fetchPopularMovies(callback:retrofit2.Callback<MoviesResponse>){
        val call = apiService.getPopularMoviesAsync()
        call.enqueue(callback)
    }

    fun fetchMovieByQuery(query:String, callback:retrofit2.Callback<MoviesResponse>){
        val call = apiService.getMoviesbyQueryAsync(query)
        call.enqueue(callback)
    }

    fun getPopularMoviesFromDB():List<Movie>{
        val list = roomDatabase.moviesDao().getPopularMovies()
        return getMoviesFromEntityMovies(list)
    }

    fun addPopularMoviesToDB(movies: List<Movie>?) {
        if(movies == null)return
        val movieDao = roomDatabase.moviesDao()
        val list:List<MovieT> = getEntityMoviesList(movies)
        if(movieDao.getPopularMovies().size == 0) {
            movieDao.insertAllPopularMovies(list)
        }
    }

    private fun getEntityMoviesList(movies: List<Movie>?): List<MovieT> {
        val newList = mutableListOf<MovieT>()
        movies!!.forEach {
            val item = MovieT(it.id!!, it.title, it.overview, it.posterPath, it.releaseDate,
                it.originalTitle,it.originalLanguage, it.popularity, it.voteAverage)
            newList.add(item)
        }
        return newList
    }

    private fun getMoviesFromEntityMovies(movies: List<MovieT>?): List<Movie> {
        val newList = mutableListOf<Movie>()
        movies!!.forEach {
            val item = Movie(it.id, it.title, it.overview, it.posterPath, it.releaseDate,
                it.originalTitle,it.originalLanguage,it.popularity, it.voteAverage)
            newList.add(item)
        }
        return newList
    }
}