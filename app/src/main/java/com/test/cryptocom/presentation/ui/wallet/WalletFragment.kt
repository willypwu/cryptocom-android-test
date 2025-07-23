package com.test.cryptocom.presentation.ui.wallet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.cryptocom.R
import com.test.cryptocom.databinding.FragmentWalletBinding
import com.test.cryptocom.presentation.ui.wallet.model.WalletBalanceDisplay

class WalletFragment : Fragment() {

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!

    private lateinit var walletAdapter: WalletAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(requireContext())
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

        // TODO remove when getting data correctly
        walletAdapter.submitList(mockWalletData())
        val totalStr = String.format(getString(R.string.wallet_total_balance), 36.68)
        binding.tvTotalUsd.text = totalStr
    }

    private fun mockWalletData(): List<WalletBalanceDisplay> {
        return listOf(
            WalletBalanceDisplay(
                name = "Bitcoin",
                symbol = "BTC",
                amount = 0.0,
                usdValue = 10.0,
                logoUrl = ""
            ),
            WalletBalanceDisplay(
                name = "Ethereum",
                symbol = "ETH",
                amount = 0.0,
                usdValue = 10.0,
                logoUrl = ""
            )
        )
    }
}
