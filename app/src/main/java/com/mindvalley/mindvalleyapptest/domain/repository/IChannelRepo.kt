package com.mindvalley.mindvalleyapptest.domain.repository

import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.CategoryEntity
import com.mindvalley.mindvalleyapptest.domain.model.ChannelEntity
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import kotlinx.coroutines.flow.Flow

interface IChannelRepo {
    suspend fun getChannels() : Flow<Resource<List<ChannelEntity>>>
    suspend fun getNewEpisodes(): Flow<Resource<List<MediaEntity>>>
    suspend fun getCategories() : Flow<Resource<List<CategoryEntity>>>
}