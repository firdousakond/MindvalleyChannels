package com.mindvalley.mindvalleyapptest.data.repository

import android.content.Context
import com.mindvalley.mindvalleyapptest.data.NetworkBoundResource
import com.mindvalley.mindvalleyapptest.data.local.LocalDataSource
import com.mindvalley.mindvalleyapptest.data.model.CategoriesResponse
import com.mindvalley.mindvalleyapptest.data.model.ChannelResponse
import com.mindvalley.mindvalleyapptest.data.model.EpisodeResponse
import com.mindvalley.mindvalleyapptest.data.remote.RemoteDataSource
import com.mindvalley.mindvalleyapptest.data.remote.network.ApiResponse
import com.mindvalley.mindvalleyapptest.data.util.INetworkUtil
import com.mindvalley.mindvalleyapptest.data.util.toCategoryEntity
import com.mindvalley.mindvalleyapptest.data.util.toChannelEntity
import com.mindvalley.mindvalleyapptest.data.util.toMediaEntity
import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.CategoryEntity
import com.mindvalley.mindvalleyapptest.domain.model.ChannelEntity
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import com.mindvalley.mindvalleyapptest.domain.repository.IChannelRepo
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ChannelRepo(
    private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource,
    private val context: Context
) : IChannelRepo, KoinComponent {

    private val iNetworkUtil: INetworkUtil by inject()

    override suspend fun getChannels(): Flow<Resource<List<ChannelEntity>>> =
        object : NetworkBoundResource<List<ChannelEntity>, ChannelResponse>() {

            override fun loadFromDB(): Flow<List<ChannelEntity>> {
                return localDataSource.getChannels().map { it.toChannelEntity() }
            }

            override fun shouldFetch(): Boolean {
                return iNetworkUtil.isOnline(context)
            }

            override suspend fun createCall(): Flow<ApiResponse<ChannelResponse>> {
                return remoteDataSource.getChannels()
            }

            override suspend fun saveCallResult(data: ChannelResponse) {
                data.data?.let {
                    it.channels?.let { channel ->
                        localDataSource.insertChannels(channel)
                    }
                }
            }

        }.asFlow()

    override suspend fun getNewEpisodes(): Flow<Resource<List<MediaEntity>>> =
        object : NetworkBoundResource<List<MediaEntity>, EpisodeResponse>() {
            override fun loadFromDB(): Flow<List<MediaEntity>> {
                return localDataSource.getNewEpisodes().map { it.toMediaEntity() }
            }

            override fun shouldFetch(): Boolean {
                return iNetworkUtil.isOnline(context)
            }

            override suspend fun createCall(): Flow<ApiResponse<EpisodeResponse>> {
                return remoteDataSource.getNewEpisodes()
            }

            override suspend fun saveCallResult(data: EpisodeResponse) {
                data.data?.let {
                    it.media?.let { media ->
                        localDataSource.insertNewEpisodes(media)
                    }
                }
            }

        }.asFlow()

    override suspend fun getCategories(): Flow<Resource<List<CategoryEntity>>> =
        object : NetworkBoundResource<List<CategoryEntity>, CategoriesResponse>() {
            override fun loadFromDB(): Flow<List<CategoryEntity>> {
                return localDataSource.getCategories().map { it.toCategoryEntity() }
            }

            override fun shouldFetch(): Boolean {
                return iNetworkUtil.isOnline(context)
            }

            override suspend fun createCall(): Flow<ApiResponse<CategoriesResponse>> {
                return remoteDataSource.getCategories()
            }

            override suspend fun saveCallResult(data: CategoriesResponse) {
                data.data?.let {
                    it.categories?.let { category ->
                        localDataSource.insertCategories(category)
                    }
                }
            }

        }.asFlow()
}
