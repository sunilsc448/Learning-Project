package movies.view

import movies.viewmodel.MoviesViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlintutorial.AppClass.Companion.application
import com.example.kotlintutorial.databinding.FragmentMoviesListBinding
import javax.inject.Inject
import androidx.navigation.fragment.findNavController
import com.example.kotlintutorial.AppClass
import movies.network.ConnectivityLiveData

class MoviesListFragment : Fragment() {
    private lateinit var parentViewModel: MoviesViewModel
    private lateinit var dataBinding: FragmentMoviesListBinding
    private lateinit var connectivityLiveData:ConnectivityLiveData
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application.moviesComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentMoviesListBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        parentViewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)
        dataBinding.viewmodel = parentViewModel

        connectivityLiveData = ConnectivityLiveData(AppClass.application)

        connectivityLiveData.observe(viewLifecycleOwner, { isNetworkAvailable ->
            if(isNetworkAvailable){
                println("network available")
            }else{
                println("network lost")
            }
        })

        parentViewModel.getEditBoxText().observe(this, {
           if(!it.equals("Enter the Movie Name here",  true))
               parentViewModel.onTextChanged(it)
        })

        parentViewModel.getItemClick().observe(this, {
            findNavController().navigate(MoviesListFragmentDirections.movieDetailClicked(it))
        })

        parentViewModel.fetchPopularMovies()
        return dataBinding.root
    }
}