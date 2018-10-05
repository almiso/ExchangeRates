package org.almiso.exchangerates.presentationlayer.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import org.almiso.exchangerates.presentationlayer.adapters.holders.AbstractHolder

abstract class AbstractAdapter<T> : RecyclerView.Adapter<AbstractHolder<T>>() {
    /*
     * Fields
     */
    protected val mItems: MutableList<T> = ArrayList()


    /*
     * Overrides
     */
    abstract override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): AbstractHolder<T>

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: AbstractHolder<T>, position: Int) {
        holder.bind(mItems[position])
    }


    /*
     * Public methods
     */
    fun getItems() = mItems

    fun getItem(position: Int) = getItems()[position]

    fun reset(items: List<T>) {
        mItems.clear()
        mItems.addAll(items)
    }
}
