package com.target.targetcasestudy.data.source.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.target.targetcasestudy.domain.model.Price as DomainPrice

@JsonClass(generateAdapter = true)
data class Price(
    @Json(name = "amount_in_cents")
    val amountInCents: Int,
    @Json(name = "currency_symbol")
    val currencySymbol: String,
    @Json(name = "display_string")
    val displayString: String
)

fun Price.toDomain(): DomainPrice {
    return DomainPrice(amountInCents, currencySymbol, displayString)
}
