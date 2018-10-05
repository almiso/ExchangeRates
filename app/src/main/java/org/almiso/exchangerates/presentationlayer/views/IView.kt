package org.almiso.exchangerates.presentationlayer.views

interface IView<L> {
    fun destroy()
    fun setListener(listener: L)
}
