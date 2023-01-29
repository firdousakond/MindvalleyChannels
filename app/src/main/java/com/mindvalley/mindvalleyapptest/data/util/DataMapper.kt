package com.mindvalley.mindvalleyapptest.data.util

import com.mindvalley.mindvalleyapptest.data.model.Category
import com.mindvalley.mindvalleyapptest.data.model.Channel
import com.mindvalley.mindvalleyapptest.data.model.Media
import com.mindvalley.mindvalleyapptest.domain.model.*

fun List<Channel>.toChannelEntity(): List<ChannelEntity> {
    return this.map { entry ->
        val latestMedia: MutableList<LatestMediaEntity> = ArrayList()
        val seryList: MutableList<SeryEntity> = ArrayList()

        entry.latestMedia?.forEach {
            latestMedia.add(
                LatestMediaEntity(
                    coverAsset = CoverAssetEntity(url = it.coverAsset?.url),
                    title = it.title,
                    type = it.type
                )
            )
        }
        entry.series?.forEach {
            seryList.add(
                SeryEntity(
                    coverAsset = CoverAssetEntity(it.coverAsset?.url),
                    id = it.id,
                    title = it.title
                )
            )
        }

        ChannelEntity(
            coverAsset = CoverAssetEntity(url = entry.coverAsset?.url),
            iconAsset = IconAssetEntity(
                thumbnailUrl = entry.iconAsset?.thumbnailUrl,
                url = entry.iconAsset?.url
            ),
            latestMedia = latestMedia,
            mediaCount = entry.mediaCount,
            series = seryList,
            slug = entry.slug,
            title = entry.title
        )

    }
}

fun List<Media>.toMediaEntity(): List<MediaEntity> {
    return this.map { entry ->
        MediaEntity(
            coverAsset = CoverAssetEntity(url = entry.coverAsset?.url),
            title = entry.title,
            type = entry.type,
            channel = ChannelNameEntity(title = entry.channel?.title)
        )
    }
}

fun List<Category>.toCategoryEntity(): List<CategoryEntity> {
    return this.map { entry ->
        CategoryEntity(
            name = entry.name
        )
    }
}