package com.rumbl.rumbl_pt.network

import org.koin.core.KoinComponent
import retrofit2.Retrofit

/**
 * Created by Mohamed Shalan on 4/18/20.
 */
interface INetwork : KoinComponent {

	fun getClient(): Retrofit

	fun <T> createService(clazz: Class<T>): T
}
