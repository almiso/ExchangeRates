package org.almiso.exchangerates.businesslayer.managers

import android.util.Log
import org.almiso.exchangerates.businesslayer.managers.events.AbstractEvent
import org.almiso.exchangerates.businesslayer.managers.events.RatesEvent
import org.almiso.exchangerates.businesslayer.network.RateRepository
import org.greenrobot.eventbus.EventBus
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import rx.subscriptions.Subscriptions

open class RateManager(repository: RateRepository) {
    companion object {
        const val TAG = "RateManager"

        protected const val REPEAT_DELAY = 1L
        protected const val COUNTER_START = 1
        protected const val ATTEMPTS_COUNT = 20
        protected const val ERROR_DELAY_INCREMENT = 2
    }


    /*
     * Fields
     */
    protected val mRepository: RateRepository = repository
    protected var mSubscription: Subscription = Subscriptions.unsubscribed()


    /*
     * Public methods
     */
    fun startUpdating() {
        mSubscription.unsubscribe()
        mSubscription = mRepository.loadRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen { completed -> completed.delay(REPEAT_DELAY, TimeUnit.SECONDS) }
                .doOnError { error -> Log.d(TAG, "On error: $error") }
                .retryWhen { retryHandler ->
                    retryHandler.zipWith(Observable.range(COUNTER_START, ATTEMPTS_COUNT)) { _, attempt -> attempt }
                            .flatMap { repeatAttempt -> Observable.timer((repeatAttempt * ERROR_DELAY_INCREMENT).toLong(), TimeUnit.SECONDS) }
                }
                .subscribe(
                        { response -> post(RatesEvent(AbstractEvent.SUCCESS, response)) },
                        { error -> post(RatesEvent(AbstractEvent.ERROR, null, error)) }
                )
    }

    fun stopUpdating() {
        mSubscription.unsubscribe()
    }


    /*
     * Protected methods
     */
    protected open fun post(event: AbstractEvent<*>) {
        EventBus.getDefault().post(event)
    }
}
