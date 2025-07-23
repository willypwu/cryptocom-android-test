package com.test.cryptocom.presentation.ui.wallet.model

data class WalletBalanceDisplay(
    val name: String,
    val symbol: String,
    val amount: Double,
    val usdValue: Double,
    val logoUrl: String
)