package lifecycle.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.kotlintutorial.R
import lifecycle.fragments.listener.FragmentClickListener

class FragmentLifeCycleActivity : AppCompatActivity(), FragmentClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate >> saved instance "+savedInstanceState?.get("hello"))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_life_cycle)
        clicked("first")
    }


    override fun onAttachFragment(fragment: android.app.Fragment?) {
        Log.i(TAG, "onAttachFragment")
        super.onAttachFragment(fragment)
    }

    private val TAG = "Fragment Activity"
    override fun onStart() {
        Log.i(TAG, "onStart")
        super.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.i(TAG, "onRestoreInstanceState >> saved instance "+savedInstanceState.get("hello"))
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Log.i(TAG, "onRestoreInstanceState persistentState")
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }

    override fun onResume() {
        Log.i(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i(TAG, "onStop")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i(TAG, "onSaveInstanceState")
        outState.putString("hello", "sunil")
        super.onSaveInstanceState(outState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.i(TAG, "onSaveInstanceState outPersistentState")
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun recreate() {
        Log.i(TAG, "recreate")
        super.recreate()
    }

    override fun onNewIntent(intent: Intent?) {
        Log.i(TAG, "onNewIntent")
        super.onNewIntent(intent)
    }

    override fun onRestart() {
        Log.i(TAG, "onRestart")
        super.onRestart()
    }

    override fun clicked(id:String) {
        var fragment: Fragment
        when(id){
            "first" -> fragment = FirstFragment.newInstance(this)
            "second" -> fragment = SecondFragment.newInstance(this)
            "third" -> fragment = ThirdFragment.newInstance(this)
            else -> fragment = Fragment()
        }
        attachFragment(fragment)
    }

    private fun attachFragment(fragment: Fragment) {
        val txn = supportFragmentManager.beginTransaction()
        txn.replace(R.id.fragment_frame, fragment)
        txn.addToBackStack(fragment::class.java.name)
        txn.commit()
    }
}