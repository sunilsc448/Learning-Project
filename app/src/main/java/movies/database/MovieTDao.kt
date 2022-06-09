package movies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import movies.models.MovieT

@Dao
interface MovieTDao {
    @Insert
    fun insertPopularMovie(movie:MovieT)

    @Insert
    fun insertAllPopularMovies(movies: List<MovieT>)

    @Query("DELETE FROM Movies")
    fun deleteAllMovies()

    @Query("SELECT * FROM Movies")
    fun getPopularMovies():List<MovieT>
}