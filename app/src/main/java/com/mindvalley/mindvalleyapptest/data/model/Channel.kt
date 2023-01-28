package com.mindvalley.mindvalleyapptest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Channel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("id")
    val cid: String?,
    val coverAsset: CoverAsset?,
    val iconAsset: IconAsset?,
    val latestMedia: List<LatestMedia>?,
    val mediaCount: Int?,
    val series: List<Sery>?,
    val slug: String?,
    val title: String?
)