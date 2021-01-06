package com.rumbl.rumbl_pt.repo

import com.rumbl.rumbl_pt.bases.services.SerializationService
import com.rumbl.rumbl_pt.bases.services.SessionService
import com.rumbl.rumbl_pt.bases.services.SharedService
import org.koin.core.KoinComponent
import org.koin.core.get

/**
 * Created by Mohamed Shalan on 4/18/20.
 */

interface IRepo: KoinComponent {

	fun getSharedPreferences(): SharedService = get()

	fun getSessionService(): SessionService = get()

	fun getSerializationService(): SerializationService = get()

}
