package com.rumbl.rumbl_pt.bases.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rumbl.rumbl_pt.bases.states.IResult
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohamed Shalan on 6/10/20.
 */


abstract class BaseSingleListViewModel<T> : BaseViewModel() {

	var refreshData: Boolean = false

	private val data: MutableLiveData<IResult<T>> by lazy {
		MutableLiveData<IResult<T>>()
	}

	val data_: LiveData<IResult<T>>
		get() = data

	override fun startLogic() {
		super.startLogic()
		if (refreshData || data.value?.fetchData() == null) {
			loadData().execute(data)
			refreshData = false
		}
	}

	abstract fun loadData(): Single<T>

}
