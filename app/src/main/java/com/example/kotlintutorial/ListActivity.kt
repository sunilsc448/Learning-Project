package com.example.kotlintutorial

import Utility.FragmentTypeEnum
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import listeners.IClickListener
import pojos.Actor

class ListActivity : AppCompatActivity(),IClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        addFragment(FragmentTypeEnum.Actor)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun addFragment(fragmentTypeEnum: FragmentTypeEnum) {
        var fragment:Fragment? = null
        when(fragmentTypeEnum){
            FragmentTypeEnum.Actor -> {
                fragment = ListViewFragment()
                fragment.setClickListener(this)
            }
            FragmentTypeEnum.None -> fragment = Fragment()
        }

        val txn = supportFragmentManager.beginTransaction()
        txn.add(R.id.fragment_list, fragment)
        txn.commit()
    }

    override fun onClick(position:Int, obj: Any) {
        when(obj){
            obj as Actor -> {
                println("Request for actor ${obj.name}. information")
            }
        }
    }
}