package com.mindvalley.mindvalleyapptest

import com.mindvalley.mindvalleyapptest.data.remote.RemoteDataSource
import com.mindvalley.mindvalleyapptest.data.remote.network.ApiResponse
import com.mindvalley.mindvalleyapptest.data.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {

    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setup() {
        remoteDataSource = RemoteDataSource(apiService, Dispatchers.IO)
    }

    @Test
    fun `fetch channel list - success response`() = runTest {

        `when`(apiService.getChannels()).thenReturn(
            channelResponse
        )

        val job = launch {
            remoteDataSource.getChannels()
                .collectLatest {
                    assertTrue(it is ApiResponse.Success)
                    assertEquals(it.data.data?.channels?.get(1)?.title, "Impact at work")
                }

        }
        job.join()
        job.cancel()

    }

    @Test
    fun `fetch episodes list - success response`() = runTest {

        `when`(apiService.getNewEpisodes()).thenReturn(
            episodesResponse
        )

        val job = launch {
            remoteDataSource.getNewEpisodes()
                .collectLatest {
                    assertTrue(it is ApiResponse.Success)
                    assertEquals(
                        it.data.data?.media?.get(2)?.title,
                        "The Art Of Conciouse Parenting"
                    )
                }

        }
        job.join()
        job.cancel()

    }

    @Test
    fun `fetch categories list - success response`() = runTest {

        `when`(apiService.getCategories()).thenReturn(
            categoriesResponse
        )

        val job = launch {
            remoteDataSource.getCategories()
                .collectLatest {
                    assertTrue(it is ApiResponse.Success)
                    assertEquals(it.data.data?.categories?.get(2)?.name, "Intellectual")
                }

        }
        job.join()
        job.cancel()

    }

}