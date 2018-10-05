package org.almiso.exchangerates.businesslayer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.almiso.exchangerates.R

open class ConverterFragment : AbstractFragment() {
    companion object {
        const val TAG = "ConverterFragment"
    }


    /*
     * Overrides
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_converter, container, false)
    }
}
