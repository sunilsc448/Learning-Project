package movies.modules

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import movies.database.MoviesRoomDatabase
import movies.network.MoviesApiService
import retrofit2.Retrofit
import javax.inject.Singleton

private const val MOVIESDATABASENAME = "TMDB"
@Module
class RoomModule {
    @Singleton
    @Provides
    fun getRoomDatabase(application: Application): MoviesRoomDatabase {
        val room =  Room.databaseBuilder(application, MoviesRoomDatabase::class.java, MOVIESDATABASENAME).build()
        return room
    }
}