package com.example.ergoen.ui.chargers.adapter

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.example.ergoen.databinding.LayoutChargerViewHolderBinding
import com.example.ergoen.ui.chargers.ChargersListUiContract.ChargerViewState

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

    fun bind(charger: ChargerViewState) {
        with(binding) {
            textChargersTitle.text =  charger.distance.toString()
            textChargersKw.visibility = if (charger.kwLabelEnabled) VISIBLE else GONE
            textChargersDistance.visibility = if (charger.distanceLabelEnabled) VISIBLE else GONE
        }
    }
}
