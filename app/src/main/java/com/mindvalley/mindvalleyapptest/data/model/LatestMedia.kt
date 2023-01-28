package com.mindvalley.mindvalleyapptest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LatestMedia(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val coverAsset: CoverAsset?,
    val title: String?,
    val type: String?
)