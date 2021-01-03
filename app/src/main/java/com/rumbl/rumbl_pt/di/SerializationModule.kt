package com.rumbl.rumbl_pt.di

import com.rumbl.rumbl_pt.bases.services.SerializationService
import com.rumbl.rumbl_pt.bases.services.SerializationServiceImp
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 6/1/20.
 */


val serializationModule = module {
    single { SerializationServiceImp(moshi = get()) as SerializationService }
}
