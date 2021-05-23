package com.example.ergoen.ui.chargers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ergoen.R
import com.example.ergoen.databinding.FragmentChargersListBinding
import com.example.ergoen.ui.chargers.adapter.ChargersListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChargersListFragment : Fragment() {
    private lateinit var binding: FragmentChargersListBinding
    private val viewModel by viewModel<ChargersViewModel>()

    private val adapter by lazy {
        ChargersListAdapter(object : ChargersListAdapter.Interaction {
            override fun itemClicked(position: Int) {
                /*val selectedCharger = viewModel.getSelectedCharger(position)
                selectedCharger?.let {
                    findNavController().navigate(
                        R.id.action_chargersListFragment_to_chargersDetailsFragment,
                        bundleOf(CHARGER_BUNDLED to it))
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    private fun initObservers() {
        viewModel.accessToken.observe(viewLifecycleOwner, { token ->
            if (token.isBlank()) findNavController().navigate(R.id.loginFragment)
        })


    }
}
