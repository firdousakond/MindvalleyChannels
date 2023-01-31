package com.mindvalley.mindvalleyapptest.domain.usecase

import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.ChannelEntity
import kotlinx.coroutines.flow.Flow

interface IGetChannelUseCase {
    suspend fun getChannels(): Flow<Resource<List<ChannelEntity>>>
}