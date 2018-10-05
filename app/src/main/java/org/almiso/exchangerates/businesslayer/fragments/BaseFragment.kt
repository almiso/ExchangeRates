package org.almiso.exchangerates.businesslayer.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.almiso.exchangerates.businesslayer.coordinators.AbstractCoordinator
import org.almiso.exchangerates.businesslayer.di.DaggerDependenciesInjector
import org.almiso.exchangerates.businesslayer.di.DependenciesFactory
import org.almiso.exchangerates.businesslayer.di.DependenciesInjector
import org.almiso.exchangerates.presentationlayer.presenters.AbstractPresenter
import org.greenrobot.eventbus.EventBus

abstract class BaseFragment : AbstractFragment() {
    companion object {
        const val TAG = "BaseFragment"
    }

    /*
     * Fields
     */
    protected lateinit var mInjector: DependenciesInjector


    /*
     * Overrides
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mInjector = DaggerDependenciesInjector.builder().dependenciesFactory(DependenciesFactory()).build()
    }


    /*
     * Inner classes
     */
    open class BaseCoordinator<P : AbstractPresenter<out AbstractPresenter.IController>>(system: BaseFragment, presenter: P)
        : AbstractCoordinator<BaseFragment, P>(system, presenter) {


        /*
         * Fields
         */
        protected lateinit var mInjector: DependenciesInjector

        /*
         * Overrides
         */
        override fun initialize(context: Context) {
            super.initialize(context)

            mInjector = DaggerDependenciesInjector.builder().dependenciesFactory(DependenciesFactory()).build()
        }

        override fun subscribe() {
            super.subscribe()
            EventBus.getDefault().register(this)
        }

        override fun unsubscribe() {
            super.unsubscribe()
            EventBus.getDefault().unregister(this)
        }


        /*
         * Protected methods
         */
        protected fun injector() = mInjector

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
