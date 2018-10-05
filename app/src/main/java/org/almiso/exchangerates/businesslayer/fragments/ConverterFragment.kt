package org.almiso.exchangerates.businesslayer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.almiso.exchangerates.R
import org.almiso.exchangerates.businesslayer.coordinators.AbstractCoordinator
import org.almiso.exchangerates.presentationlayer.presenters.ConverterPresenter

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
    open class Coordinator(system: BaseFragment, presenter: ConverterPresenter) : BaseFragment.BaseCoordinator<ConverterPresenter>(system, presenter) {

    }
}
