package org.almiso.exchangerates.presentationlayer.presenters

import android.os.Bundle
import android.text.TextUtils
import org.almiso.exchangerates.objects.Rate
import org.almiso.exchangerates.objects.RateWrapper
import org.almiso.exchangerates.objects.RatesResponse
import org.almiso.exchangerates.presentationlayer.views.IConverterView

open class ConverterPresenter : AbstractPresenter<ConverterPresenter.IController>(), IConverterView.IListener {
    companion object {
        protected const val DEFAULT_CURRENCY = "EUR"
    }

    /*
     * Callback
     */
    interface IController : AbstractPresenter.IController {
        fun startUpdating(currency: String)
        fun stopUpdating()
    }


    /*
     * Fields
     */
    protected val mData: MutableList<Rate> = ArrayList()
    protected var mCurrency: String = DEFAULT_CURRENCY
    protected var mAmount: Double = 1.0


    /*
     * Overrides
     */
    override fun setData(bundle: Bundle) {
        super.setData(bundle)

        var currency: String? = bundle.getString(DATA)

        if (TextUtils.isEmpty(currency)) {
            currency = DEFAULT_CURRENCY
        }

        mCurrency = currency!!
    }

    override fun saveData(bundle: Bundle) {
        super.saveData(bundle)

        bundle.putString(DATA, mCurrency)
    }

    override fun viewSetted() {
        super.viewSetted()

        getView().setListener(this)
    }

    override fun start() {
        super.start()

        getController().startUpdating(mCurrency)
    }

    override fun stop() {
        super.stop()

        getController().stopUpdating()
    }


    /*
     * Implemented methods
     */
    override fun onRateSelected(rate: Rate, position: Int) {
        mCurrency = rate.currency!!
        mAmount = rate.amount

        getView().moveFromPosition(position)
        getController().startUpdating(mCurrency)
    }

    override fun onAmountChanged(rate: Rate, amount: Double) {
        mCurrency = rate.currency!!
        mAmount = rate.amount

        getView().updateCoefficient(amount)
    }


    /*
     * Public methods
     */
    fun dataUpdated(response: RatesResponse?) {
        if (response?.rates == null) {
            putDataOnView()
            return
        }
        val baseRate = Rate()
        baseRate.currency = mCurrency
        baseRate.amount = mAmount

        data().clear()
        data().add(baseRate)
        data().addAll(response.rates!!)
        putDataOnView()
    }


    /*
     * Protected methods
     */
    protected open fun putDataOnView() {
        if (data().isEmpty()) {
            getView().showProgress(true)
            return
        }

        getView().showProgress(false)
        getView().setData(data())
    }

    protected open fun data() = mData

    protected open fun getView() = view(IConverterView::class.java)
}
