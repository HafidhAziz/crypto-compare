package com.example.framework

import retrofit2.HttpException
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class ResponseHandler {

    fun <T : Any> setSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> setException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(e.code(), getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.error(
                msg = getErrorMessage(ErrorCodes.SocketTimeOut.code),
                data = null
            )
            else -> Resource.error(msg = getErrorMessage(Int.MAX_VALUE), data = null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorized. Please re-login"
            404 -> "No Data Available"
            else -> "Something went wrong"
        }
    }
}
