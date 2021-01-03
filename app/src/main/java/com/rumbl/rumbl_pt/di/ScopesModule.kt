package com.rumbl.rumbl_pt.di

import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 6/25/20.
 */


val scopesModule = module {
    scope(named(PlanScopeConstants.SCOPE_NAMED)) {

    }
}
