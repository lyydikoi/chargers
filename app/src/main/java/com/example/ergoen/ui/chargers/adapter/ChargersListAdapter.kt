package com.example.ergoen.ui.chargers.adapter


/*
class ChargersListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<User, ChargersViewHolder>(ChargerDU()) {

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

    private class ChargerDU : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }
    }
}*/