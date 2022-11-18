package com.chyrta.livesport.search.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chyrta.livesport.search.logic.presentation.SearchContract
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.ButtonPrimary
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TextField

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onClearQueryClick: () -> Unit,
    onChangeQuery: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.weight(0.6f),
            value = searchQuery,
            trailingIcon = {
                Icon(
                    painter = Icons.CloseCircle,
                    contentDescription = null,
                )
            },
            onTrailingIconClick = onClearQueryClick,
            onValueChange = onChangeQuery,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            placeholder = { Text("Enter your search request") }
        )
        Spacer(modifier = Modifier.width(8.dp))
        ButtonPrimary(
            modifier = Modifier.weight(0.3f),
            onClick = onSearchClick
        ) {
            Text("Search")
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    OrbitTheme {
        Scaffold {
            SearchBar(
                searchQuery = "",
                onClearQueryClick = {},
                onChangeQuery = {},
                onSearchClick = {}
            )
        }
    }
}
