package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils

object ErrorMessages {
    val MESSAGE_BAD_REQUEST_EXCEPTION: Pair<String, String> = Pair(
            "Tentar novamente!",
            "Houve um erro ao tentar realizar a operação"
    )
    val MESSAGE_TIMEOUT_EXCEPTION: Pair<String, String> = Pair(
            "Parece que você está",
            "com a conexão com a internet um pouco lenta"
    )
    val MESSAGE_ILLEGAL_ARGUMENT_EXCEPTION: Pair<String, String> = Pair(
            "Ops!",
            "Algo não saiu como o esperado"
    )
    val MESSAGE_UNKNOW_HOST_EXCEPTION: Pair<String, String> = Pair(
            "Parece que você está",
            "sem conexão com a internet"
    )
    val MESSAGE_CONNECTION_EXCEPTION: Pair<String, String> = Pair(
            "Não foi possivel",
            "fazer conexão com o nosso servidor"
    )
    val MESSAGE_DEFAULT_EXCEPTION: Pair<String, String> = Pair(
            "Aconteceu algo inesperado!",
            "Houve um erro ao tentar realizar a operação"
    )
}