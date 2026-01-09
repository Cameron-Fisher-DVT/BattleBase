package za.co.dvt.battlebase.common.data.remote.infrastructure

sealed class NetworkResponse<out T> {
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class HttpError(val message: String) : NetworkResponse<Nothing>()
    data class NetworkError(val exception: Exception) : NetworkResponse<Nothing>()
}