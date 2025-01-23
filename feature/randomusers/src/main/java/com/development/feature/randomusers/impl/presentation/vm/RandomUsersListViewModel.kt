package com.development.feature.randomusers.impl.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.development.feature.randomusers.impl.domain.model.RandomUser
import com.development.feature.randomusers.impl.domain.usecases.GetRandomUsersList
import com.development.feature.randomusers.impl.presentation.mapper.DomainToUiMapper
import com.development.feature.randomusers.impl.presentation.model.RandomUserItemUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RandomUsersListViewModel(
    private val getRandomUsersList: GetRandomUsersList,
    private val domainToUiMapper: DomainToUiMapper
) : ViewModel() {

    val randomUsersPagingDataFlow: Flow<PagingData<RandomUserItemUi>> = with(domainToUiMapper) {
        getRandomUsersList()
            .map { it.map { randomUser -> randomUser.toRandomUserItemUi() } }
            .cachedIn(viewModelScope)
    }

}