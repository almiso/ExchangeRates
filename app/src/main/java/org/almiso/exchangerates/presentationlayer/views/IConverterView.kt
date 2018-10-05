package org.almiso.exchangerates.presentationlayer.views

import org.almiso.exchangerates.presentationlayer.adapters.ConverterAdapter

interface IConverterView : IView<IConverterView.IListener> {
    interface IListener : ConverterAdapter.IListener {
    }

}
