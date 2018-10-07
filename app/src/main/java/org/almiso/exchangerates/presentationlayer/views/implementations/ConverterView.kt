package org.almiso.exchangerates.presentationlayer.views.implementations

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import org.almiso.exchangerates.R
import org.almiso.exchangerates.extensions.bind
import org.almiso.exchangerates.objects.Rate
import org.almiso.exchangerates.objects.RateWrapper
import org.almiso.exchangerates.presentationlayer.adapters.ConverterAdapter
import org.almiso.exchangerates.presentationlayer.views.IConverterView

open class ConverterView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeView<IConverterView.IListener>(context, attrs, defStyleAttr), IConverterView {

    /*
     * Fields
     */
    protected val mToolbar: Toolbar by bind(R.id.toolbar)
    protected val mRecycler: RecyclerView by bind(R.id.recycler)
    protected val mProgress: View by bind(R.id.progress)

    protected lateinit var mAdapter: ConverterAdapter


    /*
     * Overrides
     */
    override fun onFinishInflate() {
        super.onFinishInflate()

        mToolbar.title = context.resources.getString(R.string.title_converter)
        mRecycler.layoutManager = LinearLayoutManager(context)
    }

    override fun setListener(listener: IConverterView.IListener) {
        super.setListener(listener)

        mAdapter = ConverterAdapter(LayoutInflater.from(context), getListener())
        mRecycler.adapter = adapter()
    }


    /*
     * Implemented methods
     */
    override fun showProgress(isVisible: Boolean) {
        setViewVisibility(mProgress, isVisible)
        setViewVisibility(mRecycler, !isVisible)
    }

    override fun setData(items: List<Rate>) {
        adapter().getItems().clear()
        for (rate in items) {
            adapter().getItems().add(RateWrapper(rate))
        }

        adapter().refresh()
    }

    override fun moveFromPosition(position: Int) {
        if (position <= 0) {
            return
        }

        adapter().getItems().removeAt(position).also { adapter().getItems().add(0, it) }
        adapter().notifyItemMoved(position, 0)
        mRecycler.scrollToPosition(0)
    }

    override fun updateCoefficient(coefficient: Double) {
        adapter().refresh(coefficient)
    }


    /*
     * Protected methods
     */
    protected fun adapter() = mAdapter

    protected fun setViewVisibility(view: View, isVisible: Boolean) {
        if (isVisible) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}
