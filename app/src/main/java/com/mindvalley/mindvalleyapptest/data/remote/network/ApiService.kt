package com.mindvalley.mindvalleyapptest.data.remote.network

import com.mindvalley.mindvalleyapptest.data.model.CategoriesResponse
import com.mindvalley.mindvalleyapptest.data.model.ChannelResponse
import com.mindvalley.mindvalleyapptest.data.model.EpisodeResponse
import retrofit2.http.GET

interface ApiService {
    @GET("Xt12uVhM")
    suspend fun getChannels(): ChannelResponse
    @GET("z5AExTtw")
    suspend fun getNewEpisodes(): EpisodeResponse
    @GET("A0CgArX3")
    suspend fun getCategories() : CategoriesResponse
}