package com.example.countrylist.domain.model

data class CurrencyDetail(
    val code: String?,
    val name: String?,
    val symbol: String?
) {
    override fun toString(): String {
        return buildString {
            if (!name.isNullOrEmpty()) append(name)
            if (!symbol.isNullOrEmpty()) append(" ($symbol)")
        }
    }
}
