package viewmodels

import Utility.SingleLiveEvent
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pojos.Actor

class ListActivityViewModel:ViewModel() {
    private var addActorClick:MutableLiveData<Unit> = SingleLiveEvent()
    fun getAddActorClick():LiveData<Unit>{
        return addActorClick
    }

    private var itemClick:MutableLiveData<Actor> = SingleLiveEvent()
    fun getItemClick():LiveData<Actor>{
        return itemClick
    }

    fun addActorClick(){
        addActorClick.postValue(null)
    }

    fun onItemClicked(actor: Actor){
        itemClick.postValue(actor)
    }
}