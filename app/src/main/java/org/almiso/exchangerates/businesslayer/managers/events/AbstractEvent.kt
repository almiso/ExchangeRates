package org.almiso.exchangerates.businesslayer.managers.events

abstract class AbstractEvent<T>(status: Int, data: T?, error: Throwable?) {
    companion object {
        const val SUCCESS = 1
        const val ERROR = 2
    }


    /*
     * Fields
     */
    protected val mStatus: Int = status
    protected val mData: T? = data
    protected val mError: Throwable? = error


    /*
     * Public methods
     */
    fun isSuccess() = mStatus == SUCCESS

    fun data() = mData

    fun error() = mError
}
