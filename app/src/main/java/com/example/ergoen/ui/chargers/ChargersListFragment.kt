package com.example.ergoen.ui.chargers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.ergoen.databinding.FragmentChargersListBinding
import com.example.ergoen.ui.chargers.adapter.ChargersListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChargersListFragment : Fragment() {

    private var isReversedSorting = false
    private lateinit var binding: FragmentChargersListBinding
    private val viewModel by viewModel<ChargersViewModel>()
    private val adapter by lazy {
        ChargersListAdapter(object : ChargersListAdapter.Interaction {
            override fun itemClicked(position: Int) {
                /*val selectedBird = viewModel.getSelectedCharger(position)
                selectedBird?.let {
                    findNavController().navigate(
                        R.id.action_chargersListFragment_to_chargersDetailsFragment,
                        bundleOf(BIRD_BUNDLED to it))
                }*/
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChargersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.ldSortedChargersList.observe(viewLifecycleOwner, Observer { chargers ->
            chargers?.let {
                adapter.submitList(chargers)
                adapter.notifyItemChanged(chargers.lastIndex)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
