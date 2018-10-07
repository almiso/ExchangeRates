package org.almiso.exchangerates.datalayer.serializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.almiso.exchangerates.datalayer.Fields
import org.almiso.exchangerates.objects.Rate
import org.almiso.exchangerates.objects.RatesResponse
import java.lang.reflect.Type

open class RateResponseDeserializer : JsonDeserializer<RatesResponse> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): RatesResponse {
        val response = RatesResponse()

        val obj = json?.asJsonObject
        if (obj != null && obj.has(Fields.rates)) {
            val data = obj.get(Fields.rates) as JsonObject
            response.rates = ArrayList()

            for (entry in data.entrySet()) {
                val rate = Rate()
                rate.currency = entry.key
                rate.amount = entry.value.asDouble
                response.rates!!.add(rate)
            }
        }

        return response
    }
}
