package com.example.kotlintutorial

import Utility.FragmentTypeEnum
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.kotlintutorial.databinding.ActivityListBinding
import listeners.IClickListener
import pojos.Actor
import viewmodels.ListActivityViewModel
import java.io.Serializable

class ListActivity : AppCompatActivity(),IClickListener {
    private lateinit var dataBinding:ActivityListBinding
    private lateinit var viewModel:ListActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        viewModel = ViewModelProviders.of(this).get(ListActivityViewModel::class.java)
        dataBinding.setVariable(BR.viewmodel, viewModel)

        viewModel.getAddActorClick().observe(this, {
            onAddActorClick()
        })
        viewModel.getItemClick().observe(this, {
            onActorClicked(it)
        })

//        addFragment(FragmentTypeEnum.ActorList, null)
        addFragmentNew(FragmentTypeEnum.ActorList, null)
    }

    private fun addFragmentNew(fragmentTypeEnum: FragmentTypeEnum, bundle: Bundle?) {
        when(fragmentTypeEnum){
            FragmentTypeEnum.ActorList -> {
                val navController = findNavController(R.id.fragment_list_view)
                navController.navigateUp()
            }
            FragmentTypeEnum.ActorDetail -> {
                val navController = findNavController(R.id.fragment_list_view)
                navController.navigate(R.id.detailsViewFragment, bundle)
            }
            FragmentTypeEnum.None -> TODO()
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

        txn.replace(R.id.fragment_list_view, fragment)
        txn.commit()
    }

    override fun onClick(position:Int, obj: Any) {
        when(obj){
            obj as Actor -> {
                onActorClicked(obj)
            }
        }
    }

    private fun onActorClicked(actor: Actor) {
        val bundle = Bundle()
        isChangingConfigurations
        bundle.putSerializable("actor", actor as Serializable)
//                addFragment(FragmentTypeEnum.ActorDetail, bundle)
        addFragmentNew(FragmentTypeEnum.ActorDetail, bundle)
    }

    fun onAddActorClick(){
        val fragment = supportFragmentManager.fragments.lastOrNull()
        val childFragment = fragment?.childFragmentManager?.fragments?.lastOrNull()

        if(childFragment is ListViewFragment){
            childFragment.addActor()
        }else{
            Snackbar.make(dataBinding.root, "Actor can be added in the list view only", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }
    }
}