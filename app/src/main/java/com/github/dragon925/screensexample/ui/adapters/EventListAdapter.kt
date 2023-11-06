package com.github.dragon925.screensexample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.dragon925.screensexample.databinding.ItemListBinding
import com.github.dragon925.screensexample.domain.item.EventItem

class EventListAdapter : ListAdapter<EventItem, EventListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<EventItem>() {

        override fun areItemsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
            return oldItem == newItem
        }

    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EventItem) {
            binding.tvTime.text = item.time
            binding.tvName.text = item.name
        }

    }
}