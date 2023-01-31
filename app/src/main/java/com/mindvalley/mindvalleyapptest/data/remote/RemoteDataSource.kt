package com.mindvalley.mindvalleyapptest.data.remote

import com.mindvalley.mindvalleyapptest.data.remote.network.ApiResponse
import com.mindvalley.mindvalleyapptest.data.remote.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteDataSource(private val apiService: ApiService, private  val  ioDispatcher: CoroutineDispatcher) {

    suspend fun getChannels() = flow {
        try {
            val channels = apiService.getChannels()
            emit(ApiResponse.Success(channels))
        } catch (e: Exception) {
            Timber.e(e)
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(ioDispatcher)

    suspend fun getNewEpisodes() = flow {
        try {
            val episodes = apiService.getNewEpisodes()
            emit(ApiResponse.Success(episodes))
        } catch (e: Exception) {
            Timber.e(e)
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(ioDispatcher)

    suspend fun getCategories() = flow {
        try {
            val categories = apiService.getCategories()
            emit(ApiResponse.Success(categories))
        } catch (e: Exception) {
            Timber.e(e)
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(ioDispatcher)

}