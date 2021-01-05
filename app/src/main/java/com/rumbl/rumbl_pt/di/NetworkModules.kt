package com.rumbl.rumbl_pt.di

import com.rumbl.rumbl_pt.bases.network.INetwork
import com.rumbl.rumbl_pt.bases.network.NetworkImp
import com.rumbl.rumbl_pt.bases.network.services.AuthApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 4/19/20.
 */

val networkModules = module {

    single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }

    single {
        NetworkImp(androidContext()) as INetwork
    }
}

val networkServiceModule = module {
    factory { get<INetwork>().createService(AuthApi::class.java) }
}
