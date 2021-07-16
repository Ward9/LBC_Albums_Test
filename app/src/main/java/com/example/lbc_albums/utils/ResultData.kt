package com.example.lbc_albums.utils

/**
 * It helps us encapsulate our repository responses according to their state, making it easy for our views to display information accordingly.
 * This is how HomeFragment observes a LiveData value and updates itself accordingly.
 */
data class ResultData<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ResultData<T> {
            return ResultData(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ResultData<T> {
            return ResultData(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): ResultData<T> {
            return ResultData(Status.LOADING, data, null)
        }
    }
}