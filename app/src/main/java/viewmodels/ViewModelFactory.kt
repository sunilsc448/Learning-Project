package viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(val parentViewModel: ListActivityViewModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass){
            FragmentListViewModel::class.java -> {
                FragmentListViewModel(parentViewModel)
//                modelClass.declaredConstructors.first().newInstance(parentViewModel)
            }
            else -> throw Exception("Unknown type for this factory")
        } as T
    }
}