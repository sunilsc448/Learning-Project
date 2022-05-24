package com.example.kotlintutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kotlintutorial.databinding.FragmentDetailsBinding
import listeners.IClickListener
import pojos.Actor

class DetailsViewFragment : DialogFragment() {
    private lateinit var dataBinding:FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        setBundleDataToBinding()
        return dataBinding.root
    }

    private fun setBundleDataToBinding() {
        val serzdData = arguments?.getSerializable("actor")
        val actor:Actor = serzdData as Actor
        dataBinding.setVariable(BR.actor, actor)
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?) = DetailsViewFragment().apply {
            arguments = bundle
        }
    }
}