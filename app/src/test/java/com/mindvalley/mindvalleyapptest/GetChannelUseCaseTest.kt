package com.mindvalley.mindvalleyapptest

import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.repository.IChannelRepo
import com.mindvalley.mindvalleyapptest.domain.usecase.GetChannelUseCase
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
class GetChannelUseCaseTest {

    @Mock
    lateinit var iChannelRepo: IChannelRepo
    private lateinit var getChannelUseCase: GetChannelUseCase

    @Before
    fun setup(){
        getChannelUseCase = GetChannelUseCase(iChannelRepo)
    }

    @Test
    fun `fetch channel list - success response`() = runTest{


        `when`(iChannelRepo.getChannels()).thenReturn(
            flowOf(Resource.Success(data = channelEntityTestData)))

        val job = launch {
            getChannelUseCase.getChannels()
                .collectLatest {
                   assertTrue(it is Resource.Success)
                   assertEquals(it.data[2].title, "Happiness Recipe")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `fetch channel list - Error response`() = runTest {

        val errorMessage = "channel error"
        `when`(
            iChannelRepo.getChannels()
        ).thenReturn(
            flowOf(Resource.Error(message = errorMessage))
        )
        getChannelUseCase.getChannels().
           collectLatest {
                assertTrue(it is Resource.Error)
                assertEquals(it.message, errorMessage)
            }
    }
}