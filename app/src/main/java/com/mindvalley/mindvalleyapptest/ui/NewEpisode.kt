package com.mindvalley.mindvalleyapptest.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mindvalley.mindvalleyapptest.domain.MAX_ROW_COUNT
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import com.mindvalley.mindvalleyapptest.ui.theme.Typography
import com.mindvalley.mindvalleyapptest.ui.theme.dividerColor
import java.util.*
import kotlin.collections.List

@Composable
fun NewEpisodes(modifier: Modifier = Modifier, episodes: List<MediaEntity>) {

    val columnCount = if (episodes.count() % MAX_ROW_COUNT == 0) {
        episodes.count() / MAX_ROW_COUNT
    } else {
        episodes.count() / MAX_ROW_COUNT + 1
    }

    LazyColumn(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp).height( (LocalConfiguration.current.screenHeightDp/3).dp)
    ) {
        items(count = columnCount) { index ->
            LazyRow {
                val startIndex = index * MAX_ROW_COUNT
                val endIndex =
                    if ((index * MAX_ROW_COUNT) + MAX_ROW_COUNT > episodes.count()) {
                        episodes.count()
                    } else {
                        (index * MAX_ROW_COUNT) + MAX_ROW_COUNT
                    }
                val mediaList = episodes.subList(startIndex, endIndex)
                itemsIndexed(mediaList) { index, mediaData ->
                    if (index < MAX_ROW_COUNT) {
                        NewEpisodeItem(modifier = modifier, mediaData)
                    }
                }
            }
        }
    }
    Divider(color = dividerColor, modifier = dividerModifier)

}

@Composable
fun NewEpisodeItem(modifier: Modifier, media: MediaEntity) {
    Column(
        modifier = modifier.padding(top = 20.dp, end = 10.dp).wrapContentHeight()
    ) {
        AsyncImage(
            modifier = imageModifierPortrait.align(Alignment.CenterHorizontally),
            model = ImageRequest.Builder(LocalContext.current)
                .data(media.coverAsset?.url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Text(
            modifier = modifier
                .width(152.dp)
                .padding(top = 10.dp),
            text = media.title ?: "",
            style = Typography.subtitle1
        )
        media.channel?.title?.uppercase(Locale.getDefault())?.let {
            Text(
                modifier = modifier
                    .padding(top = 10.dp)
                    .width(152.dp),
                text = it,
                style = Typography.subtitle2
            )
        }
    }
}