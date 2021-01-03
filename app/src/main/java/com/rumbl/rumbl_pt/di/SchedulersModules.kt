package com.rumbl.rumbl_pt.di

import com.rumbl.rumbl_pt.bases.services.SchedulerService
import com.rumbl.rumbl_pt.bases.services.SchedulerServiceImp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.internal.schedulers.ComputationScheduler
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 4/18/20.
 */


val schedulersModule = module {

    single { SchedulerServiceImp() as SchedulerService }
    single(named(name = "mainthread")) { AndroidSchedulers.mainThread() as Scheduler }
    single(named(name = "io")) { IoScheduler() as Scheduler }
    single(named(name = "computation")) { ComputationScheduler() as Scheduler }
}
