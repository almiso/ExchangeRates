package org.almiso.exchangerates.businesslayer.network

import org.almiso.exchangerates.objects.RatesResponse
import retrofit2.http.GET
import rx.Observable

interface RateRepository {
    companion object {
        const val BASE_URL = "https://revolut.duckdns.org/"
    }

    @GET("latest?base=EUR")
    fun loadRates(): Observable<RatesResponse>
}
