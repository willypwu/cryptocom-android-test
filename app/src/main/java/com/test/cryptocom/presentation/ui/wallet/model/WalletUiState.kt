package com.test.cryptocom.presentation.ui.wallet.model

data class WalletUiState(
    val isLoading: Boolean = true,
    val walletDisplayList: List<WalletBalanceDisplay> = emptyList(),
    val totalUsdValue: Double = 0.0,
    val errorMessage: String? = null
)