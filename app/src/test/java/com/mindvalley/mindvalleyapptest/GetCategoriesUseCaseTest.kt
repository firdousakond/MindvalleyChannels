package com.mindvalley.mindvalleyapptest

import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.repository.IChannelRepo
import com.mindvalley.mindvalleyapptest.domain.usecase.GetCategoriesUseCase
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
class GetCategoriesUseCaseTest {

    @Mock
    lateinit var iChannelRepo: IChannelRepo
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Before
    fun setup(){
        getCategoriesUseCase = GetCategoriesUseCase(iChannelRepo)
    }

    @Test
    fun `fetch categories list - success response`() = runTest{

        `when`(iChannelRepo.getCategories()).thenReturn(
            flowOf(Resource.Success(data = categoryEntityTestData)))

        val job = launch {
            getCategoriesUseCase.getCategories()
                .collectLatest {
                   assertTrue(it is Resource.Success)
                   assertEquals(it.data[1].name, "Emotional")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `fetch categories list - Error response`() = runTest {

        val errorMessage = "categories error"
        `when`(
            iChannelRepo.getCategories()
        ).thenReturn(
            flowOf(Resource.Error(message = errorMessage))
        )
        getCategoriesUseCase.getCategories().
           collectLatest {
                assertTrue(it is Resource.Error)
                assertEquals(it.message, errorMessage)
            }
    }
}