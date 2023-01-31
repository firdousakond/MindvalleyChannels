package com.mindvalley.mindvalleyapptest

import android.content.Context
import com.mindvalley.mindvalleyapptest.data.local.LocalDataSource
import com.mindvalley.mindvalleyapptest.data.remote.RemoteDataSource
import com.mindvalley.mindvalleyapptest.data.remote.network.ApiResponse
import com.mindvalley.mindvalleyapptest.data.repository.ChannelRepo
import com.mindvalley.mindvalleyapptest.data.util.INetworkUtil
import com.mindvalley.mindvalleyapptest.domain.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ChannelRepoTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var iNetworkUtil: INetworkUtil
    private val context = mock(Context::class.java)

    private lateinit var channelRepo: ChannelRepo

    @Before
    fun setup() {
        channelRepo = ChannelRepo(localDataSource, remoteDataSource, iNetworkUtil, context)
    }

    @Test
    fun `fetch channel list offline - success response`() = runTest {

        `when`(iNetworkUtil.isOnline(context)).thenReturn(
            false
        )

        `when`(localDataSource.getChannels()).thenReturn(
            flowOf(channelTestData)
        )

        val job = launch {
            channelRepo.getChannels()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    assertEquals(it.data[1].title, "Impact at work")
                }

        }

        job.join()
        job.cancel()
    }

    @Test
    fun `fetch channel list online - success response`() = runTest {

        `when`(iNetworkUtil.isOnline(context)).thenReturn(
            true
        )

        `when`(localDataSource.getChannels()).thenReturn(
            flowOf(channelTestData)
        )

        `when`(remoteDataSource.getChannels()).thenReturn(
            flowOf(ApiResponse.Success(channelResponse))
        )

        val job = launch {
            channelRepo.getChannels()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    assertEquals(it.data[1].title, "Impact at work")
                }

        }

        job.join()
        job.cancel()
    }

    @Test
    fun `fetch episode list offline - success response`() = runTest {

        `when`(iNetworkUtil.isOnline(context)).thenReturn(
            false
        )

        `when`(localDataSource.getNewEpisodes()).thenReturn(
            flowOf(mediaTestData)
        )

        val job = launch {
            channelRepo.getNewEpisodes()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    assertEquals(it.data[0].title, "The Cure of loneliness")
                }

        }

        job.join()
        job.cancel()
    }

    @Test
    fun `fetch episode list online - success response`() = runTest {

        `when`(iNetworkUtil.isOnline(context)).thenReturn(
            true
        )

        `when`(localDataSource.getNewEpisodes()).thenReturn(
            flowOf(mediaTestData)
        )

        `when`(remoteDataSource.getNewEpisodes()).thenReturn(
            flowOf(ApiResponse.Success(episodesResponse))
        )

        val job = launch {
            channelRepo.getNewEpisodes()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    assertEquals(it.data[1].title, "Evolved Enterprise")
                }

        }

        job.join()
        job.cancel()
    }

    @Test
    fun `fetch category list offline - success response`() = runTest {

        `when`(iNetworkUtil.isOnline(context)).thenReturn(
            false
        )

        `when`(localDataSource.getCategories()).thenReturn(
            flowOf(categoryTestData)
        )

        val job = launch {
            channelRepo.getCategories()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    assertEquals(it.data[0].name, "Career")
                }

        }

        job.join()
        job.cancel()
    }

    @Test
    fun `fetch category list online - success response`() = runTest {

        `when`(iNetworkUtil.isOnline(context)).thenReturn(
            true
        )

        `when`(localDataSource.getCategories()).thenReturn(
            flowOf(categoryTestData)
        )

        `when`(remoteDataSource.getCategories()).thenReturn(
            flowOf(ApiResponse.Success(categoriesResponse))
        )

        val job = launch {
            channelRepo.getCategories()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    assertEquals(it.data[0].name, "Career")
                }

        }

        job.join()
        job.cancel()
    }
}