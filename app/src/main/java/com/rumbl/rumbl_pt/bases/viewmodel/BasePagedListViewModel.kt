package com.rumbl.bases.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.states.Result
import com.rumbl.network.NetworkErrorHandlingImp
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Consumer

/**
 * Created by Mohamed Shalan on 6/2/20.
 */


abstract class BasePagedListViewModel<T : IPaginatedModel> : BaseViewModel() {

	var refreshData: Boolean = false

	var page: Int = 1
		private set

	private val dataList: MutableLiveData<IResult<T>> by lazy {
		MutableLiveData<IResult<T>>()
	}

	val dataList_: LiveData<IResult<T>>
		get() = dataList

	private val loadMoreEvent: MutableLiveData<Boolean> by lazy {
		MutableLiveData<Boolean>(false)
	}

	val loadMoreEvent_: LiveData<Boolean>
		get() = loadMoreEvent

	fun enableLoadMore() {
		loadMoreEvent.value = true
	}

	override fun startLogic() {
		super.startLogic()
		if (refreshData || dataList.value?.fetchData() == null) {
			loadDataFromScratch(page).execute(dataList)
			refreshData = false
		}
	}

	fun resetPageNumber() {
		page = 1
	}

	open fun getNextPage() {
		if (dataList.value?.fetchData()?.shouldPaginate() == true)
			loadMoreData(++page).executeLoadMore()
	}

	abstract fun loadDataFromScratch(page: Int): Single<T>

	abstract fun loadMoreData(page: Int): Single<T>


	fun Single<T>.executeLoadMore() {
		this.doOnSubscribe {
			dataList.postValue(Result.loading())
		}.compose(getIo())
			.subscribe(
				Consumer {
					dataList.value = Result.success(it)
					loadMoreEvent.value = false
				}, NetworkErrorHandlingImp(
					result = dataList,
					ioExceptionMessage = ioExceptionMessage,
					socketExceptionMessage = socketTimeoutExceptionMessage,
					loadMoreEvent = loadMoreEvent,
					internalServerErrorExceptionMessage = internalServerErrorMessage
				)
			).addTo(getDisposable())
	}
}
