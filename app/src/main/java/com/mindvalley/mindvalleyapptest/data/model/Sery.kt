package com.mindvalley.mindvalleyapptest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sery(
    val coverAsset: CoverAsset?,
    @PrimaryKey
    val id: String="",
    val title: String?
)