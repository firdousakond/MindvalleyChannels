package com.mindvalley.mindvalleyapptest.domain.model

data class MediaEntity(
    val channel: ChannelEntity?,
    val coverAsset: CoverAssetEntity?,
    val title: String?,
    val type: String?
)