package com.mindvalley.mindvalleyapptest.domain.usecase

import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import com.mindvalley.mindvalleyapptest.domain.repository.IChannelRepo
import kotlinx.coroutines.flow.Flow

class GetNewEpisodeUseCase (private val iChannelRepo: IChannelRepo) {
    suspend fun getNewEpisodes(): Flow<Resource<List<MediaEntity>>> = iChannelRepo.getNewEpisodes()
}