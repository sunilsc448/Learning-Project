package kotlinFlow

import android.app.TaskStackBuilder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.kotlintutorial.R
import kotlinx.android.synthetic.main.activity_flow.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {
    private lateinit var viewModel:FlowActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
        viewModel = ViewModelProviders.of(this).get(FlowActivityViewModel::class.java)

//        observeCountDownFlow()
//        viewModel.flatMapConcat()
//        viewModel.flatMapConcatUseCase()
//        viewModel.flatMapMergeUseCase()
//          viewModel.flatMapLatestUseCase()

//        viewModel.recipesFlow()
//        observeCounterStateFlow()
    }

    private fun observeCounterStateFlow() {
       lifecycleScope.launch {
           viewModel.incrementCounterStateFlow()
           viewModel.getCounterStateFlow().collect {
               state_flow_counter_txt.text = "counter state flow value is ${it}"
           }
       }
    }

    private fun observeCountDownFlow() {
        //onEach
        viewModel.countDownFlow.onEach {
            println("item process final > $it")
        }.launchIn(lifecycleScope)


        lifecycleScope.launch {
            //filter and map with collect function
            viewModel.countDownFlow.filter { it % 2 == 0 }.map { it * it }.collect {
                collect_txt.text = "last collected data is $it"
            }
        }

        lifecycleScope.launch {
            //collect latest > collects only the last data and ignores the prev data if pressure is higher than observation
            viewModel.countDownFlow.collectLatest {
                delay(1000L)
                collect_latest_txt.text = "latest collected data is $it"
            }
        }

        lifecycleScope.launch {
            //terminal flow operator > count
            //terminate flow because it takes all emit and provide result
            //count > gives
            val count = viewModel.countDownFlow.count { time ->
                time % 2 == 0
            }
            count_txt.text = "count data is $count"
        }

        lifecycleScope.launch {
            //terminal flow operator > reduce
            //this functions accumulates the prev function results with current one and at last provide the all accumulated data
            //example
            // 1st emit: 5(accumulated), 2nd emit: 4 + 5(prev acculumated) = 9,3rd emit: 3 + 9 = 12, 4th emit: 12 + 2 = 14,
            // 5th emit: 1 + 14 = 15 is returned
            val reduce = viewModel.countDownFlow.reduce { accumulator, value ->
                accumulator + value
            }
            reduce_txt.text = "reduce data is $reduce"
        }

        lifecycleScope.launch {
            //terminal flow operator > fold
            //similar to reduce, only difference is initial value for accumulation is provided
            //example
            // 1st emit: 5(accumulated), initialvalue = 5 value = 5 + 5  10, 2nd emit: 4 + 10(prev acculumated) = 14,
            // 3rd emit: 3 + 14 = 17, 4th emit: 2 + 17 = 19, 5th emit: 1 + 19 = 20 is returned
            val fold = viewModel.countDownFlow.fold(5) { accumulator, value ->
                accumulator + value
            }
            fold_txt.text = "fold data is $fold"
        }
    }
}