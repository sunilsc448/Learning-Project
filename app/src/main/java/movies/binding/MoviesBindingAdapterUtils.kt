package movies.binding

import movies.models.Movie
import adapters.RecyclerViewAdapter
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintutorial.R
import com.squareup.picasso.Picasso
import movies.models.MoviesLoadingState
import movies.viewmodel.MoviesViewModel

object MoviesBindingAdapterUtils {
    @BindingAdapter(value = ["viewmodel","movies"])
    @JvmStatic
    fun setMoviesAdapter(recyclerView: RecyclerView, viewmodel: MoviesViewModel?, movies:List<Movie>?) {
        if (viewmodel != null && movies != null) {
            var adapter = recyclerView.adapter
            if (adapter == null){
                adapter = RecyclerViewAdapter(movies, null, viewmodel)
                recyclerView.adapter = adapter
            }else{
                (adapter as RecyclerViewAdapter).updateData(movies)
                recyclerView.scrollToPosition(0)
            }
        }
    }

    const val POSTER_IMAGE_PATH_PREFIX = "https://image.tmdb.org/t/p/w300"
    @BindingAdapter("loadImage")
    @JvmStatic
    fun setImage(imageView: ImageView, url: String?) {
        Picasso.with(imageView.context).load(POSTER_IMAGE_PATH_PREFIX+url).placeholder(R.drawable.placeholder_image).into(imageView)
    }

    @BindingAdapter("moviesProgressbar")
    @JvmStatic
    fun setMoviesProgressBar(progressBar:ProgressBar, state: MoviesLoadingState){
        when(state){
            MoviesLoadingState.LOADING -> progressBar.visibility = View.VISIBLE
            MoviesLoadingState.ERROR -> progressBar.visibility = View.GONE
            MoviesLoadingState.LOADED -> progressBar.visibility = View.GONE
            MoviesLoadingState.INVALID_API_KEY -> progressBar.visibility = View.GONE
        }
    }
}