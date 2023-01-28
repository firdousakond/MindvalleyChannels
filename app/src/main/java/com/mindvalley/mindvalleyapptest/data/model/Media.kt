package com.mindvalley.mindvalleyapptest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Media(
    val channel: ChannelX?,
    val coverAsset: CoverAsset?,
    @PrimaryKey
    val title: String = "",
    val type: String?
)