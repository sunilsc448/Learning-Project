package movies.view

import movies.viewmodel.MoviesViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.kotlintutorial.AppClass
import com.example.kotlintutorial.R
import javax.inject.Inject

class MoviesActivity : AppCompatActivity() {
    private lateinit var viewModel: MoviesViewModel
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        AppClass.application.moviesComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return super.onRetainCustomNonConfigurationInstance()
    }

    override fun getLastNonConfigurationInstance(): Any? {
        return super.getLastNonConfigurationInstance()
    }
}