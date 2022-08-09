package com.runprof.usedbrands.pojo

import com.example.moviestask.utils.Status


data class ListResult<out T, out U>(
    val status: Status,
    val data: List<T>?,
    val lastDoc: U?,
    val exception: Throwable?,
) {


    companion object {
        fun <T, U> success(data: List<T>?, lastDoc: U? = null): ListResult<T, U> {
            return ListResult(Status.SUCCESS, data, lastDoc, null)
        }

        fun <T, U> error(exception: Throwable? = null): ListResult<T, U> {
            return ListResult(Status.ERROR, null, null, exception)
        }

        fun <T, U> loading(): ListResult<T, U> {
            return ListResult(Status.LOADING, null, null, null)
        }

        fun <T, U> loadingMore(): ListResult<T, U> {
            return ListResult(Status.LOADING_MORE, null, null, null)
        }
    }
}
