package org.almiso.exchangerates.presentationlayer.views.implementations

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import org.almiso.exchangerates.presentationlayer.views.IView

open class RelativeView<L> @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr), IView<L> {

    /*
     * Fields
     */
    protected var mListener: L? = null


    /*
     * Overrides
     */
    override fun setListener(listener: L) {
        mListener = listener
    }

    override fun destroy() {
    }


    /*
     * Protected methods
     */
    protected fun getListener() = mListener
}
