package org.almiso.exchangerates.presentationlayer.presenters

import android.util.Log
import org.almiso.exchangerates.objects.Rate
import org.almiso.exchangerates.objects.RatesResponse
import org.almiso.exchangerates.presentationlayer.views.IConverterView

open class ConverterPresenter : AbstractPresenter<ConverterPresenter.IController>(), IConverterView.IListener {
    /*
     * Callback
     */
    interface IController : AbstractPresenter.IController {
        fun startUpdating()
        fun stopUpdating()
    }


    /*
     * Fields
     */
    protected val mData: MutableList<Rate> = ArrayList()


    /*
     * Overrides
     */
    override fun viewSetted() {
        super.viewSetted()

        getView().setListener(this)
    }


    override fun start() {
        super.start()

        getController().startUpdating()
    }

    override fun stop() {
        super.stop()

        getController().stopUpdating()
    }


    /*
     * Public methods
     */
    fun dataUpdated(response: RatesResponse?) {
        if (response?.rates == null) {
            putDataOnView()
            return
        }

        mData.clear()
        mData.addAll(response.rates!!)
        putDataOnView()
    }


    /*
     * Protected methods
     */
    protected open fun putDataOnView() {

    }

    protected open fun data() = mData

    protected open fun getView() = view(IConverterView::class.java)
}
