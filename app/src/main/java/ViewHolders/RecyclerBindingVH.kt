package ViewHolders

import movies.models.Movie
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintutorial.BR
import pojos.Actor

class RecyclerBindingVH(private val binding: ViewDataBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(position:Int,  item: Any?){
        if(item is Actor){
            binding.setVariable(BR.actor, item)
            binding.setVariable(BR.position, position)
        }else if (item is Movie){
            binding.setVariable(BR.movie, item)
            binding.setVariable(BR.position, position)
        }
    }
}