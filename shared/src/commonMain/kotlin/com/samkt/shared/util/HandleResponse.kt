package com.samkt.shared.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T,
): Resources<T> {
    return withContext(dispatcher) {
        try {
            Resources.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            Resources.Error(throwable.message ?: "Unexpected error occurred")
        }
    }
}
