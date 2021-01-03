package com.rumbl.rumbl_pt.di

import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 4/18/20.
 */


val othersModules = module {
	factory {
		CompositeDisposable()
	}
}
