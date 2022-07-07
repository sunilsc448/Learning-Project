package com.example.kotlintutorial.movies

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.google.gson.Gson
import junit.framework.Assert.assertNotNull
import movies.database.MovieTDao
import movies.database.MoviesRoomDatabase
import movies.models.Movie
import movies.models.MoviesResponse
import movies.network.MoviesApiService
import movies.repository.MoviesRepository
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStreamReader
import java.net.HttpURLConnection

class MoviesRepositoryTest {
    private lateinit var SUT:MoviesRepository
    private lateinit var mockWebServer:MockWebServer
    private lateinit var moviesApiServiceTD:MoviesApiServiceTD
    private lateinit var moviesRoomDatabaseTD:MoviesRoomDatabaseTD
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mockWebServer = MockWebServer()
        mockWebServer.start()

//        moviesApiServiceTD = MoviesApiServiceTD()
//        moviesRoomDatabaseTD = MoviesRoomDatabaseTD()
//        SUT = MoviesRepository(moviesApiServiceTD, moviesRoomDatabaseTD)
    }

    @Test
    fun success_listenersNotified_correctData(){
        val reader = MockResponseFileReader("res/raw/movies_success.json")
        assertNotNull(reader.content)
    }

    companion object{
        fun successDataString():String {
            val lists = ArrayList<Movie>()
            lists.add(Movie(1, "Charlie 777","Charlie is a movie about the chracters Dharma and a dog name Charlie",
                "shorturl.at/mHMT4", "June 9, 2022","Charlie 777", "Kannada", 9.9, 9.9f  ))
            lists.add(Movie(2, "KGF 2","KGF2 is sequel of KGF1 which is criminal based mafia film which plays arround a gold mine called KGF",
                "shorturl.at/ewzVY", "April 10, 2022","KGF Chapter 2", "Kannada", 9.9, 9.9f  ))
            val response =  MoviesResponse(1, lists, 1,1)
            val jsonString = Gson().toJson(response)
            return jsonString
        }

        fun successData():MoviesResponse {
            val lists = ArrayList<Movie>()
            lists.add(Movie(1, "Charlie 777","Charlie is a movie about the chracters Dharma and a dog name Charlie",
                "shorturl.at/mHMT4", "June 9, 2022","Charlie 777", "Kannada", 9.9, 9.9f  ))
            lists.add(Movie(2, "KGF 2","KGF2 is sequel of KGF1 which is criminal based mafia film which plays arround a gold mine called KGF",
                "shorturl.at/ewzVY", "April 10, 2022","KGF Chapter 2", "Kannada", 9.9, 9.9f  ))
            val response =  MoviesResponse(1, lists, 1,1)
            return response
        }
    }
}

private class MoviesApiServiceTD: MoviesApiService{
    override fun getPopularMoviesAsync(): Call<MoviesResponse> {

        val call = object : Call<MoviesResponse>{
            override fun clone(): Call<MoviesResponse> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<MoviesResponse> {
               return Response.success(MoviesRepositoryTest.successData())
            }

            override fun enqueue(callback: Callback<MoviesResponse>) {
                TODO("Not yet implemented")
            }

            override fun isExecuted(): Boolean {
                TODO("Not yet implemented")
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }

            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }
        }
        return call.clone()
    }

    override fun getMoviesbyQueryAsync(query: String): Call<MoviesResponse> {
        TODO("Not yet implemented")
    }

    override fun getPopularMovies(): Response<MoviesResponse> {
        TODO("Not yet implemented")
    }
}

private class MoviesRoomDatabaseTD: MoviesRoomDatabase(){
    override fun moviesDao(): MovieTDao {
        TODO("Not yet implemented")
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }
}

class MockResponseFileReader(path: String) {
    val content: String
    init {
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }
}