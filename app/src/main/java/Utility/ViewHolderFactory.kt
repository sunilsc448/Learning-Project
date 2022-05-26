package Utility

import ViewHolders.ActorViewHolder
import ViewHolders.BaseViewHolder
import ViewHolders.RecyclerBindingVH
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintutorial.BR
import com.example.kotlintutorial.R
import listeners.IClickListener
import pojos.Actor
import viewmodels.FragmentListViewModel

object ViewHolderFactory {
    fun getViewHolder(resourceId: Int, parent: ViewGroup, listener: IClickListener?, viewmodel: FragmentListViewModel): RecyclerView.ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding: ViewDataBinding = DataBindingUtil.inflate(inflater, resourceId, parent, false)
        dataBinding.setVariable(BR.listener, listener)
        dataBinding.setVariable(BR.viewmodel, viewmodel)
        return RecyclerBindingVH(dataBinding)
    }
    fun bindViewHolder(pos:Int, resourceId:Int, holder:RecyclerView.ViewHolder, obj:Any?){
        if(obj == null)return
        when(resourceId){
            R.layout.item_actor -> {
                (holder as RecyclerBindingVH).bind(pos, obj)
            }
        }
    }
}