package org.almiso.exchangerates.presentationlayer.adapters.holders

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class AbstractHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /*
     * Fields
     */
    protected var mItem: T? = null


    /*
     * Public methods
     */
    open fun bind(item: T) {
        mItem = item
    }


    /*
     * Protected methods
     */
    protected fun item() = mItem!!
}
