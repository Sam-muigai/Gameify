package com.samkt.gameify.util

sealed class Resources<out T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resources<T>(data)
    class Error<T>(message: String?) : Resources<T>(message = message)
}
