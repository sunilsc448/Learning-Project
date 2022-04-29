package KotlinSamples

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class BackingPropertySample {
    init{
        val modernStudent1 = ModernStudent1()
        modernStudent1.addHobby("studying")
        modernStudent1.hobbies.removeAll{item:String -> true}

        val modernStudent2 = ModernStudent2()
        modernStudent2.addHobby("studying")
//        modernStudent2.hobbies.removeAll{item:String -> true} //not possible as hobbies is immutable now

        val gameViewModel = GameViewModel()
        gameViewModel.score.value?.let { scr -> gameViewModel.score.value = scr + 1}
    }
}

class ModernStudent1{
    val hobbies = mutableListOf<String>()
    fun addHobby(hobby:String){
        hobbies.add(hobby)
    }
}

class ModernStudent2{
    val hobbies:List<String>
    get() = _hobbies
    private val _hobbies = mutableListOf<String>() //backing property
    fun addHobby(hobby:String){
        _hobbies.add(hobby)
    }
}

class GameViewModel{
    val score = MutableLiveData<Int>()
    fun increment1(){
        score.value?.let { scr -> score.value = scr + 1}
    }
    fun decrement1(){
        score.value?.let { scr -> score.value = scr - 1}
    }
}

class GameViewModelOptimised{
//    val score = LiveData<Int>()

    private val _score = MutableLiveData<Int>()
    fun increment1(){
        _score.value?.let { scr -> _score.value = scr + 1}
    }
    fun decrement1(){
        _score.value?.let { scr -> _score.value = scr - 1}
    }
}

