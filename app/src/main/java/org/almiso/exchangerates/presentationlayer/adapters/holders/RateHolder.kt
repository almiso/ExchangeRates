package org.almiso.exchangerates.presentationlayer.adapters.holders

import android.view.View
import android.widget.EditText
import android.widget.TextView
import org.almiso.exchangerates.R
import org.almiso.exchangerates.extensions.bind
import org.almiso.exchangerates.objects.Rate

open class RateHolder(itemView: View, listener: IListener?) : AbstractHolder<Rate>(itemView), View.OnClickListener {
    interface IListener {
    }


    /*
     * Fields
     */
    protected val mListener: IListener? = listener
    protected val mTitle: TextView by bind(R.id.title)
    protected val mValue: EditText by bind(R.id.rate_value)


    /*
     * Constructor
     */
    init {
    }


    /*
     * Overrides
     */
    override fun bind(item: Rate) {
        super.bind(item)

        mTitle.text = item.title
        mValue.setText(item.value.toString())

    }

    override fun onClick(view: View?) {
    }
}
