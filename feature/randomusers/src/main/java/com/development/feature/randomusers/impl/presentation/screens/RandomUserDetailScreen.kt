@file:OptIn(ExperimentalMaterial3Api::class)

package com.development.feature.randomusers.impl.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.development.feature.randomusers.impl.presentation.model.RandomUserDetailUi
import com.development.feature.randomusers.impl.presentation.vm.DetailUiState
import com.development.feature.randomusers.impl.presentation.vm.RandomUserDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun RandomUserDetailScreen(
    onBack: () -> Unit
) {
    val viewModel: RandomUserDetailViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()

    RandomUserDetailRoot(
        uiState = uiState.value,
        onBack = onBack
    )
}

@Composable
internal fun RandomUserDetailRoot(
    uiState: DetailUiState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    when (uiState) {
                        DetailUiState.Loading -> {}
                        is DetailUiState.Loaded -> Text(text = uiState.randomUserDetail.fullName)
                        is DetailUiState.Error -> {}
                    }
                },
                navigationIcon = {
                    Icon(
                        painter = rememberVectorPainter(Icons.AutoMirrored.Filled.ArrowBack),
                        contentDescription = "Back navigation",
                        modifier = Modifier
                            .clickable { onBack() }
                            .padding(16.dp)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                DetailUiState.Loading -> LoadingView()
                is DetailUiState.Error -> ErrorView(uiState.message)
                is DetailUiState.Loaded -> {
                    LoadedRandomUserView(
                        randomUser = uiState.randomUserDetail,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LoadedRandomUserView(
    randomUser: RandomUserDetailUi,
    modifier: Modifier = Modifier
) {
    Spacer(Modifier.height(16.dp))
    AsyncImage(
        model = randomUser.picture,
        contentDescription = null,
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .border(1.dp, Color.DarkGray, CircleShape)
    )
    Spacer(Modifier.height(8.dp))
    Text(text = randomUser.fullName)
    Spacer(Modifier.height(24.dp))
    Text(text = randomUser.gender)
    Spacer(Modifier.height(8.dp))
    Text(text = randomUser.email)
    Spacer(Modifier.height(8.dp))
    Text(text = randomUser.locationString)
    Spacer(Modifier.height(8.dp))
    Text(text = randomUser.formattedRegisteredDate)
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorView(
    errorMessage: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage)
    }
}