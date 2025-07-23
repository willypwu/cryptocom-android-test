package com.test.cryptocom.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WalletBalanceResponse(
    val ok: Boolean = false,
    val warning: String = "",
    val wallet: List<WalletBalance> = emptyList()
)

@Serializable
data class WalletBalance(
    val currency: String = "",
    val amount: Double = 0.0
)