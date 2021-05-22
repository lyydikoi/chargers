package com.example.ergoen.ui.chargers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.ergoen.databinding.LayoutChargerViewHolderBinding
import com.example.ergoen.domain.model.Charger

class ChargersListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Charger, ChargersViewHolder>(ChargerDU()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ChargersViewHolder(
            LayoutChargerViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            interaction
        )

    override fun onBindViewHolder(holder: ChargersViewHolder, position: Int) =
        holder.bind(getItem(position))

    interface Interaction {
        fun itemClicked(position: Int)
    }

    private class ChargerDU : DiffUtil.ItemCallback<Charger>() {
        override fun areItemsTheSame(oldItem: Charger, newItem: Charger): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Charger, newItem: Charger): Boolean {
            return oldItem.id == newItem.id
        }
    }
}