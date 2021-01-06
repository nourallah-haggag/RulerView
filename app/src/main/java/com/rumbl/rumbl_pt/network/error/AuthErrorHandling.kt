package com.rumbl.rumbl_pt.network.error

import androidx.lifecycle.MutableLiveData
import com.rumbl.rumbl_pt.network.AuthError
import com.rumbl.rumbl_pt.network.NetworkErrorHandlingImp
import com.rumbl.rumbl_pt.network.response.AuthResponse
import com.rumbl.rumbl_pt.bases.states.IResult


class AuthErrorHandling(
    result: MutableLiveData<IResult<AuthResponse>>,
    ioExceptionMessage: String,
    socketExceptionMessage: String,
    internalServerErrorExceptionMessage: String? = null
) :
    NetworkErrorHandlingImp<AuthResponse>(
        result = result,
        ioExceptionMessage = ioExceptionMessage,
        socketExceptionMessage = socketExceptionMessage,
        internalServerErrorExceptionMessage = internalServerErrorExceptionMessage
    ) {
    override fun handleForbiddenStatus(errorBody: String?): String? {
        errorBody?.let {
            serializationService.deserialize(
                it,
                AuthError::class.java
            )?.errorCode?.let { unVerifiedError ->
                return unVerifiedError
            }
        }
        return super.handleForbiddenStatus(errorBody)
    }
}
