package com.mindvalley.mindvalleyapptest

import com.mindvalley.mindvalleyapptest.data.model.*
import com.mindvalley.mindvalleyapptest.domain.model.CategoryEntity
import com.mindvalley.mindvalleyapptest.domain.model.ChannelEntity
import com.mindvalley.mindvalleyapptest.domain.model.ChannelNameEntity
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity

val mediaEntityTestData = listOf(
    MediaEntity(
        channel = ChannelNameEntity(title = "MindValley Mentoring"),
        coverAsset = null,
        title = "The Cure of loneliness",
        type = "Series"
    ),
    MediaEntity(
        channel = ChannelNameEntity(title = "Impact At Work"),
        coverAsset = null,
        title = "Evolved Enterprise",
        type = "Series"
    ),
    MediaEntity(
        channel = ChannelNameEntity(title = "Happiness Recipe"),
        coverAsset = null,
        title = "The Art Of Conciouse Parenting",
        type = "Series"
    )
)

val channelEntityTestData = listOf(
    ChannelEntity(
        coverAsset = null,
        iconAsset = null,
        latestMedia = listOf(),
        mediaCount = 100,
        series = listOf(),
        slug = "",
        title = "MindValley Mentoring"
    ),
    ChannelEntity(
        coverAsset = null,
        iconAsset = null,
        latestMedia = listOf(),
        mediaCount = 100,
        series = listOf(),
        slug = "",
        title = "Impact at work"
    ),
    ChannelEntity(
        coverAsset = null,
        iconAsset = null,
        latestMedia = listOf(),
        mediaCount = 100,
        series = listOf(),
        slug = "",
        title = "Happiness Recipe"
    )

)

val categoryEntityTestData = listOf(
    CategoryEntity(name = "Career"),
    CategoryEntity(name = "Emotional"),
    CategoryEntity(name = "Intellectual")
)


val mediaTestData = listOf(
    Media(
        channel = ChannelX(title = "MindValley Mentoring"),
        coverAsset = null,
        title = "The Cure of loneliness",
        type = "Series"
    ),
    Media(
        channel = ChannelX(title = "Impact At Work"),
        coverAsset = null,
        title = "Evolved Enterprise",
        type = "Series"
    ),
    Media(
        channel = ChannelX(title = "Happiness Recipe"),
        coverAsset = null,
        title = "The Art Of Conciouse Parenting",
        type = "Series"
    )
)

val channelTestData = listOf(
    Channel(
        coverAsset = null,
        iconAsset = null,
        latestMedia = listOf(),
        mediaCount = 100,
        series = listOf(),
        slug = "",
        title = "MindValley Mentoring"
    ),
    Channel(
        coverAsset = null,
        iconAsset = null,
        latestMedia = listOf(),
        mediaCount = 100,
        series = listOf(),
        slug = "",
        title = "Impact at work"
    ),
    Channel(
        coverAsset = null,
        iconAsset = null,
        latestMedia = listOf(),
        mediaCount = 100,
        series = listOf(),
        slug = "",
        title = "Happiness Recipe"
    )

)

val categoryTestData =
    listOf(Category(name = "Career"), Category(name = "Emotional"), Category(name = "Intellectual"))

val channelResponse = ChannelResponse(ChannelData(channelTestData))
val episodesResponse = EpisodeResponse(MediaData(mediaTestData))
val categoriesResponse = CategoriesResponse(CategoriesData(categoryTestData))

