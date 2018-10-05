package org.almiso.exchangerates.businesslayer.managers.events

import org.almiso.exchangerates.objects.RatesResponse

open class RatesEvent(status: Int, response: RatesResponse?, error: Throwable? = null) : AbstractEvent<RatesResponse>(status, response, error)
