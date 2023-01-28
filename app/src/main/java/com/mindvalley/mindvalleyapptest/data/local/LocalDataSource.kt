package com.mindvalley.mindvalleyapptest.data.local

import com.mindvalley.mindvalleyapptest.data.local.room.ChannelDao
import com.mindvalley.mindvalleyapptest.data.model.Category
import com.mindvalley.mindvalleyapptest.data.model.Channel
import com.mindvalley.mindvalleyapptest.data.model.Media
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val channelDao: ChannelDao) {
    fun getChannels(): Flow<List<Channel>> = channelDao.getChannels()
    suspend fun insertChannels(channels: List<Channel>): Array<Long> =
        channelDao.insertChannels(channels)

    fun getNewEpisodes(): Flow<List<Media>> = channelDao.getNewEpisodes()
    suspend fun insertNewEpisodes(episodes: List<Media>): Array<Long> {
        return channelDao.insertNewEpisodes(episodes)
    }

    fun getCategories(): Flow<List<Category>> = channelDao.getCategories()
    suspend fun insertCategories(categories: List<Category>): Array<Long> =
        channelDao.insertCategories(categories)
}