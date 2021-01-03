package com.rumbl.rumbl_pt.bases.services

import com.rumbl.rumbl_pt.bases.models.IUser
import org.koin.core.KoinComponent

/**
 * Created by Mohamed Shalan on 6/3/20.
 */

interface SessionService : KoinComponent {

	fun <T : IUser> saveUserSession(id: Long, user: T, clazz: Class<T>)

	fun <T : IUser> getUser(clazz: Class<T>): T?

	fun saveSessionToken(token: String)

	fun getSessionToken(): String?

	fun setLocale(locale: String)

	fun getLocale(): String

	fun hasValidSession(): Boolean = getSessionToken() != null

	fun clearSession()
}
