package com.rumbl.rumbl_pt.di


import com.rumbl.rumbl_pt.bases.services.ImageLoadingService
import com.rumbl.rumbl_pt.bases.services.ImageLoadingServiceImp
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 4/20/20.
 */


val imageLoadingModules = module {
    single { ImageLoadingServiceImp() as ImageLoadingService }
}
