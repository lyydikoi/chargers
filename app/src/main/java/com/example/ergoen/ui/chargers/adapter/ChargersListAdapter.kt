package com.example.ergoen.ui.chargers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.ergoen.databinding.LayoutChargerViewHolderBinding
import com.example.ergoen.ui.chargers.ChargersListUiContract.ChargerViewState

class ChargersListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<ChargerViewState, ChargersViewHolder>(ChargerDU()) {

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

    private class ChargerDU : DiffUtil.ItemCallback<ChargerViewState>() {
        override fun areItemsTheSame(oldItem: ChargerViewState, newItem: ChargerViewState): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ChargerViewState, newItem: ChargerViewState): Boolean {
            return oldItem.locationName == newItem.locationName
        }
    }
}
