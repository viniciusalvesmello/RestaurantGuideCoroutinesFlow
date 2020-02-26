package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils.ErrorMessages.MESSAGE_BAD_REQUEST_EXCEPTION
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils.ErrorMessages.MESSAGE_CONNECTION_EXCEPTION
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils.ErrorMessages.MESSAGE_DEFAULT_EXCEPTION
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils.ErrorMessages.MESSAGE_ILLEGAL_ARGUMENT_EXCEPTION
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils.ErrorMessages.MESSAGE_TIMEOUT_EXCEPTION
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils.ErrorMessages.MESSAGE_UNKNOW_HOST_EXCEPTION
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.handleNetworkError(): Pair<String, String> {
    return when (this) {
        is HttpException -> MESSAGE_BAD_REQUEST_EXCEPTION
        is SocketTimeoutException -> MESSAGE_TIMEOUT_EXCEPTION
        is IllegalArgumentException -> MESSAGE_ILLEGAL_ARGUMENT_EXCEPTION
        is UnknownHostException -> MESSAGE_UNKNOW_HOST_EXCEPTION
        is ConnectException -> MESSAGE_CONNECTION_EXCEPTION
        else -> MESSAGE_DEFAULT_EXCEPTION
    }
}