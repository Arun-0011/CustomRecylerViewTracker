package com.app.recyclerviewtracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.recyclerviewtracker.databinding.ListItemBinding
import com.app.recyclerviewtracker.model.DataModel

class ListAdapter(
     val list: ArrayList<DataModel> = ArrayList(),
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    inner class ViewHolder(internal val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            with(list[position]) {
                binding.apply {
                    tvTitle.text = title
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

}
