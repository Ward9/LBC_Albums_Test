package com.example.lbc_albums.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

/**
 * First we need to let our LiveData know that we are looking for the album, so that it should have a LOADING state.
Then, we would like to get that character from the local data source, because it is faster than getting it from the internet. If it finds it, we are changing the state to a SUCCESS
Regardless of the result of the local database operation, we would want to keep our app synced,
so we are fetching that character from the internet as well (but remember that the ui thread won’t be blocked and the user could already be shown the correct character information because of it being in the database!).
Finally, we need to save our result from the remote call in the database, in order to keep it updated.
 */
fun <T, A> resultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> ResultData<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<ResultData<T>> =
    liveData(Dispatchers.IO) { // when we do that we are launching a new IO coroutine, therefore letting us use our suspend functions, and we are as well storing our results in a LiveData holder, which is being observed by the ViewModel
        emit(ResultData.loading())
        val source = databaseQuery.invoke().map { ResultData.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == ResultData.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == ResultData.Status.ERROR) {
            emit(ResultData.error(responseStatus.message!!))
            emitSource(source)
        }
    }
