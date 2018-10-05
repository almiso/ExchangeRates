package org.almiso.exchangerates.objects

import java.util.*

open class Keeper<K>(isWeak: Boolean = false) {
    /*
     * Fields
     */
    protected val mStorage: Map<Class<out K>, K> = if (isWeak) {
        WeakHashMap()
    } else {
        HashMap()
    }


    /*
     * Public methods
     */
    fun put(clazz: Class<out K>, implementation: K) {
        storage()[clazz] = implementation
    }

    operator fun <T : K> get(clazz: Class<T>): T {
        return storage()[clazz] as T
    }

    fun clear() {
        storage().clear()
    }

    fun values() = storage().values


    /*
     * Protected methods
     */
    protected open fun storage() = mStorage as MutableMap
}
