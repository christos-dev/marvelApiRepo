package com.project.marvelapplication.ui.main.view

import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.project.marvelapplication.R
import com.project.marvelapplication.data.database.CharacterRoom
import com.project.marvelapplication.databinding.FragmentCharacterInfoBinding
import com.project.marvelapplication.ui.base.viewModelBuilder
import com.project.marvelapplication.ui.main.adapter.BaseFragment
import com.project.marvelapplication.ui.main.viewmodel.CharacterInfoVM
import com.project.marvelapplication.utils.Status

class CharacterInfoFragment : BaseFragment<CharacterInfoVM, FragmentCharacterInfoBinding>() {

    private lateinit var character: CharacterRoom

    override val viewModel: CharacterInfoVM by viewModelBuilder {
        CharacterInfoVM(requireActivity().application)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCharacterInfoBinding.inflate(inflater, container, false)

    override fun initView() {
        character = sharedViewModel.charToPass

        binding?.navUpBtn?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding?.apply {
            charName.text = character.name
            descriptionTv.text = character.description
            Glide.with(this.root.context)
                .load(character.imageUrl)
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(this.characterImage)
        }

    }

    override fun setObservers() {
        viewModel.isCharacterInSquad(character.id).observe(viewLifecycleOwner){
            it?.let{ resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        val result = resource.data
                        binding?.squadBtn?.apply {
                            if (result == 0) {
                                text = getString(R.string.squad_hire)
                                setOnClickListener { insertToDatabase() }
                            }else {
                                text = getString(R.string.squad_fire)
                                setBackgroundColor(requireContext().resources.getColor(R.color.button_fire_color))
                                setOnClickListener { showDialog() }
                            }
                        }
                    }
                }
            }
        }

    }

    private fun insertToDatabase() {
        viewModel.addCharacter(sharedViewModel.charToPass)
    }

    private fun deleteFromDatabase(char: CharacterRoom) {
        viewModel.deleteCharacter(char)
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(false)
            .setMessage(getString(R.string.delete_consent))
            .setPositiveButton(getString(R.string.dialog_confirm)) { _, _ ->
                deleteFromDatabase(character)
            }
            .setNegativeButton(getString(R.string.dialog_cancel)) {
                    dialogInterface, _ -> dialogInterface.cancel() }
        val alert = builder.create()
        alert.show()
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
    }

}