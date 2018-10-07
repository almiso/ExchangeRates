package org.almiso.exchangerates.presentationlayer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import org.almiso.exchangerates.R
import org.almiso.exchangerates.objects.Rate
import org.almiso.exchangerates.objects.RateWrapper
import org.almiso.exchangerates.presentationlayer.adapters.holders.AbstractHolder
import org.almiso.exchangerates.presentationlayer.adapters.holders.RateHolder

open class ConverterAdapter(inflater: LayoutInflater, listener: IListener?) : AbstractAdapter<RateWrapper>() {

    /*
     * Callback
     */
    interface IListener : RateHolder.IListener

    /*
     * Fields
     */
    protected val mInflater: LayoutInflater = inflater
    protected val mListener: IListener? = listener
    protected var mCoefficient = 1.0


    /*
     * Overrides
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): AbstractHolder<RateWrapper> {
        return RateHolder(mInflater.inflate(R.layout.item_rate, viewGroup, false), mListener)
    }

    override fun onBindViewHolder(holder: AbstractHolder<RateWrapper>, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            super.onBindViewHolder(holder, position)
        }
    }


    /*
     * Public methods
     */
    fun refresh(coefficient: Double = -1.0) {
        if (coefficient > 0) {
            mCoefficient = coefficient
        }

        for (position in 1 until itemCount) {
            getItem(position).coefficient = mCoefficient
        }

        notifyItemRangeChanged(0, itemCount - 1, mCoefficient)
    }
}
