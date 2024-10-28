package com.cred.sampleapp.utils

import java.text.NumberFormat
import java.util.Locale

fun formatCurrencyWithRupee(amount: String): String {
    return try {
        val number = amount.toDoubleOrNull() ?: 0.0
        "₹${NumberFormat.getNumberInstance(Locale("en", "IN")).format(number)}"
    } catch (e: NumberFormatException) {
        "₹$amount" // Return the raw input with rupee symbol if formatting fails
    }
}