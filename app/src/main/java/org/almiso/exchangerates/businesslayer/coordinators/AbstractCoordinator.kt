package org.almiso.exchangerates.businesslayer.coordinators

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import org.almiso.exchangerates.presentationlayer.presenters.AbstractPresenter
import org.almiso.exchangerates.presentationlayer.views.IView

abstract class AbstractCoordinator<S, P : AbstractPresenter<*>>(system: S, presenter: P) : AbstractPresenter.IController {
    /*
     * Fields
     */
    protected val mSystem = system
    protected val mPresenter: P = presenter


    init {
        presenter().setController(controller())
    }


    /*
     * Public methods
     */
    open fun initialize(context: Context) {
        presenter().initialize(context)
    }

    open fun destroy() {
        presenter().destroy()
    }

    open fun setData(state: Bundle) {
        presenter().setData(state)
        presenter().validateData()
    }

    open fun saveData(state: Bundle) {
        presenter().saveData(state)
    }

    open fun setView(view: Any) {
        findAndSetViews(view)
        presenter().viewSetted()
    }

    open fun destroyView() {
        presenter().destroyView()
    }

    open fun start() {
        subscribe()
        presenter().start()
    }

    open fun stop() {
        presenter().stop()
        unsubscribe()
    }

    open fun resume() {
        presenter().resume()
    }

    open fun pause() {
        presenter().pause()
    }


    /*
     * Protected methods
     */
    protected open fun findAndSetViews(view: Any?) {
        view ?: return

        var i: Int
        var target: Class<*> = view.javaClass
        while (IView::class.java.isAssignableFrom(target)) {
            val interfaces = target.interfaces
            i = interfaces.size

            for (j in 0 until i) {
                val item = interfaces[j]
                if (IView::class.java != item && IView::class.java.isAssignableFrom(item)) {
                    presenter().setView(item as Class<IView<*>>, view as IView<*>)
                }
            }
            target = target.superclass
        }

        if (view is ViewGroup) {
            val group = view as ViewGroup?

            i = 0
            while (i < group!!.childCount) {
                findAndSetViews(group.getChildAt(i))
                ++i
            }
        }
    }

    protected open fun subscribe() {}

    protected open fun unsubscribe() {}

    protected open fun presenter() = mPresenter

    protected open fun controller() = this

    protected open fun system() = mSystem
}
