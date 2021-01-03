package com.rumbl.rumbl_pt.bases.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rumbl.bases.viewmodel.IViewModel
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.states.Result
import com.rumbl.rumbl_pt.bases.network.IErrorHandling
import com.rumbl.rumbl_pt.bases.network.NetworkErrorHandlingImp
import com.rumbl.rumbl_pt.utils.IoLoadingTransformation
import com.rumbl.rumbl_pt.utils.IoTransformation
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.kotlin.addTo

/**
 * Created by Mohamed Shalan on 4/18/20.
 */

open class BaseViewModel : ViewModel(), IViewModel {

	override fun startLogic() {

	}

	var ioExceptionMessage: String = ""

	var socketTimeoutExceptionMessage: String = ""

	var internalServerErrorMessage: String? = null

	protected fun <T> getIoLoading(result: MutableLiveData<IResult<T>>): IoLoadingTransformation<T> =
		IoLoadingTransformation(getSchedulerService(), result)

	protected fun <T> getIo(): IoTransformation<T> = IoTransformation(getSchedulerService())

	fun <T> Observable<T>.execute(result: MutableLiveData<IResult<T>>) {
		this.compose(getIoLoading(result))
			.subscribe(
				{
					result.value = Result.success(it)
				},
				NetworkErrorHandlingImp(
					result = result,
					ioExceptionMessage = ioExceptionMessage,
					socketExceptionMessage = socketTimeoutExceptionMessage,
					internalServerErrorExceptionMessage = internalServerErrorMessage
				)
			).addTo(getDisposable())
	}

	fun <T> Single<T>.execute(result: MutableLiveData<IResult<T>>) {
		this.compose(getIoLoading(result))
			.subscribe(
				Consumer {
					result.value = Result.success(it)
				}, NetworkErrorHandlingImp(
					result = result,
					ioExceptionMessage = ioExceptionMessage,
					socketExceptionMessage = socketTimeoutExceptionMessage,
					internalServerErrorExceptionMessage = internalServerErrorMessage
				)
			).addTo(getDisposable())
	}

	fun <T> Maybe<T>.execute(
		result: MutableLiveData<IResult<T>>
	) {
		this.compose(getIoLoading(result))
			.subscribe(
				Consumer {
					result.value = Result.success(it)
				}, NetworkErrorHandlingImp(
					result = result,
					ioExceptionMessage = ioExceptionMessage,
					socketExceptionMessage = socketTimeoutExceptionMessage,
					internalServerErrorExceptionMessage = internalServerErrorMessage
				)
			).addTo(getDisposable())
	}

	fun Completable.execute(
		result: MutableLiveData<IResult<Any>>
	) {
		this.compose(getIoLoading(result))
			.subscribe(
				Action {
					result.value = Result.success()
				}, NetworkErrorHandlingImp(
					result = result,
					ioExceptionMessage = ioExceptionMessage,
					socketExceptionMessage = socketTimeoutExceptionMessage,
					internalServerErrorExceptionMessage = internalServerErrorMessage
				)
			).addTo(getDisposable())
	}

	fun <T, Error : IErrorHandling> Single<T>.execute(
        result: MutableLiveData<IResult<T>>,
        error: Error
	) {
		this.compose(getIoLoading(result))
			.subscribe(Consumer {
				result.value = Result.success(it)
			}, error)
	}

	override fun onCleared() {
		super.onCleared()
		getDisposable().dispose()
	}

}
