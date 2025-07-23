package com.test.cryptocom.data.repository

import android.content.Context
import com.test.cryptocom.data.model.*
import com.test.cryptocom.util.Constants
import com.test.cryptocom.util.JsonLoader


class JsonWalletRepository(private val context: Context) : WalletRepository {

    override suspend fun getCurrencies(): List<Currency> {
        val res = JsonLoader.loadJsonFromAssets<CurrencyResponse>(context, Constants.CURRENCIES_FILE)
        // if server/json return !ok, just throw exception, let view model handle
        if (!res.ok) throw IllegalStateException("Currency data error" )
        return res.currencies
    }

    override suspend fun getExchangeRates(): List<ExchangeRateTier> {
        val res = JsonLoader.loadJsonFromAssets<ExchangeRateResponse>(context, Constants.EXCHANGE_RATES_FILE)
        if (!res.ok) throw IllegalStateException(res.warning.ifBlank { "Exchange rate data error" })
        return res.tiers
    }

    override suspend fun getWalletBalances(): List<WalletBalance> {
        val res = JsonLoader.loadJsonFromAssets<WalletBalanceResponse>(context, Constants.WALLET_BALANCE_FILE)
        if (!res.ok) throw IllegalStateException(res.warning.ifBlank { "Wallet balance data error" })
        return res.wallet
    }
}