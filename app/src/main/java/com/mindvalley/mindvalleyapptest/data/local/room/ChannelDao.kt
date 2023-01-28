package com.mindvalley.mindvalleyapptest.data.local.room

import androidx.room.*
import com.mindvalley.mindvalleyapptest.data.model.Category
import com.mindvalley.mindvalleyapptest.data.model.Channel
import com.mindvalley.mindvalleyapptest.data.model.Media
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelDao {
    @Query("SELECT * FROM channel")
    fun getChannels(): Flow<List<Channel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannels(channels: List<Channel>): Array<Long>

    @Query("SELECT * FROM category")
    fun getCategories(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<Category>): Array<Long>

    @Query("SELECT * FROM media")
    fun getNewEpisodes(): Flow<List<Media>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewEpisodes(channels: List<Media>): Array<Long>

}