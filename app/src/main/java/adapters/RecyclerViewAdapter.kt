package adapters

import movies.models.Movie
import Utility.ViewHolderFactory
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintutorial.R
import listeners.IClickListener
import pojos.Actor
import viewmodels.FragmentListViewModel

class RecyclerViewAdapter(var mList:List<Any>?, val listener: IClickListener?, val viewModel: ViewModel):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        if(mList == null)return -1
        return when (mList!![position]) {
            is Actor -> {
                R.layout.item_actor
            }
            is Movie -> {
                R.layout.item_movie
            }
            else->{
                throw IllegalArgumentException("Layout is missing for the selection")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh = ViewHolderFactory.getViewHolder(viewType, parent, listener, viewModel)
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        ViewHolderFactory.bindViewHolder(position, getItemViewType(position), holder, mList?.get(position))
    }

    override fun getItemCount() = mList?.size ?: 0

    fun updateData(list:List<Any>?){
        mList = list
        notifyDataSetChanged()
    }
}