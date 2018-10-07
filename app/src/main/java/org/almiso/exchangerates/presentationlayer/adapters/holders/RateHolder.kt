package org.almiso.exchangerates.presentationlayer.adapters.holders

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import org.almiso.exchangerates.R
import org.almiso.exchangerates.extensions.bind
import org.almiso.exchangerates.extensions.currency
import org.almiso.exchangerates.objects.Rate
import org.almiso.exchangerates.objects.RateWrapper

open class RateHolder(itemView: View, listener: IListener?) : AbstractHolder<RateWrapper>(itemView), TextWatcher, View.OnFocusChangeListener {
    interface IListener {
        fun onRateSelected(rate: Rate, position: Int)
        fun onAmountChanged(rate: Rate, amount: Double)
    }


    /*
     * Fields
     */
    protected val mListener: IListener? = listener
    protected val mTitle: TextView by bind(R.id.title)
    protected val mValue: EditText by bind(R.id.rate_value)

    var mCurrentCurrency: String = ""


    /*
     * Constructor
     */
    init {
        mValue.addTextChangedListener(this)
        mValue.onFocusChangeListener = this
    }


    /*
     * Overrides
     */
    override fun bind(item: RateWrapper) {
        super.bind(item)

        if (!TextUtils.equals(mCurrentCurrency, item.rate.currency)) {
            mCurrentCurrency = item.rate.currency!!
            mTitle.text = item.rate.currency
        }

        if (!mValue.isFocused) {
            mValue.setText((item.rate.amount * item.coefficient).currency())
        }
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            return
        }

        mListener?.onRateSelected(item().rate, layoutPosition)
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(value: CharSequence?, start: Int, before: Int, count: Int) {
        if (mValue.isFocused) {
            val amount = if (TextUtils.isEmpty(value)) {
                0.0
            } else {
                value?.toString()?.toDouble() ?: 0.0
            }
            mItem!!.rate.amount = amount
            mItem!!.coefficient = amount
            mListener?.onAmountChanged(item().rate, amount)
        }
    }
}
