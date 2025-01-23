@file:OptIn(ExperimentalMaterial3Api::class)

package com.development.feature.randomusers.impl.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.development.feature.randomusers.impl.presentation.components.RandomUserListItem
import com.development.feature.randomusers.impl.presentation.model.RandomUserItemUi
import com.development.feature.randomusers.impl.presentation.vm.RandomUsersListAction
import com.development.feature.randomusers.impl.presentation.vm.RandomUsersListEvent
import com.development.feature.randomusers.impl.presentation.vm.RandomUsersListUiState
import com.development.feature.randomusers.impl.presentation.vm.RandomUsersListViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun RandomUsersListScreen(
    onRandomUserClick: (String) -> Unit
) {
    val context = LocalContext.current

    val viewModel: RandomUsersListViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect { event ->
            when (event) {
                RandomUsersListEvent.UserDeletedError -> {
                    Toast.makeText(context, "Couldn't delete user", Toast.LENGTH_LONG).show()
                }
                RandomUsersListEvent.UserDeletedSuccessfully -> {
                    Toast.makeText(context, "Random User deleted successfully", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    RandomUsersListRoot(
        uiState = uiState.value,
        onRandomUserClick = onRandomUserClick,
        onDeleteClick = { viewModel.onAction(RandomUsersListAction.DeleteUser(it)) }
    )
}

@Composable
internal fun RandomUsersListRoot(
    uiState: RandomUsersListUiState,
    onRandomUserClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Random Users List") }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (uiState) {
                RandomUsersListUiState.Loading -> LoadingView()
                is RandomUsersListUiState.Loaded -> {
                    val randomUsersPagingItems = uiState.randomUsersPagingDataFlow.collectAsLazyPagingItems()
                    LoadedRandomUsersView(
                        randomUsersPagingItems = randomUsersPagingItems,
                        onRandomUserClick = onRandomUserClick,
                        onDeleteClick = onDeleteClick
                    )
                }
            }

        }
    }

}

@Composable
private fun LoadingView() {
    CircularProgressIndicator()
}

@Composable
private fun BoxScope.LoadedRandomUsersView(
    randomUsersPagingItems: LazyPagingItems<RandomUserItemUi>,
    onRandomUserClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    if (randomUsersPagingItems.loadState.refresh is LoadState.Loading) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                count = randomUsersPagingItems.itemCount,
                key = randomUsersPagingItems.itemKey { it.id },
            ) { index ->
                val randomUser = randomUsersPagingItems[index]
                if (randomUser != null) {
                    RandomUserListItem(
                        randomUser = randomUser,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable { onRandomUserClick(randomUser.id) },
                        onDeleteClick = onDeleteClick
                    )
                }
            }
            item {
                if (randomUsersPagingItems.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}