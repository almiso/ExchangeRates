package org.almiso.exchangerates.businesslayer.fragments

import android.content.Intent
import org.almiso.exchangerates.businesslayer.coordinators.AbstractCoordinator
import org.almiso.exchangerates.presentationlayer.presenters.AbstractPresenter

abstract class BaseFragment : AbstractFragment() {
    companion object {
        const val TAG = "BaseFragment"
    }


    /*
     * Inner classes
     */
    open class BaseCoordinator<P : AbstractPresenter<out AbstractPresenter.IController>>(system: BaseFragment, presenter: P)
        : AbstractCoordinator<BaseFragment, P>(system, presenter) {

        /*
         * Protected methods
         */
        protected open fun activity() = system().activity!!

        protected open fun startActivity(intent: Intent) {
            system().startActivity(intent)
        }

        protected open fun startActivityForResult(intent: Intent, requestCode: Int) {
            system().startActivityForResult(intent, requestCode)
        }

        protected open fun finish() {
            activity().finish()
        }
    }
}
