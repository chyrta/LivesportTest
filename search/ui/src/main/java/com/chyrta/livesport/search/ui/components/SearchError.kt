package com.chyrta.livesport.search.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.chyrta.livesport.search.logic.presentation.SearchContract
import com.chyrta.livesport.common.R as CommonR
import kiwi.orbit.compose.illustrations.R as IllustrationsR
import kiwi.orbit.compose.ui.controls.ButtonPrimary
import kiwi.orbit.compose.ui.controls.EmptyState
import kiwi.orbit.compose.ui.controls.Text

@Composable fun SearchError(
    errorState: SearchContract.SearchErrorState,
    onButtonClick: () -> Unit
) {
    val descriptionResId = when (errorState) {
        SearchContract.SearchErrorState.InvalidRequestParameters -> CommonR.string.error_state_invalid_parameters
        SearchContract.SearchErrorState.MissingRequestParameters -> CommonR.string.error_state_missing_parameters
        SearchContract.SearchErrorState.NetworkError -> CommonR.string.error_state_network_error
        SearchContract.SearchErrorState.HttpError -> CommonR.string.error_state_http_error
        SearchContract.SearchErrorState.ServiceUnavailable -> CommonR.string.error_state_service_unavailable
        SearchContract.SearchErrorState.UnknownError -> CommonR.string.error_state_unknown_error
        else -> CommonR.string.error_state_unknown_error
    }

    EmptyState(
        illustration = painterResource(id = IllustrationsR.drawable.il_orbit_error),
        title = stringResource(id = CommonR.string.error),
        description = stringResource(id = descriptionResId),
        action = {
            ButtonPrimary(onClick = onButtonClick) {
                Text(stringResource(id = com.chyrta.livesport.common.R.string.refresh))
            }
        },
    )

}
