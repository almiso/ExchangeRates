package org.almiso.exchangerates.extensions

import java.util.*

fun Double.currency(): String = String.format(Locale.ENGLISH, "%.2f", this)
