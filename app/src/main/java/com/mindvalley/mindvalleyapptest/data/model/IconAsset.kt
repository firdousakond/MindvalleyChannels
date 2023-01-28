package com.mindvalley.mindvalleyapptest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IconAsset(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val thumbnailUrl: String?,
    val url: String?
)