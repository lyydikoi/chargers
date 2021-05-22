package com.example.ergoen.ui.chargers.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.ergoen.databinding.LayoutChargerViewHolderBinding
import com.example.ergoen.domain.model.Charger

class ChargersViewHolder(
    private val binding: LayoutChargerViewHolderBinding,
    private val interaction: ChargersListAdapter.Interaction?
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener{
            if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
            interaction?.itemClicked(adapterPosition)
        }
    }

    fun bind(charger: Charger) {
        //binding.tvLocation.text = getCoordString(item.lat, item.lon)
    }
}