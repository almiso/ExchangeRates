package org.almiso.exchangerates.presentationlayer.views.implementations

import android.content.Context
import android.util.AttributeSet
import org.almiso.exchangerates.presentationlayer.views.IConverterView

open class ConverterView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeView<IConverterView.IListener>(context, attrs, defStyleAttr), IConverterView {

}
