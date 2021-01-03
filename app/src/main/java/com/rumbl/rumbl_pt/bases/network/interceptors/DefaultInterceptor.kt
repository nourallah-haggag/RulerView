package com.rumbl.rumbl_pt.bases.network.interceptors

import com.rumbl.rumbl_pt.bases.services.SessionService
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Mohamed Shalan on 6/6/20.
 */


class DefaultInterceptor : Interceptor, KoinComponent {

    private val sessionService by inject<SessionService>()

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("X-Locale", sessionService.getLocale())
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json").apply {
                sessionService.getSessionToken()?.let { token ->
                    addHeader("Authorization", "bearer $token")
                }
            }.method(originalRequest.method, originalRequest.body)
            .build()
        return chain.proceed(newRequest)
    }
}
