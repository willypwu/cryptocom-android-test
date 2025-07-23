package com.test.cryptocom.presentation.ui.wallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.cryptocom.R
import com.test.cryptocom.databinding.ItemWalletBalanceBinding
import com.test.cryptocom.presentation.ui.wallet.model.WalletBalanceDisplay


class WalletAdapter : ListAdapter<WalletBalanceDisplay, WalletAdapter.WalletViewHolder>(
    WalletDiffCallback()
) {

    inner class WalletViewHolder(private val binding: ItemWalletBalanceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WalletBalanceDisplay) {
            binding.tvCoinName.text = item.name
            binding.tvAmount.text = binding.root.context.getString(
                R.string.wallet_item_amount_format,
                item.amount,
                item.symbol
            )

            binding.tvUsdValue.text = binding.root.context.getString(
                R.string.wallet_item_usd_value_format,
                item.usdValue
            )

            Glide.with(binding.ivCoin.context)
                .load(item.logoUrl)
                .placeholder(R.drawable.ic_money_24)
                .into(binding.ivCoin)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWalletBalanceBinding.inflate(inflater, parent, false)
        return WalletViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}