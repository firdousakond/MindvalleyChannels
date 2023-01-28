package com.mindvalley.mindvalleyapptest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Sery(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val coverAsset: CoverAsset?,
    @SerializedName("id")
    val sid: String?,
    val title: String?
)