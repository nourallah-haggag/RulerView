package com.rumbl.rumbl_pt.utils

import androidx.lifecycle.MutableLiveData
import com.rumbl.rumbl_pt.bases.services.SchedulerService
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.states.Result
import io.reactivex.rxjava3.core.*

/**
 * Created by Mohamed Shalan on 6/1/20.
 */

class IoLoadingTransformation<T>(
    private val schedulerService: SchedulerService,
    private val result: MutableLiveData<IResult<T>>
) :
    ObservableTransformer<T, T>,
    SingleTransformer<T, T>,
    CompletableTransformer, MaybeTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.getIoScheduler())
        .observeOn(schedulerService.getMainThread())

    override fun apply(upstream: Single<T>): SingleSource<T> = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.getIoScheduler())
        .observeOn(schedulerService.getMainThread())

    override fun apply(upstream: Completable): CompletableSource = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.getIoScheduler())
        .observeOn(schedulerService.getMainThread())

    override fun apply(upstream: Maybe<T>): MaybeSource<T> = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.getIoScheduler())
        .observeOn(schedulerService.getMainThread())
}
