package movies.viewmodel

import Utility.SingleLiveEvent
import movies.models.Movie
import movies.models.MoviesLoadingState
import movies.repository.MoviesRepository
import androidx.lifecycle.*
import kotlinx.coroutines.*
import movies.models.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val repository:MoviesRepository):ViewModel(){
    private var itemClick:MutableLiveData<Movie> = SingleLiveEvent()
    fun getItemClick():LiveData<Movie>{
        return itemClick
    }

    var editBoxText:MutableLiveData<String> = MutableLiveData()
    fun getEditBoxText():LiveData<String> = editBoxText

    private var movies:MediatorLiveData<List<Movie>> = MediatorLiveData()
    fun getMovies():LiveData<List<Movie>>{
        return movies
    }

    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()
    private var _searchMoviesLiveData = MutableLiveData<List<Movie>>()

    private val movieLoadingStateLiveData = MutableLiveData<MoviesLoadingState>()
    fun getMovieLoadingStateLiveData():LiveData<MoviesLoadingState>{
        return movieLoadingStateLiveData
    }

    private val isEditTextFocus = MutableLiveData<Boolean>()
    fun getEditFocus():LiveData<Boolean> = isEditTextFocus

    private val DEBOUNCE_PERIOD = 500L
    private var searchJob: Job? = null

    init {
        movies.addSource(_popularMoviesLiveData){
            movies.value = it

            //do the Room Operation in IO thread
            viewModelScope.launch(Dispatchers.IO) {
                repository.addPopularMoviesToDB(it)
            }
        }
        movies.addSource(_searchMoviesLiveData){
            movies.value = it
        }
        isEditTextFocus.postValue(true)
    }

    fun onTextChanged(query:String){
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DEBOUNCE_PERIOD)
            if (query.length > 2) {
//                movieLoadingStateLiveData.postValue(MoviesLoadingState.LOADING)
                isEditTextFocus.postValue(false)
                fetchMovieByQuery(query)
            }else if(query.isEmpty()){
                movies.postValue(_popularMoviesLiveData.value)
            }
        }
    }

    private fun fetchMovieByQuery(query: String): LiveData<List<Movie>> {
        val liveData = MutableLiveData<List<Movie>>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //1
                withContext(Dispatchers.Main) {
                    movieLoadingStateLiveData.value = MoviesLoadingState.LOADING
                }
                repository.fetchMovieByQuery(query, retrofitCallBackSearch)
            } catch (e: Exception) {
                //3
                movieLoadingStateLiveData.postValue(MoviesLoadingState.INVALID_API_KEY)
            }
        }
        return liveData
    }

    fun fetchPopularMovies() {
        movieLoadingStateLiveData.value = MoviesLoadingState.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.fetchPopularMovies(retrofitCallBack)
            } catch (e: Exception) {
                movieLoadingStateLiveData.postValue(MoviesLoadingState.INVALID_API_KEY)
            }
        }
    }

    private val retrofitCallBack = object : Callback<MoviesResponse>{
        override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
            if(response.body() != null) {
                 movieLoadingStateLiveData.value = MoviesLoadingState.LOADED
                _popularMoviesLiveData.postValue(response.body()?.movies)
            }else{
                movieLoadingStateLiveData.value = MoviesLoadingState.INVALID_API_KEY
                setTheDataFromDB()
            }
        }

        override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
            movieLoadingStateLiveData.value = MoviesLoadingState.ERROR
            setTheDataFromDB()
        }
    }

    private fun setTheDataFromDB() {
        //do the Room Operation in IO thread
        viewModelScope.launch(Dispatchers.IO) {
            _popularMoviesLiveData.postValue(repository.getPopularMoviesFromDB())
        }
    }

    private val retrofitCallBackSearch = object : Callback<MoviesResponse>{
        override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
            if(response.body() != null) {
                movieLoadingStateLiveData.value = MoviesLoadingState.LOADED
                _searchMoviesLiveData.postValue(response.body()?.movies)
                viewModelScope.launch {
                    delay(1000)
                    isEditTextFocus.postValue(true)
                }
            }else{
                movieLoadingStateLiveData.value = MoviesLoadingState.INVALID_API_KEY
            }
        }

        override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
            movieLoadingStateLiveData.value = MoviesLoadingState.ERROR
        }
    }

    fun onMovieClicked(movie:Movie){
        itemClick.postValue(movie)
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}