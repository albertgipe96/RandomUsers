package com.development.feature.randomusers.impl.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.development.feature.randomusers.impl.presentation.model.RandomUserItemUi

@Composable
fun RandomUserListItem(
    randomUser: RandomUserItemUi,
    modifier: Modifier = Modifier,
    onDeleteClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .border(1.dp, Color.DarkGray, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = randomUser.picture,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.dp, Color.DarkGray, CircleShape)
        )
        Column(
            modifier = Modifier.fillMaxWidth().weight(1F, false)
        ) {
            Text(text = randomUser.fullName)
            Text(text = randomUser.email)
            Text(text = randomUser.phone)
        }
        Icon(
            painter = rememberVectorPainter(Icons.Outlined.Delete),
            tint = Color.Red,
            contentDescription = "Delete random user",
            modifier = Modifier
                .size(20.dp)
                .border(1.dp, Color.Red, RoundedCornerShape(16.dp))
                .clickable { onDeleteClick(randomUser.id) }
        )
    }
}