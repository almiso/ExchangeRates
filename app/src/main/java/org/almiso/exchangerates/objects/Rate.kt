package org.almiso.exchangerates.objects

open class Rate {
    var currency: String? = null
    var amount: Double = 0.0

    override fun toString(): String {
        return "currency$currency, amount: $amount"
    }
}
