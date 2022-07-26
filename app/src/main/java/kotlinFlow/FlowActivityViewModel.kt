package kotlinFlow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowActivityViewModel:ViewModel() {
    //cold flow, if not collected it doesn't do anything
    val countDownFlow = flow<Int> {
        val startingValue = 5
        emit(startingValue)
        var currentValue = startingValue
        while (currentValue > 1){
            delay(500L)
            currentValue--
            emit(currentValue)
        }
    }

    //cold flow, if not collected it doesn't do anything
    private fun collectFlow(){
        viewModelScope.launch {
            countDownFlow.collectLatest { time ->
                println("latest is > $time")
            }
        }
    }

    //flatmap: flatMapConcat
    fun flatMapConcat():Flow<Int>{
        //[[1, 2], [1, 2, 3]]
        //[1, 2, 1, 2, 3]
        val flattenFlow = flow{
            emit(5)
            delay(1000L)
            emit(10)
        }

        viewModelScope.launch {
            flattenFlow.flatMapConcat {
                flow{
                    emit(it + 10)
                    emit(it + 20)
                }
            }.collect{
                println("flattened item is $it")
            }
        }

        return flattenFlow
    }

    //flatmap: flatMapConcat
    @FlowPreview
    fun flatMapConcatUseCase(){
        val productsFlow = flow {
            emit(1)
            emit(2)
            emit(3)
            emit(4)
            emit(5)
        }

        viewModelScope.launch {
            productsFlow.flatMapConcat {
                //productOverviewFlow
                flow {
                    emit(getProductOverview(it))
                }
            }.collect{
                println(it)
            }
        }
    }

    //flatmap: flatMapMerge
    @FlowPreview
    fun flatMapMergeUseCase(){
        val productsFlow = (1..5).asFlow()
        viewModelScope.launch {
            productsFlow.flatMapMerge{
                //productDetailsFlow
                flow{
                    emit(getProductDetails(it))
                }
            }.collect{
                println(it)
            }
        }
    }

    //flatmap: flatMapLatest
    @ExperimentalCoroutinesApi
    fun flatMapLatestUseCase(){
        val productsFlow = (1..5).asFlow()
        viewModelScope.launch {
            productsFlow.flatMapLatest{
                //productDetailsFlow
                flow{
                    emit(getProductDetails(it))
                }
            }.collect{
                println(it)
            }
        }
    }

    private suspend fun getProductOverview(id: Int): String {
        delay(500L)
        return "Overview of product id $id"
    }

    private suspend fun getProductDetails(id: Int): String {
        delay(1000L)
        return "Details of product id $id"
    }

    //normally collect will bring one emit wait till execution adn picks next emit
    // Suspend function inside collect will delay the emit as well
    //expect a async output

    //buffer operator - runs the collect part in different coroutines parallel, so all emits run parallel

    //conflate operator - if there are two emissions which are not finished yet, drop all other coming emissions

    //this is cold flow, if not collected it doesn't do anything
    fun recipesFlow(){
        val flow = flow{
            delay(500L)
            emit("Starters")
            delay(1500L)
            emit("Main Course")
            delay(250L)
            emit("Dessert")
        }

        viewModelScope.launch{
            flow.onEach {
                println("$it delivered")
            }.collect {
                println("eating $it")
                delay(1000L)
                println("finshed eating $it")
            }
        }
    }

    private val counterStateFlow = MutableStateFlow(0)
    fun getCounterStateFlow() = counterStateFlow.asStateFlow()

    suspend fun incrementCounterStateFlow(){
        counterStateFlow.emit(1)
//        delay(1000)
//        counterStateFlow.emit(2)
//        delay(1000)
//        counterStateFlow.emit(3)
    }

    private val squareSharedFlow = MutableSharedFlow<Int>(2)
    fun getSquareSharedFlow() = squareSharedFlow.asSharedFlow()

    init {
        squareNumber(5)
        viewModelScope.launch {
            squareSharedFlow.collect{
                delay(1000L)
                println("collected first shared flow")
            }
        }
        viewModelScope.launch {
            squareSharedFlow.collect{
                delay(2000L)
                println("collected second shared flow")
            }
        }
    }
    fun squareNumber(number:Int){
       viewModelScope.launch {
           squareSharedFlow.emit(number * number)
       }
    }
}