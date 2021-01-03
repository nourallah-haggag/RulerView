package com.rumbl.rumbl_pt.bases.services

import io.reactivex.rxjava3.core.Scheduler
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.qualifier.named

/**
 * Created by Mohamed Shalan on 4/18/20.
 */

interface SchedulerService : KoinComponent {

	fun getMainThread(): Scheduler = get(named("mainthread"))

	fun getIoScheduler(): Scheduler = get(named("io"))

	fun getComputationScheduler(): Scheduler = get(named("computation"))
}
