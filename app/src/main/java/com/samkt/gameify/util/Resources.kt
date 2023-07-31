package com.samkt.gameify.util

sealed class Resources<out T>(val data:T? = null,val message:String? = null){
    class Success<T>(data: T?):Resources<T>(data)
    class Error<T>(data: T? = null,message: String?):Resources<T>(data,message)
    object Loading:Resources<Nothing>()
}
