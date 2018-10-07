package org.almiso.exchangerates.businesslayer.network

import org.almiso.exchangerates.datalayer.Fields
import org.almiso.exchangerates.objects.RatesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface RateRepository {
    companion object {
        const val BASE_URL = "https://revolut.duckdns.org"
    }

    @GET("/latest")
    fun loadRates(@Query(Fields.base) baseCurrency: String): Observable<RatesResponse>
}
