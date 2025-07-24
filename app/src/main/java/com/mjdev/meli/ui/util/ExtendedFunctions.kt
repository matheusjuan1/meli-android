package com.mjdev.meli.ui.util

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

/**
 * Formata um BigDecimal como uma string de moeda.
 *
 * @param locale O locale a ser usado para formatação. Padrão é pt_BR.
 * @param currency A moeda a ser usada para formatação. Padrão é BRL.
 * @return A string formatada como moeda.
 */
fun BigDecimal.currencyFormat(
    locale: Locale = Locale("pt", "BR"),
    currency: Currency = Currency.getInstance("BRL")
): String {
    return try {
        val numberFormat = NumberFormat.getCurrencyInstance(locale)
        numberFormat.currency = currency
        numberFormat.format(this)
    } catch (e: Exception) {
        this.toString()
    }
}