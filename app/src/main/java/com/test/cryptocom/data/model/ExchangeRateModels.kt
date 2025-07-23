package com.test.cryptocom.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateResponse(
    val ok: Boolean = false,
    val warning: String = "",
    val tiers: List<ExchangeRateTier> = emptyList()
)

@Serializable
data class ExchangeRateTier(
    @SerialName("from_currency")
    val fromCurrency: String = "",

    @SerialName("to_currency")
    val toCurrency: String = "",

    val rates: List<RateEntry> = emptyList(),

    @SerialName("time_stamp")
    val timeStamp: Long = 0L
)

@Serializable
data class RateEntry(
    val amount: String = "",
    val rate: String = ""
)