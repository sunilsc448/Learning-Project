package movies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import movies.models.MovieT

@Database(entities = [MovieT::class], version = MOVIES_DATABASE_VERSION)
abstract class MoviesRoomDatabase: RoomDatabase() {
    abstract fun moviesDao(): MovieTDao
}

private const val MOVIES_DATABASE_VERSION = 1