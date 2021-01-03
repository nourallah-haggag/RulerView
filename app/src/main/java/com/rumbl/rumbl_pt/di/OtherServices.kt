package com.rumbl.rumbl_pt.di

import com.rumbl.rumbl_pt.bases.services.SessionService
import com.rumbl.rumbl_pt.bases.services.SessionServiceImp
import com.rumbl.rumbl_pt.bases.services.SharedService
import com.rumbl.rumbl_pt.bases.services.SharedServiceImp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 6/3/20.
 */


val otherServices = module {
    single { SharedServiceImp(androidContext()) as SharedService }
    single { SessionServiceImp() as SessionService }
}
