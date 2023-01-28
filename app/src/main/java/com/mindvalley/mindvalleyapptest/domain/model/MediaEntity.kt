package com.mindvalley.mindvalleyapptest.domain.model

data class MediaEntity(
    val channel: ChannelNameEntity?,
    val coverAsset: CoverAssetEntity?,
    val title: String?,
    val type: String?
)