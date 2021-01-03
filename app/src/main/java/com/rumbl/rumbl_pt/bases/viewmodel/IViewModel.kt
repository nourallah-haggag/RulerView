package com.rumbl.bases.viewmodel

import androidx.lifecycle.LifecycleObserver
import com.rumbl.rumbl_pt.bases.services.SchedulerService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.get

/**
 * Created by Mohamed Shalan on 4/18/20.
 */
interface IViewModel : KoinComponent, LifecycleObserver {

	fun getDisposable(): CompositeDisposable = get()

	fun getSchedulerService(): SchedulerService = get()

	fun startLogic()
}
