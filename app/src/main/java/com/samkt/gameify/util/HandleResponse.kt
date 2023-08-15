package com.samkt.gameify.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


fun <T : Any> handleResponse(
    response: suspend () -> T
): Flow<Resources<T>> = flow {
    try {
        emit(Resources.Loading)
        val result = response.invoke()
        emit(Resources.Success(result))
    } catch (e: Exception) {
        emit(
            Resources.Error(
                data = null,
                message = e.localizedMessage ?: "Unknown error"
            )
        )
    }
}