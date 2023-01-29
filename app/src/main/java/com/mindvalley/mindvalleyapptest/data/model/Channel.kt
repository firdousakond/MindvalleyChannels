package com.mindvalley.mindvalleyapptest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Channel(

    val coverAsset: CoverAsset?,
    val iconAsset: IconAsset?,
    val latestMedia: List<LatestMedia>?,
    val mediaCount: Int?,
    val series: List<Sery>?,
    val slug: String?,
    @PrimaryKey
    val title: String = ""
)