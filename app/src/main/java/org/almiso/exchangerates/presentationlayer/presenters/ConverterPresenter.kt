package org.almiso.exchangerates.presentationlayer.presenters

import org.almiso.exchangerates.presentationlayer.views.IConverterView

open class ConverterPresenter : AbstractPresenter<ConverterPresenter.IController>(), IConverterView.IListener {
    /*
     * Callback
     */
    interface IController : AbstractPresenter.IController {

    }

    /*
     * Overrides
     */
    override fun viewSetted() {
        super.viewSetted()

        getView().setListener(this)
    }


    /*
     * Protected methods
     */
    protected fun getView() = view(IConverterView::class.java)
}
