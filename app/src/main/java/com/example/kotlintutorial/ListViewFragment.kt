package com.example.kotlintutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.kotlintutorial.databinding.FragmentListBinding
import listeners.IClickListener
import viewmodels.FragmentListViewModel

class ListViewFragment : Fragment() {
    private lateinit var viewmodel: FragmentListViewModel
    private var mListener:IClickListener? = null
    private lateinit var dataBinding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentListBinding.inflate(inflater, container, false)
        dataBinding.setVariable(BR.listener, mListener)
        viewmodel = ViewModelProviders.of(this).get(FragmentListViewModel::class.java)
        dataBinding.viewmodel = viewmodel
        dataBinding.lifecycleOwner = viewLifecycleOwner
        viewmodel.getNoResponse().observe(this, {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
        return this.dataBinding.root
    }

    fun setClickListener(listener: IClickListener){
        mListener = listener
    }
}