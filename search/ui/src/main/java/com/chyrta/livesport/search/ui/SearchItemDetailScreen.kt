package com.chyrta.livesport.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chyrta.livesport.search.logic.presentation.SearchDetailViewModel
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.utils.plus

@Composable
fun SearchItemDetailScreen(viewModel: SearchDetailViewModel) {
    val state by viewModel.uiState.collectAsState()

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding + PaddingValues(16.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = state.item?.image,
                contentDescription = state.item?.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = state.item?.name.orEmpty(),
                style = OrbitTheme.typography.title3
            )
        }
    }
}
