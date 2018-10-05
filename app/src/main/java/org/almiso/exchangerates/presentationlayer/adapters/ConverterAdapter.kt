package org.almiso.exchangerates.presentationlayer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import org.almiso.exchangerates.R
import org.almiso.exchangerates.objects.Rate
import org.almiso.exchangerates.presentationlayer.adapters.holders.AbstractHolder
import org.almiso.exchangerates.presentationlayer.adapters.holders.RateHolder

open class ConverterAdapter(inflater: LayoutInflater, listener: IListener?) : AbstractAdapter<Rate>() {

    /*
     * Callback
     */
    interface IListener : RateHolder.IListener

    /*
     * Fields
     */
    protected val mInflater: LayoutInflater = inflater
    protected val mListener: IListener? = listener


    /*
     * Overrides
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): AbstractHolder<Rate> {
        return RateHolder(mInflater.inflate(R.layout.item_rate, viewGroup, false), mListener)
    }
}
