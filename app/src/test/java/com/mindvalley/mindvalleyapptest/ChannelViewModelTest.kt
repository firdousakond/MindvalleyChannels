package com.mindvalley.mindvalleyapptest

import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.usecase.GetCategoriesUseCase
import com.mindvalley.mindvalleyapptest.domain.usecase.GetChannelUseCase
import com.mindvalley.mindvalleyapptest.domain.usecase.GetNewEpisodeUseCase
import com.mindvalley.mindvalleyapptest.ui.ChannelViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ChannelViewModelTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Mock
    lateinit var getChannelUseCase: GetChannelUseCase

    @Mock
    lateinit var getNewEpisodeUseCase: GetNewEpisodeUseCase

    private lateinit var viewModel: ChannelViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = ChannelViewModel(getChannelUseCase, getNewEpisodeUseCase, getCategoriesUseCase)
    }

    @Test
    fun `fetch channels data - success response`() = runTest(UnconfinedTestDispatcher()) {

        `when`(
            getChannelUseCase.getChannels()
        ).thenReturn(
            flowOf(Resource.Success(data = channelEntityTestData))
        )
        `when`(
            getNewEpisodeUseCase.getNewEpisodes()
        ).thenReturn(
            flowOf(Resource.Success(data = mediaEntityTestData))
        )
        `when`(
            getCategoriesUseCase.getCategories()
        ).thenReturn(
            flowOf(Resource.Success(data = categoryEntityTestData))
        )
        viewModel.getChannelsData()
        val channelJob = launch {
            viewModel.channelStateFlow.collectLatest {
                delay(1000)
                assertTrue(it is Resource.Success)
                assertEquals(it.data[1].title, "Impact at work")
            }
        }
        val episodeJob = launch {
            viewModel.episodeStateFlow.collectLatest {
                delay(1000)
                assertTrue(it is Resource.Success)
                assertEquals(it.data[2].title, "The Art Of Conciouse Parenting")
            }
        }
    val categoryJob = launch {
            viewModel.categoryStateFlow.collectLatest {
                delay(1000)
                assertTrue(it is Resource.Success)
                assertEquals(it.data[0].name, "Career")
            }
        }

        channelJob.cancel()
        episodeJob.cancel()
        categoryJob.cancel()
    }

    @Test
    fun `fetch album list - Error response`() = runTest(UnconfinedTestDispatcher()) {

        `when`(
            getChannelUseCase.getChannels()
        ).thenReturn(
            flowOf(Resource.Error(message = "channel error"))
        )

        `when`(
            getNewEpisodeUseCase.getNewEpisodes()
        ).thenReturn(
            flowOf(Resource.Error(message = "episode error"))
        )

        `when`(
            getCategoriesUseCase.getCategories()
        ).thenReturn(
            flowOf(Resource.Error(message = "category error"))
        )

        viewModel.getChannelsData()
        val channelJob = launch {
            viewModel.channelStateFlow.collectLatest {
                delay(1000)
                assertTrue(it is Resource.Error)
                assertEquals(it.message, "channel error")
            }
        }
        val episodeJob = launch {
            viewModel.episodeStateFlow.collectLatest {
                delay(1000)
                assertTrue(it is Resource.Error)
                assertEquals(it.message, "episode error")
            }
        }
        val categoryJob = launch {
            viewModel.categoryStateFlow.collectLatest {
                delay(1000)
                assertTrue(it is Resource.Error)
                assertEquals(it.message, "category error")
            }
        }
        channelJob.cancel()
        episodeJob.cancel()
        categoryJob.cancel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

}
