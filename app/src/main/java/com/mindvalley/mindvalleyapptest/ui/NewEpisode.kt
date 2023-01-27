package com.mindvalley.mindvalleyapptest.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mindvalley.mindvalleyapptest.R
import com.mindvalley.mindvalleyapptest.domain.MAX_ROW_COUNT
import com.mindvalley.mindvalleyapptest.domain.model.ChannelEntity
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import com.mindvalley.mindvalleyapptest.domain.model.MediaDataEntity
import com.mindvalley.mindvalleyapptest.ui.theme.Typography
import java.util.*

@Composable
fun NewEpisodes(modifier: Modifier = Modifier) {

    val mediaData =
        MediaDataEntity(
            media = listOf(
                MediaEntity(
                    channel = ChannelEntity(title = "MindValley Mentoring"),
                    coverAsset = null,
                    title = "The Cure of loneliness",
                    type = "Series"
                ),
                MediaEntity(
                    channel = ChannelEntity(title = "Impact At Work"),
                    coverAsset = null,
                    title = "Evolved Enterprise",
                    type = "Series"
                ),
                MediaEntity(
                    channel = ChannelEntity(title = "Happiness Recipe"),
                    coverAsset = null,
                    title = "The Art Of Conciouse Parenting",
                    type = "Series"
                ),
                MediaEntity(
                    channel = ChannelEntity(title = "Soul Finding"),
                    coverAsset = null,
                    title = "The Physics Of Spirituality",
                    type = "Series"
                ),
                MediaEntity(
                    channel = ChannelEntity(title = "MindValley Mentoring"),
                    coverAsset = null,
                    title = "The Barcelona Experiment",
                    type = "Series"
                ),
                MediaEntity(
                    channel = ChannelEntity(title = "Impact At Work"),
                    coverAsset = null,
                    title = "Live Your Quest",
                    type = "Series"
                ),
                MediaEntity(
                    channel = ChannelEntity(title = "MindValley Mentoring"),
                    coverAsset = null,
                    title = "The Female Perspective",
                    type = "Series"
                ),
                MediaEntity(
                    channel = ChannelEntity(title = "MindValley Mentoring"),
                    coverAsset = null,
                    title = "Feuled By Courage",
                    type = "Series"
                ),
                MediaEntity(
                    channel = ChannelEntity(title = "Impact At Work"),
                    coverAsset = null,
                    title = "A-Fest Sardinia 2018",
                    type = "Series"
                )
            )
        )

    val columnCount = if (mediaData.media?.count()!! % MAX_ROW_COUNT == 0) {
        mediaData.media.count() / MAX_ROW_COUNT
    } else {
        mediaData.media.count() / MAX_ROW_COUNT + 1
    }

    LazyColumn(
        modifier = modifier
    ) {
        items(count = columnCount) { index ->
            LazyRow {
                val startIndex = index * MAX_ROW_COUNT
                val endIndex =
                    if ((index * MAX_ROW_COUNT) + MAX_ROW_COUNT > mediaData.media.count()) {
                        mediaData.media.count()
                    } else {
                        (index * MAX_ROW_COUNT) + MAX_ROW_COUNT
                    }
                val mediaList = mediaData.media.subList(startIndex, endIndex)
                itemsIndexed(mediaList) { index, mediaData ->
                    if (index < MAX_ROW_COUNT) {
                        mediaData?.let { media -> NewEpisodeItem(modifier = modifier, media) }
                    }
                }
            }
        }
    }

}

@Composable
fun NewEpisodeItem(modifier: Modifier, media: MediaEntity) {
    Column(
        modifier = modifier.padding(top = 20.dp, end = 8.dp)
    ) {
        Image(
            modifier = imageModifierPortrait.align(Alignment.CenterHorizontally),
            painter = painterResource(R.drawable.cover),
            contentDescription = "New Episodes",
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = modifier.width(152.dp),
            text = media.title ?: "",
            style = Typography.subtitle1
        )
        media.channel?.title?.uppercase(Locale.getDefault())?.let {
            Text(
                modifier = modifier.padding(top = 10.dp).width(152.dp),
                text = it,
                style = Typography.subtitle2
            )
        }
    }
}