package com.example.countrylist.util

import java.util.Locale

object NumberFormatter {
    fun toCompactString(value: Long): String {
        return when {
            value >= 1_000_000_000 -> String.format(
                locale = Locale.US, "%.1fB", (value / 1_000_000_000.0)
            )

            value >= 1_000_000 -> String.format(
                locale = Locale.US, "%.1fM", (value / 1_000_000.0)
            )

            value >= 1_000 -> String.format(
                locale = Locale.US, "%.1fK", (value / 1_000.0)
            )

            else -> value.toString()
        }
    }

    fun toLocaleString(value: Int): String {
        return String.format(Locale.US, "%,d", value)
    }
}