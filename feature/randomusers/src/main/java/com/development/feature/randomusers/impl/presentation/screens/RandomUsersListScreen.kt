package com.development.feature.randomusers.impl.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.development.feature.randomusers.impl.presentation.vm.RandomUsersListViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun RandomUsersListScreen(
    onRandomUserClick: (Int) -> Unit
) {
    val viewModel: RandomUsersListViewModel = koinViewModel()
    RandomUsersListRoot(
        viewModel
    )
}

@Composable
internal fun RandomUsersListRoot(
    viewModel: RandomUsersListViewModel
) {
    val randomUsersPagingItems = viewModel.randomUsersPagingDataFlow.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        if (randomUsersPagingItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    count = randomUsersPagingItems.itemCount,
                    key = randomUsersPagingItems.itemKey { it.id },
                ) { index ->
                    val randomUser = randomUsersPagingItems[index]
                    if (randomUser != null) {
                        Text(text = randomUser.name + " " + randomUser.id)
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