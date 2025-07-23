package com.test.cryptocom.presentation.ui.wallet

import androidx.recyclerview.widget.DiffUtil
import com.test.cryptocom.presentation.ui.wallet.model.WalletBalanceDisplay

class WalletDiffCallback : DiffUtil.ItemCallback<WalletBalanceDisplay>() {
    override fun areItemsTheSame(oldItem: WalletBalanceDisplay, newItem: WalletBalanceDisplay): Boolean {
        return oldItem.symbol == newItem.symbol
    }

    override fun areContentsTheSame(oldItem: WalletBalanceDisplay, newItem: WalletBalanceDisplay): Boolean {
        // use data class equals to compare
        return oldItem == newItem
    }
}