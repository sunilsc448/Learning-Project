package com.example.kotlintutorial

import Utility.FragmentTypeEnum
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import listeners.IClickListener
import pojos.Actor
import java.io.Serializable

class ListActivity : AppCompatActivity(),IClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        addFragment(FragmentTypeEnum.ActorList, null)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val fragment = supportFragmentManager.fragments.lastOrNull()

            if(fragment is ListViewFragment){
                fragment.addActor()
            }else{
                Snackbar.make(view, "Actor can be added in the list view only", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            }
        }
    }

    private fun addFragment(fragmentTypeEnum: FragmentTypeEnum, bundle: Bundle?) {
        var fragment:Fragment? = null
        val txn = supportFragmentManager.beginTransaction()
        when(fragmentTypeEnum){
            FragmentTypeEnum.ActorList -> {
                fragment = ListViewFragment()
                fragment.setClickListener(this)
            }
            FragmentTypeEnum.ActorDetail -> {
                fragment = DetailsViewFragment.newInstance(bundle)
                fragment.setClickListener(this)
                //to open DetailsViewFragment as Dialog popup
//                (fragment as DetailsViewFragment).show(supportFragmentManager, "DetailsViewFragment")
//                return
                txn.addToBackStack(null)
            }
            FragmentTypeEnum.None -> fragment = Fragment()
        }

        txn.replace(R.id.fragment_list, fragment)
        txn.commit()
    }

    override fun onClick(position:Int, obj: Any) {
        when(obj){
            obj as Actor -> {
                val bundle = Bundle()
                bundle.putSerializable("actor", obj as Serializable)
                addFragment(FragmentTypeEnum.ActorDetail, bundle)
            }
        }
    }
}