package com.rumbl.rumbl_pt.bases.network

import io.reactivex.rxjava3.functions.Consumer
import org.koin.core.KoinComponent

/**
 * Created by Mohamed Shalan on 6/1/20.
 */

interface IErrorHandling : Consumer<Throwable>, KoinComponent
