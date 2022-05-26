package binding.utils

import adapters.RecyclerViewAdapter
import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlintutorial.R
import com.squareup.picasso.Picasso
import listeners.IClickListener
import pojos.Actor
import viewmodels.FragmentListViewModel

object BindingAdapterUtils {
    @BindingAdapter("loadImage", "clickListener")
    @JvmStatic
    fun setImage(imageView: ImageView, url: String?,clickListener: IClickListener?) {
//        val activityContext = clickListener as Context
        Picasso.with(imageView.context).load(url).placeholder(R.drawable.placeholder_image).into(imageView)
//        Glide.with(imageView.context).load(url).placeholder(R.drawable.placeholder_image).into(imageView)
    }

    @BindingAdapter(value = ["viewmodel","actorsList", "clickListener"])
    @JvmStatic
    fun setActorsAdapter(recyclerView: RecyclerView, viewmodel: FragmentListViewModel?,actorsList:List<Actor>?, clickListener: IClickListener?) {
        if (viewmodel != null && actorsList != null) {
            var adapter = recyclerView.adapter
            if (adapter == null){
                adapter = RecyclerViewAdapter(actorsList, clickListener, viewmodel)
                recyclerView.adapter = adapter
            }else{
                (adapter as RecyclerViewAdapter).updateData(actorsList)
                recyclerView.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}