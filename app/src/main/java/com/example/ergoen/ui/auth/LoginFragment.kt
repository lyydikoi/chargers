package com.example.ergoen.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.ergoen.R
import com.example.ergoen.databinding.FragmentLoginBinding
import com.example.ergoen.ui.auth.LoginUiContract.Action
import com.example.ergoen.ui.auth.LoginUiContract.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            inputLoginUsername.doOnTextChanged { text, _, _, _ ->
                viewModel.onUsernameChanged(text.toString())
            }
            inputLoginPassword.doOnTextChanged { text, _, _, _ ->
                viewModel.onPasswordChanged(text.toString())
            }
            buttonLogin.setOnClickListener {
                viewModel.handleAction(Action.PerformLogin)
            }
        }
    }

    private fun initObservers() {
        viewModel.uiState.observe(viewLifecycleOwner, { updateUiState(it) })
    }

    private fun updateUiState(uiState: UiState) {
        with(binding) {
            when (uiState) {
                is UiState.Loading -> {
                    progressLogin.visibility = View.VISIBLE
                    buttonLogin.isEnabled = false
                }
                is UiState.Failure -> {
                    progressLogin.visibility = View.GONE
                    Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
                }
                is UiState.Success -> {
                    progressLogin.visibility = View.GONE
                    findNavController().navigate(R.id.chargersListFragment)
                }
                is UiState.FormValidation -> {
                    buttonLogin.isEnabled = uiState.isFormValid
                }
            }
        }

    }
}
