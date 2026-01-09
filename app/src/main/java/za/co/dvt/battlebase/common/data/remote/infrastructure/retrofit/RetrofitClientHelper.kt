package za.co.dvt.battlebase.common.data.remote.infrastructure.retrofit

import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import za.co.dvt.battlebase.common.data.remote.infrastructure.NetworkResponse

object RetrofitClientHelper {
    suspend inline fun <reified T> serviceCall(
        apiCall: suspend () -> Response<T>
    ): NetworkResponse<T> {
        return try {
            val response = apiCall()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                NetworkResponse.Success(body)
            } else {
                NetworkResponse.HttpError(response.message())
            }
        } catch (e: HttpException) {
            NetworkResponse.HttpError(e.message ?: "")
        } catch (e: IOException) {
            NetworkResponse.HttpError(e.message ?: "")
        } catch (e: Exception) {
            NetworkResponse.NetworkError(e)
        }
    }
}