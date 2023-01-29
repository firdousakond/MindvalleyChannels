package com.mindvalley.mindvalleyapptest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IconAsset(
    val thumbnailUrl: String?,
    @PrimaryKey
    val url: String = ""
)