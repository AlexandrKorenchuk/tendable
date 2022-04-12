package com.release.data.utils

import com.release.core_ui.utilis.ResourceManager
import com.release.domain.utils.AppException
import com.release.domain.utils.NoInternetException
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class SafeApiCall @Inject constructor(
    val resourceManager: ResourceManager
) {

    suspend inline fun <T : Any> apiCall(crossinline block: suspend () -> T): T {
        return try {
            block.invoke()
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    throw AppException(e.message())
                }
                is UnknownHostException -> {
                    throw NoInternetException(e.message.toString())
                }
                else -> {
                    throw AppException(e.message.toString())
                }
            }
        }
    }
}
