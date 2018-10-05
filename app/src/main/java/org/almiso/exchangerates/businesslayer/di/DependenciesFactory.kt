package org.almiso.exchangerates.businesslayer.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.almiso.exchangerates.businesslayer.managers.RateManager
import org.almiso.exchangerates.businesslayer.network.RateRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton
import com.google.gson.GsonBuilder
import org.almiso.exchangerates.datalayer.serializers.RateResponseDeserializer
import org.almiso.exchangerates.objects.RatesResponse


@Module
open class DependenciesFactory {

    @Provides
    @Singleton
    fun gson(): GsonBuilder {
        return GsonBuilder()
                .registerTypeAdapter(RatesResponse::class.java, RateResponseDeserializer())
    }

    @Provides
    @Singleton
    fun httpClient(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(logging)
    }

    @Provides
    @Singleton
    fun retrofit(httpClient: OkHttpClient.Builder, parser: GsonBuilder): Retrofit {
        return Retrofit.Builder()
                .baseUrl(RateRepository.BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(parser.create()))
                .build()
    }

    @Provides
    @Singleton
    fun rateRepository(retrofit: Retrofit): RateRepository {
        return retrofit.create(RateRepository::class.java)
    }

    @Provides
    @Singleton
    fun rateManager(repository: RateRepository): RateManager {
        return RateManager(repository)
    }
}
