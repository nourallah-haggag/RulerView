package com.rumbl.rumbl_pt.network

import androidx.lifecycle.MutableLiveData
import com.rumbl.rumbl_pt.bases.states.IResult
import okhttp3.Headers

/**
 * Created by Mohamed Shalan on 6/22/20.
 */


open class NetworkErrorHandlingImp<T>(
    result: MutableLiveData<IResult<T>>,
    loadMoreEvent: MutableLiveData<Boolean>? = null,
    private val ioExceptionMessage: String,
    private val socketExceptionMessage: String,
    private val internalServerErrorExceptionMessage: String? = null
) : DefaultErrorHandlingImp<T>(result, loadMoreEvent) {


    override fun getIoExceptionMessage(): String = ioExceptionMessage

    override fun getSocketTimeExceptionMessage(): String = socketExceptionMessage

    override fun extractErrorMessagesIfAny(errorBody: String?): String? = errorBody?.let {
        serializationService.deserialize(it, Error::class.java)?.message
    }

    override fun handleUnAuthorizationException(errorString: String?, headers: Headers?): String? {
        if (headers?.get(Constants.AUTHORIZATION_HEADER) != null) {
            TODO("Not yet implemented")
        } else {
            errorString?.let {
                return serializationService.deserialize(it, Error::class.java)?.message
            }
        }
        return null
    }

    override fun handleForbiddenStatus(errorBody: String?): String? = errorBody?.let {
        return serializationService.deserialize(it, Error::class.java)?.message
    }

    override fun handleInternalServerError(): String? = internalServerErrorExceptionMessage

    override fun handleNotFoundError(errorBody: String?): String? = errorBody?.let {
        serializationService.deserialize(it, Error::class.java)?.message
    }
}
