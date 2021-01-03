package com.rumbl.rumbl_pt.bases.services

import com.rumbl.rumbl_pt.bases.models.IUser
import com.rumbl.rumbl_pt.bases.services.SharedService
import org.koin.core.inject

/**
 * Created by Mohamed Shalan on 6/3/20.
 */


class SessionServiceImp : SessionService {

	companion object {
		val USER_ID_KEY = "user-id"
		val USER_OBJECT_LEY = "user-object"
		val TOKEN_KEY = "user-token"
		val LOCALE_KEY = "locale"
	}

	private val sharedService by inject<SharedService>()

	private val serializationService by inject<SerializationService>()

	override fun <T : IUser> saveUserSession(id: Long, user: T, clazz: Class<T>) {
		sharedService.save(USER_ID_KEY, id)
		val userJson = serializationService.serialize(user, clazz)
		sharedService.save(USER_OBJECT_LEY, userJson)
	}

	override fun <T : IUser> getUser(clazz: Class<T>): T? =
		sharedService.get(USER_OBJECT_LEY, "").let {
			if (it.isNotEmpty())
				serializationService.deserialize(it, clazz)
			else
				null
		}

	override fun saveSessionToken(token: String) {
		sharedService.save(TOKEN_KEY, token)
	}

	override fun getSessionToken(): String? = sharedService.get(TOKEN_KEY, "").let {
		if (it.isNotEmpty())
			it
		else
			null
	}

	override fun setLocale(locale: String) {
		sharedService.save(LOCALE_KEY, locale)
	}

	override fun getLocale(): String = sharedService.get(LOCALE_KEY, "en")

	override fun clearSession() {
		sharedService.remove(USER_ID_KEY)
		sharedService.remove(USER_OBJECT_LEY)
		sharedService.remove(TOKEN_KEY)
	}
}
