package org.almiso.exchangerates

import android.os.Bundle
import org.almiso.exchangerates.businesslayer.activities.AbstractActivity
import org.almiso.exchangerates.businesslayer.fragments.ConverterFragment

class MainActivity : AbstractActivity() {
    /*
     * Overrides
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            replaceFragment(ConverterFragment(), ConverterFragment.TAG, false)
        }
    }
}
