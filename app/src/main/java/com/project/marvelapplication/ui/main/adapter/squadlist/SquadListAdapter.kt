package com.project.marvelapplication.ui.main.adapter.squadlist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.project.marvelapplication.R
import com.project.marvelapplication.data.database.CharacterRoom
import com.project.marvelapplication.databinding.SquadCustomListBinding
import com.project.marvelapplication.ui.main.adapter.BaseAdapter

class SquadListAdapter(
    private val clickListener: (CharacterRoom) -> Unit
) : BaseAdapter<CharacterRoom>() {

    override fun createItemViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val squadListBinding = SquadCustomListBinding.inflate(inflater, parent, false)
        return SquadListViewHolder(squadListBinding){
            val selSC = tempList[it]!!
            clickListener(selSC)
        }
    }

    inner class SquadListViewHolder(private val binding: SquadCustomListBinding, clickAtPosition: (Int) -> Unit) : BaseViewHolder(binding) {

        init {
            binding.root.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        override fun onBindData(item: CharacterRoom?) {
            binding.apply {
                squadCharacterName.text = item?.name
                Glide.with(binding.squadCharacterImage.context)
                    .load(item?.imageUrl)
                    .error(R.drawable.ic_launcher_background)
                    .transform(CircleCrop())
                    .into(binding.squadCharacterImage)
            }
        }

    }
}