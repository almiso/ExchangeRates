package org.almiso.exchangerates.presentationlayer.presenters

import android.content.Context
import android.os.Bundle
import org.almiso.exchangerates.objects.Keeper
import org.almiso.exchangerates.presentationlayer.views.IView


abstract class AbstractPresenter<C : AbstractPresenter.IController> {
    companion object {
        const val DATA = "data"
        const val MODEL = "model"
    }

    /*
     * Controller
     */
    interface IController


    /*
     * Fields
     */
    protected lateinit var mController: C
    protected val mViews: Keeper<IView<*>> = Keeper(true)


    /*
     * Public methods
     */
    fun setController(controller: AbstractPresenter.IController) {
        mController = controller as C
    }

    open fun initialize(context: Context) {}

    open fun setData(bundle: Bundle) {}

    open fun saveData(bundle: Bundle) {}

    open fun destroy() {}

    open fun validateData() {}

    open fun <K : IView<*>, V : IView<*>> setView(clazz: Class<K>, view: V) {
        mViews.put(clazz, view)
    }

    open fun viewSetted() {}

    open fun destroyView() {
        val view = mViews.values().iterator()

        while (view.hasNext()) {
            val item = view.next() as IView
            item.destroy()
        }

        mViews.clear()
    }

    open fun start() {}

    open fun resume() {}

    open fun pause() {}

    open fun stop() {}


    /*
     * Protected methods
     */
    protected fun getController() = mController

    protected fun <V : IView<*>> view(clazz: Class<V>): V = mViews[clazz]
}
