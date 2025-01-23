@file:OptIn(ExperimentalMaterial3Api::class)

package com.development.feature.randomusers.impl.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.development.feature.randomusers.impl.presentation.components.RandomUserListItem
import com.development.feature.randomusers.impl.presentation.vm.DetailUiState
import com.development.feature.randomusers.impl.presentation.vm.RandomUsersListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun RandomUsersListScreen(
    onRandomUserClick: (String) -> Unit
) {
    val viewModel: RandomUsersListViewModel = koinViewModel()
    RandomUsersListRoot(
        viewModel,
        onRandomUserClick = onRandomUserClick
    )
}

@Composable
internal fun RandomUsersListRoot(
    viewModel: RandomUsersListViewModel,
    onRandomUserClick: (String) -> Unit
) {
    val randomUsersPagingItems = viewModel.randomUsersPagingDataFlow.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Random Users List") }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
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
                                    .clickable { onRandomUserClick(randomUser.id) }
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
    }

}