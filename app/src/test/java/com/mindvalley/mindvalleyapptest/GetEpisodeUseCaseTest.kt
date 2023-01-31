package com.mindvalley.mindvalleyapptest

import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.repository.IChannelRepo
import com.mindvalley.mindvalleyapptest.domain.usecase.GetNewEpisodeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
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
class GetEpisodeUseCaseTest {

    @Mock
    lateinit var iChannelRepo: IChannelRepo
    private lateinit var getNewEpisodeUseCase: GetNewEpisodeUseCase

    @Before
    fun setup(){
        getNewEpisodeUseCase = GetNewEpisodeUseCase(iChannelRepo)
    }

    @Test
    fun `fetch episodes list - success response`() = runTest{


        `when`(iChannelRepo.getNewEpisodes()).thenReturn(
            flowOf(Resource.Success(data = mediaEntityTestData)))

        val job = launch {
            getNewEpisodeUseCase.getNewEpisodes()
                .collectLatest {
                   assertTrue(it is Resource.Success)
                   assertEquals(it.data[0].title, "The Cure of loneliness")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `fetch episodes list - Error response`() = runTest {

        val errorMessage = "episode error"
        `when`(
            iChannelRepo.getNewEpisodes()
        ).thenReturn(
            flowOf(Resource.Error(message = errorMessage))
        )
        getNewEpisodeUseCase.getNewEpisodes().
           collectLatest {
                assertTrue(it is Resource.Error)
                assertEquals(it.message, errorMessage)
            }
    }
}