package com.mindvalley.mindvalleyapptest.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mindvalley.mindvalleyapptest.data.model.Category
import com.mindvalley.mindvalleyapptest.data.model.Channel
import com.mindvalley.mindvalleyapptest.data.model.Media

@Database(
    entities = [Channel::class, Media::class, Category::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ChannelDatabase : RoomDatabase() {
    abstract fun channelDao(): ChannelDao
}