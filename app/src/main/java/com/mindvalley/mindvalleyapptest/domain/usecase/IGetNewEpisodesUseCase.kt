package com.mindvalley.mindvalleyapptest.domain.usecase

import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import kotlinx.coroutines.flow.Flow

interface IGetNewEpisodesUseCase {
    suspend fun getNewEpisodes(): Flow<Resource<List<MediaEntity>>>
}