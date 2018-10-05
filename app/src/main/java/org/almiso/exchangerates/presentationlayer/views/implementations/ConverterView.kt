package org.almiso.exchangerates.presentationlayer.views.implementations

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.LayoutInflater
import org.almiso.exchangerates.R
import org.almiso.exchangerates.extensions.bind
import org.almiso.exchangerates.presentationlayer.adapters.ConverterAdapter
import org.almiso.exchangerates.presentationlayer.views.IConverterView

open class ConverterView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeView<IConverterView.IListener>(context, attrs, defStyleAttr), IConverterView {

    /*
     * Fields
     */
    protected val mToolbar: Toolbar by bind(R.id.toolbar)
    protected val mRecycler: RecyclerView by bind(R.id.recycler)

    protected lateinit var mAdapter: ConverterAdapter


    /*
     * Overrides
     */
    override fun onFinishInflate() {
        super.onFinishInflate()

        mToolbar.title = context.resources.getString(R.string.title_converter)
    }

    override fun setListener(listener: IConverterView.IListener) {
        super.setListener(listener)

        mAdapter = ConverterAdapter(LayoutInflater.from(context), getListener())
        mRecycler.adapter = mAdapter
    }
}
