package com.project.marvelapplication.ui.main.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.project.marvelapplication.MarvelSharedVM
import com.project.marvelapplication.ui.base.activityViewModelBuilder

abstract class BaseFragment<VM: ViewModel, B: ViewBinding> : Fragment() {

    protected val sharedViewModel: MarvelSharedVM by activityViewModelBuilder {
        MarvelSharedVM()
    }

    protected abstract val viewModel: VM

    protected var binding : B? = null

    protected abstract fun initView()

    protected abstract fun setObservers()

    protected val navController by lazy { findNavController() }

    protected abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObservers()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}