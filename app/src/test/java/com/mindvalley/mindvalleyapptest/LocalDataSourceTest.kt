package com.mindvalley.mindvalleyapptest

import com.mindvalley.mindvalleyapptest.data.local.LocalDataSource
import com.mindvalley.mindvalleyapptest.data.local.room.ChannelDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LocalDataSourceTest {

    private lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var channelDao: ChannelDao

    @Before
    fun setup() {
        localDataSource = LocalDataSource(channelDao)
    }

    @Test
    fun `fetch channel list - success response`() = runTest {

        `when`(channelDao.getChannels()).thenReturn(
            flowOf(channelTestData)
        )

        val job = launch {
            localDataSource.getChannels()
                .collectLatest {
                    assertEquals(it[0].title, "MindValley Mentoring")
                }

        }
        job.join()
        job.cancel()

        }

    @Test
    fun `insert channel list`() = runTest {

        `when`(channelDao.insertChannels(channelTestData)).thenReturn(
            arrayOf(1,2,3)
        )

        val job = launch {
            val result = localDataSource.insertChannels(channelTestData)
            assertEquals(result.size ,3)
            assertEquals(result[1],2)

        }
        job.join()
        job.cancel()

    }

    @Test
    fun `fetch episodes list - success response`() = runTest {

        `when`(channelDao.getNewEpisodes()).thenReturn(
            flowOf(mediaTestData)
        )

        val job = launch {
            localDataSource.getNewEpisodes()
                .collectLatest {
                    assertEquals(it[1].title, "Evolved Enterprise")
                }

        }
        job.join()
        job.cancel()

    }

    @Test
    fun `insert episodes list`() = runTest {

        `when`(channelDao.insertNewEpisodes(mediaTestData)).thenReturn(
            arrayOf(1,2,3)
        )

        val job = launch {
            val result = localDataSource.insertNewEpisodes(mediaTestData)
            assertEquals(result.size ,3)
            assertEquals(result[1],2)

        }
        job.join()
        job.cancel()

    }


    @Test
    fun `fetch categories list - success response`() = runTest {

        `when`(channelDao.getCategories()).thenReturn(
            flowOf(categoryTestData)
        )

        val job = launch {
            localDataSource.getCategories()
                .collectLatest {
                    assertEquals(it[1].name, "Emotional")
                }

        }
        job.join()
        job.cancel()

    }

    @Test
    fun `insert categories list`() = runTest {

        `when`(channelDao.insertNewEpisodes(mediaTestData)).thenReturn(
            arrayOf(1,2,3)
        )

        val job = launch {
            val result = localDataSource.insertNewEpisodes(mediaTestData)
            assertEquals(result.size ,3)
            assertEquals(result[1],2)

        }
        job.join()
        job.cancel()

    }
}