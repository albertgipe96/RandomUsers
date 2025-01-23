package com.development.feature.randomusers.impl.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.development.feature.randomusers.impl.domain.model.RandomUser
import com.development.feature.randomusers.impl.domain.usecases.GetRandomUsersList
import kotlinx.coroutines.flow.Flow

class RandomUsersListViewModel(
    private val getRandomUsersList: GetRandomUsersList
) : ViewModel() {

    val randomUsersPagingDataFlow: Flow<PagingData<RandomUser>> = getRandomUsersList()
        .cachedIn(viewModelScope)

}