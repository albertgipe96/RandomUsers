package com.development.feature.randomusers.impl.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.development.feature.randomusers.impl.domain.usecases.DeleteRandomUser
import com.development.feature.randomusers.impl.domain.usecases.GetRandomUsersList
import com.development.feature.randomusers.impl.presentation.mapper.DomainToUiMapper
import com.development.feature.randomusers.impl.presentation.model.RandomUserItemUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

sealed interface RandomUsersListUiState {
    data object Loading : RandomUsersListUiState
    data class Loaded(val randomUsersPagingDataFlow: Flow<PagingData<RandomUserItemUi>>) : RandomUsersListUiState
}

sealed interface RandomUsersListAction {
    data class DeleteUser(val id: String) : RandomUsersListAction
}

sealed interface RandomUsersListEvent {
    data object UserDeletedSuccessfully : RandomUsersListEvent
    data object UserDeletedError : RandomUsersListEvent
}

class RandomUsersListViewModel(
    private val getRandomUsersList: GetRandomUsersList,
    private val deleteRandomUser: DeleteRandomUser,
    private val domainToUiMapper: DomainToUiMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<RandomUsersListUiState>(RandomUsersListUiState.Loading)
    val uiState: StateFlow<RandomUsersListUiState> = _uiState

    private val _events = MutableSharedFlow<RandomUsersListEvent>()
    val events: SharedFlow<RandomUsersListEvent> = _events

    init {
        val randomUsersPagingDataFlow: Flow<PagingData<RandomUserItemUi>> = with(domainToUiMapper) {
            getRandomUsersList()
                .map { it.map { randomUser -> randomUser.toRandomUserItemUi() } }
                .cachedIn(viewModelScope)
        }
        _uiState.value = RandomUsersListUiState.Loaded(randomUsersPagingDataFlow)
    }

    fun onAction(action: RandomUsersListAction) {
        when (action) {
            is RandomUsersListAction.DeleteUser -> handleDeleteRandomUser(action.id)
        }
    }

    private fun handleDeleteRandomUser(id: String) {
        viewModelScope.launch {
            deleteRandomUser(id)
                .onSuccess {
                    _events.emit(RandomUsersListEvent.UserDeletedSuccessfully)
                }
                .onFailure {
                    _events.emit(RandomUsersListEvent.UserDeletedError)
                }
        }
    }

}