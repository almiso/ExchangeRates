package org.almiso.exchangerates.businesslayer.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import org.almiso.exchangerates.businesslayer.coordinators.AbstractCoordinator

abstract class AbstractFragment : Fragment() {
    companion object {
        const val TAG = "AbstractFragment"
    }


    /*
     * Fields
     */
    protected val mCoordinator = initializeCoordinator()


    /*
     * Overrides
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getCoordinator().initialize(context!!)
    }

    override fun onDestroy() {
        getCoordinator().destroy()
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getCoordinator().setData(findArguments(savedInstanceState))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getCoordinator().saveData(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCoordinator().setView(view)
    }

    override fun onDestroyView() {
        getCoordinator().destroyView()
        super.onDestroyView()
    }

    override fun onStart() {
        super.onStart()
        getCoordinator().start()
    }

    override fun onStop() {
        getCoordinator().stop()
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
        getCoordinator().resume()
    }

    override fun onPause() {
        getCoordinator().pause()
        super.onPause()
    }


    /*
     * Protected methods
     */
    protected open fun findArguments(savedInstanceState: Bundle?): Bundle = savedInstanceState ?: if (arguments != null) arguments!! else Bundle()

    protected open fun getCoordinator() = mCoordinator

    protected abstract fun initializeCoordinator(): AbstractCoordinator<*, *>
}
