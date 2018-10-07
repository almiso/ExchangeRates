package org.almiso.exchangerates.businesslayer.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.almiso.exchangerates.R
import org.almiso.exchangerates.businesslayer.coordinators.AbstractCoordinator
import org.almiso.exchangerates.businesslayer.managers.RateManager
import org.almiso.exchangerates.businesslayer.managers.events.RatesEvent
import org.almiso.exchangerates.presentationlayer.presenters.ConverterPresenter
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

open class ConverterFragment : BaseFragment() {
    companion object {
        const val TAG = "ConverterFragment"
    }


    /*
     * Overrides
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_converter, container, false)
    }


    /*
     * Overrides
     */
    override fun initializeCoordinator(): AbstractCoordinator<BaseFragment, ConverterPresenter> {
        return Coordinator(this, ConverterPresenter())
    }


    /*
     * Coordinator
     */
    open class Coordinator(system: BaseFragment, presenter: ConverterPresenter) : BaseFragment.BaseCoordinator<ConverterPresenter>(system, presenter), ConverterPresenter.IController {

        /*
         * Fields
         */
        @Inject protected lateinit var mRateManager: RateManager


        /*
         * Overrides
         */
        override fun initialize(context: Context) {
            super.initialize(context)

            injector().inject(this)
        }


        /*
         * Implemented methods
         */
        override fun startUpdating(currency: String) {
            mRateManager.startUpdating(currency)
        }

        override fun stopUpdating() {
            mRateManager.stopUpdating()
        }


        /*
         * Events
         */
        @Subscribe(threadMode = ThreadMode.MAIN)
        fun onRatesUpdatedComplete(event: RatesEvent) {
            presenter().dataUpdated(event.data())
        }
    }
}
