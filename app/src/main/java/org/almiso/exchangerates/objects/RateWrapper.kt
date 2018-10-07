package org.almiso.exchangerates.objects

open class RateWrapper(rate: Rate) {
    var rate: Rate = rate
    var coefficient: Double = 1.0
}
