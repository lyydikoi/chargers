package com.example.ergoen.ui.chargers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ergoen.R
import com.example.ergoen.databinding.FragmentChargersListBinding
import com.example.ergoen.ui.chargers.adapter.ChargersListAdapter
import com.example.ergoen.ui.chargers.ChargersListUiContract.UiState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChargersListFragment : Fragment() {
    private var isKwEnabled = true
    private var isDistanceEnabled = false
    private lateinit var binding: FragmentChargersListBinding
    private val viewModel: ChargersViewModel by viewModel()

    private val adapter by lazy {
        ChargersListAdapter(object : ChargersListAdapter.Interaction {
            override fun itemClicked(position: Int) {
                // TODO: handle selected charger item here.
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

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvChargers.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            rvChargers.adapter = adapter
            imageChargersKwToggle.setOnClickListener {
                isKwEnabled = !isKwEnabled
                viewModel.setIsKwEnabled(isKwEnabled)
            }
            imageChargersDistanceToggle.setOnClickListener {
                isDistanceEnabled = !isDistanceEnabled
                viewModel.setIsDistanceEnabled(isDistanceEnabled)
            }
        }

        initObservers()
    }

    private fun initObservers() {
        viewModel.accessTokenStream.observe(viewLifecycleOwner, { token ->
            if (token.isBlank()) findNavController().navigate(R.id.loginFragment)
        })
        viewModel.locationDetailsStream.observe(viewLifecycleOwner, { locationDetails ->
            viewModel.updateChargers(locationDetails)
        })
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is UiState.Success -> {
                        adapter.submitList(uiState.chargers)
                        adapter.notifyItemChanged(uiState.chargers.lastIndex)
                    }
                    is UiState.Failure ->  {
                        Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
