package com.development.feature.randomusers.impl.presentation.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.development.feature.randomusers.api.navigation.RandomUsersNavRoute
import com.development.feature.randomusers.impl.domain.repository.RandomUsersRepository
import com.development.feature.randomusers.impl.domain.usecases.GetRandomUser
import com.development.feature.randomusers.impl.presentation.mapper.DomainToUiMapper
import com.development.feature.randomusers.impl.presentation.model.RandomUserDetailUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface DetailUiState {
    data object Loading : DetailUiState
    data class Loaded(val randomUserDetail: RandomUserDetailUi) : DetailUiState
    data class Error(val message: String) : DetailUiState
}

class RandomUserDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getRandomUser: GetRandomUser,
    private val domainToUiMapper: DomainToUiMapper
) : ViewModel() {

    private val id: String = savedStateHandle.toRoute<RandomUsersNavRoute.Detail>().id

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState

    init {
        viewModelScope.launch {
            getRandomUser(id)
                .onSuccess {
                    with(domainToUiMapper) {
                        _uiState.value = DetailUiState.Loaded(it.toRandomUserDetailUi())
                    }
                }
                .onFailure {
                    _uiState.value = DetailUiState.Error(it.message ?: "Error loading user")
                }
        }
    }

}