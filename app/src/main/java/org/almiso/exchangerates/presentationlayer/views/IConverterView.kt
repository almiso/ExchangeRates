package org.almiso.exchangerates.presentationlayer.views

import org.almiso.exchangerates.objects.Rate
import org.almiso.exchangerates.objects.RateWrapper
import org.almiso.exchangerates.presentationlayer.adapters.ConverterAdapter

interface IConverterView : IView<IConverterView.IListener> {
    interface IListener : ConverterAdapter.IListener

    fun showProgress(isVisible: Boolean)
    fun setData(items: List<Rate>)
    fun moveFromPosition(position: Int)
    fun updateCoefficient(coefficient: Double)
}
