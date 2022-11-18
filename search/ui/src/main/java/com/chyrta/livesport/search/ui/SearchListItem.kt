package com.chyrta.livesport.search.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chyrta.livesport.search.logic.data.remote.model.ApiId
import com.chyrta.livesport.search.logic.domain.model.GenderEntity
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity
import com.chyrta.livesport.search.logic.domain.model.SportEntity
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.SurfaceCard
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun SearchListItem(item: SearchResultItemEntity, onItemClick: (SearchResultItemEntity) -> Unit) {
    SurfaceCard(
        onClick = { onItemClick(item) },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp),
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = item.name,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.name,
                style = OrbitTheme.typography.bodyNormalMedium,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun SearchListItemPreview() {
    OrbitTheme {
        SearchListItem(
            item = SearchResultItemEntity(
                id = "",
                name = "Something",
                gender = GenderEntity.MALE,
                type = ApiId(id = 1, name = "something"),
                sport = SportEntity.Football,
                defaultCountry = "Czechia",
                image = null,
            ),
            onItemClick = {},
        )
    }
}
