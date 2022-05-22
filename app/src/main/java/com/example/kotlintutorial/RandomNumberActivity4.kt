package com.example.kotlintutorial

import Utility.RandomNumberGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_4.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import observers.Activity4LifeCycleObserver
import org.greenrobot.eventbus.EventBus
import viewmodels.Activity4ViewModel
import android.widget.Toast

import KotlinSamples.MessageEvent
import androidx.lifecycle.Observer

import org.greenrobot.eventbus.ThreadMode

import org.greenrobot.eventbus.Subscribe

class RandomNumberActivity4 : AppCompatActivity() {
    private val TAG = this.javaClass.name
    private lateinit var randomNumberGenerator:RandomNumberGenerator
    private lateinit var viewModel: Activity4ViewModel
    val range = 1..10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_4)

        Log.i(TAG, "ACTIVITY_ON_CREATE")
        lifecycle.addObserver(Activity4LifeCycleObserver())

        //if viewmodel is not used
        if(savedInstanceState != null && savedInstanceState.containsKey("random-number")){
            randomNumberGenerator = RandomNumberGenerator()
            randomNumberGenerator.setrandomNumber(savedInstanceState.getString("random-number"))
            txt.text = randomNumberGenerator.createNumber(range)
        }else {
//            randomNumberGenerator = RandomNumberGenerator()
            viewModel = ViewModelProviders.of(this).get(Activity4ViewModel::class.java)
            val value = viewModel.createNumber(range)
            setTextOnMainThread(value)

            viewModel.randomNumber?.observe(this, {
                setTextOnMainThread(it)
            })
        }
    }

    private fun setTextOnMainThread(value:String) {
//        CoroutineScope(Dispatchers.Main).launch {
            txt.text = value
//        }
    }


    override fun onStart() {
        Log.i(TAG, "ACTIVITY_ON_START")
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        Log.i(TAG, "ACTIVITY_ON_PAUSE")
        super.onPause()
    }

    override fun onResume() {
        Log.i(TAG, "ACTIVITY_ON_RESUME")
        super.onResume()
    }

    override fun onStop() {
        Log.i(TAG, "ACTIVITY_ON_STOP")
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    override fun onRestart() {
        Log.i(TAG, "ACTIVITY_ON_RESTART")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.i(TAG, "ACTIVITY_ON_DESTROY")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i(TAG, "ACTIVITY_ON_SAVED_INSTANCE")
        //if viewmodel is not used
//        outState.putString("random-number", randomNumberGenerator.createNumber(range))
        super.onSaveInstanceState(outState)
    }

    // This method will be called when a MessageEvent is posted (in the UI thread for Toast)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        Log.i(TAG, "Got the event")
        txt.text = "Random Number: ${event.message}"
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
    }
}