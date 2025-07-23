package com.test.cryptocom.presentation.ui.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.cryptocom.data.repository.WalletRepository
import com.test.cryptocom.presentation.ui.wallet.model.WalletBalanceDisplay
import com.test.cryptocom.presentation.ui.wallet.model.WalletUiState
import com.test.cryptocom.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WalletViewModelFactory(
    private val repository: WalletRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WalletViewModel::class.java)) {
            return WalletViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class WalletViewModel(
    private val repository: WalletRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WalletUiState())
    val uiState: StateFlow<WalletUiState> = _uiState.asStateFlow()

    init {
        loadWalletData()
    }

    private fun loadWalletData() {
        viewModelScope.launch {
            try {
                // switch to IO thread to load json from asset
                val (currencies, rates, balances) = withContext(Dispatchers.IO) {
                    // currencies info : name / symbol / icon url
                    val currencies = repository.getCurrencies()
                    // live rate, ex : BTC to USD
                    val rates = repository.getExchangeRates()
                    // Users wallet balance (include non-supported currencies)
                    val balances = repository.getWalletBalances()
                    Triple(currencies, rates, balances)
                }

                // only support the SUPPORTED_SYMBOLS, filter balances
                val filteredBalances = balances.filter { Constants.SUPPORTED_SYMBOLS.contains(it.currency) }

                // generate WalletBalanceDisplay for UI
                val result = filteredBalances.mapNotNull { wallet ->
                    val currency = currencies.find { it.symbol == wallet.currency }
                    val rate = rates.find { it.fromCurrency == wallet.currency && it.toCurrency == Constants.USD }

                    // The rate list might be empty, contain empty strings, or have invalid formats.
                    // Fall back to 0.0 in such cases to avoid crashes.
                    val usdRate = rate?.rates?.firstOrNull()?.rate?.toDoubleOrNull() ?: 0.0

                    // Skip the item if required data is missing or exchange rate is zero. Avoid showing incorrect usdValue
                    if (currency != null && usdRate > 0.0) {
                        // Always provide a new instance when updating data to avoid reusing stale references.
                        // Otherwise, DiffUtil may skip updates due to unchanged object references.
                        WalletBalanceDisplay(
                            name = currency.name,
                            symbol = wallet.currency,
                            amount = wallet.amount,
                            usdValue = wallet.amount * usdRate,
                            logoUrl = currency.logoUrl
                        )
                    } else {
                        null
                    }
                }

                // supported total balance
                val total = result.sumOf { it.usdValue }

                // update ui state
                _uiState.value = WalletUiState(
                    isLoading = false,
                    walletDisplayList = result,
                    totalUsdValue = total
                )
            } catch (e: Exception) {
                // any exception, ex : repository.getCurrencies(), set errorMessage and let ui to handle
                _uiState.value = WalletUiState(
                    isLoading = false,
                    errorMessage = e.localizedMessage ?: "Unknown error"
                )
            }
        }
    }
}