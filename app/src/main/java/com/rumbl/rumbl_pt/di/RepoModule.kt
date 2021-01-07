package com.rumbl.rumbl_pt.di

import com.rumbl.rumbl_pt.repo.AuthRepo
import com.rumbl.rumbl_pt.repo.SessionsRepo
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 6/5/20.
 */


val repoModule = module {
    factory { AuthRepo(api = get()) }
    factory { SessionsRepo(api = get()) }
}
