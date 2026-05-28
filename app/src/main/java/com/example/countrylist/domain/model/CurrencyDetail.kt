package com.example.countrylist.domain.model

data class CurrencyDetail(
    val symbol: String,
    val name: String
) {
    override fun toString(): String {
        return "$name ($symbol)"
    }
}
