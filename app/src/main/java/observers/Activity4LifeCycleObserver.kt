package observers

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class Activity4LifeCycleObserver : LifecycleObserver {
    private val TAG = this.javaClass.name

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateEvent(){
        Log.i(TAG, "OBSERVER_ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartEvent(){
        Log.i(TAG, "OBSERVER_ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPauseEvent(){
        Log.i(TAG, "OBSERVER_ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeEvent(){
        Log.i(TAG, "OBSERVER_ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopEvent(){
        Log.i(TAG, "OBSERVER_ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyEvent(){
        Log.i(TAG, "OBSERVER_ON_DESTROY")
    }
}