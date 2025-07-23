package com.test.cryptocom.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(
    val currencies: List<Currency> = emptyList(),
    val total: Int = 0,
    val ok: Boolean = false
)

@Serializable
data class Currency(
    val symbol: String = "",
    val name: String = "",

    @SerialName("colorful_image_url")
    val logoUrl: String = ""
)