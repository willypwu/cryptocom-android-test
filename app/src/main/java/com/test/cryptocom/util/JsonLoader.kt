package com.test.cryptocom.util

import android.content.Context
import com.test.cryptocom.data.model.Currency
import com.test.cryptocom.data.model.CurrencyResponse
import com.test.cryptocom.data.model.ExchangeRateResponse
import com.test.cryptocom.data.model.ExchangeRateTier
import com.test.cryptocom.data.model.WalletBalance
import com.test.cryptocom.data.model.WalletBalanceResponse
import kotlinx.serialization.json.Json
import java.io.InputStreamReader

object JsonLoader {

    /**
     * Purpose : Loads and decodes a JSON file from the assets folder into a Kotlin data class.
     *
     * @param T The target type to decode to. Must be marked with @Serializable.
     * @param context Android context to access the assets.
     * @param fileName The name of the JSON file in the assets directory.
     * @return An instance of T decoded from the JSON file.
     *
     * This function uses reified type parameter with inline to enable generic deserialization.
     */
    inline fun <reified T> loadJsonFromAssets(context: Context, fileName: String): T {
        val json = Json { ignoreUnknownKeys = true }
        val jsonString = context.assets.open(fileName).use { input ->
            InputStreamReader(input).readText()
        }

        return json.decodeFromString(jsonString)
    }
}