package org.almiso.exchangerates.businesslayer.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import org.almiso.exchangerates.R

abstract class AbstractActivity : AppCompatActivity() {
    companion object {
        const val CONTENT_ID = R.id.content
    }


    /*
     * Overrides
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_content)
    }


    /*
     * Protected methods
     */
    protected open fun replaceFragment(fragment: Fragment, tag: String, addToBackStack: Boolean) {
        replaceFragment(CONTENT_ID, fragment, addToBackStack, tag)
    }

    protected open fun replaceFragment(contentId: Int, fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction().replace(contentId, fragment, tag)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        try {
            transaction.commitAllowingStateLoss()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }
    }
}
