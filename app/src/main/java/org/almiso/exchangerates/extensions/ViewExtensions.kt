package org.almiso.exchangerates.extensions

import android.support.annotation.IdRes
import android.view.View
import org.almiso.exchangerates.presentationlayer.adapters.holders.AbstractHolder


fun <T : View> View.bind(@IdRes idRes: Int): Lazy<T> {
    return unsafeLazy { findViewById<T>(idRes) }
}

fun <T : View> AbstractHolder<*>.bind(@IdRes idRes: Int): Lazy<T> {
    return unsafeLazy { itemView.findViewById<T>(idRes) }
}

private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
