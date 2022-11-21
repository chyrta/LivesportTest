package com.chyrta.livesport.search.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.ButtonPrimary
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TextField
import com.chyrta.livesport.common.R as CommonR

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onClearQueryClick: () -> Unit,
    onChangeQuery: (String) -> Unit,
    onSearchClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
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
                imeAction = ImeAction.Done,
            ),
            placeholder = { Text(stringResource(CommonR.string.enter_your_search_request)) },
        )
        Spacer(modifier = Modifier.width(8.dp))
        ButtonPrimary(
            modifier = Modifier.weight(0.3f),
            onClick = onSearchClick,
        ) {
            Text(stringResource(CommonR.string.search_bar_button))
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
                onSearchClick = {},
            )
        }
    }
}
