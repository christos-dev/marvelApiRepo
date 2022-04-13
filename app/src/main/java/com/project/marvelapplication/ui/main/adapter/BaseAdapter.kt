package com.project.marvelapplication.ui.main.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T>() :
    RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder>() {

    protected var tempList: MutableList<T?> = emptyList<T?>().toMutableList()

    abstract fun createItemViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder

    abstract inner class BaseViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun onBindData(item: T?)
    }

    override fun getItemCount(): Int {
        return tempList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBindData(if (position < tempList.size) tempList[position] else null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return createItemViewHolder(parent, viewType)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<T?>) {
        this.tempList = data
        notifyDataSetChanged()
    }


}