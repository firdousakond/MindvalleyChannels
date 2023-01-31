package com.mindvalley.mindvalleyapptest.domain.usecase

import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.ChannelEntity
import com.mindvalley.mindvalleyapptest.domain.repository.IChannelRepo
import kotlinx.coroutines.flow.Flow

class GetChannelUseCase(private val iChannelRepo: IChannelRepo) : IGetChannelUseCase{
    override suspend fun getChannels(): Flow<Resource<List<ChannelEntity>>> = iChannelRepo.getChannels()
}