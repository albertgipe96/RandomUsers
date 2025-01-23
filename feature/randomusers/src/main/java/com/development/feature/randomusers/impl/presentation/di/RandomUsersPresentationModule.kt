package com.development.feature.randomusers.impl.presentation.di

import androidx.lifecycle.SavedStateHandle
import com.development.feature.randomusers.impl.presentation.mapper.DomainToUiMapper
import com.development.feature.randomusers.impl.presentation.vm.RandomUserDetailViewModel
import com.development.feature.randomusers.impl.presentation.vm.RandomUsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val randomUsersPresentationModule = module {
    viewModelOf(::RandomUsersListViewModel)
    viewModel { (handle: SavedStateHandle) -> RandomUserDetailViewModel(handle, get(), get()) }
    factoryOf(::DomainToUiMapper)
}