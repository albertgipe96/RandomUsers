package com.development.feature.randomusers.impl.presentation.di

import com.development.feature.randomusers.impl.presentation.vm.RandomUsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val randomUsersPresentationModule = module {
    viewModelOf(::RandomUsersListViewModel)
}