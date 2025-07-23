package com.test.cryptocom.data.repository

import com.test.cryptocom.data.model.Currency
import com.test.cryptocom.data.model.ExchangeRateTier
import com.test.cryptocom.data.model.WalletBalance

interface WalletRepository {
    suspend fun getCurrencies(): List<Currency>
    suspend fun getExchangeRates(): List<ExchangeRateTier>
    suspend fun getWalletBalances(): List<WalletBalance>
}