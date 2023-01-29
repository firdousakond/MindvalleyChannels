package com.mindvalley.mindvalleyapptest.domain.model


data class ChannelEntity(
    val coverAsset: CoverAssetEntity?,
    val iconAsset: IconAssetEntity?,
    val latestMedia: List<LatestMediaEntity>?,
    val mediaCount: Int?,
    val series: List<SeryEntity>?,
    val slug: String?,
    val title: String?
)