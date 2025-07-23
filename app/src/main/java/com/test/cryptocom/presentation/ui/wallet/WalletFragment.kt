package com.test.cryptocom.presentation.ui.wallet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.cryptocom.R
import com.test.cryptocom.data.repository.JsonWalletRepository
import com.test.cryptocom.databinding.FragmentWalletBinding
import kotlinx.coroutines.launch

class WalletFragment : Fragment() {

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!

    private lateinit var walletAdapter: WalletAdapter
    private lateinit var viewModel: WalletViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(requireContext())
        setupViewModel(requireContext())
        setUpObserve(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupView(context: Context) {
        walletAdapter = WalletAdapter()
        binding.rvWallet.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = walletAdapter
        }
    }

    private fun setupViewModel(context: Context) {
        val repository = JsonWalletRepository(context.applicationContext)
        val factory = WalletViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[WalletViewModel::class.java]
    }

    private fun setUpObserve(context: Context) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.isLoading) {
                        binding.progressBar.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.GONE
                    }

                    // has error, show toast
                    if (state.errorMessage != null) {
                        Toast.makeText(context, state.errorMessage, Toast.LENGTH_LONG)
                            .show()
                    }

                    walletAdapter.submitList(state.walletDisplayList)

                    val totalStr = String.format(getString(R.string.wallet_total_balance), state.totalUsdValue)
                    binding.tvTotalUsd.text = totalStr
                }
            }
        }
    }
}
