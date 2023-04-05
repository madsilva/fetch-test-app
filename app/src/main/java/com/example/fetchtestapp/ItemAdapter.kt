package com.example.fetchtestapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val dataset: MutableList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.list_item)
    }

    class ListIDViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView : TextView = view.findViewById(R.id.list_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when {
            viewType == 0 -> {
                val adapterLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_id, parent, false)
                return ListIDViewHolder(adapterLayout)
            }
            else -> {
                val adapterLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
                return ItemViewHolder(adapterLayout)
            }
        }
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            holder.itemViewType == 0 -> {
                val myListIDViewHolder = holder as ListIDViewHolder
                myListIDViewHolder.textView.text = dataset[position].toString()
            }
            else -> {
                val myItemViewHolder = holder as ItemViewHolder
                myItemViewHolder.textView.text = dataset[position].toString()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = dataset[position]
        return when {
            item is ListID -> 0
            item is ListItem -> 1
            else -> -1
        }
    }
}
