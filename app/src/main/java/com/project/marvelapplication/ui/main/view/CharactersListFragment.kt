package com.project.marvelapplication.ui.main.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.marvelapplication.R
import com.project.marvelapplication.data.model.Results
import com.project.marvelapplication.databinding.FragmentCharactersListBinding
import com.project.marvelapplication.ui.base.viewModelBuilder
import com.project.marvelapplication.ui.main.adapter.BaseFragment
import com.project.marvelapplication.ui.main.adapter.characterslist.CharactersListAdapter
import com.project.marvelapplication.ui.main.adapter.squadlist.SquadListAdapter
import com.project.marvelapplication.ui.main.viewmodel.CharactersListVM
import com.project.marvelapplication.utils.Status

class CharactersListFragment : BaseFragment<CharactersListVM, FragmentCharactersListBinding>() {

    private lateinit var charactersList : List<Results>

    private val characterAdapter by lazy {
        CharactersListAdapter {
            sharedViewModel.charToPass = it
            navController.navigate(R.id.action_charactersListFragment_to_characterInfoFragment)
        }
    }

    private val squadAdapter by lazy {
        SquadListAdapter {
            sharedViewModel.charToPass = it
            navController.navigate(R.id.action_charactersListFragment_to_characterInfoFragment)
        }
    }

    override val viewModel: CharactersListVM by viewModelBuilder {
        CharactersListVM(
            requireActivity().application
        )
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCharactersListBinding.inflate(inflater, container, false)

    override fun initView() {
        binding?.charactersRecyclerView?.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        binding?.squadRecyclerView?.apply {
            adapter = squadAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    override fun setObservers() {
        viewModel.getMarvelCharacters().observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val results = resource.data?.data?.results
                        charactersList = results!!.toList()
                        characterAdapter.setData(results.toMutableList())
                    }
                }
            }
        }

        viewModel.readAllCharacters.observe(viewLifecycleOwner) {
            squadAdapter.setData(it.toMutableList())
            squadVisibility(it.isEmpty())
        }
    }

    private fun squadVisibility(bool: Boolean) {
        binding?.squadView?.visibility = if (bool) {
            RecyclerView.GONE
        }else{
            RecyclerView.VISIBLE
        }
    }

}