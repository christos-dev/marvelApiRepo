package com.project.marvelapplication.ui.main.adapter.characterslist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.project.marvelapplication.R
import com.project.marvelapplication.data.database.CharacterRoom
import com.project.marvelapplication.data.model.Results
import com.project.marvelapplication.databinding.RowCharacterListBinding
import com.project.marvelapplication.ui.main.adapter.BaseAdapter

class CharactersListAdapter(
    private val clickListener: (CharacterRoom) -> Unit
) : BaseAdapter<Results>() {

        override fun createItemViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val heroListBinding = RowCharacterListBinding.inflate(inflater, parent, false)
            return HeroesListViewHolder(heroListBinding) {
                val character = tempList[it]!!.changeToCharacterRoom()
                clickListener(character)
            }
        }

        inner class HeroesListViewHolder(private val binding: RowCharacterListBinding, clickAtPosition: (Int) -> Unit) : BaseViewHolder(binding) {

            init {
                binding.root.setOnClickListener {
                    clickAtPosition(adapterPosition)
                }
            }

            override fun onBindData(item: Results?) {
                binding.apply {
                    characterName.text = item?.name
                    val path = item?.changeToCharacterRoom()?.imageUrl
                    Glide.with(binding.characterImageView.context)
                        .load(path)
                        .error(R.drawable.ic_launcher_background)
                        .transform(CircleCrop())
                        .into(binding.characterImageView)
                }
            }

        }

}